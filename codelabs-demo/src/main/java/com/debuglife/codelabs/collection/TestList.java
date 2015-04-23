package com.debuglife.codelabs.collection;

import java.util.ArrayList;
import java.util.List;


public class TestList {
    public static void main(String[] args){
        try {
            List<String> list = new ArrayList<>();
            list.add(null);
            
            System.out.println(list.size());
            System.out.println(list.isEmpty());
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
