package com.debuglife.codelabs.corejava;

public class TestReturnInTryCatch {
	private static int i = 1;
	
	public static void main(String[] args){
		int i = returnValue();
		System.out.println("returnValue i=" + i);
	}
	
	private static int returnValue(){
		try{
			//throw new Exception();
			int j = 0;
			//int a = i/j;
			return ++i;
		} catch(Exception e){
			return ++i;
		} finally{
			++i;
			System.out.println("finally block i=" + i);
			//return i;
		}
	}
}
