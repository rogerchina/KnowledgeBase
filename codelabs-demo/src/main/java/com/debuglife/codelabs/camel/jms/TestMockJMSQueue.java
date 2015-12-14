package com.debuglife.codelabs.camel.jms;

import java.util.List;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.hl7.HL7MLLPNettyDecoderFactory;
import org.apache.camel.component.hl7.HL7MLLPNettyEncoderFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMockJMSQueue extends CamelTestSupport {
   private static String JMS_BROKER_URL="vm://g4m_v3_nextbroker?brokerConfig=xbean:com/debuglife/codelabs/camel/jms/g4m_activemq.xml&waitForStart=3000";

   String messageContent;

   @Before
   /**
    * It will be used every test method
    */
   public void setUp() throws Exception {
       messageContent = "MSH|^~\\&|OP|op|ris03|mpi-medavis|20120223140817||ADT^A01|3|P|2.5|||AL|NE\n" + "PID|||6^^^domain-c||Peter^John||20120216|M\n"
               + "PV1||I|^^^^^^000901||||0|0|0||||||||0||5|||||||||||||||||||||||||20120223140500";
       super.setUp();
   }

   @Override
   protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext= super.createCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(getJmsBrokerUrl());

        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConnectionFactory(connectionFactory);
        camelContext.addComponent("jms", jmsComponent);

        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConnectionFactory(connectionFactory);
        camelContext.addComponent("activemq", activeMQComponent);
        return camelContext;
    }
    private static String getJmsBrokerUrl(){
        return JMS_BROKER_URL;
    }
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
      return new ActiveMQRoute();
    }

    @Test
    public void testJMSQueue() throws InterruptedException {
        getMockEndpoint("mock:result").expectedMessageCount(1);
        Thread.sleep(20000);
        template.sendBody("netty4:tcp://localhost:7799?sync=true&encoder=#camelHL7Encoder&decoder=#camelHL7Decoder",messageContent);
        List<Exchange> list = getMockEndpoint("mock:result").getReceivedExchanges();
     
        for(Exchange exchange : list) {
            System.out.println("mock produce message:" + exchange.getIn().getBody());
        }

        assertMockEndpointsSatisfied();
    }

//    @Test
//    public void testJMSQueueInAnyOrder() throws InterruptedException {
//        MockEndpoint mockEndpoint = getMockEndpoint("mock:star");
//        mockEndpoint.expectedBodiesReceivedInAnyOrder("order", "test");
//        template.sendBody("activemq:queue:foo", "test");
//        template.sendBody("activemq:queue:foo", "order");
//        mockEndpoint.assertIsSatisfied();
//    }

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
}
