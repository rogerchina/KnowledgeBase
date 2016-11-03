package com.debuglife.codelabs.corejava.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ClientSide {
	public static void main(String[] args) throws Exception {
		// method 1
		// Socket socket = new Socket("127.0.0.1", 3333);

		// method 2
		// Socket socket = new Socket();
		// SocketAddress address = new InetSocketAddress("127.0.0.1", 3333);
		// socket.connect(address, 1000);

		// method 3
		InetAddress address = Inet4Address.getByName("127.0.0.1");
		Socket socket = new Socket(address, 3333);

		BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

		PrintWriter os = new PrintWriter(socket.getOutputStream());

		BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		String readLine = sin.readLine();
		while (!readLine.equals("end")) {
			os.println(readLine);
			os.flush();

			System.out.println("Client:" + readLine);
			System.out.println("Server:" + is.readLine());

			readLine = sin.readLine();
		}

		os.close();
		is.close();
		socket.close();
	}
}
