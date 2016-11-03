package com.debuglife.codelabs.corejava;

public class TestLock implements Runnable{
	private int b = 100;
	
	public void run(){
		try{
			System.out.println("run() child thread name锛�" + Thread.currentThread().getName());
			m1();
		}catch(Exception e){
			
		}
	}
	
	public synchronized void m1() throws Exception{
		b = 1000;
		Thread.sleep(5000);
		System.out.println("m1() b=" + b);
	}
	
	public /*synchronized*/ void m2(){
		System.out.println("m2() b=" + b);
	}
	
	public static void main(String[] args) throws Exception{
		TestLock test = new TestLock();
		Thread t = new Thread(test,"haha");
		t.start();
		
		Thread.sleep(1000);
		test.m2();
		
		System.out.println("main() main thread name锛�" + Thread.currentThread().getName());
	}
}



