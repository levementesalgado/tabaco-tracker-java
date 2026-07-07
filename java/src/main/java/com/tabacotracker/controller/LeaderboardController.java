package com.tabacotracker.controller;

import com.tabacotracker.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {
    private final RankingService s;

    @GetMapping
    public List<Map<String, Object>> list() { return s.ranking(); }

    @GetMapping("/podium")
    public List<Map<String, Object>> podium() { return s.ranking().stream().limit(3).toList(); }

    @GetMapping("/ranking")
    public List<Map<String, Object>> ranking() { return s.ranking(); }
}
