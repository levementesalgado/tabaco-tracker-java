package com.tabacotracker.controller;

import com.tabacotracker.dto.PerfilDTO;
import com.tabacotracker.model.Perfil;
import com.tabacotracker.service.PerfilService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/perfil")
@RequiredArgsConstructor
public class PerfilController {
    private final PerfilService s;

    @GetMapping
    public ResponseEntity<Perfil> get(HttpServletRequest r) {
        return ResponseEntity.of(s.buscar((UUID) r.getAttribute("userId")));
    }

    @PutMapping
    public Perfil update(@RequestBody PerfilDTO d, HttpServletRequest r) {
        return s.update((UUID) r.getAttribute("userId"), d);
    }

    @GetMapping("/exportar/dados")
    public Map<String, Object> export(HttpServletRequest r) {
        return s.export((UUID) r.getAttribute("userId"));
    }
}
