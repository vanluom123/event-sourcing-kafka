package com.techbank.cqrs.core.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techbank.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
