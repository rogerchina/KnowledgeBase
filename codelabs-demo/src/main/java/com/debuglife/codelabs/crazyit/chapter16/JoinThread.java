package com.debuglife.codelabs.crazyit.chapter16;

public class JoinThread extends Thread {
	@Override
	public void run() {
		for(int i=0; i<100; i++){
			System.out.println(getName() + i);
		}
	}
	
	public static void main(String[] args) throws Exception{
		for(int i=0; i<100; i++){
			System.out.println(Thread.currentThread().getName() + i);
			if(i == 20){
				JoinThread t = new JoinThread();
				t.start();
				t.join();
			}
		}
	}
}
