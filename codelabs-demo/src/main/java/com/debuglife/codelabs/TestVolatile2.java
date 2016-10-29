package com.debuglife.codelabs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * Java中的原子操作包括：
 * 1）除long和double之外的基本类型的赋值操作
 * 2）所有引用reference的赋值操作
 * 3）java.concurrent.Atomic.* 包中所有类的一切操作
 * 
 * count++不是原子操作，是3个原子操作组合
 * 1.读取主存中的count值，赋值给一个局部成员变量tmp
 * 2.tmp+1
 * 3.将tmp赋值给count
 * 可能会出现线程1运行到第2步的时候，tmp值为1；这时CPU调度切换到线程2执行完毕，count值为1；
 * 切换到线程1，继续执行第3步，count被赋值为1------------结果就是两个线程执行完毕，count的值只加了1；
 * 还有一点要注意，如果使用AtomicInteger.set(AtomicInteger.get() + 1)，会和上述情况一样有并发问题，
 * 要使用AtomicInteger.getAndIncrement()才可以避免并发问题.
 *
 */
public class TestVolatile2 {
	public static void main(String[] args) throws Exception{
		
		for(int i=0; i<1000; i++) {
			//new Thread(() -> Counter.increase()).start();
			new Thread(() -> RightCounter.inc()).start();
		}
		
		Thread.sleep(2000);
		System.out.println(RightCounter.count.get());
	}
	
}
//有问题
class Counter {
	//whether or not volatile exists, result is all uncertain.
	
	public static int number = 0;
	//public volatile static int number = 0;
	
	public static void increase() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// number++; // value reference by thread
		Counter.number++;
	}
}

//正确
class RightCounter {
	public static AtomicInteger count = new AtomicInteger(0);
    public static void inc() {
        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
        count.getAndIncrement();
    }
}

class VolatileCache {
    private static final int threadQuantity = 1000;
    public static CountDownLatch startLatch = new CountDownLatch(threadQuantity);
    public static CountDownLatch endLatch = new CountDownLatch(threadQuantity);
    public volatile static int count = 0;
 
    public static void inc() {
        count++;
    }
 
    public static void test(String[] args) {
        //同时启动1000个线程，去进行i++计算，看看实际结果
        for (int i = 0; i < threadQuantity; i++) {
            new Thread(new Runnable() {
                public void run() {
                    String name = Thread.currentThread().getName();
                    try {
                        startLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int got = VolatileCache.count;
                    VolatileCache.count = got + 1;
                    System.out.println(got + ",->," + VolatileCache.count + "," + name);
                    endLatch.countDown();
                }
            }, "Thread-" + i).start();
            startLatch.countDown();
        }
        try {
            System.out.println("Waiting Work Done");
            endLatch.await();
            System.out.println("运行结果:Counter.count=" + VolatileCache.count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Counter4 {
    public static AtomicInteger count = new AtomicInteger(0);
  
    public static void inc() {
        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
        count.getAndIncrement();
    }
  
    public static void test(String[] args) throws InterruptedException {
  
        //同时启动1000个线程，去进行i++计算，看看实际结果
        int N = 100000;
        ExecutorService pool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < N; i++) {
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    Counter4.inc();
                }
            };
            pool.submit(runnable);
        }
         
        pool.shutdown();
        pool.awaitTermination(50, TimeUnit.SECONDS);
         
        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + Counter4.count);
    }
}
