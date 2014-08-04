package com.debuglife.codelabs;


import java.io.*;
import java.util.zip.GZIPInputStream;
//import java.util.zip.ZipInputStream;
 
public class TestUnGzFiles {
    private static String initFilePath;
    private static String ungzFilePath;
    public static void main(String[] args) throws Exception{
        System.out.println("=============ing============");
       
        initFilePath = "C:\\QDP Related Document\\TestFiles\\4M_to_7M_nh_xmlFile.tar\\4M_to_7M_nh_xmlFile";
        ungzFilePath = initFilePath+File.separator+"ungz";
        File file = new File(ungzFilePath);
        if(!file.exists()){
        	file.mkdirs();
        }
       
        unGzAllFiles(new File(initFilePath));
       
        System.out.println("=============end============");
    }
    
    static void unGzAllFiles(File file) throws Exception{
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(int i=files.length-1;i>=0;i--){
            	unGzAllFiles(files[i]);
            }
        }else{
        	unGzFile(file);
        }
    }
    
    static void unGzFile(File file) throws Exception{
        String name;
        if(file.getName().contains(".gz")){
        	name = file.getName().replace(".gz", "");
        }else{
        	name = file.getName()+ "_new";
        }
        File ubGzFile = new File(ungzFilePath + File.separator + name);
       
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(ubGzFile);
        GZIPInputStream gzis = new GZIPInputStream(fis);
       
        int iBytes = 0;
        int iBufferMax = 5120000;
        int iBufferSize = 0;
        long lFileSize = 0;
        byte[] bBuffer = null;
       
        lFileSize = file.length();
        lFileSize = lFileSize / 5;
        if (lFileSize > iBufferMax) {
        	iBufferSize = iBufferMax;
        } else {
        	iBufferSize = (new Long(lFileSize)).intValue();
        }
        bBuffer = new byte[iBufferSize];
        while ((iBytes = gzis.read(bBuffer)) != -1) {
        	fos.write(bBuffer, 0, iBytes);
        }
        fos.flush();
        fos.close();
        gzis.close();
        fis.close();
    }
    
    static void test3(){
//  	java.util.zip.ZipInputStream z = new ZipInputStream(null);
    }
}