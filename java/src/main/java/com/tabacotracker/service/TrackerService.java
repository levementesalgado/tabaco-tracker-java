package com.tabacotracker.service;

import com.tabacotracker.dto.TrackerDTO;
import com.tabacotracker.model.RegistroDiario;
import com.tabacotracker.repository.RegistroDiarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TrackerService {
    private final RegistroDiarioRepository r;

    public List<RegistroDiario> list(UUID userId) { return r.findByUsuarioIdOrderByDataDesc(userId); }
    public Optional<RegistroDiario> hoje(UUID userId) { return r.findByUsuarioIdAndData(userId, LocalDate.now()); }

    public RegistroDiario create(TrackerDTO d, UUID userId) {
        return r.save(RegistroDiario.builder().usuarioId(userId).quantidade(d.getQuantidade()).build());
    }

    public Optional<RegistroDiario> update(UUID id, TrackerDTO d, UUID userId) {
        return r.findById(id).map(reg -> {
            if (!reg.getUsuarioId().equals(userId)) return null;
            reg.setQuantidade(d.getQuantidade());
            return r.save(reg);
        });
    }
}
