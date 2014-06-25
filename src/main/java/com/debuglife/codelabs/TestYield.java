package com.debuglife.codelabs;


public class TestYield {
	public static void main(String[] args){
		Runner2 r1 = new Runner2("r1");
		Runner2 r2 = new Runner2("r2");
		r1.start();
		r2.start();
	}
}

class Runner2 extends Thread{
	public Runner2(String name){
		super(name);
	}
	public void run(){
		System.out.println(Thread.currentThread().isAlive());
		for(int i=0; i<10; i++){
			System.out.println(getName() + "--"+ i);
			if(i % 10 == 0)
				yield();
		}
	}
}