package com.tabacotracker.service;

import com.tabacotracker.model.Avaliacao;
import com.tabacotracker.repository.AvaliacaoRepository;
import com.tabacotracker.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RankingService {
    private final MarcaRepository marcaRepo;
    private final AvaliacaoRepository avRepo;
    private final RedisTemplate<String, Object> redis;

    public List<Map<String, Object>> ranking() {
        String cached = (String) redis.opsForValue().get("ranking:global");
        if (cached != null) {
            return List.of();
        }
        var marcas = marcaRepo.findAll();
        var rank = marcas.stream().map(m -> {
            var avs = avRepo.findByMarcaIdOrderByCreatedAtDesc(m.getId());
            int total = avs.size();
            double media = total > 0 ? avs.stream().mapToInt(Avaliacao::getNota).average().orElse(0) : 0;
            double wilson = total > 0 ? (media / 20.0) * ((double) total / (total + 10)) : 0;
            return Map.<String, Object>of(
                "id", m.getId(), "nome", m.getNome(), "slug", m.getSlug(),
                "foto_url", m.getFotoUrl(), "total_avaliacoes", total,
                "media", Math.round(media * 100) / 100.0,
                "wilson", Math.round(wilson * 10000) / 10000.0);
        }).sorted((a, b) -> Double.compare((Double) b.get("wilson"), (Double) a.get("wilson"))).toList();

        redis.opsForValue().set("ranking:global", rank.toString(), Duration.ofHours(1));
        return rank;
    }
}
