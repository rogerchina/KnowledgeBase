package com.debuglife.codelabs;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class TestClass {
    public static int m = 0;
    public int n = 1;
    
    public static void main(String[] args) throws Exception{
        testClass();
    }
    
    public TestClass(){
    }
    
    public static void testClassAsParameter() throws Exception{
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        
        test1(List.class);
        test1(String.class);
    }
    
    public static void test1(Class clazz) throws Exception{
        final Class c = null;
        if(clazz == String.class){
            
        }
    }
    
    @SuppressWarnings("rawtypes")
    public static void testClass() throws Exception{
        System.out.println(TestClass.class.getPackage());
        System.out.println(TestClass.class.getCanonicalName());
        
        Annotation[] annotations = TestClass.class.getDeclaredAnnotations();
        for(int i=0; i<annotations.length; i++){
            System.out.println(annotations[i].toString());
        }
        
        Constructor[] constructors = TestClass.class.getConstructors(); 
        for(int i=0; i<constructors.length; i++){
            System.out.println(constructors[i].getModifiers());
            System.out.println(constructors[i].getName());
        }
        
        Field[] fields = TestClass.class.getFields();
        for(int i=0; i<fields.length; i++){
            System.out.println(fields[i].getName() + "\t\t" + fields[i].getModifiers());
        }
        
        Method[] methods = TestClass.class.getMethods();
        for(int i=0; i<methods.length; i++){
            System.out.println(methods[i].getName() + "\t" + methods[i].getReturnType().getName() + "\t" +
                    methods[i].isAccessible());
        }
        
        System.out.println(TestClass.class.getResource("hello.txt").getPath());
        //System.out.println(TestClass.class.getResource("/hello.txt").getPath());
        
        Class clazz = TestClass.class.forName("java.lang.Thread");
        Class clazz1 = TestClass.class.forName("java.lang.Thread", true, TestClass.class.getClassLoader());
        
        Thread t = (Thread)clazz.newInstance();
        Thread t1 = (Thread)clazz1.newInstance();
        
        System.out.println(t);
        System.out.println(t.getContextClassLoader().toString());
        System.out.println(t1);
        System.out.println(t1.getContextClassLoader().toString());
        
        System.out.println(TestClass.class.getClass().getSimpleName());
        
        ClassLoader classLoader = TestClass.class.getClassLoader();
        Class clazz2 = classLoader.loadClass("java.lang.Thread");
        Thread t2 = (Thread)clazz2.newInstance();
        System.out.println(t2);
        System.out.println(t2.getContextClassLoader().toString());
        
        Class<? extends InputStream> clazz3 = FileInputStream.class;
        System.out.println(clazz3.getCanonicalName());
        System.out.println(clazz3.getSuperclass().getCanonicalName());
    }
    
    public static void testClassLoader(){
        ClassLoader classLoader = TestClass.class.getClassLoader();
    }
}
