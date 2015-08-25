package com.debuglife.codelabs.camel.x;

import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.RouteDefinition;


public class IMC2RISRoute extends RouteBuilder implements DynamicRoute{

    private RouteDefinition routeDefinition = null;
    
    @Override
    public void print(RouteDefinition routeDefinition) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void configure() throws Exception {
        
    }
}
