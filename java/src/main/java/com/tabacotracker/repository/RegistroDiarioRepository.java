package com.tabacotracker.repository;

import com.tabacotracker.model.RegistroDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RegistroDiarioRepository extends JpaRepository<RegistroDiario, UUID> {
    List<RegistroDiario> findByUsuarioIdOrderByDataDesc(UUID usuarioId);
    Optional<RegistroDiario> findByUsuarioIdAndData(UUID usuarioId, LocalDate data);
}
