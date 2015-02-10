package com.debuglife.codelabs.fileIO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * this is for creating and deleting files in multiple threads, it
 * is faster than os file system generally. 
 * so far, it didn't support recursive folder. <br/>
 * @author roger
 *
 */
public class TestFastCreateAndDeleteFiles {
    // config info
    private static String suffix = ".HL7";
    private static String fileSize_1k = "D:\\workspace\\communication-stack-parent\\communication-hl7\\src\\test\\resources\\messages\\ADT_A01 - Copy (2).hl7";
    private static String fileSize_2M = "D:\\workspace\\communication-stack-trunk\\communication-hl7\\src\\test\\resources\\messages\\MDM_T02_pat_JPG.hl7";
    private static String desFilePath = "D:\\test\\medavis\\service\\gateway4med\\in\\10w\\ADT_A01_";
    private static String testFilePath = "D:\\test";
    private static int totalNum = 50000;
    private static int numOfGroup = 8;
    
    // thread list
    private static List<Thread> threadList = new ArrayList<Thread>();

    public static void main(String[] args) throws Exception{
        //createFiles(fileSize_1k, desFilePath, suffix);
        deleteFiles(desFilePath, suffix);
        deleteFilesWithRecurisiveFolder(testFilePath);
        calExecuteTime();
    }
    
    /**
     * create files in one folder.
     * @param srcFilePath
     * @param desFilePath
     * @param suffix
     */
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
    
    /**
     * delete files in one folder.
     * @param desFilePath
     * @param suffix
     */
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
    
    /**
     * delete files in resurisive way.
     * @param filePath
     */
    public static void deleteFilesWithRecurisiveFolder(String filePath){
        if(filePath == null) return;
        File f = new File(filePath);
        if(!f.exists()) return;
        if(f.isFile()) f.delete();
        if(f.isDirectory()){
            File[] files = f.listFiles();
            int x[] = groupData(files.length, numOfGroup);
            for(int k=0;k<x.length;k++){
                String[] fileStrArray = new String[x[k]];
                for(int j=0;j<fileStrArray.length;j++){
                    for(int i=x[k]*(k);i<files.length;i++){
                        fileStrArray[j] = files[i].getAbsolutePath();
                    }
                }
                FileDeletorWithRecursiveFolder fdwrf = new FileDeletorWithRecursiveFolder(fileStrArray, "thread-name-" + k);
                fdwrf.start();
                threadList.add(fdwrf);
            }
        }
    }
    
    public static void calExecuteTime(){
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