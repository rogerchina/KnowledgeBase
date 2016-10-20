package com.debuglife.codelabs.designpattern.structurepattern_1_06.adapter;

/**
 * 类的适配器模式
 * 核心思想就是：有一个Source类，拥有一个方法，待适配，目标接口是Targetable，通过Adapter类，将Source的功能扩展到Targetable里
 * @author roger.zhang
 *
 */
public class TestClassAdapter {

	public static void main(String[] args) {
		TestClassAdapter t = new TestClassAdapter();
		
		Targetable target = t.new Adapter();
		target.method1();
		target.method2();
	}

	private class Source {
		public void method1() {
			System.out.println("i am source method");
		}
	}
	
	private interface Targetable {
		
		// same as with Source class
		public void method1();
		// in the new class
		public void method2();
	}
	
	private class Adapter extends Source implements Targetable {

		@Override
		public void method2() {
			System.out.println("i am targetable method");
		}
		
	}
}
