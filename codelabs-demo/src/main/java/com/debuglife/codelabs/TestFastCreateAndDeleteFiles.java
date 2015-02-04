package com.debuglife.codelabs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * this is for creating and deleting files in multiple threads, it
 * is faster than os file system generally. 
 * so far, it didn't support recursive folder.
 * @author roger
 *
 */
public class TestFastCreateAndDeleteFiles {
    private static int totalNum = 50000;
    private static int numOfGroup = 8;
    private static List<Thread> threadList = new ArrayList<Thread>();

    public static void main(String[] args) throws Exception{
        String suffix = ".HL7";
        
        String fileSize_1k = "D:\\workspace\\communication-stack-parent\\communication-hl7\\src\\test\\resources\\messages\\ADT_A01 - Copy (2).hl7";
        String fileSize_2M = "D:\\workspace\\communication-stack-trunk\\communication-hl7\\src\\test\\resources\\messages\\MDM_T02_pat_JPG.hl7";
        String desFilePath = "D:\\test\\medavis\\service\\gateway4med\\in\\10w\\ADT_A01_";
        
        //createFiles(fileSize_1k, desFilePath, suffix);
        deleteFiles(desFilePath, suffix);
        
        boolean allFinished = false;
        while(!allFinished){
            allFinished = true;
            for(int i=0; i<threadList.size(); i++){
                FileHandler t = (FileHandler)threadList.get(i);
                allFinished = allFinished && t.isFinished();
            }
        }
        
        long [] time = new long[threadList.size()]; 
        for(int i=0; i<threadList.size(); i++){
            FileHandler t = (FileHandler)threadList.get(i);
            time[i] = t.getExecuteTime();
        }
        
        long temp;
        for(int i=0; i<time.length; i++){
            for(int j=i+1; j<time.length; j++){
                if(time[i] > time[j]){
                    temp = time[i];
                    time[i] = time[j];
                    time[j] = temp;
                }
            }
        }
        
        System.out.println("******* it takes total " + time[time.length-1] + " ******");
        System.out.println("******* it takes total " + showHumanizedTime(time[time.length-1]) + " ******");
    }
    
    public static String showHumanizedTime(long time){
        String timeString = "";
        if(time < 1000)
            timeString = time + " milliseconds";
        else if(time >= 1000 && time < 60000)
            timeString = (time /= 1000) + " seconds";
        else if(time >= 60000 && time < 3600000)
            timeString = (time /= 60000) + " minutes";
        else if(time >= 3600000)
            timeString = (time /= 3600000) + " hours";
        
        return timeString;
    }
    
    public static void createFiles(String srcFilePath, String desFilePath, String suffix) {
        int x[] = groupData(totalNum, numOfGroup);
        printArray(x);
        
        int temp = 0;
        for(int i=0; i<x.length; i++){
            FileCreator w = null;
            if(i != x.length-1){
                w = new FileCreator((i+1)*x[i], i*x[i], "thread-"+i);
                temp = x[i];
            } else{
                w = new FileCreator(totalNum, i*temp, "thread-"+i);
            }
            
            w.setSrcFilePath(srcFilePath);
            w.setDesFilePath(desFilePath);
            w.setSuffix(suffix);
            
            w.start();
            threadList.add(w);
        }
    }
    
    public static void deleteFiles(String desFilePath,String suffix){
        int x[] = groupData(totalNum, numOfGroup);
        for(int i=0; i<x.length; i++){
            FileDeletor w = new FileDeletor(i, x[i], "thread-"+i);
            w.setDesFilePath(desFilePath);
            w.setSuffix(suffix);
            
            w.start();
            threadList.add(w);
        }
    }
    
    public static void printArray(int x[]){
        for(int i=0; i<x.length; i++){
            System.out.print(x[i] + "  ");
        }
        System.out.println();
    }
    
    public static int[] groupData(int data, int groupNum){
        int[] x = null;
        if(data % groupNum == 0){
            x = new int[groupNum];
            for(int i=0; i<groupNum; i++){
                x[i] = data/groupNum;
            }
        }else{
            x = new int[groupNum + 1];
            for(int i=0; i<groupNum; i++){
                x[i] = data/groupNum;
            }
            x[groupNum] = data%groupNum;
        }
            
        return x;
    }
}

class FileCreator extends FileHandler{
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

class FileDeletor extends FileHandler{
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

abstract class FileHandler extends Thread{
    
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
