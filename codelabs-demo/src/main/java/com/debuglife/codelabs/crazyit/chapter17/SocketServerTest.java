package com.debuglife.codelabs.crazyit.chapter17;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerTest {
	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(30000);
		while(true){
			Socket socket = server.accept();
			Thread.sleep(50000);
			PrintStream out = new PrintStream(socket.getOutputStream());
			out.println("hello, everyone i am roger.");
			out.flush();
			out.close();
			socket.close();
		}
	}
}
