package com.debuglife.codelabs.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public abstract class AbstractRouteBuilder extends RouteBuilder {

    @Override
    public final void configure() throws Exception {
        // global level error handler strategy.
        errorHandler();

        // customize user router definition
        customRouterDefinition();
    }

    protected void errorHandler() throws Exception {
        // error handling
        errorHandler(defaultErrorHandler()
                .maximumRedeliveries(5)
                .redeliveryDelay(10000)
                .retryAttemptedLogLevel(LoggingLevel.WARN));
    }

    public abstract void customRouterDefinition() throws Exception;

}
