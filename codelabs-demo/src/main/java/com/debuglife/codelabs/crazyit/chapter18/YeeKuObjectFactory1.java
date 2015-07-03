package com.debuglife.codelabs.crazyit.chapter18;

import java.lang.reflect.Constructor;


public class YeeKuObjectFactory1<T> {
	public static void main(String[] args){
		YeeKuObjectFactory1<String> factory = new YeeKuObjectFactory1<String>();
		String str = factory.getInstance(String.class);
		System.out.println(str + "here");
	}
	
	public T getInstance(Class<T> cls){
		try {
			Constructor constructor = cls.getConstructor(String.class);
			return (T)constructor.newInstance("i am a string");
//			return cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public T get(T t){
		T tt = null;
		return tt;
	}
}
