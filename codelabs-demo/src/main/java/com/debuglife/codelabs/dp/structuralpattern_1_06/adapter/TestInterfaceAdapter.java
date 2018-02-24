package com.debuglife.codelabs.dp.structuralpattern_1_06.adapter;

/**
 * 接口的适配器是这样的：有时我们写的一个接口中有多个抽象方法，当我们写该接口的实现类时，
 * 必须实现该接口的所有方法，这明显有时比较浪费，因为并不是所有的方法都是我们需要的，
 * 有时只需要某一些，此处为了解决这个问题，我们引入了接口的适配器模式，借助于一个抽象类，
 * 该抽象类实现了该接口，实现了所有的方法，而我们不和原始的接口打交道，只和该抽象类取得联系，
 * 所以我们写一个类，继承该抽象类，重写我们需要的方法就行.
 * @author roger.zhang
 *
 */
public class TestInterfaceAdapter {

	public static void main(String[] args) {
		TestInterfaceAdapter t = new TestInterfaceAdapter();
		
		Sourceable s1 = t.new SourceSub1();
		Sourceable s2 = t.new SourceSub2();
		
		s1.method1();
		s1.method2();
		
		s2.method1();
		s2.method2();
	}
	
	private interface Sourceable {
		public void method1();
		public void method2();
	}
	
	private abstract class Wrapper implements Sourceable {
		public void method1(){};
		public void method2(){};
	}
	
	private class SourceSub1 extends Wrapper {
		public void method1() {
			System.out.println("i am method1");
		}
	}
	
	private class SourceSub2 extends Wrapper {
		public void method2() {
			System.out.println("i am method2");
		}
	}
}
