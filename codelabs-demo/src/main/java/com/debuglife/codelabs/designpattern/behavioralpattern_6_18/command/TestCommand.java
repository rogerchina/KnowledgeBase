package com.debuglife.codelabs.designpattern.behavioralpattern_6_18.command;


/**
 * 这个很哈理解，命令模式的目的就是达到命令的发出者和执行者之间解耦，实现请求和执行分开，
 * 熟悉Struts的同学应该知道，Struts其实就是一种将请求和呈现分离的技术，其中必然涉及命令模式的思想！
 * 
 * Invoker是调用者（司令员），Receiver是被调用者（士兵），MyCommand是命令，实现了Command接口，持有接收对象
 * @author roger.zhang
 *
 */
public class TestCommand {

	public static void main(String[] args) {
		TestCommand t = new TestCommand();
		
		Receiver receiver = t.new Receiver();  
        Command cmd = t.new MyCommand(receiver);  
        Invoker invoker = t.new Invoker(cmd);  
        invoker.action();  

	}

	private interface Command {  
	    public void exe();  
	}
	
	private class MyCommand implements Command {  
		  
	    private Receiver receiver;  
	      
	    public MyCommand(Receiver receiver) {  
	        this.receiver = receiver;  
	    }  
	  
	    @Override  
	    public void exe() {  
	        receiver.action();  
	    }  
	}
	
	private class Receiver {  
	    public void action(){  
	        System.out.println("command received!");  
	    }  
	}
	
	private class Invoker {  
	      
	    private Command command;  
	      
	    public Invoker(Command command) {  
	        this.command = command;  
	    }  
	  
	    public void action(){  
	        command.exe();  
	    }  
	}  
}
