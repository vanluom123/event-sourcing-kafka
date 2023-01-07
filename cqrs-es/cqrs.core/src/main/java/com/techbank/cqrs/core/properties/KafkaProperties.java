package com.techbank.cqrs.core.properties;

import lombok.Data;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "cqrs.kafka")
public class KafkaProperties {
    private String bootstrapServers = "localhost:9092";
    private String groupId = "group";
    private final Consumer consumer = new Consumer();
    private final Producer producer = new Producer();

    @Data
    public static class Consumer {
        private String bootstrapServers = "localhost:9092";
        private String groupId = "group";
        private Object keyDeserializer = IntegerDeserializer.class;
        private Object valueDeserializer = StringDeserializer.class;
        private Boolean enableAutoCommit = false;
        private Long autoCommitInterval = 0L;
        private String autoOffsetReset = "earliest";
        private Long sessionTimeout = 0L;
    }

    @Data
    public static class Producer {
        private String bootstrapServers = "localhost:9092";
        private String groupId = "group";
        private Object keySerializer = IntegerSerializer.class;
        private Object valueSerializer = StringSerializer.class;
    }
}
