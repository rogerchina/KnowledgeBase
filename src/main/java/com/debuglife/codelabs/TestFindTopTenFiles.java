package com.debuglife.codelabs;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class TestFindTopTenFiles {
	//used to store all files found out
	private static List<FileEntry> list = new ArrayList<FileEntry>();
	private static int NUM = 10;
	
	/*
	 * main() for testing
	 */
	public static void main(String[] args){
		String path = "C:\\test\\test2";
		findAllFileList(path);
		printTopTenFile();
	}

	/*
	 * print the top 10 large files.
	 */
	@SuppressWarnings("unchecked")
	private static void printTopTenFile() {
		if(list.size() > 0){
			Collections.sort(list);
			for(int i=0; i<(list.size()>NUM?NUM:list.size()); i++){
				System.out.println(list.get(i));
			}
		}
	}
	
	/*
	 * search for the path to find out all the files and deposit
	 * into file list
	 */
	public static void findAllFileList(String path){
		if(path == null || "".equals(path)){
			System.out.println("Given path is empty or invalid!");
			return;
		}
			
		File f = new File(path);
		if(f != null && f.exists()){
			File [] fileArray = null;
			if(f.isFile()){
				if(list.size() > NUM){
					Collections.sort(list);
					list.remove(list.size()-1);
				}
				list.add(new FileEntry(f.getName(), f.length()));
			}
			else{
				fileArray = f.listFiles();
				if(fileArray.length > 0){
					File ff = null;
					for(int i=0; i<fileArray.length; i++){
						ff = fileArray[i];
						findAllFileList(ff.getAbsolutePath());
					}
				}
			}
		}else{
			System.out.println("Given path is not exists!");
		}
	}
}

/*
 * define a FileEntry class to indicate that a object with fileName
 * and fileSize attributes.
 */
@SuppressWarnings("rawtypes")
class FileEntry implements Comparable{
	private String fileName;
	private long fileSize;
	
	public FileEntry(String fileName, long fileSize){
		this.fileName = fileName;
		this.fileSize = fileSize;
	}
	
	public int compareTo(Object o) {
		FileEntry fe = null;
		if(o instanceof FileEntry){
			fe = (FileEntry)o;
			return (int)(fe.fileSize - this.fileSize);
		}
		return 0;
	}
	
	@Override
	public String toString(){
		return "==fileName:[" + this.fileName + "] " + "fileSize:[" + this.fileSize + "]==";
	}
}