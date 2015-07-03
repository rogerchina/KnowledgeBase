package com.debuglife.codelabs.crazyit.chapter18;

import java.util.Date;

public class YeeKuObjectFactory {
	public static void main(String[] args){
		YeeKuObjectFactory factory = new YeeKuObjectFactory();
		String d = (String)factory.getInstance("java.util.Date");
		Date date = (Date)factory.getInstance("java.util.Date");
		System.out.println(d);
	}
	
	public Object getInstance(String className){
		try {
			Class clazz = Class.forName(className);
			return clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
