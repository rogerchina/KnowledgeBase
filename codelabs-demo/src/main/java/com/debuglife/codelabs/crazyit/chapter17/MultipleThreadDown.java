package com.debuglife.codelabs.crazyit.chapter17;

public class MultipleThreadDown {
	public static void main(String[] args) throws Exception{
		final DownUtil downUtil = new DownUtil("http://dldir1.qq.com/qqfile/qq/QQ7.4/15179/QQ7.4.exe","qq7.4.exe",4);
		downUtil.download();
		
		new Thread(){
			@Override
			public void run() {
				while(downUtil.getCompleteRate() < 1){
					System.out.println("have completed of " + downUtil.getCompleteRate() * 100 + "%");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
