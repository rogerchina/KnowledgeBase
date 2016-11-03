package com.debuglife.codelabs.corejava.jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;


public class HelloJMXAgent {
    public static void main(String[] args) throws Exception{
        MBeanServer server = MBeanServerFactory.createMBeanServer();
        ObjectName helloName = new ObjectName("chenguang:name=HelloWorld");
        server.registerMBean(new HelloJMX(), helloName);
        ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8888");
//        HtmlAdapterServer adapterServer = new HtmlAdapterServer();
//        server.registerMBean(adapterServer, adapterName);
//        adapterServer.start();
//        System.out.println("monitoring started...");
    }
}
