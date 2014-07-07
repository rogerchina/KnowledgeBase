package com.debuglife.codelabs;

import java.util.Map;
import java.util.Properties;

public class TestSystem {
    public static void main(String [] args){
        //testSystemGetProperties();
        //testArrayCopy();
        //testSystemGetEnv();
        testSystemExit();
    }
    
    static void testSystemExit(){
        try {
            Thread.sleep(1000 * 5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        // terminate the current java vitural machine
        System.exit(-1);
        // below sentence will never be executed
        System.out.println("after the call exit() method");
    }
    
    static void testSystemGetProperties(){
        Properties properties = System.getProperties();
        String[] propertiesArray = properties.toString().split(",");
        
        for(String str : propertiesArray)
            System.out.println(str.split("=")[0] + ":" + "\t\t\t\t" + (str.split("=").length == 1? "" : str.split("=")[1]));
        
        System.out.println(System.setProperty("king", "hello"));
        System.out.println(System.setProperty("king", "world"));
        System.out.println(System.getProperty("king"));
        
        System.clearProperty("king");
        System.out.println(System.getProperty("king"));
        
        //System.out.println(properties instanceof Map);
    }
    
   static void testArrayCopy(){
        int [] srcArray = {1,2,3,4,5,6};
        int [] desArray = new int[2];
        System.arraycopy(srcArray, 2, desArray, 0, 2);
        for(int i: desArray)
            System.out.println(i);
    }
    
   static void testSystemGetEnv(){
        Map<String, String> envMap = System.getenv();
        for(String key: envMap.keySet()){
            System.out.println(key + ":" + envMap.get(key));
        }
    }
}
