package com.tabacotracker.service;

import com.tabacotracker.dto.AvaliacaoDTO;
import com.tabacotracker.model.Avaliacao;
import com.tabacotracker.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {
    private final AvaliacaoRepository r;

    public List<Avaliacao> list(UUID marcaId) { return r.findByMarcaIdOrderByCreatedAtDesc(marcaId); }

    public Avaliacao create(AvaliacaoDTO d, UUID userId) {
        return r.save(Avaliacao.builder()
            .marcaId(d.getMarcaId()).usuarioId(userId)
            .nota(d.getNota()).comentario(d.getComentario())
            .preco(d.getPreco()).regiao(d.getRegiao()).build());
    }

    public Optional<Avaliacao> update(UUID id, AvaliacaoDTO d, UUID userId) {
        return r.findById(id).map(a -> {
            if (!a.getUsuarioId().equals(userId)) return null;
            if (d.getNota() != null) a.setNota(d.getNota());
            if (d.getComentario() != null) a.setComentario(d.getComentario());
            return r.save(a);
        });
    }

    public void delete(UUID id, UUID userId) {
        r.findById(id).ifPresent(a -> {
            if (a.getUsuarioId().equals(userId)) r.delete(a);
        });
    }
}
