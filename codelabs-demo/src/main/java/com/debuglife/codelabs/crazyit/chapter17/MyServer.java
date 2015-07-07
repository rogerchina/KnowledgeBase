package com.debuglife.codelabs.crazyit.chapter17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {
	public static ArrayList<Socket> socketList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		ServerSocket ss = new ServerSocket(30000);
		while(true){
			Socket s = ss.accept();
			socketList.add(s);
			(new ServerThread(s)).start();
		}
	}
}

class ServerThread extends Thread{
	private Socket s = null;
	private BufferedReader br = null;
	
	public ServerThread(Socket s) throws Exception{
		this.s = s;
		this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
	@Override
	public void run() {
		try {
			String content = null;
			while((content = readFromClient()) != null){
				for(Socket s : MyServer.socketList){
					PrintStream ps = new PrintStream(s.getOutputStream());
					ps.println(content);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String readFromClient(){
		try {
			return br.readLine();
		} catch (IOException e) {
			MyServer.socketList.remove(s);
			e.printStackTrace();
		}
		return null;
	}
}
