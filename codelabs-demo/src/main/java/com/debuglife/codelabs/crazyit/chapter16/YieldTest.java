package com.debuglife.codelabs.crazyit.chapter16;

public class YieldTest extends Thread {
	
	public YieldTest(String name){
		super(name);
	}
	
	@Override
	public void run() {
		for(int i=0; i<50; i++){
			System.out.println(Thread.currentThread().getName() + " " + i);
			if(i == 20){
				Thread.yield();
			}
		}
	}
	
	public static void main(String[] args){
		YieldTest yt1 = new YieldTest("YieldTest-1");
		yt1.setPriority(Thread.MAX_PRIORITY);
		yt1.start();
		
		YieldTest yt2 = new YieldTest("YieldTest-2");
		yt2.setPriority(Thread.MIN_PRIORITY);
		yt2.start();
	}
}
