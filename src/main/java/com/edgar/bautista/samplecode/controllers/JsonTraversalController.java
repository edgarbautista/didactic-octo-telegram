package com.edgar.bautista.samplecode.controllers;

import com.edgar.bautista.samplecode.common.JsonNodeHelper;
import com.edgar.bautista.samplecode.models.CustomJsonNodeEvent;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/json")
public class JsonTraversalController {

    private final ApplicationEventPublisher jsonNodePayload;

    @Autowired
    public JsonTraversalController(ApplicationEventPublisher jsonNodePayload) {
        this.jsonNodePayload = jsonNodePayload;
    }

    @PostMapping
    public String jsonTraversal(@RequestBody JsonNode payload) {
        CustomJsonNodeEvent event = new CustomJsonNodeEvent(payload);
        jsonNodePayload.publishEvent(event);
        return JsonNodeHelper.jsonToTypeTraversal(payload);
    }
}
