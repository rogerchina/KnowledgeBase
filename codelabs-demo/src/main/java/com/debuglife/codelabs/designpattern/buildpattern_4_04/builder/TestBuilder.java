package com.debuglife.codelabs.designpattern.buildpattern_4_04.builder;

import java.util.ArrayList;
import java.util.List;


public class TestBuilder {

	public static void main(String[] args) {
		TestBuilder tb = new TestBuilder();
		
		Builder builder = tb.new Builder();
		builder.produceMailSender(10);
		
		builder.produceSMSSender(10);

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
	
	private class Builder {
		private List<Sender> list = new ArrayList<>();
		
		public void produceMailSender(int num) {
			for(int i=0; i<num; i++) {
				list.add(new MailSender());
			}
		}
		
		public void produceSMSSender(int num) {
			for(int i=0; i<num; i++) {
				list.add(new SMSSender());
			}
		}
		
	}
	
}








