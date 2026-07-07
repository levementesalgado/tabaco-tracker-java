CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE marcas (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    slug VARCHAR(255) UNIQUE NOT NULL,
    variedade VARCHAR(255),
    fabricante VARCHAR(255),
    foto_url TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'pending',
    criado_por UUID NOT NULL,
    report_count INTEGER DEFAULT 0,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE avaliacoes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    marca_id UUID NOT NULL REFERENCES marcas(id) ON DELETE CASCADE,
    usuario_id UUID NOT NULL,
    nota INTEGER NOT NULL CHECK (nota >= 1 AND nota <= 20),
    comentario TEXT,
    preco DOUBLE PRECISION,
    regiao VARCHAR(100),
    created_at TIMESTAMPTZ DEFAULT NOW(),
    UNIQUE(marca_id, usuario_id)
);

CREATE TABLE tags (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE marcas_tags (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    marca_id UUID NOT NULL REFERENCES marcas(id) ON DELETE CASCADE,
    tag_id UUID NOT NULL REFERENCES tags(id) ON DELETE CASCADE,
    sugerido_por UUID NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'pending',
    total_upvotes INTEGER DEFAULT 0,
    total_downvotes INTEGER DEFAULT 0
);

CREATE TABLE votos_tags (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    marcas_tag_id UUID NOT NULL REFERENCES marcas_tags(id) ON DELETE CASCADE,
    usuario_id UUID NOT NULL,
    voto INTEGER NOT NULL CHECK (voto = 1 OR voto = -1),
    UNIQUE(marcas_tag_id, usuario_id)
);

CREATE TABLE comentarios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    avaliacao_id UUID NOT NULL REFERENCES avaliacoes(id) ON DELETE CASCADE,
    usuario_id UUID NOT NULL,
    texto TEXT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE curtidas (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    alvo_id UUID NOT NULL,
    usuario_id UUID NOT NULL,
    alvo_tipo VARCHAR(20) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    UNIQUE(alvo_id, alvo_tipo, usuario_id)
);

CREATE TABLE registros_diarios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id UUID NOT NULL,
    quantidade INTEGER NOT NULL,
    data DATE NOT NULL DEFAULT CURRENT_DATE,
    UNIQUE(usuario_id, data)
);

CREATE TABLE perfis (
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    idade INTEGER,
    genero VARCHAR(50),
    regiao VARCHAR(100),
    media_cigarros_dia INTEGER,
    opt_in_leaderboard BOOLEAN DEFAULT FALSE
);

CREATE INDEX idx_avaliacoes_marca ON avaliacoes(marca_id);
CREATE INDEX idx_avaliacoes_usuario ON avaliacoes(usuario_id);
CREATE INDEX idx_marcas_tags_marca ON marcas_tags(marca_id);
CREATE INDEX idx_votos_tags_mt ON votos_tags(marcas_tag_id);
CREATE INDEX idx_comentarios_avaliacao ON comentarios(avaliacao_id);
CREATE INDEX idx_curtidas_alvo ON curtidas(alvo_id, alvo_tipo);
CREATE INDEX idx_registros_diarios_usuario ON registros_diarios(usuario_id);
CREATE INDEX idx_registros_diarios_data ON registros_diarios(data);
