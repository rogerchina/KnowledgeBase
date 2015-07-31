package com.debuglife.codelabs.crazyit.chapter16;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DrawDepositThreadTest5{
	
	public static void main(String[] args){
		Account5 account = new Account5("888-8888-88", 0.0);
		new DrawThread5("A", account, 2000).start();
		new DepositThread5("B", account, 2000).start();
		new DepositThread5("C", account, 2000).start();
		new DepositThread5("D", account, 2000).start();
	}
	
}

class DrawThread5 extends Thread{
	private Account5 account;
	private double money;
	
	public DrawThread5(String name, Account5 account, double money){
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

class DepositThread5 extends Thread{
	private Account5 account;
	private double money;
	
	public DepositThread5(String name, Account5 account, double money){
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

class Account5{
	private String accountNo;
	private double balance;
	private boolean flag = false;
	
	private final Lock lock = new ReentrantLock();
	private final Condition cond = lock.newCondition();
	
	public Account5(String accountNo, double balance){
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

			if(!flag){
				cond.await();
			} else {
				balance -= number;
				System.out.println(Thread.currentThread().getName() + " draw " + number + ", remaining money is " + balance);
				flag = false;
				cond.signalAll();
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void deposit(double number){
		lock.lock();
		try {
			
			if(flag){
				cond.await();
			} else {
				balance += number;
				System.out.println(Thread.currentThread().getName() + " deposit " + number + ", remaining money is " + balance);
				flag = true;
				cond.signalAll();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
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
