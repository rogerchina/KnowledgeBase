package com.debuglife.codelabs.corejava;

import java.lang.management.ManagementFactory;


public class TestJavaProcess {
    public static void main(String[] args) throws Exception{
        testAvailableProcessors();
     }
    
    public static void testAvailableProcessors(){
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
    
    public static void testMXBean(){
        System.out.println(ManagementFactory.getRuntimeMXBean().getName());
    }
}
