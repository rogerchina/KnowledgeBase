package com.debuglife.codelabs;

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
		 * 濡傛灉瀵筺鐨勬搷浣滄槸鍘熷瓙绾у埆鐨勶紝鏈�悗杈撳嚭鐨勭粨鏋滃簲璇ヤ负n=1000锛岃�鍦ㄦ墽琛屼笂闈唬鐮佹椂锛屽緢澶氭椂渚緭鍑虹殑n閮藉皬浜�000锛�
		 * 杩欒鏄巒=n+1涓嶆槸鍘熷瓙绾у埆鐨勬搷浣溿�鍘熷洜鏄０鏄庝负volatile鐨勭畝鍗曞彉閲忓鏋滃綋鍓嶅�鐢辫鍙橀噺浠ュ墠鐨勫�鐩稿叧锛�
		 * 閭ｄ箞volatile鍏抽敭瀛椾笉璧蜂綔鐢紝涔熷氨鏄濡備笅鐨勮〃杈惧紡閮戒笉鏄師瀛愭搷浣滐細 
		 * n  =  n  +   1 ; 
		 * n ++ ; 
		 * 濡傛灉瑕佹兂浣胯繖绉嶆儏鍐靛彉鎴愬師瀛愭搷浣滐紝闇�浣跨敤synchronized鍏抽敭瀛�
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
				n = n + 1; //姝ゆ搷浣滃苟闈炲叿鏈夊師瀛愭�锛屽簳灞傚疄闄呯敱澶氫釜姝ラ缁勬垚
				increment();
				sleep(4);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}