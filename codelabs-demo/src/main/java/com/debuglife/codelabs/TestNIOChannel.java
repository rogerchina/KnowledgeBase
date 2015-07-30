package com.debuglife.codelabs;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestNIOChannel {
	public static void main(String[] args) throws Exception{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		System.out.println(serverSocketChannel.validOps());
		
		SocketChannel socketChannel = SocketChannel.open();
		System.out.println(socketChannel.validOps());
	}
}
