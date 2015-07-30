package com.debuglife.codelabs.crazyit.chapter16;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyThirdThread implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int i = 0;
		for(; i<100; i++){
			System.out.println("The thread [" + Thread.currentThread().getName() + "] value of variable i is " + i);
		}
		return i;
	}

	public static void main(String[] args){
		MyThirdThread mtt = new MyThirdThread();
		FutureTask<Integer> task = new FutureTask<Integer>(mtt);
		for(int i=0; i<100; i++){
			System.out.println("The thread [" + Thread.currentThread().getName() + "] value of variable i is " + i);
			if(i == 20){
				new Thread(task, "Thread with returned value").start();
			}
		}
		
		try {
			System.out.println("the child thread's returned value is " + task.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
