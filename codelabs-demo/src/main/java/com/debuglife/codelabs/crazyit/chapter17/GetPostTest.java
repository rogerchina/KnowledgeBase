package com.debuglife.codelabs.crazyit.chapter17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class GetPostTest {
	public static void main(String[] args) throws Exception{
		String str = new GetPostTest().sendGet("http://www.baidu.com", "");
		System.out.println(str);
		
		String str1 = new GetPostTest().sendGet("http://www.baidu.com", "name=hello");
		System.out.println(str1);
	}
	
	public String sendGet(String url, String param) throws Exception{
		String result = "";
		String urlPath = url + "?" + param;
		
		URL realUrl = new URL(urlPath);
		URLConnection conn = realUrl.openConnection();
		conn.setRequestProperty("Accept", "*/*");
		conn.setRequestProperty("connectioin", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		conn.connect();
		Map<String, List<String>> headers = conn.getHeaderFields();
		for(String key : headers.keySet()){
			System.out.println(key + "--->" + headers.get(key));
		}
		
		try(
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8")))
		{
			String line;
			while((line = reader.readLine()) != null){
				result += "\n" + line;
			}
		}
		return result;
	}
	
	public String sendPost(String url, String param) throws Exception{
		String result = "";
//		String urlPath = url + "?" + param;
		
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		conn.setRequestProperty("Accept", "*/*");
		conn.setRequestProperty("connectioin", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		try(PrintWriter out = new PrintWriter(conn.getOutputStream())){
			out.print(param);
			out.flush();
		};
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))){
			String line;
			while((line = reader.readLine()) != null){
				result += "\n" + line;
			}
		}
		return result;
	}
}
