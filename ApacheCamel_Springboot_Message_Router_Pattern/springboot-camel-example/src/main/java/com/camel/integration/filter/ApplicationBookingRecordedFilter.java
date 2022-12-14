package com.camel.integration.filter;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.stereotype.Component;

@Component
public class ApplicationBookingRecordedFilter implements Predicate {

    @Override
    public boolean matches(Exchange exchange) {
        RecordHeaders headers = (RecordHeaders) exchange.getMessage().getHeaders().get("kafka.HEADERS");
        Header header = headers.headers("X-TASK-NAME").iterator().next();
        String headerValue = new String(header.value());
        return headerValue.equals("BOOKING_RECORDED");
    }
}
