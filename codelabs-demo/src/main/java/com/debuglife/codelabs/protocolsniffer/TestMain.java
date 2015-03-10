package com.debuglife.codelabs.protocolsniffer;

import java.util.HashMap;
import java.util.Map;

public class TestMain {
	
	private static void printLog(String msg, boolean flag){
		if(flag)
			System.out.println(msg);
	}
	
	public static void main(String[] args) throws Exception{
		Map<String, String> serverInfo = new HashMap<String, String>();
		/**/
		serverInfo.put("host", "");
		serverInfo.put("user", "");
		serverInfo.put("password", "");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "");
		serverInfo.put("identity", "");
		serverInfo.put("port", "");
		serverInfo.put("sourceOS", "");
		
		printLog("==============before new sniffer()=============", true);
		ProtocolSniffer ps = new ProtocolSniffer(serverInfo);
		printLog("starting....", true);
		ps.testProtocol();
		printLog("the final protocol is: " + ps.returnProtocol(), true);
		printLog("the result message: " + ps.returnMessage(), true);
		printLog("ending....", true);
		printLog("==============after new sniffer().returnProtocol() invoked=============", true);
	}
}
