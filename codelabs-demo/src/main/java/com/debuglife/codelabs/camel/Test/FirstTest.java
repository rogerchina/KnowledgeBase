package com.debuglife.codelabs.camel.Test;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;


public class FirstTest extends CamelTestSupport{
    private String inbox = "file:///C:/data/test/inbox";
    private String outbox = "file:///C:/data/test/outbox";
    
    @Override
    public void setUp() throws Exception {
        deleteDirectory(inbox);
        deleteDirectory(outbox);
        super.setUp();
    }
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        
        return new FileMoveRoute();
        
//        return new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                from(inbox)
//                    .to(outbox);
//            }
//        };
    }
    
    @Test
    public void testMoveFile() throws Exception{
        template.sendBodyAndHeader(inbox, "Hello World", Exchange.FILE_NAME, "hello.txt");
        Thread.sleep(1000);
        File target = new File("C://data//inbox//hello.txt");
        assertTrue("File not moved", target.exists());
        
        String content = context.getTypeConverter().convertTo(String.class, target);
        assertEquals("Hello World", content);
    }
    
    class FileMoveRoute extends RouteBuilder{
        @Override
        public void configure() throws Exception {
            from(inbox).to(outbox);
        }
    }
}

