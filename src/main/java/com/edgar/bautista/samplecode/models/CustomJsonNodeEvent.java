package com.edgar.bautista.samplecode.models;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.context.ApplicationEvent;

public class CustomJsonNodeEvent extends ApplicationEvent {
    private JsonNode node;

    public CustomJsonNodeEvent(JsonNode node) {
        super(node);
        this.node = node;
    }

    public JsonNode getNode() {
        return this.node;
    }
}
