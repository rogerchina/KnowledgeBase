/**
 * 
 */
package com.debuglife.codelabs.ssl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


/**
 * @author roger
 *
 */
public class SSLClient {

    private SSLSocket socket = null;
    
    public SSLClient() throws IOException {
        SSLSocketFactory socketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        socket = (SSLSocket)socketFactory.createSocket("localhost", 7070);
    }
    
    public void connnect(){
        try {
            // 1.
            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            // 2.
            String userName = "principal";
            output.println(userName);
            String password = "credential";
            output.println(password);
            output.flush();
            
            // 3.
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseContent = input.readLine();
            responseContent += "\n" + input.readLine();
            System.out.println(responseContent);
            
            // 4.
            output.close();
            input.close();
            socket.close();
            
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
    
    public static void main(String args[]) throws Exception{
        SSLClient client = new SSLClient();
        client.connnect();
    }
            
}
