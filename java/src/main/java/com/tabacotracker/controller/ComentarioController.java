package com.tabacotracker.controller;

import com.tabacotracker.dto.ComentarioDTO;
import com.tabacotracker.dto.CurtirDTO;
import com.tabacotracker.model.Comentario;
import com.tabacotracker.service.ComentarioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comentarios")
@RequiredArgsConstructor
public class ComentarioController {
    private final ComentarioService s;

    @GetMapping("/avaliacao/{id}")
    public List<Comentario> list(@PathVariable UUID id) { return s.list(id); }

    @PostMapping
    public Comentario create(@RequestBody ComentarioDTO d, HttpServletRequest r) {
        return s.create(d, (UUID) r.getAttribute("userId"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, HttpServletRequest r) {
        s.delete(id, (UUID) r.getAttribute("userId"));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/curtir")
    public Map<String, Object> curtir(@RequestBody CurtirDTO d, HttpServletRequest r) {
        return s.curtir(d, (UUID) r.getAttribute("userId"));
    }
}
