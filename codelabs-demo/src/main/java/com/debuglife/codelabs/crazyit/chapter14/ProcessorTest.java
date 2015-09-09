package com.debuglife.codelabs.crazyit.chapter14;

import java.lang.reflect.Method;

public class ProcessorTest {
	public void process(String clazz) throws ClassNotFoundException{
		int passed = 0;
		int failed = 0;
		for(Method m : Class.forName(clazz).getMethods()){
			if(m.isAnnotationPresent(Testable.class)){
				try {
					m.invoke(null);
					passed++;
				} catch (Exception e) {
					System.out.println("method " + m + " failed to run, the exception is " + e.getCause());
					failed++;
				}
			}
		}
		
		System.out.println("there are total " + (passed + failed) + " invoked, in which " + passed + " sucessed, " + failed + " failed." );
	}
}
