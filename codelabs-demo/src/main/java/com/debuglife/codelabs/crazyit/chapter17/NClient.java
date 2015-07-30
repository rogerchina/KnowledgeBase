package com.debuglife.codelabs.crazyit.chapter17;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NClient {
	private Selector selector = null;
	private static int PORT = 3000;
	private SocketChannel socketChannel = null;
	private Charset charset = Charset.forName("UTF-8");
	
	public void init() throws Exception{
		selector = Selector.open();
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", PORT);
		socketChannel = SocketChannel.open(address);
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);
		new ClientThread().start();
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String content = scanner.nextLine();
			socketChannel.write(charset.encode(content));
		}
	}
	
	private class ClientThread extends Thread{
		@Override
		public void run() {
			try {
				while(selector.select() > 0){
					for(SelectionKey key : selector.selectedKeys()){
						selector.selectedKeys().remove(key);
						if(key.isReadable()){
							SocketChannel socketChannel = (SocketChannel)key.channel();
							ByteBuffer buffer = ByteBuffer.allocate(1024);
							String content = "";
							while(socketChannel.read(buffer) > 0){
								socketChannel.read(buffer);
								buffer.flip();
								content += charset.decode(buffer);
							}
							
							System.out.println("the read content: " + content);
							// make a preparation for next time reading data.
							key.interestOps(SelectionKey.OP_READ);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		new NClient().init();
	}
}
