package com.debuglife.codelabs.designpattern.creationalpattern_1_01.factorymethod;


public class TestMultipleFactoryMethod {

	public static void main(String[] args) {
		TestMultipleFactoryMethod t = new TestMultipleFactoryMethod();
		
		SenderFactory sf = t.new SenderFactory();
		sf.produceMailSender().send();
		sf.produceSMSSender().send();
		
	}
	
	private interface Sender {
		void send();
	}

	private class MailSender implements Sender {

		@Override
		public void send() {
			System.out.println("i am MailSender...");
		}
		
	}

	private class SMSSender implements Sender {

		@Override
		public void send() {
			System.out.println("i am SMSSender...");
		}
		
	}

	private class SenderFactory {
		
		public MailSender produceMailSender() {
			return new MailSender();
		}
		
		public SMSSender produceSMSSender() {
			return new SMSSender();
		}
	}

}
