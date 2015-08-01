package com.debuglife.codelabs.crazyit.chapter16;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
	public static void main(String[] args){
		ExecutorService pool = Executors.newFixedThreadPool(6);
		pool.submit(new MyTestThread());
		pool.submit(new MyTestThread());
//		pool.shutdown();
		pool.shutdownNow();
	}
}

class MyTestThread implements Runnable{

	@Override
	public void run() {
		for(int i=0; i<100000000; i++){
			System.out.println(Thread.currentThread().getName() + " i=" + i);
		}
	}
	
}
