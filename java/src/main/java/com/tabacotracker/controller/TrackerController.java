package com.tabacotracker.controller;

import com.tabacotracker.dto.TrackerDTO;
import com.tabacotracker.model.RegistroDiario;
import com.tabacotracker.service.TrackerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tracker")
@RequiredArgsConstructor
public class TrackerController {
    private final TrackerService s;

    @GetMapping
    public List<RegistroDiario> list(HttpServletRequest r) { return s.list((UUID) r.getAttribute("userId")); }

    @GetMapping("/hoje")
    public ResponseEntity<RegistroDiario> hoje(HttpServletRequest r) {
        return ResponseEntity.of(s.hoje((UUID) r.getAttribute("userId")));
    }

    @PostMapping
    public RegistroDiario create(@RequestBody TrackerDTO d, HttpServletRequest r) {
        return s.create(d, (UUID) r.getAttribute("userId"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroDiario> update(@PathVariable UUID id, @RequestBody TrackerDTO d, HttpServletRequest r) {
        return ResponseEntity.of(s.update(id, d, (UUID) r.getAttribute("userId")));
    }
}
