package com.debuglife.codelabs;

public class TestStaticInnerClass {
	private static int si = 10;
	int i = 20;
	static class InnerClass1{
		public void getValue1(){
			si++;
			//i++;
			System.out.println(si);
		}
	}
	
	class InnerClass2{
		static final int i = 1;
		int sk = 2;
		void method(){
			
		}
	}
}
