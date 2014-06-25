package com.debuglife.codelabs;

import java.util.Map;

public class TestDemo {
    public static void main(String[] args){
        Map<String, String> envMap = System.getenv();
        for(String key: envMap.keySet()){
            System.out.println(envMap.get(key));
        }
    }
    
}
