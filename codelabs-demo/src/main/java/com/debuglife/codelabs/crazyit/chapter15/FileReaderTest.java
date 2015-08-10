package com.debuglife.codelabs.crazyit.chapter15;

import java.io.FileReader;
/**
 * 
 * @author roger
 *
 */
public class FileReaderTest {
	public static void main (String[] args) throws Exception{
		FileReader reader = new FileReader("/home/roger/workspace/codelabs/codelabs-demo/src/main/java/com/debuglife/codelabs/crazyit/chapter15/FileReaderTest.java");
		char[] buffer = new char[8];
		int hasRead = 0;
		while((hasRead = reader.read(buffer)) > 0){
			System.out.print(new String(buffer, 0, hasRead));
		}
		reader.close();
	}
}
