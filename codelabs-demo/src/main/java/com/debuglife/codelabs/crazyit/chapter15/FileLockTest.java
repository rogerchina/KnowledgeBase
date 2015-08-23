package com.debuglife.codelabs.crazyit.chapter15;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {
	public static void main(String[] args) throws Exception{
		try(FileChannel channel = new FileOutputStream("/home/roger/test/test1.txt").getChannel()){
			FileLock lock = channel.tryLock();
			
			Thread.sleep(15000);
			lock.release();
		}
	}
}
