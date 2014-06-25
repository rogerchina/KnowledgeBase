package com.debuglife.codelabs;

public class TestInnerClass {
	private int i = 1;
	public static void main(String [] args){
		// how to use inner class in the static method.
		TestInnerClass tic = new TestInnerClass();
		TestInnerClass.Cat cat = tic.new Cat("mimi");
		cat.sayItselfName();
		
		tic.method();
		
		//method1();
	}
	
	public void method(){
		Cat cat = new Cat("xixi");
		cat.sayItselfName();
	}

	//inner class
	class Cat{
		private String name;
		
		public Cat(String name){
			this.name = name;
		}
		
		public void sayItselfName(){
			System.out.println(name);
			System.out.println(i);
		}
	}
}