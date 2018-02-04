package com.debuglife.codelabs.dp.behavioralpattern_9_21.visitor;

/**
 * 该模式适用场景：如果我们想为一个现有的类增加新功能，不得不考虑几个事情：
 * 1、新功能会不会与现有功能出现兼容性问题？
 * 2、以后会不会再需要添加？
 * 3、如果类不允许修改代码怎么办？
 * 面对这些问题，最好的解决方法就是使用访问者模式，访问者模式适用于数据结构相对稳定的系统，把数据结构和算法解耦.
 * @author roger
 *
 */
public class TestVisitor {

	public static void main(String[] args) {
		TestVisitor t = new TestVisitor();
		
		Visitor visitor = t.new MyVisitor();  
		Subject sub = t.new MySubject();  
		sub.accept(visitor);    
	}
	
	// Subject类，accept方法，接受将要访问它的对象，getSubject()获取将要被访问的属性，
    private interface Subject {  
        public void accept(Visitor visitor);  
        public String getSubject();  
    }  

    // 一个Visitor类，存放要访问的对象
	private interface Visitor {  
	    void visit(Subject sub);  
	} 
	
	private class MyVisitor implements Visitor {  
	    @Override  
	    public void visit(Subject sub) {  
	        System.out.println("visit the subject："+sub.getSubject());  
	    }  
	}  
	
    public class MySubject implements Subject {  
      
        @Override  
        public void accept(Visitor visitor) {  
            visitor.visit(this);  
        }  
      
        @Override  
        public String getSubject() {  
            return "love";  
        }  
    }  


}
