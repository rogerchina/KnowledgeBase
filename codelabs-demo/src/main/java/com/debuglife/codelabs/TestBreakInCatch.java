package com.debuglife.codelabs;


public class TestBreakInCatch {
    public static void main(String[] args){
        tryBreakFinally();
    }
    
    public void x(){
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
    
    public static void tryBreakFinally(){
        for(;;){
            boolean isOK = false;
            try{
                System.out.println("dddd");
                isOK = true;
                break;
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                if(!isOK){
                    try {
                        System.out.println("sleeping...");
                        Thread.sleep(5000);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
