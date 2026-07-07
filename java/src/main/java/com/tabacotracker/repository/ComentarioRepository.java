package com.tabacotracker.repository;

import com.tabacotracker.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {
    List<Comentario> findByAvaliacaoIdOrderByCreatedAtDesc(UUID avaliacaoId);
}
