package com.debuglife.codelabs.corejava.fileIO;


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
    protected abstract boolean isFinished();
    
    /**
     * return the total execute time.
     */
    protected abstract long getExecuteTime();
    
    /**
     * execute task.
     */
    protected abstract void execute();
    
}

