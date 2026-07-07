package com.tabacotracker.controller;

import com.tabacotracker.dto.TagSugerirDTO;
import com.tabacotracker.dto.TagVotarDTO;
import com.tabacotracker.model.Tag;
import com.tabacotracker.service.TagService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService s;

    @PostMapping("/sugerir")
    public Tag sugerir(@RequestBody TagSugerirDTO d, HttpServletRequest r) {
        return s.sugerir(d, (UUID) r.getAttribute("userId"));
    }

    @PostMapping("/votar")
    public Map<String, Object> votar(@RequestBody TagVotarDTO d, HttpServletRequest r) {
        return s.votar(d, (UUID) r.getAttribute("userId"));
    }
}
