package com.debuglife.codelabs.protocolsniffer;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;

public class ProtocolSniffer {
	protected Map<String, String> serverInfo = null;
	protected Server server = null;
	private String protocol = null;
	private String sourceOS = null;
	private String message = "";
	
	// construction method
	public ProtocolSniffer(Map<String,String> serverInfo){
		this.serverInfo = serverInfo;
		server = new Server(serverInfo.get("host"), serverInfo.get("user"),
				 serverInfo.get("password"), serverInfo.get("passphrase"),
				 serverInfo.get("identity"), serverInfo.get("pulickey"),
				 Integer.parseInt(serverInfo.get("port")), serverInfo.get("protocol"),
				 serverInfo.get("sourceOS"));
		
		// Get protocol from Conf DB
		protocol = serverInfo.get("protocol");
		// Note, 1: Windows   other: Linux/Unix
		sourceOS = serverInfo.get("sourceOS"); 
	}

	public void testProtocol(){
		if(sourceOS != null && !"".equals(sourceOS)){
			if("1".equals(sourceOS))
				testWindows();
			else
				testUnix();
		}
	}
	
	// test for Windows
	private void testWindows(){
		String command = "cmd /c dir";
		tryExecCommand(command);
	}
	
	// test for Unix/Linux
	private void testUnix(){
		String command = "pwd";
		tryExecCommand(command);
	}
	
