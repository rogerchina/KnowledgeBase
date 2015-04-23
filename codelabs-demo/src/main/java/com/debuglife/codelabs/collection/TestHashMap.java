package com.debuglife.codelabs.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class TestHashMap {
    public static void main(String[] args){
//        testMap();
        testKeyOverride();
    }
    
    public static void testKeyOverride(){
        String key = "roger";
        Map<String,String> map = new HashMap<>();
        map.put(key, "roger"); 
        System.out.println(map.get(key));
        map.put(key, "roger1");
        System.out.println(map.get(key));
        
    }
    
    public static void testMap(){
        Map<String, String> map = new HashMap<>();
        map.put("roger", "roger");
        map.put("sam", "sam");
        map.put("lucy", "lucy");
        
        for(String str : map.keySet()){
            System.out.println(str);
        }
        
        Set<String> set = new TreeSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        
        for(String str : set){
            System.out.println(str);
        }
    }
}
