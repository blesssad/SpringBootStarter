package com.openschool.springbootstarterlogger.config;

import com.openschool.springbootstarterlogger.interceptor.CustomInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnProperty("custom-interceptor.enable")
public class CustomWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingControllerInterceptor());
    }

    @Bean
    public CustomInterceptor loggingControllerInterceptor() {
        return new CustomInterceptor();
    }
}