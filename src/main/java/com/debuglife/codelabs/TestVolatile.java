package com.debuglife.codelabs;

public class TestVolatile {
	public static void main(String[] args) throws InterruptedException{
		Thread[] threads = new Thread[100];
		for(int i=0;i<threads.length;i++)
			threads[i] = new MyThread();
		
		for(int i=0;i<threads.length;i++)
			threads[i].start();
		
		for(int i=0;i<threads.length;i++)
			threads[i].join();
		
		System.out.println(MyThread.n);
		System.out.println(MyThread.m);
	}
}

class MyThread extends Thread{
	//case 1
	public static volatile int n = 0;
	
	//case 2
	public static int m = 0;
	public static synchronized void increment(){
		m++;
	}
	
	public void run(){
		for(int i=0;i<10;i++){
			try{
				n = n + 1; 
				increment();
				sleep(4);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}