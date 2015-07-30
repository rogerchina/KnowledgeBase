package com.debuglife.codelabs.crazyit.chapter16;

public class DeamonThread extends Thread {
	@Override
	public void run() {
		Thread t = new Thread();
		System.out.println("is sub-thread daemon thread? :" + t.isDaemon());
		
		for(int i=0; i<1000; i++){
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}
	
	public static void main(String[] args){
		DeamonThread dt = new DeamonThread();
		dt.setDaemon(true);
		dt.start();
		
		for(int i=0; i<10; i++){
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}
}
