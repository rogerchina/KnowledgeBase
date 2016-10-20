package com.debuglife.codelabs.designpattern.structurepattern_2_07.decorator;

public class TestDecorator {

	public static void main(String[] args) {
		TestDecorator t = new TestDecorator();
		
		Source source = t.new Source();
		Sourceable decorator = t.new Decorator(source);
		decorator.method();
	}

	private interface Sourceable {  
	    public void method();  
	}
	
	private class Source implements Sourceable {

		@Override
		public void method() {
			System.out.println("i am original method.");
		}
		
	}
	
	private class Decorator implements Sourceable {

		private Sourceable source;
		
		public Decorator(Sourceable source) {
			this.source = source;
		}
		
		@Override
		public void method() {
			
			//TODO do action before decorator
			beforeMethod();
			
			source.method();
			
			//TODO do action after decorator
			afterMethod();
		}
		
		private void beforeMethod() {
			System.out.println("do something before decorator...");
		}
		
		private void afterMethod() {
			System.out.println("do something after decorator...");
		}
		
	}
}
