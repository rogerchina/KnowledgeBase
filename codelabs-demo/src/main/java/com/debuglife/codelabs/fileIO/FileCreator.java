package com.debuglife.codelabs.fileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;


public class FileCreator extends FileHandler{
    private boolean finishedFlag = false;
    private long startTime = 0;
    private long endTime = 0;
    
    private String srcFilePath = "";
    private String desFilePath = "";
    private String suffix = "";
    
    private int start;
    private int end;
    public FileCreator(int start, int end, String name){
        super(name);
        this.start = start;
        this.end = end;
    }
    
    public void setSrcFilePath(String srcFilePath){
        this.srcFilePath = srcFilePath; 
    }
    
    public void setDesFilePath(String desFilePath){
        this.desFilePath = desFilePath;
    }
    
    public void setSuffix(String suffix){
        this.suffix = suffix;
    }

    @Override
    public boolean isFinished(){
        return finishedFlag;
    }
    
    @Override
    public long getExecuteTime() {
        return endTime - startTime;
    }

    @Override
    public void execute() {
        try {
            startTime = System.currentTimeMillis();
            for(int i=start-1; i>=end; i--){
                copyFile(srcFilePath, desFilePath + i + suffix);
                System.out.println(Thread.currentThread().getName() + "--" + desFilePath + i + suffix + "--created succesfully!");
            }
            endTime = System.currentTimeMillis();
            finishedFlag = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void copyFile(String oldPath, String newPath) {
        //int bytesum = 0;
        int byteread = 0;

        File oldfile = new File(oldPath);
        if (oldfile.exists()) {
            InputStream fis = null;
            FileOutputStream fs = null;
            byte[] buffer = null;
            try {
                fis = new FileInputStream(oldPath);
                fs = new FileOutputStream(newPath);
                buffer = new byte[1024];
                while ((byteread = fis.read(buffer)) != -1) {
                    //bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                fs.flush();
                //System.out.println("the file's size is: " + bytesum);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try{
                    fs.close();
                    fis.close();
                }catch(Exception ex){
                    // some log info
                }
            }
        }
    }
}

