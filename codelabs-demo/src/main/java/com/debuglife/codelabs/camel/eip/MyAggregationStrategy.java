package com.debuglife.codelabs.camel.eip;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;


public class MyAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        // TODO Auto-generated method stub
        return null;
    }

}
