package com.hp.it.techlab;

public class TestVolatile {
	public static void main(String[] args) throws InterruptedException{
		Thread[] threads = new Thread[100];
		for(int i=0;i<threads.length;i++)
			threads[i] = new MyThread();
		
		for(int i=0;i<threads.length;i++)
			threads[i].start();
		
		for(int i=0;i<threads.length;i++)
			threads[i].join();
		
		/*
		 * 如果对n的操作是原子级别的，最后输出的结果应该为n=1000，而在执行上面代码时，很多时侯输出的n都小于1000，
		 * 这说明n=n+1不是原子级别的操作。原因是声明为volatile的简单变量如果当前值由该变量以前的值相关，
		 * 那么volatile关键字不起作用，也就是说如下的表达式都不是原子操作： 
		 * n  =  n  +   1 ; 
		 * n ++ ; 
		 * 如果要想使这种情况变成原子操作，需要使用synchronized关键字.
		 */
		System.out.println(MyThread.n);
		System.out.println(MyThread.m);
	}
}

class MyThread extends Thread{
	//case 1
	public static volatile int n = 0;
	
	//case 2
	public static int m = 0;
	public static synchronized void increment(){
		m++;
	}
	
	public void run(){
		for(int i=0;i<10;i++){
			try{
				n = n + 1; //此操作并非具有原子性，底层实际由多个步骤组成
				increment();
				sleep(4);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}