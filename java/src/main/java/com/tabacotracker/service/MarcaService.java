package com.tabacotracker.service;

import com.tabacotracker.dto.MarcaDTO;
import com.tabacotracker.model.Marca;
import com.tabacotracker.model.MarcasTag;
import com.tabacotracker.repository.MarcaRepository;
import com.tabacotracker.repository.MarcasTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MarcaService {
    private final MarcaRepository marcaRepo;
    private final MarcasTagRepository mtRepo;

    public List<Marca> list() { return marcaRepo.findAll(); }
    public Optional<Marca> byId(UUID id) { return marcaRepo.findById(id); }
    public Optional<Marca> bySlug(String slug) { return marcaRepo.findBySlug(slug); }
    public List<Marca> busca(String q) { return marcaRepo.findByNomeContainingIgnoreCase(q); }

    public Marca create(MarcaDTO d, UUID userId) {
        String slug = (d.getNome() + " " + (d.getVariedade() != null ? d.getVariedade() : ""))
            .toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");
        return marcaRepo.save(Marca.builder()
            .nome(d.getNome()).variedade(d.getVariedade())
            .fabricante(d.getFabricante()).fotoUrl(d.getFotoUrl())
            .slug(slug).status("approved").criadoPor(userId).build());
    }

    public Optional<Marca> update(UUID id, MarcaDTO d, UUID userId) {
        return marcaRepo.findById(id).map(m -> {
            if (!m.getCriadoPor().equals(userId)) return null;
            if (d.getNome() != null) m.setNome(d.getNome());
            if (d.getVariedade() != null) m.setVariedade(d.getVariedade());
            if (d.getFabricante() != null) m.setFabricante(d.getFabricante());
            if (d.getFotoUrl() != null) m.setFotoUrl(d.getFotoUrl());
            return marcaRepo.save(m);
        });
    }

    public void delete(UUID id, UUID userId) {
        marcaRepo.findById(id).ifPresent(m -> {
            if (m.getCriadoPor().equals(userId)) marcaRepo.delete(m);
        });
    }

    public List<MarcasTag> tags(UUID marcaId) { return mtRepo.findByMarcaId(marcaId); }
}
