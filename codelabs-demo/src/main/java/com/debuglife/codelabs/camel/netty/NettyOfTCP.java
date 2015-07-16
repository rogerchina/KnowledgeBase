package com.debuglife.codelabs.camel.netty;

import io.netty.buffer.ByteBuf;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;


public class NettyOfTCP {
    public static void main(String[] args) throws Exception{
       Main m = new Main(); 
       m.addRouteBuilder(new MyRouteBuilder1());
       m.enableHangupSupport();
       m.run();
    }
}

class MyRouteBuilder1 extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        // workflow - 1
        from("file:///C:/data/netty/inbox?noop=true")
            .convertBodyTo(String.class)
            .process(new Processor(){
                @Override
                public void process(Exchange exchange) throws Exception {
                    System.out.println(exchange.getIn().getBody());
                    Map<String, Object> headers = exchange.getIn().getHeaders();
                    for(Entry<String, Object> entry : headers.entrySet()){
                        System.out.println(entry.getKey() + ":" + headers.get(entry.getKey()));
                    }
                }
            })
            .to("netty4:tcp://localhost:9999?clientMode=true");
        
        // workflow - 2
        from("netty4:tcp://localhost:9999")
            .process(new Processor(){
                @Override
                public synchronized void  process(Exchange exchange) throws Exception {
                    Map<String, Object> headers = exchange.getIn().getHeaders();
                    System.out.println("from netty4 ... server");
                    for(Entry<String, Object> entry : headers.entrySet()){
                        System.out.println(entry.getKey() + ":" + headers.get(entry.getKey()));
                    }
                }
            })
            .to("file:///C:/data/netty/outbox");
    }
}


