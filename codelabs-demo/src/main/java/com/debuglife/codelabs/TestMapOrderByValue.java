package com.debuglife.codelabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class TestMapOrderByValue {
    public static void main(String[] args){
        Map<String,Integer> map = new LinkedHashMap<>();
        map.put("a", 9);
        map.put("b", 2);
        map.put("c", 7);
        map.put("d", 1);
        map.put("e", 4);
        map.put("f", 0);
        
        printMap(map);
        map = sortMap(map);
        printMap(map);
    }
    
    @SuppressWarnings("unchecked")
    public static void printMap(Map<String, Integer> map){
        System.out.println("-------------map start--------------");
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)it.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("-------------map end--------------");
    }
    
    public static Map<String, Integer> sortMap(Map<String, Integer> map){
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override  
            public int compare(Entry<String, Integer> arg0,  
                    Entry<String, Integer> arg1) {  
                return arg0.getValue() - arg1.getValue();  
            }  
        });
        
        Map<String, Integer> newMap = new LinkedHashMap<>();  
        for (int i = 0; i<list.size(); i++) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        } 
        return newMap;
    }
}
