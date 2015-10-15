package com.debuglife.codelabs.camel.file;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;


public class FileCopierWithCamel {
    public static void main(String[] args) throws Exception{
        Main main = new Main();
        main.addRouteBuilder(new RouteBuilder(){
            @Override
            public void configure() throws Exception {
                
                // error handling
                // context-scoped
//                errorHandler(defaultErrorHandler()
//                        .maximumRedeliveries(5)
//                        .redeliveryDelay(10000)
//                        .retryAttemptedLogLevel(LoggingLevel.WARN));
        	from("").routeId("").setAutoStartup("false");
//                
                from("file:///C:/data/inbox?noop=true")
                    .routeId("")//.noAutoStartup()
                    .to("file:///C:/data/outbox?fileExist=Override")/*fileExist=Fail&overruleFile=copy-of-${file:name}*/
                    .log("copied files ${file:name} ").setAutoStartup("false");
                    //.errorHandler(deadLetterChannel("log：dead?level=ERROR").useOriginalMessage())
                    ;
//                // output the file content to standard output
//                from("file:///C:/data/inbox?noop=true")
//                    .to("stream:out");
//                
//                // retrieve the string and write into the file
//                from("stream:in?promptMessage=Enter something:")
//                    .to("file:///C:/data/outbox"); //?fileName=prompt.txt 
//                                                   //?fileName=${date:now:yyyyMMdd-hh:mm:ss}.txt
//                
//                from("file:///C:/data/inbox?noop=true").to("jms:incomingOrders");
//                from("jms:incomingOrders")
//                    .choice()
//                    .when(header("CamelFileName").endsWith(".xml"))
//                        .to("jms：topic:xmlOrders")
//                    .when(header("CamelFileName").endsWith(".txt"))
//                        .to("jms:topic:txtOrders")
//                    .when(header("CamelFileName").regex("^.*(csv|csl)$"))
//                        .to("jms:topic:csvOrders");
//                from("jms:topic:xmlOrders").to("jms:accounting");
//                from("jms:topic:xmlOrders").to("jms:production");
                
            }
        });
        
        
        main.enableHangupSupport();
        main.run();
        main.shutdown();
    }
}
