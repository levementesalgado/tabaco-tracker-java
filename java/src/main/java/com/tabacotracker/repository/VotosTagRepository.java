package com.tabacotracker.repository;

import com.tabacotracker.model.VotosTag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VotosTagRepository extends JpaRepository<VotosTag, UUID> {
    Optional<VotosTag> findByMarcasTagIdAndUsuarioId(UUID marcasTagId, UUID usuarioId);
    List<VotosTag> findByMarcasTagId(UUID marcasTagId);
}
