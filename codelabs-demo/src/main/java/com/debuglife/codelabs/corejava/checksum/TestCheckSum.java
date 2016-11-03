/*
 * The contents of this file are copyright (c) 2016 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.corejava.checksum;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class TestCheckSum {

	public static void main(String[] args) throws Exception {
		checkSumGenerator();
	}

	public static void checkSumGenerator() throws Exception {
		String datafile = "C:\\temp\\helloworld.txt";

		MessageDigest md = MessageDigest.getInstance("MD5");
		FileInputStream fis = new FileInputStream(datafile);
		byte[] dataBytes = new byte[1024];

		int nread = 0;
		while ((nread = fis.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		}

		byte[] mdbytes = md.digest();
		// convert the byte to hex format
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		System.out.println("Digest(in hex format):: " + sb.toString());
	}
}
