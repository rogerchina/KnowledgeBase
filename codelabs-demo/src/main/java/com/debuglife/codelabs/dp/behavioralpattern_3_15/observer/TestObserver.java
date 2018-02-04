package com.debuglife.codelabs.dp.behavioralpattern_3_15.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 这些东西，其实不难，只是有些抽象，不太容易整体理解，建议读者：根据关系图，新建项目，自己写代码,
 * 按照总体思路走一遍，这样才能体会它的思想，理解起来容易！
 * @author roger.zhang
 *
 */
public class TestObserver {

	public static void main(String[] args) {
		TestObserver t = new TestObserver();
		
		Subject sub = t.new MySubject();  
        sub.add(t.new Observer1());  
        sub.add(t.new Observer2());  
          
        sub.operation();  

	}

	private interface Observer {  
	    public void update();  
	} 
	
	private class Observer1 implements Observer {  
		  
	    @Override  
	    public void update() {  
	        System.out.println("observer1 has received!");  
	    }  
	}
	
	private class Observer2 implements Observer {  
		  
	    @Override  
	    public void update() {  
	        System.out.println("observer2 has received!");  
	    }  
	  
	}  
	
	private interface Subject {  
	      
	    /*增加观察者*/  
	    public void add(Observer observer);  
	      
	    /*删除观察者*/  
	    public void del(Observer observer);  
	      
	    /*通知所有的观察者*/  
	    public void notifyObservers();  
	      
	    /*自身的操作*/  
	    public void operation();  
	}  
	
	private abstract class AbstractSubject implements Subject {  
		  
	    private Vector<Observer> vector = new Vector<Observer>();  
	    @Override  
	    public void add(Observer observer) {  
	        vector.add(observer);  
	    }  
	  
	    @Override  
	    public void del(Observer observer) {  
	        vector.remove(observer);  
	    }  
	  
	    @Override  
	    public void notifyObservers() {  
	        Enumeration<Observer> enumo = vector.elements();  
	        while(enumo.hasMoreElements()){  
	            enumo.nextElement().update();  
	        }  
	    }  
	}  
	
	private class MySubject extends AbstractSubject {  
		  
	    @Override  
	    public void operation() {  
	        System.out.println("update self!");  
	        notifyObservers();  
	    }  
	  
	}  
}
