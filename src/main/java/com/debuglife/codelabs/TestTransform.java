package com.debuglife.codelabs;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;

public class TestTransform {
	public static void main(String[] args){
		FileReader fr = null;
		FileWriter fw = null;
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		ByteArrayInputStream bais = null;
		
		//×¢ÊÍ
		int c = 0;
		try{
			fr = new FileReader("C:\\Users\\Roger\\workspaceeclipse\\JavaTest\\src\\com\\hp\\it\\techlab\\TestTransform.java");
			while((c = fr.read()) != -1)
				System.out.print((char)c);
			fr.close();
			
			
			fw = new FileWriter("c:\\test.txt");
			for(int i=0; i<50000; i++){
				fw.write(i);
			}
			fw.close();
			
			fis = new FileInputStream("C:\\Users\\Roger\\workspaceeclipse\\JavaTest\\src\\com\\hp\\it\\techlab\\TestTransform.java");
			// read the file through byte stream
			int ch = 0;
			while((ch = fis.read()) != -1)
				System.out.print((char)ch);
			// read the file through character stream
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String line;
			while((line = br.readLine()) != null)
				System.out.println(line);
			fis.close();
			isr.close();
			br.close();
			
			String str = "Hello World";
			byte[] byteArray = str.getBytes();
			bais = new ByteArrayInputStream(byteArray);
			while((c = bais.read()) != -1)
				System.out.print((char)c);
			
			int a = 3;
			if(a > 2 & a < 4)
				System.out.println(a);
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			//TO-DO
		}
	}
}
