package com.openschool.springbootstarterlogger.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.*;

@Slf4j
public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());

        Map<String, String> headersMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersMap.put(headerName, headerValue);
        }

        log.info("""
                -------------------------------------
                Request:
                -------------------------------------
                URI: {}
                Method: {}
                Parameters: {}
                Headers: {}
                -------------------------------------
                """,
                request.getRequestURI(),
                request.getMethod(),
                request.getQueryString(),
                headersMap);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            log.error("Exception occur: {}. Request {}", ex.getMessage(), request.getRequestURI());
        }
        long startTime = (long) request.getAttribute("startTime");
        long processingTime = System.currentTimeMillis() - startTime;

        Collection<String> headerNames = response.getHeaderNames();
        Map<String, String> headersMap = new HashMap<>();

        headerNames.forEach(header -> headersMap.put(header, response.getHeader(header)));

        log.info("""
                -------------------------------------
                Response:
                -------------------------------------
                Status: {}
                Headers: {}
                Time: {}
                -------------------------------------
                """, response.getStatus(), headersMap, processingTime);

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
