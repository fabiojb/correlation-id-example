package com.example.config;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class CorrelationIdHeaderFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String correlationId = httpServletRequest.getHeader("X-Correlation-ID");

        if (!currentRequestIsAsyncDispatcher(httpServletRequest)) {
            if (correlationId == null) {
                correlationId = UUID.randomUUID().toString();
            }
            MDC.put("correlationId", correlationId);
        }

        filterChain.doFilter(httpServletRequest, servletResponse);
    }


    private boolean currentRequestIsAsyncDispatcher(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getDispatcherType() == DispatcherType.ASYNC;
    }
}
