package com.ex.consumer;

import com.ex.model.BookingRequest;
import com.ex.producer.ApacheCamelKafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookingCaptureConsumer {

    @Value("${app.kafka.capture-topic}")
    private String topicName;

    @Autowired
    private ApacheCamelKafkaSender camelKafkaSender;

    @KafkaListener(topics = "${app.kafka.capture-topic}", groupId = "${app.kafka.consumer-group-id}", containerFactory = "concurrentKafkaListenerConsumerFactory")
    public void receive(BookingRequest request) {
        log.info("received data='{}={}' from topic: {}", request.hashCode(), request, topicName);
        camelKafkaSender.send(request);
    }
}
