package com.debuglife.codelabs.corejava.collection;

import java.util.HashSet;
import java.util.Set;


public class TestSet {
    public static void main(String[] args){
        try {
            Set<String> sets = new HashSet<>();
            sets.add("a");
            sets.add("b");
            sets.add("c");
            
            System.out.println(sets.contains(null));
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
