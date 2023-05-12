package com.techbank.cqrs.core.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techbank.cqrs.core.events.BaseEvent;
import com.techbank.cqrs.core.exceptions.CqrsJSonProcessingException;
import org.springframework.kafka.core.KafkaTemplate;

public class AbstractEventProducer implements EventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    protected AbstractEventProducer(KafkaTemplate<String, String> kafkaTemplate,
                                    ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void produce(String topic, BaseEvent event) {
        String strEvent;
        try {
            strEvent = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new CqrsJSonProcessingException("An error occurred during formatting", e.getCause());
        }
        kafkaTemplate.send(topic, strEvent);
    }
}
