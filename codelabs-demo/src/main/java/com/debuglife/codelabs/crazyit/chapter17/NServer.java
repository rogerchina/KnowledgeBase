package com.debuglife.codelabs.crazyit.chapter17;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NServer {
	private Selector selector;
	private static int PORT = 3000;
	private Charset charset = Charset.forName("UTF-8");
	
	public void init() throws Exception{
		selector = Selector.open();
		ServerSocketChannel server = ServerSocketChannel.open();
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
		server.bind(isa);
		server.configureBlocking(false);
		server.register(selector, SelectionKey.OP_ACCEPT);
		while(selector.select() > 0){
			for(SelectionKey sk : selector.selectedKeys()){
				selector.selectedKeys().remove(sk);
				if(sk.isAcceptable()){ // prepare to accept socket connection
					SocketChannel sc = server.accept();
					sc.configureBlocking(false);
					sc.register(selector, SelectionKey.OP_READ);
					sk.interestOps(SelectionKey.OP_ACCEPT);
				}
				if(sk.isReadable()){ // prepare to read data
					SocketChannel sc = (SocketChannel)sk.channel();
					ByteBuffer buff = ByteBuffer.allocate(1024);
					String content = "";
					try {
						while(sc.read(buff) > 0){
							buff.flip();
							content += charset.decode(buff);
							System.out.println("the read data: " + content);
							sk.interestOps(SelectionKey.OP_READ);
						}
					} catch (Exception e) {
						sk.cancel();
						if(sk.channel() != null){
							sk.channel().close();
						}
					}
					
					if(content.length() > 0){
						for(SelectionKey key : selector.keys()){
							Channel targetChannel = key.channel();
							if(targetChannel instanceof SocketChannel){
								SocketChannel dest = (SocketChannel)targetChannel;
								dest.write(charset.encode(content));
								
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		new NServer().init();
	}
}
