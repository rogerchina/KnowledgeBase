package com.debuglife.codelabs.corejava.fileIO;

import java.io.File;


public class FileDeletor extends FileHandler{
    private boolean finishedFlag = false;
    private long startTime = 0;
    private long endTime = 0;
    
    private String desFilePath = "";
    private String suffix = "";
    
    private int start;
    private int num;
    public FileDeletor(int start, int num, String name){
        super(name);
        this.start = start;
        this.num = num;
    }
    
    public void setDesFilePath(String desFilePath){
        this.desFilePath = desFilePath;
    }
    
    public void setSuffix(String suffix){
        this.suffix = suffix;
    }
    
    @Override
    protected boolean isFinished(){
        return finishedFlag;
    }
    
    @Override
    protected long getExecuteTime() {
        return endTime - startTime;
    }

    @Override
    protected void execute() {
        try {
            startTime = System.currentTimeMillis();
            for(int i=num*start; i<num*(1+start); i++){
                File f = new File(desFilePath + i + suffix);
                f.delete();
                System.out.println(Thread.currentThread().getName() + "--" + desFilePath + i + suffix + "--deleted succesfully!");
            }
            endTime = System.currentTimeMillis();
            finishedFlag = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
