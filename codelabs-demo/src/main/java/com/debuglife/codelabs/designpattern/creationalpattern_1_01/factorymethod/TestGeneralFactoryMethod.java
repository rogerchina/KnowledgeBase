package com.debuglife.codelabs.designpattern.creationalpattern_1_01.factorymethod;

public class TestGeneralFactoryMethod {
	public static void main(String[] args) {
		
		TestGeneralFactoryMethod t = new TestGeneralFactoryMethod();
		SenderFactory sf = t.new SenderFactory();
		sf.produce("mail").send();
		sf.produce("sms").send();
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
		
		public Sender produce(String type) {
			if("mail".equals(type)) {
				return new MailSender();
			} else if("sms".equals(type)) {
				return new SMSSender();
			}
			return null;
		}
	}
}









