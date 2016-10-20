package com.debuglife.codelabs.designpattern.structuralpattern_1_06.adapter;

/**
 * 基本思路和类的适配器模式相同，只是将Adapter类作修改，这次不继承Source类，而是持有Source类的实例，以达到解决兼容性的问题
 * @author roger.zhang
 *
 */
public class TestInstanceAdapter {

	public static void main(String[] args) {
		TestInstanceAdapter t = new TestInstanceAdapter();
		
		Source source = t.new Source();
		Targetable target = t.new Adapter(source);
		target.method1();
		target.method2();
		
	}
	
	private class Source {
		public void method1(){
			System.out.println("i am Source method.");
		}
	}
	
	private interface Targetable {
		
		// same as with Source class
		public void method1();
		// in the new class
		public void method2();
	}
	
	private class Adapter implements Targetable {
		
		private Source source;
		
		public Adapter(Source source) {
			super();
			this.source = source;
		}

		@Override
		public void method1() {
			source.method1();
		}

		@Override
		public void method2() {
			System.out.println("i am targetable method.");
		}
		
	}

}
