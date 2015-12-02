/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.camel.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;


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
        camelContext.addComponent("jms", JmsComponent.jmsComponentClientAcknowledge(connectionFactory));

        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConnectionFactory(connectionFactory);
        activeMQComponent.setAcknowledgementModeName("CLIENT_ACKNOWLEDGE");
        activeMQComponent.setReceiveTimeout(5000);
        camelContext.addComponent("activemq", ActiveMQComponent.jmsComponentClientAcknowledge(connectionFactory));
    }
    
    @Test
    public void testActiveMQ(){
        ProducerTemplate template = camelContext.createProducerTemplate();
        template.sendBody("activemq:receiver", "HelloWorld");
    }
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder(){
            @Override
            public void configure() throws Exception {
                from("active:receiver").process(new Processor(){
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        
                    }
                });
            }
        };
    }
    
}
