package com.hp.it.techlab;

public class TestLock implements Runnable{
	private int b = 100;
	
	public void run(){
		try{
			m1();
		}catch(Exception e){
			
		}
	}
	
	public synchronized void m1() throws Exception{
		b = 1000;
		Thread.sleep(5000);
		System.out.println("b=" + b);
	}
	
	public void m2(){
		System.out.println(b);
	}
	
	public static void main(String[] args) throws Exception{
		TestLock test = new TestLock();
		Thread t = new Thread(test);
		t.start();
		
		Thread.sleep(1000);
		test.m2();
	}
}



