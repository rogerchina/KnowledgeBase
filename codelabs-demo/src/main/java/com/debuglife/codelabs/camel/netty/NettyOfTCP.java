package com.debuglife.codelabs.camel.netty;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class NettyOfTCP {
    public static void main(String[] args) throws Exception{
//       Main m = new Main(); 
//       m.addRouteBuilder(new MyRouteBuilder1());
//       m.enableHangupSupport();
//       m.run();
       
       CamelContext context = new DefaultCamelContext();
       context.addRoutes(new MyRouteBuilder1());
       context.start();
       ProducerTemplate template = context.createProducerTemplate();
       template.sendBody("netty4:tcp://localhost:9999?clientMode=true", "hello");
    }
}

class MyRouteBuilder1 extends RouteBuilder{
    @Override
    public void configure() throws Exception {
//        // workflow - 1
//        from("file:///C:/data/netty/inbox?noop=true")
//            .convertBodyTo(String.class)
//            .process(new Processor(){
//                @Override
//                public void process(Exchange exchange) throws Exception {
//                    System.out.println(exchange.getIn().getBody());
//                    Map<String, Object> headers = exchange.getIn().getHeaders();
//                    for(Entry<String, Object> entry : headers.entrySet()){
//                        System.out.println(entry.getKey() + ":" + headers.get(entry.getKey()));
//                    }
//                }
//            })
//            .to("netty4:tcp://localhost:9999?clientMode=true");
//        
//        // workflow - 2
//        from("netty4:tcp://localhost:9999")
//            .process(new Processor(){
//                @Override
//                public synchronized void  process(Exchange exchange) throws Exception {
//                    Map<String, Object> headers = exchange.getIn().getHeaders();
//                    System.out.println("from netty4 ... server");
//                    for(Entry<String, Object> entry : headers.entrySet()){
//                        System.out.println(entry.getKey() + ":" + headers.get(entry.getKey()));
//                    }
//                }
//            })
//            .to("file:///C:/data/netty/outbox");
        
        
//        from("file:///C:/data/netty/inbox?noop=true")
//            .convertBodyTo(String.class)
//            .process(new Processor(){
//                @Override
//                public void process(Exchange exchange) throws Exception {
//                    System.out.println(exchange.getIn().getBody());
//                    Map<String, Object> headers = exchange.getIn().getHeaders();
//                    for(Entry<String, Object> entry : headers.entrySet()){
//                        System.out.println(entry.getKey() + ":" + headers.get(entry.getKey()));
//                    }
//                }
//            })
//            .to("netty4:tcp://localhost:9999?clientMode=true");
        
        from("netty4:tcp://localhost:9999").autoStartup(true).startupOrder(2)
            .convertBodyTo(String.class)
            .process(new Processor(){
                @Override
                public void process(Exchange exchange) throws Exception {
                    System.out.println("from netty through tcp port 9999 receiving data:" + exchange.getIn().getBody());
                }
            })
            .to("netty4:tcp://localhost:8888?clientMode=true");
        
        from("netty4:tcp://localhost:8888").autoStartup(true).startupOrder(1)
            .process(new Processor(){
                @Override
                public void process(Exchange exchange) throws Exception {
                    System.out.println("from netty through tcp port 8888 receiving data:" + exchange.getIn().getBody());
                }
            });
        
    }
}


