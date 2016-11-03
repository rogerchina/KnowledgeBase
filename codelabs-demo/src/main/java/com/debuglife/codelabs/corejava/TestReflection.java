package com.debuglife.codelabs.corejava;

import java.lang.reflect.Method;

public class TestReflection {
	public static void main(String[] args) throws Exception{
		String[] a = new String[1];
		a[0] = "hello";
//		load(null);
		load(a);
	}
	
	public static void load(String[] args) throws Exception{
		String methodName = "test";
		Object param[];
		Class<?> paramTypes[];
		if(args == null || args.length == 0){
			param = null;
			paramTypes = null;
		}else{
			paramTypes = new Class[1];
			paramTypes[0] = args.getClass();
			param = new Object[1];
			param[0] = args;
		}
		
		Method method = Target.class.getMethod(methodName, paramTypes);
		method.invoke(new Target(), param);
	}
}

class Target {
	public void test(String[] args){
		System.out.println(args.length);
	}
}
