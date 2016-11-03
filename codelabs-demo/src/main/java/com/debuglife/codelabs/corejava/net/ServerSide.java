package com.debuglife.codelabs.corejava.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerSide {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(3333);
        Socket socket = serverSocket.accept();
//        System.out.println(socket.getKeepAlive());
//        System.out.println(socket.getLocalPort());
//        System.out.println(socket.getPort());
//        System.out.println(socket.getReceiveBufferSize());
//        System.out.println(socket.getReuseAddress());
//        System.out.println(socket.getRemoteSocketAddress());
//        System.out.println(socket.getSoTimeout());
//        System.out.println(socket.getTcpNoDelay());
        BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        PrintWriter os = new PrintWriter(socket.getOutputStream());
        
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
        
        String line = sin.readLine();
        while(!line.equals("end")){
            os.println(line);
            os.flush();
            
            System.out.println("Server:" + line);
            System.out.println("Client:" + is.readLine());
            
            line = sin.readLine();
        }
        
        os.close();
        is.close();
        socket.close();
        serverSocket.close();
    }
}
