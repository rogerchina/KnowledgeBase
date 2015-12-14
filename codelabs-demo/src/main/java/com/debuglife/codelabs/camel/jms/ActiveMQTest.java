/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.camel.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.hl7.HL7MLLPNettyDecoderFactory;
import org.apache.camel.component.hl7.HL7MLLPNettyEncoderFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.model.language.SimpleExpression;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;

import ca.uhn.hl7v2.model.Message;


public class ActiveMQTest extends CamelTestSupport{
    
    private CamelContext camelContext;
    
    @Before
    public void setup() throws Exception{
        camelContext = super.context();
        addJMSComponent();
        super.setUp();
    }
    
    private void addJMSComponent(){
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false&broker.useJmx=false&mapJmsMessage=false");
        
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(connectionFactory);
        camelContext.addComponent("jms", jmsComponent);

        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConnectionFactory(connectionFactory);
        activeMQComponent.setReceiveTimeout(5000);
        camelContext.addComponent("activemq", activeMQComponent);
    }
    
    @Override
    protected JndiRegistry createRegistry() throws Exception {
        JndiRegistry jndi = super.createRegistry();
        // START SNIPPET: e1
        HL7MLLPNettyDecoderFactory decoder = new HL7MLLPNettyDecoderFactory();
        decoder.setCharset("iso-8859-1");
        jndi.bind("camelHL7Decoder", decoder);
        HL7MLLPNettyEncoderFactory encoder = new HL7MLLPNettyEncoderFactory();
        decoder.setCharset("iso-8859-1");
        jndi.bind("camelHL7Encoder", encoder);
        // END SNIPPET: e1
        return jndi;
    }
    
    @Test
    public void testActiveMQ() throws Exception{
        Thread.sleep(10000000);
//        ProducerTemplate template = camelContext.createProducerTemplate();
//        template.sendBody("", "HelloWorld");
    }
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder(){
            @Override
            public void configure() throws Exception {
                
                from("netty4:tcp://192.168.230.117:7799?sync=true&encoder=#camelHL7Encoder&decoder=#camelHL7Decoder")
                .setProperty("originalMessage", new SimpleExpression("${body}"))
                .log(LoggingLevel.INFO, "received message.")
                //.to(ExchangePattern.InOnly, "activemq:weld")
                .log(LoggingLevel.INFO, "pushed into activemq:weld queue.")
                .process(new Processor(){
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Message message = exchange.getProperty("originalMessage", Message.class); //dto.getSourceMessage();
                        Message ackMessage = message.generateACK();
                        exchange.getOut().setBody(ackMessage);
                    }
                });
                
//                from("netty4:tcp://192.168.230.117:7800?sync=true&amp;encoder=#camelHL7Encoder&amp;decoder=#camelHL7Decoder&amp;disconnect=true")
//                .log(LoggingLevel.INFO, "sent message.");
            }
        };
    }
    
}
