/**
 * 
 */
package com.debuglife.codelabs.ssl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;


/**
 * @author roger
 *
 */
public class SSLServer {
    
    private static final String USER_NAME = "principal";
    private static final String PASSWORD = "credential";
    private static final String SECRET_CONTENT = "This is a confidential content from server X, for your eye!";
    
    private SSLServerSocket serverSocket = null;
    
    public SSLServer() throws Exception{
        SSLServerSocketFactory serverSocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
        serverSocket = (SSLServerSocket)serverSocketFactory.createServerSocket(7070);
    }
    
    private void runServer(){
        while(true){
            try {
                System.out.println("Waiting for client's connection... ");
                SSLSocket socket = (SSLSocket)serverSocket.accept();
                
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String userName = input.readLine();
                String password = input.readLine();
                PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                if(userName.equals(USER_NAME) && password.equals(PASSWORD)){
                    output.println("Welcome, " + userName);
                    output.println(SECRET_CONTENT);
                }else{
                    output.println("Authentication failed, you have no access to server X...");
                }
                
                output.close();
                input.close();
                socket.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String args[]) throws Exception{
        SSLServer server =  new SSLServer();
        server.runServer();
    }
    
}
