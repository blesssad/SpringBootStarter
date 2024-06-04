package com.openschool.springbootstarterlogger.environmentInit;

import com.openschool.springbootstarterlogger.exception.InterceptorPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
public class CustomEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String loggingEnable = environment.getProperty("custom-interceptor.enable");
        String logsDestination = environment.getProperty("custom-interceptor.logger.destination");
        String logsPath = environment.getProperty("custom-interceptor.logger.path");
        String logsFilename = environment.getProperty("custom-interceptor.logger.filename");
        String logsLevel = environment.getProperty("custom-interceptor.logger.level");

        if (!isBoolean(loggingEnable)) {
            throw new InterceptorPropertyException("Property 'custom-interceptor.enable' must be a boolean value ('true' or 'false')");
        }

        boolean enabled = Boolean.parseBoolean(loggingEnable);

        if (enabled) {
            check(logsDestination, logsFilename, logsPath, logsLevel);
        }
    }

    private void check(String logsDestination, String logsFilename, String logsPath, String logsLevel) {
        boolean badDestination = !StringUtils.hasText(logsDestination) ||
                (!Objects.equals(logsDestination, "FILE") && !Objects.equals(logsDestination, "CONSOLE"));

        if (badDestination) {
            throw new InterceptorPropertyException("Property 'custom-interceptor.logger.destination' must be FILE or CONSOLE");
        }
        if (Objects.equals(logsDestination, "FILE") && !StringUtils.hasText(logsFilename)) {
            throw new InterceptorPropertyException("Property 'custom-interceptor.logger.filename' must contain any file name");
        }
        if (Objects.equals(logsDestination, "FILE") && !StringUtils.hasText(logsPath)) {
            throw new InterceptorPropertyException("Property 'custom-interceptor.logger.path' must contain path to logs");
        }
        if (!StringUtils.hasText(logsLevel) || !logsLevel.matches("trace|debug|info|warn|error")) {
            throw new InterceptorPropertyException("Property 'custom-interceptor.logger.level' must be trace, debug, info, warn or error");
        }
    }

    private boolean isBoolean(String value) {
        return "true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value);
    }
}
