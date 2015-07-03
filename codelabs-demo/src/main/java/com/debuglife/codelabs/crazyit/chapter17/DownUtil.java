package com.debuglife.codelabs.crazyit.chapter17;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownUtil {
	// resource url 
	private String path;
	// save path
	private String targetFilePath;
	// number of download file
	private int threadNum;
	// thread array for downloading
	private DownloadThread[] threads;
	// the total file size;
	private int fileSize;
	
	public DownUtil(String path, String targetFilePath, int threadNum){
		this.path = path;
		this.threadNum = threadNum;
		this.threads = new DownloadThread[threadNum];
		this.targetFilePath = targetFilePath;
	}
	
	public void download() throws Exception{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg,"
				+ "application/x-shockwave-flash, application/xaml+xml,"
				+ "application/vnd.ms-xpsdocument, application/x-ms-xbap,"
				+ "application/x-ms-application, application/vnd.ms-excel,"
				+ "application/vnd.ms-powerpoint, application/msword, */*");
		conn.setRequestProperty("Accept-Language", "zh-CN");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Connection", "Keep-Alive");
		
		fileSize = conn.getContentLength();
		conn.disconnect();
		int currentPartSize = fileSize / threadNum +1;
		RandomAccessFile file = new RandomAccessFile(targetFilePath, "rw");
		file.setLength(fileSize);
		file.close();
		for(int i=0; i<threadNum; i++){
			int startPos = i * currentPartSize;
			RandomAccessFile currentPart = new RandomAccessFile(targetFilePath,"rw");
			currentPart.seek(startPos);
			threads[i] = new DownloadThread(startPos, currentPartSize, currentPart);
			threads[i].start();
		}
	}
	
	public double getCompleteRate(){
		int sumSize = 0;
		for(int i=0; i<threadNum; i++){
			sumSize += threads[i].getLength();
		}
		return sumSize * 1.0 / fileSize;
	}
	
	private class DownloadThread extends Thread{
		private int startPos;
		private int currentPartSize;
		private RandomAccessFile currentPart;
		private int length;
		
		public DownloadThread(int startPos, int currentPartSize, RandomAccessFile currentPart){
			this.startPos = startPos;
			this.currentPartSize = currentPartSize;
			this.currentPart = currentPart;
		}
		
		@Override
		public void run() {
			try {
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setConnectTimeout(5 * 1000);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg,"
						+ "application/x-shockwave-flash, application/xaml+xml,"
						+ "application/vnd.ms-xpsdocument, application/x-ms-xbap,"
						+ "application/x-ms-application, application/vnd.ms-excel,"
						+ "application/vnd.ms-powerpoint, application/msword, */*");
				conn.setRequestProperty("Accept-Language", "zh-CN");
				conn.setRequestProperty("Charset", "UTF-8");
				
				InputStream is = conn.getInputStream();
				is.skip(startPos);
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				while(length < currentPartSize && (hasRead = is.read(buffer)) != -1){
					currentPart.write(buffer);
					length += hasRead;
				}
				currentPart.close();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public int getLength(){
			return length;
		}
	}
}




