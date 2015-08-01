package com.debuglife.codelabs.crazyit.chapter16;

public class MyThread extends Thread {
	public MyThread(String name){
		super(name);
	}
	
	public MyThread(ThreadGroup group, String name){
		super(group, name);
	}
	
	@Override
	public void run() {
		for(int i=0; i<50; i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(getName() + " thread's variable i " + i);
		}
	}
	
	public static void main(String[] args){
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		System.out.println(mainGroup.getName());
		System.out.println(mainGroup.isDaemon());
		
		Thread t1 = new MyThread("[" + mainGroup.getName() + "] group thread-1");
		System.out.println(t1.getThreadGroup().getName());
		t1.start();
		
		ThreadGroup tg = new ThreadGroup("new Thread Group");
		tg.setDaemon(true);
		System.out.println(tg.isDaemon());
		MyThread t2 = new MyThread(tg, "[tg] group thread-2");
		t2.start();
		new MyThread(tg, "[tg] group thread-3").start();
	}
}
