package com.debuglife.codelabs.crazyit.chapter17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class SocketClientTest {
	public static void main(String[] args) throws Exception{
//		Socket socket = new Socket("127.0.0.1", 30000);
		
		try {
			Socket socket = new Socket();
			InetSocketAddress address = new InetSocketAddress("127.0.0.1", 30000);
			socket.connect(address, 5000);
			
//			Scanner scanner = new Scanner(socket.getInputStream());
//			String str = scanner.nextLine();
//			System.out.println(str);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = br.readLine();
			System.out.println("the coming data from server is:" + line);
			br.close();
			socket.close();
		} catch(SocketTimeoutException ee){
			System.out.println("socket time out error occurred.");
			ee.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
