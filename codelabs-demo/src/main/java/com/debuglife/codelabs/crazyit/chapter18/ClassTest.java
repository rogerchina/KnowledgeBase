package com.debuglife.codelabs.crazyit.chapter18;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@SuppressWarnings("unused")
@Deprecated
public class ClassTest {
	
	private ClassTest(){
	}
	
	public ClassTest(String name){
		System.out.println("execute constructor with argument.");
	}
	
	public void info(){
		System.out.println("execute info() without argument.");
	}
	
	public void info(String str){
		System.out.println("execute info(...) with argument.");
	}
	
	class Inner{
	}
	
	public static void main(String[] args) throws Exception{
		// there are 3 ways to obtain the some class' Class's object
		Class clazz = ClassTest.class; // best amongst below three ways.
		//clazz = new ClassTest().getClass();
		//clazz = Class.forName("com.debuglife.codelabs.crazyit.chapter18.ClassTest");
		
		System.out.println("获取该Class所对应类的全部构造函数");
		Constructor[] constructors = clazz.getDeclaredConstructors();
		for(Constructor c : constructors){
			System.out.println(c);
		}
		
		System.out.println("获取该Class所对应类的全部public构造函数");
	    Constructor[] constructors1 = clazz.getConstructors();
	    for(Constructor c : constructors1){
	    	System.out.println(c);
	    }
	    
	    System.out.println("获取该Class所对应类的全部public方法");
	    Method[] methods = clazz.getMethods();
	    for(Method m : methods){
	    	System.out.println(m);
	    }
	    
	    System.out.println("获取该Class所对应类的指定的方法");
	    System.out.println("带有一个字符串参数的info(...)方法是: " + clazz.getMethod("info", String.class));
	    
	    System.out.println("获取该Class所对应类的全部注释");
	    Annotation[] annotations = clazz.getAnnotations();
	    for(Annotation a : annotations){
	    	System.out.println(a);
	    }
	    
	    // @SuppressWarnings's Retention(value=SOURCE), so the clazz at runtime didn't access it.
	    System.out.println("获取指定的Annotation:" + clazz.getAnnotation(SuppressWarnings.class));
	    
	    System.out.println("获取该Class对应类的全部内部类");
	    Class<?>[] inners = clazz.getDeclaredClasses();
	    for(Class c : inners){
	    	System.out.println(c + " 该内部类所在的外部类: " + c.getDeclaringClass());
	    }
	    
	    Class inClazz = Class.forName("com.debuglife.codelabs.crazyit.chapter18.ClassTest$Inner");
	    Class outClazz = inClazz.getDeclaringClass();
	    System.out.println("获取内部类所在的外部类: " + outClazz);
	    
	    System.out.println("该类所在的包:" + clazz.getPackage());
	    System.out.println("该类的超类:" + clazz.getSuperclass());
	    System.out.println("该类的simple名称: " + clazz.getSimpleName());
	    System.out.println("该类的名称: " + clazz.getName());
	}
	
}
