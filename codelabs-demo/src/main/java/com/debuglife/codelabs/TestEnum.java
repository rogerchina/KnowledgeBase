package com.debuglife.codelabs;


public class TestEnum {
    public static void main(String[] args){
        System.out.println(Direction.FORWARD.name());
        //System.out.println(Direction.valueOf(arg0));
    }
}

enum Direction{
    FORWARD, BACK, LEFT, RIGHT
}