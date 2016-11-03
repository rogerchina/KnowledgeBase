package com.debuglife.codelabs.corejava;

import java.util.ArrayList;
import java.util.List;


public class TestArray {
    public static void main(String[] args) throws Exception{
        String[] strArray = {"he","dd","dfe"};
        System.out.println(strArray);
        
        List<String> list = new ArrayList<>();
        list.add("Mon");
        list.add("Tue");
        list.add("Wed");
        list.add("Thr");
        System.out.println(list);
        Thread.sleep(600*1000);
     }
}
