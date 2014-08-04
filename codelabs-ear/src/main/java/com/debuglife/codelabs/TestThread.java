package com.debuglife.codelabs;

public class TestThread {
	public static void main(String[] args){
		Runner runner = new Runner();
		Thread t = new Thread(runner);
		t.start();
		
		for(int i=0; i<1000; i++)
			System.out.println("MainThread--" + i);
	}
}

class Runner implements Runnable{
	public void run(){
		for(int i=0; i<1000; i++){
			System.out.println("Thread--Runner--" + i);
		}
	}
}
