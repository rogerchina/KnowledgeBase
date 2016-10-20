package com.debuglife.codelabs.designpattern.structuralpattern_5_10.bridge;

/**
 * 通过对Bridge类的调用，实现了对接口Sourceable的实现类SourceSub1和SourceSub2的调用.
 * @author roger.zhang
 *
 */
public class TestBridge {

	public static void main(String[] args) {
		TestBridge t = new TestBridge();
		
		Bridge bridge = t.new MyBridge();  
         
        /*调用第一个对象*/  
        Sourceable source1 = t.new SourceSub1();  
        bridge.setSource(source1);  
        bridge.method();  
          
        /*调用第二个对象*/  
        Sourceable source2 = t.new SourceSub2();  
        bridge.setSource(source2);  
        bridge.method();  

	}

	private interface Sourceable {  
	    public void method();  
	}
	
	private class SourceSub1 implements Sourceable {  
		  
	    @Override  
	    public void method() {  
	        System.out.println("this is the first sub!");  
	    }  
	}
	
	private class SourceSub2 implements Sourceable {  
		  
	    @Override  
	    public void method() {  
	        System.out.println("this is the second sub!");  
	    }  
	}
	
	private abstract class Bridge {  
	    private Sourceable source;  
	    
	    public void setSource(Sourceable source) {  
	        this.source = source;  
	    }  
	    
	    public Sourceable getSource() {  
	        return source;  
	    }
	    
	    public void method(){  
	        source.method();  
	    } 
	}
	
	private class MyBridge extends Bridge {  
	    public void method(){  
	        getSource().method();  
	    }  
	}  
}
