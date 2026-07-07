package com.tabacotracker.service;

import com.tabacotracker.dto.PerfilDTO;
import com.tabacotracker.model.Perfil;
import com.tabacotracker.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PerfilService {
    private final PerfilRepository r;

    public Optional<Perfil> buscar(UUID id) {
        if (!r.existsById(id)) r.save(Perfil.builder().id(id).build());
        return r.findById(id);
    }

    public Perfil update(UUID id, PerfilDTO d) {
        Perfil p = r.findById(id).orElse(Perfil.builder().id(id).build());
        if (d.getNome() != null) p.setNome(d.getNome());
        if (d.getIdade() != null) p.setIdade(d.getIdade());
        if (d.getGenero() != null) p.setGenero(d.getGenero());
        if (d.getRegiao() != null) p.setRegiao(d.getRegiao());
        if (d.getMediaCigarrosDia() != null) p.setMediaCigarrosDia(d.getMediaCigarrosDia());
        return r.save(p);
    }

    public Map<String, Object> export(UUID id) {
        return Map.of("perfil", r.findById(id).orElse(null));
    }
}
