package com.tabacotracker.processor;

import com.tabacotracker.model.Avaliacao;
import com.tabacotracker.repository.AvaliacaoRepository;
import com.tabacotracker.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Component
@RequiredArgsConstructor
public class LeaderboardCalculator implements Processor {
    private final MarcaRepository marcaRepo;
    private final AvaliacaoRepository avRepo;
    private final RedisTemplate<String, Object> redis;

    @Override
    public void process(Exchange exchange) {
        var marcas = marcaRepo.findAll();
        var agora = Instant.now();
        var semanaAtras = agora.minus(Duration.ofDays(7));

        var podium = marcas.stream().map(m -> {
            var avs = avRepo.findByMarcaIdOrderByCreatedAtDesc(m.getId());
            var recentes = avs.stream()
                .filter(a -> a.getCreatedAt() != null && a.getCreatedAt().isAfter(semanaAtras)).toList();
            int total = recentes.size();
            double media = total > 0
                ? recentes.stream().mapToInt(Avaliacao::getNota).average().orElse(0)
                : 0;
            return Map.<String, Object>of(
                "marca", m.getNome(), "slug", m.getSlug(),
                "total_avaliacoes_semana", total,
                "media", Math.round(media * 100) / 100.0,
                "wilson", total > 0 ? (media / 20.0) * ((double) total / (total + 5)) : 0);
        }).sorted((a, b) -> Double.compare((Double) b.get("wilson"), (Double) a.get("wilson")))
         .limit(3).toList();

        redis.opsForValue().set("ranking:weekly", podium.toString(), Duration.ofDays(7));
    }
}
