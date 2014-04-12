package com.hp.it.techlab;

public class Test extends TestInnerClass{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Cat c = new Cat();
//		TestInnerClass tic = new TestInnerClass();
//		TestInnerClass.Cat cat = tic.new Cat("main");
//		cat.sayItselfName(); 
		
		Test t = new Test();
		Test.Cat cat = t.new Cat("child class");
		cat.sayItselfName();
	}

}

