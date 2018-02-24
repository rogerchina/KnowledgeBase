package com.debuglife.codelabs.corejava;
public class TestDefault {
	public static void main(String[] args){
		
	}
	
	interface X {
		void test();
		default void test1() {}
	}
}
