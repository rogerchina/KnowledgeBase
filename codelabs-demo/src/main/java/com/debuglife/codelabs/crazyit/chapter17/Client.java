package com.debuglife.codelabs.crazyit.chapter17;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	private static final int PORT = 30000;
	private Socket socket;
	private PrintStream ps;
	private BufferedReader br;
	private BufferedReader keyIn;
	
	public void init(){
		try {
			keyIn = new BufferedReader(new InputStreamReader(System.in));
			socket = new Socket("127.0.0.1", PORT);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ps = new PrintStream(socket.getOutputStream());
			
			String tips = "";
			while(true){
				String username = JOptionPane.showInputDialog(tips + " input name: ");
				ps.println(CrazyitProtocol.USER_ROUND + username + CrazyitProtocol.USER_ROUND);
				String result = br.readLine();
				if(result.equals(CrazyitProtocol.NAME_REP)){
					tips = "user name is duplicated, please again ";
					continue;
				}
				if(result.equals(CrazyitProtocol.LOGIN_SUCCESS)){
					break;
				}
			}
		} catch (HeadlessException e) {
			System.out.println("the host doesn't support keyboard, display, mouse.");
			closeRS();
			System.exit(1);
		} catch (UnknownHostException e) {
			System.out.println("the host is not found, please check.");
			closeRS();
			System.exit(1);
		} catch (IOException e) {
			System.out.println("the network is abnormal, please re-login.");
			closeRS();
			System.exit(1);
		}
		
		new ClientThreadNew(br).start();
	}
	
	private void readAndSend(){
		String line = null;
		try {
			while((line = keyIn.readLine()) !=  null){
				if(line.indexOf(":") > 0 && line.startsWith("/")){
					line = line.substring(1);
					ps.println(CrazyitProtocol.PRIVATE_ROUND 
							+ line.split(":")[0] + CrazyitProtocol.SPLIT_SIGN + line.split(":")[1] 
							+ CrazyitProtocol.PRIVATE_ROUND);
				}else{
					ps.println(CrazyitProtocol.MSG_ROUND + line + CrazyitProtocol.MSG_ROUND);
				}
			}
		} catch (IOException e) {
			System.out.println("the network is abnormal. please re-login.");
			closeRS();
			System.exit(1);
		}
	}
	
	private void closeRS(){
		try {
			if(keyIn != null){
				keyIn.close();
			}
			if(ps != null){
				ps.close();
			}
			if(br != null){
				br.close();
			}
			if(socket != null){
				socket.close();
			}
		} catch (IOException e) {
			// ignore
		}
	}
	
	public static void main(String[] args){
		Client client = new Client();
		client.init();
		client.readAndSend();
	}
}

class ClientThreadNew extends Thread{
	private BufferedReader br;
	
	public ClientThreadNew(BufferedReader br){
		this.br = br;
	}
	
	@Override
	public void run() {
		try {
			String line = null;
			while((line=br.readLine()) != null){
				System.out.println(line);
				//do a lot of things here.
			}
		} catch (IOException e) {
			System.out.println("the network is abnormal. please check.");
			e.printStackTrace();
		} finally{
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				// ingore
				e.printStackTrace();
			}
		}
	}

}
