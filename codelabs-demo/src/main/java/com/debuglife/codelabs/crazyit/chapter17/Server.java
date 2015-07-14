package com.debuglife.codelabs.crazyit.chapter17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final int SERVER_PORT = 30000;
	private static CrazyitMap<String, PrintStream> clients = new CrazyitMap<>();
	
	public void init(){
		try(ServerSocket ss = new ServerSocket(SERVER_PORT)) {
			System.out.println("Server started successfully.");
			while(true){
				Socket s = ss.accept();
				new ServerThreadNew(s, this).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CrazyitMap<String, PrintStream> getClients(){
		return clients;
	}
	
	public static void main(String[] args){
		Server server = new Server();
		server.init();
	}
}

class ServerThreadNew extends Thread{
	private Socket socket;
	private Server server;
	private PrintStream ps = null;
	private BufferedReader br = null;
	public ServerThreadNew(final Socket socket, final Server server){
		this.socket = socket;
		this.server = server;
	}
	
	@Override
	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ps = new PrintStream(socket.getOutputStream());
			
			String line;
			while((line = br.readLine()) != null){
				// user login msg
				if(line.startsWith(CrazyitProtocol.USER_ROUND) 
						&& line.endsWith(CrazyitProtocol.USER_ROUND)){
					String username = getMsgBody(line);
					if(server.getClients().containsKey(username)){
						System.out.println("name duplicated.");
						ps.println(CrazyitProtocol.NAME_REP);
					}else{
						System.out.println(username + " login server successfully.");
						ps.println(CrazyitProtocol.LOGIN_SUCCESS);
						server.getClients().put(username, ps);
					}
				}
				// private msg
				else if(line.startsWith(CrazyitProtocol.PRIVATE_ROUND) 
						&& line.endsWith(CrazyitProtocol.PRIVATE_ROUND)){
					String userAndContent = getMsgBody(line);
					String user = userAndContent.split(CrazyitProtocol.SPLIT_SIGN)[0];
					String content = userAndContent.split(CrazyitProtocol.SPLIT_SIGN)[1];
					server.getClients().get(user).println(server.getClients().getKeyByValue(ps) + " say: " + content);
				}
				// public msg
				else{
					String msg = getMsgBody(line);
					for(PrintStream clientPS : server.getClients().valueSet()){
						clientPS.println(server.getClients().getKeyByValue(clientPS) + " say: " + msg);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("error");
			server.getClients().removeByValue(ps);
			System.out.println("currently, the active user number are " + server.getClients().size());
			
			try {
				if(br != null){
					br.close();
				}
				if(ps != null){
					ps.close();
				}
				if(socket != null){
					socket.close();
				}
			} catch (IOException e1) {
				// ignore
			}
		}
	}
	
	public String getMsgBody(String msg){
		return msg.substring(CrazyitProtocol.PROTOCOL_LEN, msg.length() - CrazyitProtocol.PROTOCOL_LEN);
	}

}
