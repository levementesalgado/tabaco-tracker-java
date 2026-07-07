package com.tabacotracker.repository;

import com.tabacotracker.model.Curtida;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CurtidaRepository extends JpaRepository<Curtida, UUID> {
    Optional<Curtida> findByAlvoIdAndAlvoTipoAndUsuarioId(UUID alvoId, String alvoTipo, UUID usuarioId);
    int countByAlvoIdAndAlvoTipo(UUID alvoId, String alvoTipo);
}
