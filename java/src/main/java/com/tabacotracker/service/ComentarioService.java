package com.tabacotracker.service;

import com.tabacotracker.dto.ComentarioDTO;
import com.tabacotracker.dto.CurtirDTO;
import com.tabacotracker.model.Comentario;
import com.tabacotracker.model.Curtida;
import com.tabacotracker.repository.ComentarioRepository;
import com.tabacotracker.repository.CurtidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ComentarioService {
    private final ComentarioRepository r;
    private final CurtidaRepository cr;

    public List<Comentario> list(UUID id) { return r.findByAvaliacaoIdOrderByCreatedAtDesc(id); }

    public Comentario create(ComentarioDTO d, UUID userId) {
        return r.save(Comentario.builder()
            .avaliacaoId(d.getAvaliacaoId()).usuarioId(userId).texto(d.getTexto()).build());
    }

    public void delete(UUID id, UUID userId) {
        r.findById(id).ifPresent(c -> {
            if (c.getUsuarioId().equals(userId)) r.delete(c);
        });
    }

    public Map<String, Object> curtir(CurtirDTO d, UUID userId) {
        var e = cr.findByAlvoIdAndAlvoTipoAndUsuarioId(d.getAlvoId(), d.getAlvoTipo(), userId);
        if (e.isPresent()) {
            cr.delete(e.get());
            return Map.of("curtido", false);
        }
        cr.save(Curtida.builder().alvoId(d.getAlvoId()).alvoTipo(d.getAlvoTipo()).usuarioId(userId).build());
        return Map.of("curtido", true);
    }
}
