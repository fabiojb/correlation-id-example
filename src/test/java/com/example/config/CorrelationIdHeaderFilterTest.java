package com.example.config;

import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class CorrelationIdHeaderFilterTest {

    @Test
    public void testaRequisicaoSemCorrelationId() throws IOException, ServletException {
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        ServletResponse servletResponse = mock(ServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        CorrelationIdHeaderFilter filter = new CorrelationIdHeaderFilter();
        filter.doFilter(servletRequest, servletResponse, filterChain);

        String correlationId = MDC.get("correlationId");
        assertNotNull(correlationId);
        assertEquals(36, correlationId.length()); // deve conter apenas a string do UUID
    }

    @Test
    public void testaRequisicaoComCorrelationId() throws IOException, ServletException {
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        ServletResponse servletResponse = mock(ServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        String correlationId = UUID.randomUUID().toString();
        when(servletRequest.getHeader("X-Correlation-ID")).thenReturn(correlationId);

        CorrelationIdHeaderFilter filter = new CorrelationIdHeaderFilter();
        filter.doFilter(servletRequest, servletResponse, filterChain);

        String correlationIdMDC = MDC.get("correlationId");

        assertEquals(correlationId, correlationIdMDC);
    }

}
