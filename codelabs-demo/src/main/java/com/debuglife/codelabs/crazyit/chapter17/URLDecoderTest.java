package com.debuglife.codelabs.crazyit.chapter17;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLDecoderTest {
	public static void main(String[] args) throws Exception{
		test();
	}
	
	public static void test() throws Exception{
		// convert application/x-www-form-urlencoded string to 
		// normal string
		String keyWord = URLDecoder.decode("%B7%E8%BF%F1java", "GBK");
		System.out.println(keyWord);
		
		// convert normal string to application/x-www-form-urlencoded string
		String encoded = URLEncoder.encode("疯狂java", "GBK");
		System.out.println(encoded);
	}
}
