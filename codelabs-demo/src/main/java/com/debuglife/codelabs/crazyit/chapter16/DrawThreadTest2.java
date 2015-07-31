package com.debuglife.codelabs.crazyit.chapter16;

public class DrawThreadTest2 extends Thread {
	
	private Account2 account;
	private double money;
	private static Object lock = new Object();
	
	public DrawThreadTest2(String name, Account2 account, double money){
		super(name);
		this.account = account;
		this.money = money;
	}
	
	public static void main(String[] args){
		Account2 account = new Account2("888-8888-88", 2000.0);
		new DrawThreadTest2("A", account, 1600).start();
		new DrawThreadTest2("B", account, 1600).start();
	}
	
	@Override
	public void run() {
		//if use lock, it should be static filed instead of instance field.
		account.draw(money);
	}
}

class Account2{
	private String accountNo;
	private double balance;
	
	public Account2(String accountNo, double balance){
		this.accountNo = accountNo;
		this.balance = balance;
	}
	
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public synchronized void draw(double number){
		if (balance > number) {

			// simulate delay in the real case
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			balance -= number;

			System.out.println(Thread.currentThread().getName() + " draw " + number
					+ " successully. The balance in card is "
					+ balance);
		} else {
			System.out.println(Thread.currentThread().getName() + " draw " + number +  " but the balance is " + balance
					+ ", which is less than " + number);
		}
	}
	
	@Override
	public int hashCode() {
		return accountNo.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if(obj != null && obj.getClass() == Account.class){
			Account account = (Account)obj;
			return account.getAccountNo().equals(this.accountNo);
		}
		
		return false;
	}
}
