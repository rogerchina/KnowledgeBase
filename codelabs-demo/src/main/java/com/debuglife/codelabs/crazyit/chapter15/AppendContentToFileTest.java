package com.debuglife.codelabs.crazyit.chapter15;

import java.io.RandomAccessFile;

public class AppendContentToFileTest {
	public static void main(String[] args) throws Exception{
		RandomAccessFile file = new RandomAccessFile("/home/roger/test/test.txt", "rw");
		file.seek(file.length());
		file.write("DA JIA HAO \r\n".getBytes());
		
		file.close();
	}
}
