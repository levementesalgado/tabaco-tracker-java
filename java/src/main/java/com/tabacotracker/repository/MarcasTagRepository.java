package com.tabacotracker.repository;

import com.tabacotracker.model.MarcasTag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MarcasTagRepository extends JpaRepository<MarcasTag, UUID> {
    List<MarcasTag> findByMarcaId(UUID marcaId);
}
