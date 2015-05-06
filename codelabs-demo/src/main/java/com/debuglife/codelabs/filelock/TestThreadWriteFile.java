package com.debuglife.codelabs.filelock;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Calendar;


public class TestThreadWriteFile extends Thread{

    @Override
    public void run() {
        Calendar start = Calendar.getInstance();
        File file = new File("D:/test.txt");
        
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            
            //对文件加锁
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            FileChannel fileChannel = raf.getChannel();
            FileLock fileLock = null;
            while(true){
                try {
                    fileLock = fileChannel.tryLock();
                    break;
                } catch(Exception e) {
                    System.out.println("有其他线程正在操作该文件，当前线程休眠1000ms");
                    sleep(1000);
                }
            }
            
            //操作文件
            for(int i=1; i<=100; i++){
                sleep(10);
                StringBuffer sb = new StringBuffer();
                sb.append("这是第"+i+"行，应该没啥错哈 \r\t");
                raf.write(sb.toString().getBytes("utf-8"));
            }
            
            //资源回收
            fileLock.release();
            fileChannel.close();
            raf.close();
            raf = null;
        } catch(IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }
        
        Calendar calend=Calendar.getInstance();  
        System.out.println("写文件共花了"+(calend.getTimeInMillis()-start.getTimeInMillis())+"秒");
    }
}
