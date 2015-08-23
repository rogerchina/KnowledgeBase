package com.debuglife.codelabs.crazyit.chapter15;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Map.Entry;
import java.util.SortedMap;

public class OldAndNewIOReadFile {
	public static void main(String[] args) throws Exception{
		printCharset();
	}
	
	public static void printCharset(){
		SortedMap<String, Charset> map = Charset.availableCharsets();
		for(Entry<String, Charset> entry : map.entrySet()){
			System.out.println(entry.getKey() + entry.getValue()	);
		}
		
		System.out.println(System.getProperty("file.encoding"));
	}
	
	public static void readFile() throws Exception{
		File f = new File("/home/roger/test/test.txt");
		FileInputStream fis = new FileInputStream(f);
		FileChannel fc = fis.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(64);
		while(fc.read(buffer) != -1){
			buffer.flip();
			Charset charset = Charset.forName("UTF-8");
			CharsetDecoder decoder = charset.newDecoder();
			System.out.println(decoder.decode(buffer));
			buffer.clear();
		}
		
		fis.close();
	}
}
