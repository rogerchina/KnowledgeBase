package com.debuglife.codelabs.crazyit.chapter18;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

public class URLClassLoaderTest {
	
	private static Connection conn;
	
	public static Connection getConn(String url, String userName, String password) throws Exception{
		if(conn == null){
			URL[] urls = {new URL("file:mysql-connector-java-5.1.34.jar")};
			//when parent classloader is null, the created class loader will not find the
			//class specified by urls
			//URLClassLoader urlclassLoader = new URLClassLoader(urls,null);
			URLClassLoader urlclassLoader = new URLClassLoader(urls);
			Driver driver = (Driver)urlclassLoader.loadClass("com.mysql.jdbc.Driver").newInstance();
			Properties pros = new Properties();
			pros.setProperty("user", userName);
			pros.setProperty("password", password);
			conn = driver.connect(url, pros);
		}
		return conn;
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(getConn("jdbc:mysql://localhost:3306/mysql","root","root"));
	}
}
