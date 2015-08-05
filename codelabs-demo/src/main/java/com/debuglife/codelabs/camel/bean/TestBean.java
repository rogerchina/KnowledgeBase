package com.debuglife.codelabs.camel.bean;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;


public class TestBean extends CamelTestSupport{
    @Test
    public void testUseBean() throws Exception{
        MockEndpoint endpoint = getMockEndpoint("mock:result");
        endpoint.expectedMessageCount(1);
        template.sendBody("direct:start", "ni hao");
        
        endpoint.assertIsSatisfied();
    }
}

class UseBean{
    public String processMsg(@Body String body){
        return body + " hello.";
    }
}

class UseRouter extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("direct:start")
            .bean(UseBean.class,"processMsg")
            .to("direct:end");
        
        from("direct:end")
            .process(new Processor(){
                @Override
                public void process(Exchange exchange) throws Exception {
                    System.out.println(exchange.getIn().getBody());
                }
            })
            .to("mock:result");
    }
}