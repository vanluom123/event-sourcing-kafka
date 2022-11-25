package com.techbank.cqrs.core.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techbank.cqrs.core.events.BaseEvent;
import com.techbank.cqrs.core.exceptions.JsonException;
import org.springframework.kafka.core.KafkaTemplate;

public class AbstractEventProducer implements EventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    protected AbstractEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void produce(String topic, BaseEvent event) {
        String strEvent;
        try {
            strEvent = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new JsonException("An error occurred during formatting", e.getCause());
        }
        kafkaTemplate.send(topic, strEvent);
    }
}
