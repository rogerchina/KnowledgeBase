/*
 * The contents of this file are copyright (c) 2012 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.camel.jms;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;


public class ActiveMQRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("netty4:tcp://127.0.0.1:7799?sync=true&encoder=#camelHL7Encoder&decoder=#camelHL7Decoder")
        //.to("mock:result")//;
        
        .to(ExchangePattern.InOnly,"activemq:queue:foo")
        .process(new Hl7AckProcessor());
//        .unmarshal()
//        .hl7()
//         .to("netty4:tcp://localhost:7778?sync=true&encoder=#camelHL7Encoder&decoder=#camelHL7Decoder")
       //generate the ack  and tranform it to producers.
//        .bean(ResponseAck.class,"generateAck")
//        .transform(body());
    }

}
