package com.debuglife.codelabs.crazyit.chapter16;

public class SleepTest extends Thread {
	public static void main(String[] args) throws Exception{
		for(int i=0; i<10; i++){
			System.out.println(Thread.currentThread().getName() + " " + i);
			Thread.sleep(1000);
		}
	}
}
