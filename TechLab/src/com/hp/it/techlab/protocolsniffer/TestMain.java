package com.hp.it.techlab.protocolsniffer;

import java.util.HashMap;
import java.util.Map;

public class TestMain {
	
	private static void printLog(String msg, boolean flag){
		if(flag)
			System.out.println(msg);
	}
	
	public static void main(String[] args) throws Exception{
		Map<String, String> serverInfo = new HashMap<String, String>();
		// 166
		/**/
		serverInfo.put("host", "16.158.70.166");
		serverInfo.put("user", "admin");
		serverInfo.put("password", "redhat");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "2");
		
		
		
		// 1s79w0119
		/*
		serverInfo.put("host", "s79w0119.sgp.hp.com");
		serverInfo.put("user", "svcgb2");
		serverInfo.put("password", "two.ace.hat-976");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "1");
		*/
		
		// hpcvilm4
		/*
		serverInfo.put("host", "hpcvilm4.cv.hp.com");
		serverInfo.put("user", "gb4mgr"); 
		serverInfo.put("password", "gb2book");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "2");
		*/
		
		// lm4 --gbdev
		/*
		serverInfo.put("host", "hpcvilm4.cv.hp.com");
		serverInfo.put("user", "gbdev");
		serverInfo.put("password", "gbfs_lm4");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "2");
		*/
		
		// sftp1
		/*
		serverInfo.put("host", "sft-us-sftp1.pgw.st.com");
		serverInfo.put("user", "hp_edx");
		serverInfo.put("password", "");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "C:\\SSH_Key\\sftp_private.key");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "2");
			*/
		
		//S79W0169
		/*
		serverInfo.put("host", "S79W0169.sgp.hp.com");
		serverInfo.put("user", "qdp");
		serverInfo.put("password", "!qaz2wsx3edc");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "1");
		*/
		
		// fsg-etl
		/*
		serverInfo.put("host", "fsg-etl.sdd.hp.com");
		serverInfo.put("user", "gbmgr2");
		serverInfo.put("password", "Pwd4u09!");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "C:\\SSH_Key\\sftp_private.key");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "2");
		*/
		
		// sgpbdm01
		/*
		serverInfo.put("host", "sgpbdm01.sgp.hp.com");
		serverInfo.put("user", "pwax");
		serverInfo.put("password", "gB00k2011");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "2");
		*/
		
		// C0043014
		/*
		serverInfo.put("host", "C0043014.itcs.hp.com");
		serverInfo.put("user", "qdp");
		serverInfo.put("password", "");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "C:\\SSH_Key\\qdp_2048_ossh");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "2");
		*/
		
		// PuertoRico-Presto
		/*
		serverInfo.put("host", "15.97.49.61");
		serverInfo.put("user", "qdpuser");
		serverInfo.put("password", "fileloader");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "C:\\SSH_Key\\qdp_2048_ossh");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "1");
		*/
		
		// gvu2113
		/*
		serverInfo.put("host", "gvu2113.houston.hp.com");
		serverInfo.put("user", "gbeasdd");
		serverInfo.put("password", "Jan134u!");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "C:\\SSH_Key\\qdp_2048_ossh");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "2");
		*/
		
		// lm4
		/*
		serverInfo.put("host", "lm4.cv.hp.com");
		serverInfo.put("user", "gbdev");
		serverInfo.put("password", "gbfs_lm4");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "2");
		*/
		
		// gbookfs4
		/*
		serverInfo.put("host", "gbookfs4.cv.hp.com");
		serverInfo.put("user", "gbdev");
		serverInfo.put("password", "gbfs_lm4");
		serverInfo.put("publickey", "");
		serverInfo.put("passphrase", "");
		serverInfo.put("protocol", "sftp");
		serverInfo.put("identity", "");
		serverInfo.put("port", "22");
		serverInfo.put("sourceOS", "2");
		*/
		
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
