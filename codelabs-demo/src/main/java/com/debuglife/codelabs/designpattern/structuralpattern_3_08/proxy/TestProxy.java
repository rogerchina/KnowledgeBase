package com.debuglife.codelabs.designpattern.structuralpattern_3_08.proxy;

public class TestProxy {

	public static void main(String[] args) {
		TestProxy t = new TestProxy();
		
		Source source = t.new Source();
		Sourceable decorator = t.new Proxy(source);
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
	
	private class Proxy implements Sourceable {

		private Source source;
		
		public Proxy(Source source) {
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
