package com.debuglife.codelabs.crazyit.chapter18;

import java.lang.reflect.Array;

public class CrazyitArray {
	public static void main(String[] args){
		// need implicitly cast from Object to String[];
		// should use Generic 
		String[] strArr = (String[])Array.newInstance(String.class, 10);
		
		String[] strs = getArrayInstance(String.class, 5);
		strs[0] = "wo ai bei jing tian an men";
		System.out.println(strs[0]);
		
		int[][] intArray = getArrayInstance(int[].class, 5);
		intArray[1] = new int[]{23,12};
		System.out.println(intArray[1][1]);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] getArrayInstance(Class<T> componentType, int length){
		return (T[])Array.newInstance(componentType, length);
	}
}
