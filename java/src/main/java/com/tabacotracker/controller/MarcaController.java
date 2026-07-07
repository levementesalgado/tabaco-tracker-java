package com.tabacotracker.controller;

import com.tabacotracker.dto.MarcaDTO;
import com.tabacotracker.model.Marca;
import com.tabacotracker.model.MarcasTag;
import com.tabacotracker.service.MarcaService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/marcas")
@RequiredArgsConstructor
public class MarcaController {
    private final MarcaService s;

    @GetMapping
    public List<Marca> list() { return s.list(); }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> byId(@PathVariable UUID id) { return ResponseEntity.of(s.byId(id)); }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<Marca> bySlug(@PathVariable String slug) { return ResponseEntity.of(s.bySlug(slug)); }

    @GetMapping("/busca/{q}")
    public List<Marca> busca(@PathVariable String q) { return s.busca(q); }

    @PostMapping
    public Marca create(@RequestBody MarcaDTO d, HttpServletRequest r) {
        return s.create(d, (UUID) r.getAttribute("userId"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> update(@PathVariable UUID id, @RequestBody MarcaDTO d, HttpServletRequest r) {
        return ResponseEntity.of(s.update(id, d, (UUID) r.getAttribute("userId")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, HttpServletRequest r) {
        s.delete(id, (UUID) r.getAttribute("userId"));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tags")
    public List<MarcasTag> tags(@PathVariable UUID id) { return s.tags(id); }
}
