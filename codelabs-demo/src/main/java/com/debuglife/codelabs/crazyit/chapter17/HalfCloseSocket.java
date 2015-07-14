package com.debuglife.codelabs.crazyit.chapter17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HalfCloseSocket {
	public static void main(String[] args) throws Exception{
		new InnerServer().start();
		new InnerClient().start();
	}
	static class InnerClient extends Thread{
		@Override
		public void run() {
			Socket s = null;
			BufferedReader br = null;
			try {
				s = new Socket("127.0.0.1", 30000);
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String line;
				while((line = br.readLine()) != null){
					System.out.println(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				try {
					if(s != null){
						s.close();
					}
					if(br != null){
						br.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class InnerServer extends Thread{
		@Override
		public void run() {
			Socket s = null;
			PrintStream ps = null;
			Scanner scan = null;
			ServerSocket ss = null;
			try {
				ss = new ServerSocket(30000);
				s = ss.accept();
				ps = new PrintStream(s.getOutputStream());
				ps.println("hello");
				ps.println("world");
				//s.shutdownOutput();
				ps.println("again, haha");
				
				ps.close();
				System.out.println(s.isClosed());
				scan = new Scanner(s.getInputStream());
				while(scan.hasNextLine()){
					System.out.println(scan.nextLine());
				}
				
				ps.println("hehe");
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				try {
					scan.close();
					ps.close();
					s.close();
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
