package com.hp.it.techlab;

public class TestDeadLock implements Runnable{
	public int flag = 1;
	public static Object o1 = new Object();
	public static Object o2 = new Object();
	
	public void run(){
		System.out.println("flag = " + flag);
		if(flag == 1){
			synchronized(o1){
				try{
					Thread.sleep(500);
				}catch(InterruptedException e){
					
				}
				
				synchronized(o2){
					System.out.println("1");
				}
			}
			

		}
		if(flag == 0){
			synchronized(o2){
				try{
					Thread.sleep(500);
				}catch(InterruptedException e){
					
				}
				
				synchronized(o1){
					System.out.println("2");
				}
			}
			

		}
	}
	
	public static void main(String[] args){
		TestDeadLock test = new TestDeadLock();
		test.flag = 1;
		Thread t1 = new Thread(test);
		
		TestDeadLock test1 = new TestDeadLock();
		test1.flag = 0;
		Thread t2 = new Thread(test1);
		
		t1.start();
		t2.start();
	}
}
