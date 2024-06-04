package com.openschool.springbootstarterlogger;

import com.openschool.springbootstarterlogger.interceptor.CustomInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class CustomInterceptorTest {

    private CustomInterceptor interceptor;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private Object handler;

    @BeforeEach
    void setUp() {
        interceptor = new CustomInterceptor();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handler = new Object();
    }

    @Test
    void testPreHandle() throws Exception {
        boolean result = interceptor.preHandle(request, response, handler);
        assertTrue(result);
        assertNotNull(request.getAttribute("startTime"));
    }

    @Test
    void testAfterCompletion() throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());

        Thread.sleep(50);

        response.addHeader("Content-Type", "application/json");
        response.addHeader("Vary", "Origin");

        interceptor.afterCompletion(request, response, handler, null);

        assertNotNull(response);
        assertTrue(response.containsHeader("Content-Type"));
        assertTrue(response.containsHeader("Vary"));
    }

    @Test
    void testAfterCompletionWithException() throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());

        Thread.sleep(50);

        Exception ex = new Exception("Test exception");
        interceptor.afterCompletion(request, response, handler, ex);

        assertNotNull(response);
    }
}
