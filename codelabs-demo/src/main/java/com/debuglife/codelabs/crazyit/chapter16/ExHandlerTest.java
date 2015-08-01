package com.debuglife.codelabs.crazyit.chapter16;

public class ExHandlerTest {
	public static void main(String[] args){
//		Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());
//		int a = 5 / 0;
//		System.out.println("The program terminates.");
		
		try {
			ErrorThread et = new ErrorThread();
			et.setUncaughtExceptionHandler(new MyExHandler());
			et.start();
		} catch (Exception e) {
			System.out.println("caught it, hah...");
			e.printStackTrace();
		}
	}
}

class ErrorThread extends Thread{
	@Override
	public void run() {
		int i = 5 / 0;
	}
}

class MyExHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
//		System.err.println(t.getName() + " thread has exception: " + e);
		throw new RuntimeException();
	}
	
}
