package com.techbank.account.cmd.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techbank.cqrs.core.producers.AbstractEventProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountEventProducer extends AbstractEventProducer {

    public AccountEventProducer(KafkaTemplate<String, String> kafkaTemplate,
                                ObjectMapper objectMapper) {
        super(kafkaTemplate, objectMapper);
    }
}
