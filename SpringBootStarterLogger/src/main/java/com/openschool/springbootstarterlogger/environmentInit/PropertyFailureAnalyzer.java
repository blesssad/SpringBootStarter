package com.openschool.springbootstarterlogger.environmentInit;

import com.openschool.springbootstarterlogger.exception.InterceptorPropertyException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class PropertyFailureAnalyzer extends AbstractFailureAnalyzer<InterceptorPropertyException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, InterceptorPropertyException cause) {
        return new FailureAnalysis("Exception occur: " + cause.getMessage(), "set-application-properties", cause);
    }
}
