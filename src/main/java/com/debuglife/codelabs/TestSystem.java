package com.debuglife.codelabs;

import java.util.Map;
import java.util.Properties;

public class TestSystem {
    public static void main(String [] args){
        
        Properties properties = System.getProperties();
        System.out.println(properties);
        
        System.setProperty("king", "hello");
        System.out.println(System.getProperty("king"));
        
        System.clearProperty("king");
        System.out.println(System.getProperty("king"));
        
        System.out.println(properties instanceof Map);
        
        // arraycopy
        int [] srcArray = {1,2,3,4,5,6};
        int [] desArray = new int[2];
        System.arraycopy(srcArray, 2, desArray, 0, 2);
        for(int i: desArray)
            System.out.println(i);
        
        // currentMillisecond
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
    }
}
