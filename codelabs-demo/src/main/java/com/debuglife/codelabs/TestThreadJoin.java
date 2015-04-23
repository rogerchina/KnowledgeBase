package com.debuglife.codelabs;

import java.io.File;


public class TestThreadJoin {
    public static void main(String[] args) throws Exception{
        // 1. start workers
        WorkerManager wm = new WorkerManager();
        wm.init();
        wm.startAllWorker();
        
        // 2. start workers shutdown detector
        ShutdownWorkerDetector shutdownWorkerDetector = new ShutdownWorkerDetector(wm);
        shutdownWorkerDetector.start();
        shutdownWorkerDetector.join();
        
        // 3. stop worker gracefully
        wm.waitForAllWorkerComplete();
        
        System.out.println("TestThreadJoin will stop!");
    }
}

class ShutdownWorkerDetector extends Thread{
    private String shutdown_signal = "shutdown.signal"; 
    private WorkerManager wm;
    public ShutdownWorkerDetector(WorkerManager wm){
        this.wm = wm;
    }
    
    @Override
    public void run() {
        Thread.currentThread().setName("TestThreadJoin-shutdown-detector");
        String filePath = "c:" + File.separator + shutdown_signal;
        while(true) {
            File file = new File(filePath);
            if(file.exists()){
                wm.stopAllWorker();
                file.delete();
                break;
            } else {
                try {
                    Thread.sleep(5 * 1000);
                } catch(InterruptedException e) {
                    System.out.println("shutdown signal detector is interrupted");
                }
            }
        }
        System.out.println("I am worker shut down detector, I will stop all workers...");
    }
}

class WorkerManager{
    private static Worker[] workers = new Worker[3];
    
    public WorkerManager(){
        
    }
    
    public void init(){
        for(int i=0; i<workers.length; i++){
            Worker w = new Worker(i+"");
            workers[i] = w;
        }
    }
    
    public void startAllWorker(){
        for(int i=0; i<workers.length; i++){
            workers[i].startServer();
            workers[i].start();
        }
    }
    
    public void stopAllWorker(){
        for(int i=0; i<workers.length; i++){
            workers[i].stopServer();
        }
    }
    
    public void waitForAllWorkerComplete(){
        for(int i=0; i<workers.length; i++){
            try {
                workers[i].join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Worker extends Thread{
   private boolean running = false;
    
    public Worker(String name){
        this.setName("[worker-" + name +  "]");
    }
    
    @Override
    public void run() {
        while(running){
            System.out.println("I am " + this.getName() + ", I am running...");

            try {
                Thread.sleep(2 * 1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("I am " + this.getName() + ", I will stop...");
    }
    
    public void startServer(){
        this.running = true;
    }
    
    public void stopServer(){
        this.running = false;
    }
}
