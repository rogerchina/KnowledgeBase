package com.debuglife.codelabs.corejava;

import java.util.ArrayList;
import java.util.List;

public class TestGC {
	public static void main(String[] args) throws Exception{
		List<BigContainer> list = new ArrayList<>();
		
		Thread.sleep(30 * 1000);
		
		for(int i=0; i<100000; i++){
			list.add(new BigContainer());
			System.out.println("created BigContainer - " + i);
		}
		
		for(int i=0; i<list.size(); i++){
			list.set(i, null);
		}
		
		while(true){
		}
	}
}

class BigContainer{
	private long [] longByteArray = new long[99999];
	
	public BigContainer(){
		initArray();
	}
	
	public void initArray(){
		for(int i=0; i<longByteArray.length; i++){
			longByteArray[i] = 999999999;
		}
	}
}
