package com.debuglife.codelabs.camel.eip;

import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.Header;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class EIPStudyInCamel {
    public static void main(String[] args) throws Exception{
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder(){
            @Override
            public void configure() throws Exception {
                //:Dead Letter Channel EIP
                errorHandler(deadLetterChannel("mock:error"));
                
                //:Content Based Router EIP
                from("direct:a")
                    .choice()
                        .when(header("foo").isEqualTo("bar"))
                            .to("direct:b")
                        .when(header("foo").isEqualTo("cheese"))
                            .to("direct:c")
                        .otherwise()
                            .to("direct:d")
                     .end();
                
                //:Message Filter EIP
                from("direct:start").threads(10)
                    .filter().method(MyBean.class, "isGoldCustomer").to("mock:result").end()
                .to("mock:end");
                
                //:Composed Message Process EIP
                // split up the order so individual OrderItems can be validated by the appropriate bean
                from("direct:start")
                    .split().body()
                    .choice() 
                        .when().method("orderItemHelper", "isWidget")
                            .to("bean:widgetInventory")
                        .otherwise()
                            .to("bean:gadgetInventory")
                    .end()
                    .to("seda:aggregate");
                 
                // collect and re-assemble the validated OrderItems into an order again
                from("seda:aggregate")
                    .aggregate(new MyOrderAggregationStrategy()).header("orderId").completionTimeout(1000L)
                        .to("mock:result");
                
                //:Scatter-Gather EIP
                from("direct:start").multicast().to("seda:vendor1", "seda:vendor2", "seda:vendor3");

                from("seda:vendor1").to("bean:vendor1").to("seda:quoteAggregator");
                from("seda:vendor2").to("bean:vendor2").to("seda:quoteAggregator");
                from("seda:vendor3").to("bean:vendor3").to("seda:quoteAggregator");

                from("seda:quoteAggregator")
                    .aggregate(header("quoteRequestId"), new LowestQuoteAggregationStrategy()).to("mock:result");
                
                //:Delayer EIP
                from("activemq:someQueue").delay(header("delayValue")).to("activemq:aDelayedQueue");
                
                //:Multicast EIP
                from("direct:a").multicast().parallelProcessing().to("direct:x", "direct:y", "direct:z");
                
                //:Throttler EIP
                from("seda:a").throttle(100).to("seda:b");
                
                //:Loop EIP
                from("direct:a").loop(8).to("mock:result");
                from("direct:b").loop(header("loop")).to("mock:result");
                from("direct:c").loop().xpath("/hello/@times").to("mock:result");
                
                //:Sampling Throttler EIP
                from("direct:sample")
                .sample()
                .to("mock:result");
             
                from("direct:sample-configured")
                    .sample(1, TimeUnit.SECONDS)
                    .to("mock:result");
                 
                from("direct:sample-configured-via-dsl")
                    .sample().samplePeriod(1).timeUnits(TimeUnit.SECONDS)
                    .to("mock:result");
                 
                from("direct:sample-messageFrequency")
                    .sample(10)
                    .to("mock:result");
                 
                from("direct:sample-messageFrequency-via-dsl")
                    .sample().sampleMessageFrequency(5)
                    .to("mock:result");
                
                //:Recipient List EIP
                errorHandler(deadLetterChannel("mock:error"));
                // static
                from("direct:a")
                    .multicast().to("direct:b", "direct:c", "direct:d");
                // dynamic
                from("direct:a")
                    .recipientList(header("foo"));
                
                //:Resequencer EIP
                from("direct:start")
                    .resequence(body()).batch()
                .to("mock:result");
                
                //:Aggregator EIP
                from("direct:start")
                    .aggregate(header("orderID"), new MyAggregationStrategy())    
                    .aggregationRepositoryRef("")
                    .completionSize(3)
                    .completionTimeout(5000)
                .to("mock:aggregate");
                
                //:Splitter EIP
                from("direct:start")
                    //.split(body())    
                    //.split().method(String.class, "")
                    .split(body().tokenize("\n")).streaming() // for big message
                    .stopOnException()
                    .end()
                .to("mock:split");
                
                //:Routing Slip EIP
                from("direct:start")
                    // way - 1
                    //.setHeader("myID")
                    //.method(ComputeSlip.class)
                    //.routingSlip("myID")
                
                    // way - 2
                    .bean(EIPStudyInCamel.class)
                .to("mock:routeSlip");
                
                
                //:Dynamic Router EIP
                from("direct:start")
                    .dynamicRouter(bean(EIPStudyInCamel.class, ""))
                .to("mock:dynamic");
                
                //:Load Balancer EIP
                from("direct:start")
                    .loadBalance().roundRobin() // 1
                    .loadBalance().failover(1,false,true) // 2
                    .to("seda:A")
                    .to("seda:B")
                .to("mock:lb");
                from("seda:A")
                    .log("received data: ${body}")
                    .to("mock:A");
                
                from("seda:B")
                    .log("received data: ${body}")
                    .to("mock:B");
                
            }
        });
        
        context.getShutdownStrategy().setShutdownNowOnTimeout(true);
        context.start();
    }
    
    static class MyBean {
        public boolean isGoldCustomer(@Header("level") String level) { 
            return level.equals("gold"); 
        }
    }
}
