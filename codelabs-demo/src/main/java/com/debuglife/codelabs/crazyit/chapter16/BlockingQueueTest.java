package com.debuglife.codelabs.crazyit.chapter16;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) throws Exception{
		BlockingQueue<String> blkQueue = new ArrayBlockingQueue<>(2);
		
		// when queue is full
//		blkQueue.put("java");
//		blkQueue.put("java");
//		blkQueue.put("java");	//block thread
//		System.out.println(blkQueue.add("java"));	//throws exception
//		System.out.println(blkQueue.offer("java")); // return false
		
		// when queue is empty
//		blkQueue.take();
//		blkQueue.remove();
		System.out.println(blkQueue.poll());
	}
}
