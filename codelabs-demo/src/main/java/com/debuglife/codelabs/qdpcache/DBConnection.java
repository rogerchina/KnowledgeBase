package com.debuglife.codelabs.qdpcache;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.util.Map;

public class DBConnection {
//	public static String dbUrl = "";
//	public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	public static String userName = "";
//	public static String password = "";
//	
	public static String environmentTypeNm = "ITG";
	public static String productTypeNm = "QDP";
	public static String activeFg = "Y";
	
	private static Connection con;
	
	public static void initData(Map<String,String> initParams){
		if(initParams!=null){
//			if(initParams.get("dbUrl")!=null){
//				dbUrl = initParams.get("dbUrl");
//			}
//			if(initParams.get("driver")!=null){
//				driver = initParams.get("driver");
//			}
//			if(initParams.get("userName")!=null){
//				userName = initParams.get("userName");
//			}
//			if(initParams.get("password")!=null){
//				password = initParams.get("password");
//			}
			if(initParams.get("environmentTypeNm")!=null){
				environmentTypeNm = initParams.get("environmentTypeNm");
			}
			if(initParams.get("productTypeNm")!=null){
				productTypeNm = initParams.get("productTypeNm");
			}
			if(initParams.get("activeFg")!=null){
				activeFg = initParams.get("activeFg");
			}
		}
	}
	public static void setDbConnection(Connection conn){
		con = conn;
	}
	public static Connection getDbConnection(){
//		if(con==null){
//			synchronized(DBConnection.class){
//				if(con==null)
//					try {
//						Class.forName(driver);
//						con = DriverManager.getConnection(dbUrl, userName, password);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//			}
//		}
		
		if(con==null){
			throw new NullPointerException("DataBase connection is null, please set connection before operation.");
		}
		return con;
	}
	
//	public static void closeConnection(){
//		if(con!=null){
//			synchronized(DBConnection.class){
//				if(con!=null)
//					try{
//						con.close();
//					}catch(Exception e){
//						e.printStackTrace();
//					}
//			}
//		}
//	}
}
