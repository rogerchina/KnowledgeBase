package com.debuglife.codelabs.camel.jms;

import java.util.Map;
import java.util.Map.Entry;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.ServiceStatus;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;


public class CamelJmsToFile {
    
    private static CamelContext context = null;
    
    private CamelJmsToFile(){
    }
    
    public static void main(String[] args) throws Exception{
        testActiveMQ();
        Thread.sleep(20000);
        addNewRoute();
    }
    
    public static void addNewRoute() throws Exception{
        if(context.getStatus() == ServiceStatus.Started){
            context.addRoutes(new FileTransferRoute());
        }
    }
    
    public static void testActiveMQ() throws Exception{
        context = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false&broker.useJmx=false");
        context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        context.addRoutes(new FileToJmsRoute());
        
        ProducerTemplate template = context.createProducerTemplate();
        context.start();
        //context.addRoutes(new FileTransferRoute());
       
        MsgProducer producer = new MsgProducer(template);
        producer.start();
        
//        Thread.sleep(10000);
//        producer.stopProducer();
//        producer.join();
//        
//        context.stop();
    }
}

class MsgProducer extends Thread{
    private ProducerTemplate pt;
    private boolean running = true;
    public MsgProducer(ProducerTemplate pt){
        this.pt = pt;
    }
    
    public void run(){
        while(running){
            pt.sendBody("test-jms:queue:test.queue","Test Message");
            System.out.println("one message is sent successfully.");
            try {
                Thread.sleep(2000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void stopProducer(){
        running = false;
    }
}

class FileToJmsRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        //from("sftp://192.168.230.120:21/home/roger/camel/?username=roger&password=king2085&delay=5&move=done")
        //from("file:target/download?noop=true&move=done&moveFailed=../error")
        from("test-jms:queue:test.queue")
            .process(new Processor(){
                @Override
                public void process(Exchange exchange) throws Exception {
                    Message msg = exchange.getIn();
                    Map<String,Object> headerMap = msg.getHeaders();
                    for(Entry<String,Object> entry : headerMap.entrySet()){
                        System.out.println(entry.getKey() + ":" + headerMap.get(entry.getKey()));
                    }
                    
                    Object body = msg.getBody();
                    System.out.println("the raw message in the file: " + body);
                    msg.setBody(body + "-----TIMESTAMP----" + System.currentTimeMillis());
                }
            })
            //.to("test-jms:queue:test.queue")
            //from("test-jms:queue:test.queue")
            .process(new Processor(){
                @Override
                public void process(Exchange exchange) throws Exception {
                    Message msg = exchange.getIn();
                    System.out.println("content in the queue of test.queue" + msg.getBody());
                    msg.setBody(msg.getBody() + "\nappended at test.queue phrase\n");
                }
            })
            .to("test-jms:queue:test.queue1")
            .process(new Processor(){
                @Override
                public void process(Exchange exchange) throws Exception {
                    Message msg = exchange.getIn();
                    System.out.println("content in the queue of test.queue1" + msg.getBody());
                    msg.setBody(msg.getBody() + "appended at test.queue1 phrase\n");
                }
            })
            .to("test-jms:quue:test.queue2")
            .process(new Processor(){
                @Override
                public void process(Exchange exchange) throws Exception {
                    Message msg = exchange.getIn();
                    System.out.println("content in the queue of test.queue2" + msg.getBody());
                    msg.setBody(msg.getBody() + "appended at test.queue2 phrase\n");
                }
            })
            .to("file:///C:/data/persistent");
    }
}

class FileTransferRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("file:///C:/data/inbox?noop=true")
            .to("file:///C:/data/outbox");
    }

}

