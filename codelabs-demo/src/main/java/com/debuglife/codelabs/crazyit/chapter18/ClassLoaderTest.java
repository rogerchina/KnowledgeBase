package com.debuglife.codelabs.crazyit.chapter18;

import java.lang.reflect.Constructor;

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
	ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	Class<?> clazz = classLoader.loadClass("com.debuglife.codelabs.crazyit.chapter18.Tester");
	//Constructor constructor = clazz.getConstructor();
	//constructor.newInstance();
	System.out.println(clazz.hashCode());
	System.out.println("系统已经加载了Tester类");

	Class<?> clazz1 = Class.forName("com.debuglife.codelabs.crazyit.chapter18.Tester");
	System.out.println(clazz1.hashCode());
    }
}

class Tester {
    static {
	System.out.println("Tester 类的静态初始化...");
    }

    public Tester() {
	//Constructor
    }
}