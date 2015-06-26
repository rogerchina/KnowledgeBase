package com.debuglife.codelabs.crazyit.chapter18;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ProxyTest {
	public static void main(String[] args){
		MyInvocationHandler handler = new MyInvocationHandler();
		People p = (People)Proxy.newProxyInstance(People.class.getClassLoader(), new Class[]{People.class}, handler);
		p.walk();
		p.sayHello("Hello");
	}
}

interface People{
	void walk();
	void sayHello(String string);
}

class GoodPeople implements People{
	@Override
	public void walk() {
		System.out.println("I am walking...");
	}

	@Override
	public void sayHello(String string) {
		System.out.println("I am saying..." + string);
	}
}

class MyInvocationHandler implements InvocationHandler{
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("The executing method is: " + method);
		if(args != null){
			System.out.println("Below is real argument passed into.");
			for(Object obj : args){
				System.out.println(obj);
			}
		}else{
			System.out.println("There is no argument for method.");
		}
		return null;
	}
}

