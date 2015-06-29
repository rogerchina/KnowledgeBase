package com.debuglife.codelabs.camel.camelspring;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// This file will be configurated in the META-INF/spring/camel-spring.xml as bean.
public class DownloadLogger implements Processor{
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("We just downloaded: " + exchange.getIn().getHeader("CamelFileName"));
    }
}