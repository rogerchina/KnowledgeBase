package com.debuglife.codelabs.crazyit.chapter18;

import java.lang.reflect.Array;

public class ArrayTest1 {
	public static void main(String[] args){
		Object arr = Array.newInstance(String.class, 10);
		Array.set(arr, 5, "Hello");
		Array.set(arr, 6, "World");
		System.out.println(Array.get(arr, 5));
		System.out.println(Array.get(arr, 6));
		
		String[] strs = new String[1];
		System.out.println(strs.getClass());
		System.out.println(String[].class);
	}
}
