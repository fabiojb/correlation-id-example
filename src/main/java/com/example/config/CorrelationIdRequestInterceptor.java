package com.example.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;

public class CorrelationIdRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String correlationId = MDC.get("correlationId");
        requestTemplate.header("X-Correlation-ID", correlationId);
    }

}
