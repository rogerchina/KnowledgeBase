package com.hp.it.techlab;

public class TestInnerClass {
	public static void main(String [] args){
		// how to use inner class in the static method.
		TestInnerClass tic = new TestInnerClass();
		TestInnerClass.Cat cat = tic.new Cat("mimi");
		cat.sayHello();
	}
	
	class Cat{
		private String name;
		
		public Cat(String name){
			this.name = name;
		}
		
		public void sayHello(){
			System.out.println("hello");
		}
	}
}
