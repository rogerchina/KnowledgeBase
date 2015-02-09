package com.debuglife.codelabs.fileIO;


public abstract class FileHandler extends Thread{
    
    public FileHandler(String name){
        super(name);
    }
    
    @Override
    public void run() {
        execute();
    }
    
    /**
     * the running flag.
     */
    public abstract boolean isFinished();
    
    /**
     * return the total execute time.
     */
    public abstract long getExecuteTime();
    
    /**
     * execute task.
     */
    public abstract void execute();
    
}

