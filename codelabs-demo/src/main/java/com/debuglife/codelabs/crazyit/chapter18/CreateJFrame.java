package com.debuglife.codelabs.crazyit.chapter18;

import java.lang.reflect.Constructor;

public class CreateJFrame {
	public static void main(String[] args) throws Exception{
		Class<?> clazz = Class.forName("javax.swing.JFrame");
		Constructor constructor = clazz.getConstructor(String.class);
		Object object = constructor.newInstance("test windows");
		System.out.println(object);
	}
}
