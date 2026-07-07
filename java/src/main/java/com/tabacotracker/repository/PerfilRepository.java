package com.tabacotracker.repository;

import com.tabacotracker.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PerfilRepository extends JpaRepository<Perfil, UUID> {}
