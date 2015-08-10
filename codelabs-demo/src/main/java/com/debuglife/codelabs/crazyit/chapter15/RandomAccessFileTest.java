package com.debuglife.codelabs.crazyit.chapter15;

import java.io.RandomAccessFile;

public class RandomAccessFileTest {
	public static void main(String[] args) throws Exception{
		RandomAccessFile file = new RandomAccessFile("/home/roger/test/test.txt", "r");
		System.out.println(file.getFilePointer());
		
		file.seek(20);
		int hasRead = 0;
		byte[] buffer = new byte[1024];
		while((hasRead = file.read(buffer)) > 0){
			System.out.println(new String(buffer, 0, buffer.length));
		}
		
		file.close();
	}
}
