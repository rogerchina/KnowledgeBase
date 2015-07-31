package com.debuglife.codelabs.crazyit.chapter16;

import java.util.concurrent.locks.ReentrantLock;


public class DrawDepositThreadTest4{
	
	public static void main(String[] args){
		Account4 account = new Account4("888-8888-88", 0.0);
		new DrawThread("A", account, 2000).start();
		new DepositThread("B", account, 2000).start();
		new DepositThread("C", account, 2000).start();
		new DepositThread("D", account, 2000).start();
	}
	
}

class DrawThread extends Thread{
	private Account4 account;
	private double money;
	
	public DrawThread(String name, Account4 account, double money){
		super(name);
		this.account = account;
		this.money = money;
	}
	
	@Override
	public void run() {
		for(int i=0; i<100; i++){
			account.draw(money);
			
			// 1 second pause to have an obvious effect
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class DepositThread extends Thread{
	private Account4 account;
	private double money;
	
	public DepositThread(String name, Account4 account, double money){
		super(name);
		this.account = account;
		this.money = money;
	}
	
	@Override
	public void run() {
		for(int i=0; i<100; i++){
			account.deposit(money);
		}
	}
}

class Account4{
	private String accountNo;
	private double balance;
	private boolean flag = false;
	
	public Account4(String accountNo, double balance){
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
		
		try {

			if(!flag){
				wait();
			} else {
				balance -= number;
				System.out.println(Thread.currentThread().getName() + " draw " + number + ", remaining money is " + balance);
				flag = false;
				notifyAll();
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public synchronized void deposit(double number){
		try {
			
			if(flag){
				wait();
			} else {
				balance += number;
				System.out.println(Thread.currentThread().getName() + " deposit " + number + ", remaining money is " + balance);
				flag = true;
				notifyAll();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
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
