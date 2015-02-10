package com.debuglife.codelabs.fileIO;

import java.io.File;


public class FileDeletorWithRecursiveFolder extends FileHandler{
    private boolean finishedFlag = false;
    private long startTime = 0;
    private long endTime = 0;
    
    private String[] fileArray = null;
    
    public FileDeletorWithRecursiveFolder(String[] fileArray, String name){
        super(name);
        this.fileArray = fileArray;
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

            for(int i=0;i<fileArray.length;i++){
                String filePath = fileArray[i];
                deleteFiles(filePath);
            }
                
            endTime = System.currentTimeMillis();
            finishedFlag = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void deleteFiles(String filePath){
        if(filePath != null){
            File f = new File(filePath);
            if(f.exists()){
                if(f.isDirectory()){
                    File[] fileArray = f.listFiles();
                    for(int i=0;i<fileArray.length;i++){
                        deleteFiles(fileArray[i].getAbsolutePath());
                    }
                }
                else{
                    f.delete();
                }
            }
        }
    }
}
