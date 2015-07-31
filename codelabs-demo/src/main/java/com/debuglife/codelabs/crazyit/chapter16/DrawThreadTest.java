package com.debuglife.codelabs.crazyit.chapter16;

public class DrawThreadTest extends Thread {
	
	private Account account;
	private double money;
	private static Object lock = new Object();
	
	public DrawThreadTest(Account account, double money){
		this.account = account;
		this.money = money;
	}
	
	public static void main(String[] args){
		Account account = new Account("888-8888-88", 2000.0);
		new DrawThreadTest(account, 1600).start();
		new DrawThreadTest(account, 1600).start();
	}
	
	@Override
	public void run() {
		//if use lock, it should be static filed instead of instance field.
		synchronized (account) {
			if (account.getBalance() > money) {

				// simulate delay in the real case
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				account.setBalance(account.getBalance() - money);
				System.out.println("draw " + money
						+ " successfully. The balance in card is "
						+ account.getBalance());
			} else {
				System.out.println("the balance is" + account.getBalance()
						+ ", which is less than " + money);
			}
		}
	}
}

class Account{
	private String accountNo;
	private double balance;
	
	public Account(String accountNo, double balance){
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
