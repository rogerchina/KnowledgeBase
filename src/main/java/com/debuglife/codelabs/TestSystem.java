package com.debuglife.codelabs;

import java.util.Properties;

public class TestSystem {
    public static void main(String [] args){
        
        Properties properties = System.getProperties();
        System.out.println(properties);
        
        System.setProperty("king", "hello");
        System.out.println(System.getProperty("king"));
    }
}
