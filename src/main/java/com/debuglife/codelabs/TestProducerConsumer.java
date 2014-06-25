package com.debuglife.codelabs;

public class TestProducerConsumer {
	
	public static void main(String[] args){
		SyncStack ss = new SyncStack();
		
		Producer p = new Producer(ss);
		Consumer c = new Consumer(ss);
		
		new Thread(p).start();
		new Thread(c).start();
	}
}

class Apple{
	int id;
	Apple(int id){
		this.id = id;
	}
	
	public String toString(){
		return "Apple" + id;
	}
}

class SyncStack{
	static int index = 0;
	Apple[] arr = new Apple[10];
	
	public synchronized void push(Apple apple){
		while(index == arr.length){
			try{
				this.wait();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		this.notify();
		System.out.println("the current thread--" + Thread.currentThread().getName() + "has execute notify() method");
		arr[index] = apple;
		index++;
	}
	
	public synchronized Apple pop(){
		while(index == 0){
			try{
				this.wait();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		this.notify();
		System.out.println("the current thread--" + Thread.currentThread().getName() + "has execute notify() method");
		index--;
		return arr[index];
	}
}

class Producer implements Runnable{
	SyncStack ss = null;
	public Producer(SyncStack ss){
		this.ss = ss;
	}
	public void run(){
		for(int i=0;i<20;i++){
			Apple apple = new Apple(i);
			ss.push(apple);
			System.out.println("produce " + apple);
		}
	}
}

class Consumer implements Runnable{
	SyncStack ss = null;
	public Consumer(SyncStack ss){
		this.ss = ss;
	}
	public void run(){
		for(int i=0;i<20;i++){
			Apple apple = ss.pop();
			System.out.println("consume " + apple);
		}
	}
}





