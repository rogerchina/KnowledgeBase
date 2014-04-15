package com.hp.it.techlab;

public class TestReturnInTryCatch {
	private static int i = 1;
	
	public static void main(String[] args){
		int i = returnValue();
		System.out.println(i);
	}
	
	private static int returnValue(){
		try{
			throw new Exception();
		} catch(Exception e){
			return ++i;
		} finally{
			++i;
			System.out.println("returnValue method" + i);
			return i;
		}
	}
}
