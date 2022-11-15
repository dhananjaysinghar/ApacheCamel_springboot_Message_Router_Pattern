package com.ex.orch.producer;

import com.ex.model.BookingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class OrchKafkaSender {

    @Autowired
    @Qualifier("bookingKafkaTemplate")
    private KafkaTemplate<String, BookingRequest> bookingTemplate;

    @Value("${app.kafka.capture-topic}")
    private String topicName;

    public String send(BookingRequest bookingRequest) {
        log.info("sending data='{}={}' to topic='{}'", bookingRequest.hashCode(), bookingRequest, topicName);
        bookingTemplate.send(topicName, UUID.randomUUID().toString(), bookingRequest);
        return "data sent successfully";
    }
}
