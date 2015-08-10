package com.debuglife.codelabs.crazyit.chapter15;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReadFromProcess {
	public static void main(String[] args) throws Exception{
		Process p = Runtime.getRuntime().exec("javac");
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String buff = null;
		while((buff = br.readLine()) != null){
			System.out.println(buff);
		}
	}
}
