package com.hp.it.techlab;

import java.util.Date;

public class TestInterrupted {
	public static void main(String[] args){
		Runner1 r = new Runner1();
		r.start();
		
		try{
			Thread.sleep(1000);
			//r.join();
		}catch(InterruptedException e){
			//TODO
		}
		
		for(int i=0; i<10; i++)
			System.out.println("Main Thread is running--" + i);
	}
}

class Runner1 extends Thread{
	public void run(){
		int i=0;
		while(i<10){
			System.out.println("runner is running--" + new Date());
			try{
				sleep(1000);
			}catch(InterruptedException e){
				return;
			}
			i++;
		}
	}
}