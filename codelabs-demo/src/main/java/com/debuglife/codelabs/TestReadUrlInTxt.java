package com.debuglife.codelabs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TestReadUrlInTxt {

	public void readFile(String filePath) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(filePath));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void readFile2(String filePath) {
		BufferedReader br;
		String read = "";
		try {
			File file = new File(filePath);
			FileReader fileread = new FileReader(file);
			br = new BufferedReader(fileread);
			while ((read = br.readLine()) != null) {
				//read = read.substring(1);
				//String[] reads = read.split("	");
				//read = reads[0].trim();
				//read = read + "0";
				makeImg(read);
				System.out.print(read + "\t\n");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void makeImg(String url) {
		try {
			BufferedInputStream in = new BufferedInputStream(
					new URL(url).openStream());
			int index = url.lastIndexOf("/");
			String sName = url.substring(index, url.length());
					//+ url.substring(50, 65);
			System.out.println("sName = " + sName);
			File img = new File("c:\\test\\pic\\" + sName + ".jpg");
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(img));
			byte[] buf = new byte[2048];
			int length = in.read(buf);
			while (length != -1) {
				out.write(buf, 0, length);
				length = in.read(buf);
			}
			in.close();
			out.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String filePath = "C:\\test\\url.txt";
		// new ReadFileFromTxt().readFile(filePath);
		new TestReadUrlInTxt().readFile2(filePath);
	}
}
