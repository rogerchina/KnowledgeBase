package com.debuglife.codelabs.camel.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// The corresponding configuration file is bean.xml
// just provide an example to demonstrate how spring work.
public class GreetMeBean {
    private Greeter greeter;
    
    public void setGreeter(Greeter greeter){
        this.greeter = greeter;
    }
    
    public void execute(){
        System.out.println(greeter.sayHello());
    }
    
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        GreetMeBean bean = (GreetMeBean)context.getBean("greetBean");
        bean.execute();
    }
}

interface Greeter{
    String sayHello();
}

class EnglishGreeter implements Greeter{
    @Override
    public String sayHello() {
        return "hello, " + System.getProperty("user.name");
    }
}

class ChineseGreeter implements Greeter{
    @Override
    public String sayHello() {
        return "ni hao, " + System.getProperty("user.name");
    }
}

