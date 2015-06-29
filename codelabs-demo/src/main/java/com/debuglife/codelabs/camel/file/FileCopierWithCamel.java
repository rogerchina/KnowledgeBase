package com.debuglife.codelabs.camel.file;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;


public class FileCopierWithCamel {
    public static void main(String[] args) throws Exception{
        Main main = new Main();
        main.addRouteBuilder(new RouteBuilder(){
            @Override
            public void configure() throws Exception {
                from("file:///C:/data/inbox?noop=true")
                    .to("file:///C:/data/outbox?fileExist=Override")/*fileExist=Fail&overruleFile=copy-of-${file:name}*/
                    .log("copied files ${file:name} ");
            }
        });
        main.enableHangupSupport();
        main.run();
        main.shutdown();
    }
}
