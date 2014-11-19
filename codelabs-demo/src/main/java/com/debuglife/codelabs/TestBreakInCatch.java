package com.debuglife.codelabs;


public class TestBreakInCatch {
    public static void main(String[] args){
        int i;
        if(1 == 1){
            try {
                i = 0;
                int j = 1/i;
            } catch(Exception e) {
                e.printStackTrace();
                //break;
            } finally{
                i = 1;
            }
        }
    }
}
