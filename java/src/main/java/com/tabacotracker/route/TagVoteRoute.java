package com.tabacotracker.route;

import com.tabacotracker.processor.TagScoreProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TagVoteRoute extends RouteBuilder {
    private final TagScoreProcessor processor;

    public TagVoteRoute(TagScoreProcessor processor) { this.processor = processor; }

    @Override
    public void configure() {
        from("direct:voteTag")
            .aggregate(constant(true), (old, newEx) -> {
                var list = old == null
                    ? new java.util.ArrayList<java.util.Map<String, Object>>()
                    : (java.util.List<java.util.Map<String, Object>>) old.getIn().getBody(List.class);
                list.addAll((java.util.List) newEx.getIn().getBody(List.class));
                newEx.getIn().setBody(list);
                return newEx;
            })
            .completionSize(5).completionTimeout(10000)
            .process(processor)
            .log("Lote de ${body} votos processado");
    }
}
