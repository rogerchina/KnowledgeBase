package com.debuglife.codelabs.crazyit.chapter17;

import java.net.InetAddress;

public class InetAdressTest {
	public static void main(String[] args) throws Exception{
		test();
	}
	
	public static void test() throws Exception{
		InetAddress address = InetAddress.getByName("www.crazyit.org");
		System.out.println("the host is reachable? " + address.isReachable(2000));
		System.out.println("the host ip address: " + address.getHostAddress());
		System.out.println(address.getCanonicalHostName());
		
		InetAddress local = InetAddress.getByAddress(new byte[]{127,0,0,1});
		System.out.println("the local is reachable? " + local.isReachable(2000));
		System.out.println(local.getCanonicalHostName());
	}
}
