package com.debuglife.codelabs.designpattern.buildpattern_1_01.factorymethod;

public class TestStaticFactoryMethod {

	public static void main(String[] args) {
		Sender sender1 = SenderFactory.produceMailSender();
		
		Sender sender2 = SenderFactory.produceMailSender();
	}
	
	private interface Sender {
		void send();
	}

	private static class MailSender implements Sender {

		@Override
		public void send() {
			System.out.println("i am MailSender...");
		}
		
	}

	private static class SMSSender implements Sender {

		@Override
		public void send() {
			System.out.println("i am SMSSender...");
		}
		
	}

	private static class SenderFactory {
		
		public static MailSender produceMailSender() {
			return new MailSender();
		}
		
		public static SMSSender produceSMSSender() {
			return new SMSSender();
		}
	}

}
