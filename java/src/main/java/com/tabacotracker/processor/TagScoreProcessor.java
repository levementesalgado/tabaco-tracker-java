package com.tabacotracker.processor;

import com.tabacotracker.repository.VotosTagRepository;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagScoreProcessor implements Processor {
    private final VotosTagRepository r;

    @Override
    public void process(Exchange exchange) {
        var body = exchange.getIn().getBody(java.util.List.class);
    }
}
