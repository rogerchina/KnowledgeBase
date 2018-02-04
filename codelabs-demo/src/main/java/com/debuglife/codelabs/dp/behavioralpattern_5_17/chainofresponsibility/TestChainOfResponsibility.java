package com.debuglife.codelabs.dp.behavioralpattern_5_17.chainofresponsibility;

/**
 * 链接上的请求可以是一条链，可以是一个树，还可以是一个环，模式本身不约束这个，需要我们自己去实现，
 * 同时，在一个时刻，命令只允许由一个对象传给另一个对象，而不允许传给多个对象。
 * @author roger.zhang
 *
 */
public class TestChainOfResponsibility {

	public static void main(String[] args) {
		TestChainOfResponsibility t = new TestChainOfResponsibility();
		
		MyHandler h1 = t.new MyHandler("h1");  
        MyHandler h2 = t.new MyHandler("h2");  
        MyHandler h3 = t.new MyHandler("h3");  
  
        h1.setHandler(h2);  
        h2.setHandler(h3);  
  
        h1.operator();  

	}
	
	public interface Handler {  
	    public void operator();  
	}

	public abstract class AbstractHandler {  
	      
	    private Handler handler;  
	  
	    public Handler getHandler() {  
	        return handler;  
	    }  
	  
	    public void setHandler(Handler handler) {  
	        this.handler = handler;  
	    }  
	      
	}  
	
	public class MyHandler extends AbstractHandler implements Handler {  
		  
	    private String name;  
	  
	    public MyHandler(String name) {  
	        this.name = name;  
	    }  
	  
	    @Override  
	    public void operator() {  
	        System.out.println(name+"deal!");  
	        if(getHandler()!=null){  
	            getHandler().operator();  
	        }  
	    }  
	}  
}
