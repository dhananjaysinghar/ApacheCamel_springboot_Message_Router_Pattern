package com.ex.orch.controller;

import com.ex.model.BookingRequest;
import com.ex.orch.producer.OrchKafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orch/book")
public class BookingOrchController {

    @Autowired
    private OrchKafkaSender kafkaSender;

    @PostMapping
    public String sendData(@RequestBody BookingRequest bookingRequest) {
        return kafkaSender.send(bookingRequest);
    }
}
