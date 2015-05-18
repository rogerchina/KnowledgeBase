package com.debuglife.codelabs.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TestList {
    public static void main(String[] args){
//        try {
//            List<String> list = new ArrayList<>();
//            list.add(null);
//            
//            System.out.println(list.size());
//            System.out.println(list.isEmpty());
//            
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        test();
    }
    
    private static void test(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        for(String s : list){
            if("a".equals(s)){
                list .remove(s);
            }
        }
        
//        for(int i=0; i<list.size(); i++){
//            if("a".equals(list.get(i))){
//              list .remove(i);
//          }
//        }
        
//        Iterator<String> it = list.iterator();
//        while(it.hasNext()){
//            String s = it.next();
//            if("a".equals(s)){
//                it.remove();
//            }
//        }
        
        for(String s : list){
            System.out.println(s);
        }
    }
}
