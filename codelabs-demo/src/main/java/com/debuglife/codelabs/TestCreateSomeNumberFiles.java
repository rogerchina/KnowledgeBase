package com.debuglife.codelabs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class TestCreateSomeNumberFiles {
    private static int totalNum = 20;
    private static int numOfGroup = 2;
    private static List<Thread> threadList = new ArrayList<Thread>();

    public static void main(String[] args) throws Exception{
        
        String fileSize_1k = "D:\\workspace\\communication-stack-parent\\communication-hl7\\src\\test\\resources\\messages\\ADT_A01 - Copy (2).hl7";
        String fileSize_2M = "D:\\workspace\\communication-stack-trunk\\communication-hl7\\src\\test\\resources\\messages\\MDM_T02_pat_JPG.hl7";
        String desFilePath = "D:\\test\\filesByGenerator\\ADT_A01_";
        
        executeTask(fileSize_2M, desFilePath,".HL7");
        
//        for(int i=0; i<threadList.size(); i++){
//            FileCreator t = (FileCreator)threadList.get(i);
//            if(!t.isRunning()){
//                
//            }
//        }
    }
    
    public static void executeTask(String srcFilePath, String desFilePath, String suffix) {
        int x[] = groupData(totalNum, numOfGroup);
        printArray(x);
        
        for(int i=0; i<x.length; i++){
            FileCreator w = new FileCreator(i, x[i], "thread-"+i);
            w.setSrcFilePath(srcFilePath);
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

class FileCreator extends Thread{
    private boolean runningFlag = false;
    
    private String srcFilePath = "";
    private String desFilePath = "";
    private String suffix = "";
    
    private int start;
    private int num;
    public FileCreator(int start, int num, String name){
        super(name);
        this.start = start;
        this.num = num;
    }
    
    @Override
    public void run() {
        try {
            runningFlag = true;
            for(int i=num*start; i<num*(1+start); i++){
                copyFile(srcFilePath, desFilePath + i + suffix);
                System.out.println(Thread.currentThread().getName() + "--" + desFilePath + i + suffix + "--created succesfully!");
            }
            runningFlag = false;
        } catch(Exception e) {
            e.printStackTrace();
        }
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
    
    public boolean isRunning(){
        return runningFlag;
    }
    
    
    public static void copyFile(String oldPath, String newPath)
            throws Exception {

        int bytesum = 0;
        int byteread = 0;

        File oldfile = new File(oldPath);
        if (oldfile.exists()) {
            InputStream inStream = new FileInputStream(oldPath);
            try {
                FileOutputStream fs = new FileOutputStream(newPath);
                try{
                    byte[] buffer = new byte[1024];
                    while ((byteread = inStream.read(buffer)) != -1) {
                        bytesum += byteread;
                        fs.write(buffer, 0, byteread);
                    }
                }catch(Exception ex){
                    throw ex;
                }finally{
                    try{
                        fs.close();
                    }catch(Exception ex){
                        //some log info
                    }
                }
            } catch (Exception ex) {
                throw ex;
            } finally {
                try{
                    inStream.close();
                }catch(Exception ex){
                    // some log info
                }
            }

        }
    }
}
