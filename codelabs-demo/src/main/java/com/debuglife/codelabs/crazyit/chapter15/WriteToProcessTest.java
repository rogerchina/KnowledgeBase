package com.debuglife.codelabs.crazyit.chapter15;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
/**
 * no passed????
 * @author roger
 *
 */
public class WriteToProcessTest {
	public static void main(String[] args) throws Exception{
		Process p = Runtime.getRuntime().exec("java ReadStandard");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String buff = null;
		while((buff = br.readLine()) != null){
			System.out.println(buff);
		}
		
//		PrintStream ps = new PrintStream(p.getOutputStream());
//		ps.println("haha");
//		ps.println(new WriteToProcessTest());
//		System.out.println("i am here");
	}
}

class ReadStandard{
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		FileOutputStream fos = new FileOutputStream("out.txt");
		PrintStream ps = new PrintStream(fos);
		sc.useDelimiter("\n");
		while(sc.hasNext()){
			ps.println(sc.next());
		}
		
		sc.close();
		fos.close();
		ps.close();
	}
}
