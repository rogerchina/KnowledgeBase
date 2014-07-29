package com.debuglife.codelabs.collection;

import java.util.ArrayList;
import java.util.List;


public class TestHashMap {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        // pay a close attention to source code of remove()
        list.remove(1); 
    }
}
