package com.debuglife.codelabs.crazyit.chapter16;

public class InvokeRun extends Thread {
	@Override
	public void run() {
		for(int i=0; i<100; i++){
			System.out.println("The thread--" + Thread.currentThread().getName() + "--" + i + " is running.");
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("when in the run(), the thread's state is" + this.getState().name());
	}
	
	public static void main(String[] args) throws Exception{
		for(int i=0; i<100; i++){
			System.out.println("The thread--" + Thread.currentThread().getName() + i + " is running.");
			if(i == 20){
				InvokeRun t = new InvokeRun(); 
				System.out.println("after new Thread(...) and before invoking start(...), the thread's state is " + t.getState().name());
				
				t.start();
				System.out.println("after invoking start(...), the thread's state is " + t.getState().name());
				Thread.sleep(2000);
				System.out.println("after finishing run(), the thread's state is " + t.getState().name());
//				t.start();
			}
		}
	}
}
