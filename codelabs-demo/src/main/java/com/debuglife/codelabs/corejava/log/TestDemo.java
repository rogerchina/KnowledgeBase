/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.corejava.log;


public class TestDemo extends MyLoggerManager{
    static{
        printInfo("zou ni");
        System.out.println("hello");
    }
    
    public static void printInfo(String str){
        System.out.println(str);
    }
    
    public static void main(String[] args){
        System.out.println("main");
    }
}
