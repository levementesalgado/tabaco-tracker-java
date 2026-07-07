package com.tabacotracker.route;

import com.tabacotracker.processor.LeaderboardCalculator;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class LeaderboardRoute extends RouteBuilder {
    private final LeaderboardCalculator processor;

    public LeaderboardRoute(LeaderboardCalculator processor) { this.processor = processor; }

    @Override
    public void configure() {
        from("scheduler://weeklyPodium?delay=60000&initialDelay=30000&useFixedDelay=true")
            .process(processor)
            .log("Pódio semanal recalculado em ${date:now:yyyy-MM-dd HH:mm:ss}");
    }
}
