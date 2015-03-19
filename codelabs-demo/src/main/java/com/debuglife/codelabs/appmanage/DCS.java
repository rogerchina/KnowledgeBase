package com.debuglife.codelabs.appmanage;


public class DCS extends Thread {
    private static boolean flag = false;
    
    private static String[] args = null;
    
    public boolean isRunning(){
        return flag;
    }
    
    public void loadArgs(String[] args){
        //TODO
    }
    
    public void startup(){
        flag = true;
    }
    
    public void shutdown(){
        flag = false;
    }
    
    @Override
    public void run(){
        try {
            while(flag){
                //do something
                Thread.sleep(2000);
                System.out.println("The dcs server is running...");
            }
            System.out.println("The dcs server is stoped");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
