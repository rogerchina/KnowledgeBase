package com.debuglife.codelabs.crazyit.chapter16;

import java.util.concurrent.locks.ReentrantLock;


public class DrawThreadTest3 extends Thread {
	
	private Account3 account;
	private double money;
	private static Object lock = new Object();
	
	public DrawThreadTest3(String name, Account3 account, double money){
		super(name);
		this.account = account;
		this.money = money;
	}
	
	public static void main(String[] args){
		Account3 account = new Account3("888-8888-88", 2000.0);
		new DrawThreadTest3("A", account, 1600).start();
		new DrawThreadTest3("B", account, 1600).start();
	}
	
	@Override
	public void run() {
		//if use lock, it should be static filed instead of instance field.
		account.draw(money);
	}
}

class Account3{
	private String accountNo;
	private double balance;
	private final ReentrantLock lock = new ReentrantLock();
	
	public Account3(String accountNo, double balance){
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
	
	public void draw(double number){
		lock.lock();
		try {
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
		} finally{
			lock.unlock();
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
