package com.debuglife.codelabs.crazyit.chapter18;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PracticalProxyTest {
	public static void main(String[] args){
		Dog target = new GunDog();
		Dog d = (Dog)MyProxyFactory.getProxy(target);
		d.info();
		d.run();
	}
}

interface Dog{
	void info();
	void run();
}

class GunDog implements Dog{
	// The comments have a very tight dependency
	// with DogUtil general class, in order to 
	// decouple between caller and callee, dynamical
	// proxy provide a better way to separate them.
	//DogUtil du = new DogUtil();
	
	@Override
	public void info() {
		//du.method1();
		System.out.println("I am a gun dog.");
		//du.method2();
	}

	@Override
	public void run() {
		//du.method1();
		System.out.println("I run faster.");
		//du.method2();
	}
}

class DogUtil{
	public void method1(){
		System.out.println("simulate the gerneral method 1");
	}
	
	public void method2(){
		System.out.println("simulate the gerneral method 2");
	}
}

class MyPracticalInvocationHandler implements InvocationHandler{
	private Object target;
	
	public void setTarget(Object target){
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		DogUtil du = new DogUtil();
		du.method1();
		Object result = method.invoke(target, args);
		du.method2();
		return result;
	}
}

class MyProxyFactory{
	public static Object getProxy(Object target){
		MyPracticalInvocationHandler handler = new MyPracticalInvocationHandler();
		handler.setTarget(target);
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
	}
}


