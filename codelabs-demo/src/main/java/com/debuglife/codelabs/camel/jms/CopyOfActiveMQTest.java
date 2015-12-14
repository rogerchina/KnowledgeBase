/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.camel.jms;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.hl7.HL7MLLPNettyDecoderFactory;
import org.apache.camel.component.hl7.HL7MLLPNettyEncoderFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.main.Main;
import org.apache.camel.model.language.SimpleExpression;

import ca.uhn.hl7v2.model.Message;


public class CopyOfActiveMQTest extends RouteBuilder{
    
    public static void main(String[] args) throws Exception{
        Main main = new Main();
        DefaultCamelContext camelContext = (DefaultCamelContext)main.getOrCreateCamelContext();
        addJMSComponent(camelContext);
        addRegistry(camelContext);
        
        main.addRouteBuilder(new CopyOfActiveMQTest());
        main.enableHangupSupport();
        main.enableTrace();
        main.run();
    }
    
    private static void addJMSComponent(CamelContext camelContext){
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false&broker.useJmx=false&mapJmsMessage=false");
        
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(connectionFactory);
        jmsComponent.setAcknowledgementModeName("AUTO_ACKNOWLEDGE");
        camelContext.addComponent("jms", jmsComponent);

        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConnectionFactory(connectionFactory);
        activeMQComponent.setAcknowledgementModeName("AUTO_ACKNOWLEDGE");
        activeMQComponent.setReceiveTimeout(5000);
        camelContext.addComponent("activemq", activeMQComponent);
    }
    
    private static void addRegistry(CamelContext camelContext) throws Exception{
        JndiRegistry jndi = new JndiRegistry(createJndiContext());
        // START SNIPPET: e1
        HL7MLLPNettyDecoderFactory decoder = new HL7MLLPNettyDecoderFactory();
        decoder.setCharset("UTF-8");
        jndi.bind("camelHL7Decoder", decoder);
        HL7MLLPNettyEncoderFactory encoder = new HL7MLLPNettyEncoderFactory();
        decoder.setCharset("UTF-8");
        jndi.bind("camelHL7Encoder", encoder);
        // END SNIPPET: e1
        ((DefaultCamelContext)camelContext).setRegistry(jndi);
        
    }
    
    private static Context createJndiContext() throws Exception {
        Properties properties = new Properties();

        // jndi.properties is optional
        InputStream in = CopyOfActiveMQTest.class.getClassLoader().getResourceAsStream("jndi.properties");
        if (in != null) {
            properties.load(in);
        } else {
            properties.put("java.naming.factory.initial", "org.apache.camel.util.jndi.CamelInitialContextFactory");
        }
        return new InitialContext(new Hashtable<Object, Object>(properties));
    }
    
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
    
}
