package com.debuglife.codelabs.crazyit.chapter16;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest2 {
	public static void main(String[] args) throws Exception{
		BlockingQueue<String> blkQueue = new ArrayBlockingQueue<>(1);
		new Producer("A", blkQueue).start();
		new Producer("B", blkQueue).start();
		new Producer("C", blkQueue).start();
		new Consumer("D", blkQueue).start();
	}
}

class Producer extends Thread{
	private BlockingQueue<String> queue;
	
	public Producer(String name, BlockingQueue<String> queue){
		super(name);
		this.queue = queue;
	}
	
	@Override
	public void run() {
		String[] strArr = new String[]{
				"Java",
				"Struts",
				"Spring"
		};
		
		for(int i=0; i<999999999; i++){
			System.out.println("The producer [" + this.getName() + "] is ready to produce elements into queue.");
			try {
				Thread.sleep(200);
				queue.put(strArr[i % 3]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("The producer [" + this.getName() + "] has finished element operation.");
		}
	}
}

class Consumer extends Thread{
	private BlockingQueue<String> queue;
	
	public Consumer(String name, BlockingQueue<String> queue){
		super(name);
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(true){
			System.out.println("The consumer [" + this.getName() + "] is ready to consume element from queue");
			try {
				Thread.sleep(200);
				queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("The consumer [" + this.getName() + "] has consumed one element from queue.");
		}
	}
}
