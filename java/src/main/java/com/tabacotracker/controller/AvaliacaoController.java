package com.tabacotracker.controller;

import com.tabacotracker.dto.AvaliacaoDTO;
import com.tabacotracker.model.Avaliacao;
import com.tabacotracker.service.AvaliacaoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {
    private final AvaliacaoService s;

    @GetMapping("/marca/{id}")
    public List<Avaliacao> list(@PathVariable UUID id) { return s.list(id); }

    @PostMapping
    public Avaliacao create(@RequestBody AvaliacaoDTO d, HttpServletRequest r) {
        return s.create(d, (UUID) r.getAttribute("userId"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> update(@PathVariable UUID id, @RequestBody AvaliacaoDTO d, HttpServletRequest r) {
        return ResponseEntity.of(s.update(id, d, (UUID) r.getAttribute("userId")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, HttpServletRequest r) {
        s.delete(id, (UUID) r.getAttribute("userId"));
        return ResponseEntity.noContent().build();
    }
}
