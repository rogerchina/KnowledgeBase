package com.debuglife.codelabs;

public class TestJavaFoundamental {
	public static void main(String[] args){
		testByte();
		
	}
	
	private static void testByte(){
		//number of binary bits divide by 8
		System.out.println(Byte.SIZE/8); 
		
		byte b = 1;
		Byte bC = new Byte(b);
		System.out.println(bC);
		//convert to string and then call length() method
		System.out.println(bC.toString().length()); 
		
		String byteStr = "127";
		Byte bC1 = new Byte(byteStr);
		System.out.println(bC1);
		System.out.println(bC1.byteValue());
	}
}
