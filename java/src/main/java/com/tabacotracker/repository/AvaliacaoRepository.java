package com.tabacotracker.repository;

import com.tabacotracker.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {
    List<Avaliacao> findByMarcaIdOrderByCreatedAtDesc(UUID marcaId);
    Optional<Avaliacao> findByMarcaIdAndUsuarioId(UUID marcaId, UUID usuarioId);
}
