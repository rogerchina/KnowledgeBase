package com.debuglife.codelabs.crazyit.chapter16;

public class ThreadLocalTest {
	public static void main(String[] args){
		AccountTest account = new AccountTest("I am account");
		
		new MyThreadTest(account, "Thread-1").start();
		new MyThreadTest(account, "Thread-2").start();
	}
}

class AccountTest{
	private ThreadLocal<String> name = new ThreadLocal<>();
	
	public AccountTest(String str){
		this.name.set(str);
		System.out.println(this.name.get());
	}
	
	public String getName(){
		return name.get();
	}
	
	public void setName(String str){
		this.name.set(str);
	}
}

class MyThreadTest extends Thread{
	private AccountTest account;
	
	public MyThreadTest(AccountTest account, String name){
		super(name);
		this.account = account;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++){
			if(i == 6){
				account.setName(getName());
			}
			System.out.println(account.getName() + " value of i=" + i);
		}
	}
}
