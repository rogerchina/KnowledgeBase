package com.debuglife.codelabs.process;

import java.lang.management.ManagementFactory;


public class TestJavaProcess {
    public static void main(String[] args) throws Exception{
        System.out.println(ManagementFactory.getRuntimeMXBean().getName());
        Thread.sleep(600 * 1000);
//        ManagementFactory.
    }
}
