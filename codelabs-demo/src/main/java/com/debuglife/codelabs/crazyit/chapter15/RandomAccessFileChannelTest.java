package com.debuglife.codelabs.crazyit.chapter15;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class RandomAccessFileChannelTest {
	public static void main(String[] args) throws Exception{
		File f = new File("/home/roger/test/test.txt");
		RandomAccessFile raf = new RandomAccessFile(f, "rw");
		FileChannel channel = raf.getChannel();
		ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
		channel.position(f.length());
		channel.write(buffer);
		channel.close();
		raf.close();
	}
}
