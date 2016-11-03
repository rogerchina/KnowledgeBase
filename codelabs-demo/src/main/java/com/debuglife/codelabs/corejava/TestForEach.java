package com.debuglife.codelabs.corejava;

import java.util.ArrayList;
import java.util.List;


public class TestForEach {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        for(String str : list){
            System.out.println(str);
        }
    }
}
