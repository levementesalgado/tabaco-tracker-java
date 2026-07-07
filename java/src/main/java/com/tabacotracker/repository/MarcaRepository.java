package com.tabacotracker.repository;

import com.tabacotracker.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MarcaRepository extends JpaRepository<Marca, UUID> {
    Optional<Marca> findBySlug(String slug);
    List<Marca> findByNomeContainingIgnoreCase(String nome);
}
