package com.debuglife.codelabs.dp.creationalpattern_2_02.abstractfactory;

public class TestAbstractFactory {

	public static void main(String[] args) {
		TestAbstractFactory t = new TestAbstractFactory();
		
		MailSenderFactory msf = t.new MailSenderFactory();
		msf.produce().send();
		
		SMSSenderFactory ssf = t.new SMSSenderFactory();
		ssf.produce().send();
		
		LightSenderFactory lsf = t.new LightSenderFactory();
		lsf.produce().send();
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
	
	private class LightSender implements Sender {

		@Override
		public void send() {
			System.out.println("i am LightSender...");
		}
		
	}
	
	private interface Provider {
		Sender produce();
	}
	
	private class MailSenderFactory implements Provider {

		@Override
		public Sender produce() {
			return new MailSender();
		}
		
	}
	
	private class SMSSenderFactory implements Provider {

		@Override
		public Sender produce() {
			return new SMSSender();
		}
		
	}
	
	private class LightSenderFactory implements Provider {

		@Override
		public Sender produce() {
			return new LightSender();
		}
	}
	
}







