package com.debuglife.codelabs.jmx;


public class HelloJMX implements HelloJMXMBean{

    private String message;
    
    @Override
    public void sayHello() {
        System.out.println("Hello JMX" + message);
    }

    @Override
    public void hello(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
