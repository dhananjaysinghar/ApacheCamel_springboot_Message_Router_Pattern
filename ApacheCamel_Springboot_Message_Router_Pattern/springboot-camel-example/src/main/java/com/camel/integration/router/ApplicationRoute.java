package com.camel.integration.router;

import com.camel.integration.filter.ApplicationBookingCaptureFilter;
import com.camel.integration.filter.ApplicationBookingRecordedFilter;
import com.camel.integration.processor.ApplicationProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRoute extends RouteBuilder {

    @Autowired
    private ApplicationProcessor applicationProcessor;
    @Autowired
    private ApplicationBookingCaptureFilter bookingCaptureFilter;
    @Autowired
    private ApplicationBookingRecordedFilter bookingRecordedFilter;

    @Override
    public void configure() {
        from("{{app.camel-from}}")
                .log("input: ${body}")
                .process(applicationProcessor)
                .log("output: ${body}")
                .choice()
                .when(bookingCaptureFilter)
                .to("{{app.camel-to-state-machine}}")
                .when(bookingRecordedFilter)
                .to("{{app.camel-to-core-service}}")
                .endChoice();
    }
}
