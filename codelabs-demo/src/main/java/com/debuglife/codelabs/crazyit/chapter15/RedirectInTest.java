package com.debuglife.codelabs.crazyit.chapter15;

import java.io.FileInputStream;
import java.util.Scanner;

public class RedirectInTest {
	public static void main(String[] args) throws Exception{
		FileInputStream fis = new FileInputStream("/home/roger/workspace/codelabs/codelabs-demo/src/main/java/com/debuglife/codelabs/crazyit/chapter15/RedirectInTest.java");
		System.setIn(fis);
		Scanner scan = new Scanner(System.in);
		scan.useDelimiter("\n");
		while(scan.hasNext()){
			System.out.println(scan.next());
		}
		
		fis.close();
		scan.close();
	}
}
