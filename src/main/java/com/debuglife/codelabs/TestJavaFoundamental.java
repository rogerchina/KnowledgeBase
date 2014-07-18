package com.debuglife.codelabs;

import java.lang.reflect.Field;

public class TestJavaFoundamental {
    private int i = 0;
    private String s = "";
    
	public static void main(String[] args){
		//testByte();
	    testByteField();
		//testBoolean();
	    //testGetBoolean();
	    //testParseBoolean();
	    //testBooleanField();
	}
	
	private static void testByteField(){
	    System.out.println(Byte.MAX_VALUE);
	    System.out.println(Byte.MIN_VALUE);
	    System.out.println(Byte.SIZE);
	}
	
	private static void testBooleanField(){
	    Boolean b = Boolean.TRUE;
	    Boolean.TRUE.booleanValue();
	    
	    Class<Boolean> c = Boolean.TYPE;
	    System.out.println(c.getSimpleName());
	    System.out.println(c.getName());
	}
	
	private static void testValueOf(){
	    Boolean b = Boolean.valueOf(true);
	    Boolean b1 = Boolean.valueOf("true");
	}
	
	private static void testParseBoolean(){
	    System.out.println(Boolean.parseBoolean("True"));
	    System.out.println(Boolean.parseBoolean("yes"));
	}
	
	private static void testGetBoolean(){
	    System.out.println(Boolean.getBoolean("king"));
	    System.setProperty("king", "true");
	    System.out.println(Boolean.getBoolean("king"));
	    
	}
	
	private static void testBoolean(){
	    Boolean b = new Boolean(true);
	    System.out.println(b);
	    System.out.println(b.booleanValue());
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