	// try to execute command
	private void tryExecCommand(String command){
		Channel channel = server.getChannel("exec");
		if(channel != null && channel instanceof ChannelExec){
			ChannelExec channelExec = (ChannelExec)channel;
			channelExec.setCommand(command);
			InputStream is = null;
			InputStreamReader isr = null;
			BufferedReader buffer = null;
			try{
				channelExec.setInputStream(null);
				channelExec.setErrStream(System.err);
				is = channelExec.getInputStream();
				channelExec.connect(20*60*1000);
				
				isr = new InputStreamReader(is);
				buffer = new BufferedReader(isr);
				String line1 = buffer.readLine();
				if(line1 != null && !"".equals(buffer)){
					protocol = "ssh";
					message = "[SSH is OK]";
				}else{
					message = "[No support SSH or Permission denied for SSH for account " + serverInfo.get("user") + "]";
				}
			}catch(JSchException e){
				message = e.getMessage();
			}catch(IOException e){
				message = e.getMessage();
			}catch(Exception e){
				message = e.getMessage();
				//e.printStackTrace();
			}finally{
				try{
					if(is != null){
						is.close();
					}
					if(isr != null){
						isr.close();
					}
					if(buffer != null){
						buffer.close();
					}
					if(channelExec != null && !channelExec.isClosed() && !channelExec.isEOF()){
						try{
							System.out.println("1channelExec is closed? :" + channelExec.isClosed());
							channelExec.disconnect();
							System.out.println("2channelExec is closed? :" + channelExec.isClosed());
						}catch(Exception e){
							e.printStackTrace();
						}finally{
							//System.out.println("1channelExec is closed? :" + channelExec.isClosed());
						}
					}
				}catch(IOException e){
					e.printStackTrace();
				}
				System.out.println("3channelExec is closed? :" + channelExec.isClosed());
				while (true) {
					try {
						TimeUnit.MILLISECONDS.sleep(50);
						if (channelExec.isClosed()) {
							break;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				server.closeSession();
			}
			
		}
	}
	
	// return the message
	public String returnMessage(){
		return message;
	}
	
	// return the protocol
	public String returnProtocol(){
		return protocol;
	}
}

class Server {
	private String host;
	private String user;
	private String password = "";
	private String passphrase = "";
	private String identity;
	private String publickey;
	private int port;
	private String protocol;
	private String sourceOS;
	
	private Session session;
	private JSch jsch;

	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public String getSourceOS() {
		return sourceOS;
	}
	public void setSourceOS(String sourceOS) {
		this.sourceOS = sourceOS;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassphrase() {
		return passphrase;
	}
	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getPublickey() {
		return publickey;
	}
	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	// construction method
	public Server(String host, String user,
				  String password, String passphrase,
				  String identity, String publickey,
				  int port, String protocol,
				  String sourceOS){
		this.host = host;
		this.user = user;
		this.password = password;
		this.passphrase = passphrase;
		this.identity = identity;
		this.publickey = publickey;
		this.port = port;
		this.protocol = protocol;
		this.sourceOS = sourceOS;
		setSession();
	}
	
	private void setSession(){
		jsch = new JSch();
		//JSch.setLogger(new MyLogger());
		try {
			if (identity != null && !"".equals(identity)) {
				byte[] privatekey_array, publickey_array, passphrase_array;
				privatekey_array = file2byte(identity, true);
				if (publickey == null || "".equals(publickey)) {
					publickey_array = file2byte(identity + ".pub", false);
				} else {
					publickey_array = file2byte(publickey, true);
				}
				passphrase_array = str2byte(passphrase);
				jsch.addIdentity(identity, privatekey_array,
						publickey_array, passphrase_array);
			}
			session = jsch.getSession(user, host, port);
			//session.setUserInfo(new DefaultUserInfo(password));
			session.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Channel getChannel(String type) {
		try {
			Channel result = session.openChannel(type);
			if (result instanceof ChannelSftp) {
				result.connect();
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeSession(){
		if(session != null)
			if(session.isConnected())
				session.disconnect();
	}
	
	private byte[] str2byte(String paramString) {
		if (paramString == null)
			return null;
		try {
			return paramString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return paramString.getBytes();
	}

	private byte[] file2byte(String filePath, boolean errorthrowable)
			throws JSchException {
		byte[] arrayOfByte1 = null;
		File localFile = null;
		FileInputStream fis = null;
		try {
			localFile = new File(filePath);
			fis = new FileInputStream(localFile);
			arrayOfByte1 = new byte[(int) localFile.length()];
			int i = 0;
			while (true) {
				int j = fis.read(arrayOfByte1, i,
						arrayOfByte1.length - i);
				if (j <= 0)
					break;
				i += j;
			}
			fis.close();
		} catch (Exception localException1) {
			try {
				if (fis != null)
					fis.close();
			} catch (Exception localException2) {
			}
			if (errorthrowable) {
				if ((localException1 instanceof Throwable))
					throw new JSchException(localException1.toString(),
							localException1);
				throw new JSchException(localException1.toString());
			}
			arrayOfByte1 = null;
		}
		return arrayOfByte1;
	}
//
//	public static class DefaultUserInfo implements UserInfo {
//		private String password;
//		public DefaultUserInfo(String password) {
//			this.password = password;
//		}
//		@Override
//		public String getPassphrase() {
//			return null;
//		}
//		@Override
//		public String getPassword() {
//			return password;
//		}
//		@Override
//		public boolean promptPassphrase(String arg0) {
//			return true;
//		}
//		@Override
//		public boolean promptPassword(String arg0) {
//			return true;
//		}
//		@Override
//		public boolean promptYesNo(String arg0) {
//			return true;
//		}
//		@Override
//		public void showMessage(String message) {
//		}
//	}
	
	@SuppressWarnings({ "unchecked" })
	public static class MyLogger implements com.jcraft.jsch.Logger {
	    
		@SuppressWarnings("rawtypes")
		static java.util.Hashtable name=new java.util.Hashtable();
	    static{
	      name.put(new Integer(DEBUG), "DEBUG: ");
	      name.put(new Integer(INFO), "INFO: ");
	      name.put(new Integer(WARN), "WARN: ");
	      name.put(new Integer(ERROR), "ERROR: ");
	      name.put(new Integer(FATAL), "FATAL: ");
	    }
	    public boolean isEnabled(int level){
	      return true;
	    }
	    public void log(int level, String message){
	      System.err.print(name.get(new Integer(level)));
	      System.err.println(message);
	    }
	}
}

