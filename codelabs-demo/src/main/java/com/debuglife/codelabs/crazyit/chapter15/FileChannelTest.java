package com.debuglife.codelabs.crazyit.chapter15;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelTest {
	public static void main(String[] args) throws Exception{
		File f = new File("/home/roger/test/test.txt");
		FileChannel inChannel = new FileInputStream(f).getChannel();
		
		File ff = new File("/home/roger/test/test1.txt");
		FileChannel outChannel = new FileOutputStream(ff).getChannel();
		
		MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
		Charset charset = Charset.forName("UTF-8");
		outChannel.write(buffer);
		buffer.clear();
		
		CharsetDecoder decoder = charset.newDecoder();
		CharBuffer charBuffer = decoder.decode(buffer);
		System.out.println(charBuffer);
	}
}
