package com.debuglife.codelabs.crazyit.chapter15;

import java.nio.CharBuffer;

public class NIOBufferTest {
	public static void main(String[] args){
		CharBuffer buffer = CharBuffer.allocate(8);
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());
		
		buffer.put("a");
		buffer.put("b");
		buffer.put("c");
		System.out.println(buffer.position());
		
		buffer.flip();
		System.out.println(buffer.limit());
		System.out.println(buffer.position());
		
		buffer.clear();
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.get(2));
		System.out.println(buffer.position());
		
	}
}
