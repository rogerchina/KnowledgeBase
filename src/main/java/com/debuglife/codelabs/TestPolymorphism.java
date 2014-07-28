package com.debuglife.codelabs;

import java.util.ResourceBundle;

/**
 * 1. read the resource file to get the string of dynamically generated class,
 * and create a new instance of class instead of using new XXX() method
 * 
 * 2. design the arch for java framework
 * @author roger
 *
 */
public class TestPolymorphism {
    private static String resourceBundleBaseName = "";
    private static final String TEST2 = "Test2";
    
    
    public static void main(String[] args) throws Exception{
        TestPolymorphism tp = new TestPolymorphism();
        tp.invoke();
    }
    
    public TestPolymorphism() {
        resourceBundleBaseName = this.getClass().getPackage().getName() + ".Bundle";
    }
    
    private void invoke() throws Exception{
        String className = ResourceBundle.getBundle(resourceBundleBaseName).getString(TEST2);
        Test1 testDemo = (Test1)((ClassLoader.getSystemClassLoader().loadClass(className)).newInstance());
        testDemo.start();
        System.out.println("********************************");
        testDemo.start1();
    }
}

abstract class Test1{
    public void start(){
        // 1. some action in the current class
        System.out.println("it is executing in the Test1...");
        
        // 2. call init(), which will be implemented by sub-class
        init();
        
        // 3. some other action in the current class
        System.out.println("it reached to the end in the Test1...");
    }
    
    public void start1(){
        // 1. some action in the current class
        beforeService();
        
        // 2. call init(), which will be implemented by sub-class
        init();
        
        // 3. some other action in the current class
        afterService();
    }
    
    public void beforeService(){
        beforeAction();
    }
    
    public void afterService(){
        afterAction();
    }
    
    public abstract void beforeAction();
    
    public abstract void afterAction();
    
    public abstract void init();
}

class Test2 extends Test1{
    @Override
    public void init() {
        System.out.println("it is executing int the init() of class Test2...");
    }

    @Override
    public void beforeAction() {
        System.out.println("it is executing in the beforeAction() of class Test2...");
    }

    @Override
    public void afterAction() {
        System.out.println("it is executing in the afterAction() of class Test2...");
    }
}