package com.debuglife.codelabs.corejava;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TestShutdownHook {
    private static Timer timer = new Timer("job-timer");
    private static AtomicInteger count = new AtomicInteger(0);
    
    private static class CleanWorkThread extends Thread{
        @Override
        public void run() {
            System.out.println("starting to clean some work.");
            timer.cancel();
            System.out.println("has finished cleaning some work.");
            try {
                Thread.sleep(2 * 1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String args[]){
        Runtime.getRuntime().addShutdownHook(new CleanWorkThread());
        System.out.println("main class start...");
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                count.getAndIncrement();
                System.out.println("doing job " + count);
                try {
                    if(count.get() == 10){
                        //System.exit(0);
                        int i = 1, j = 0;
                        i = i/j;
                    }
                } catch(Exception e) {
                    System.exit(1);
                }
            }
            
        }, 500, 2*1000);
    }
}
