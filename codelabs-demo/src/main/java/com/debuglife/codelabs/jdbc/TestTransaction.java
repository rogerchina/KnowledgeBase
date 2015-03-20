package com.debuglife.codelabs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class TestTransaction {
    private static String userName = "root";
    private static String password = "dcsrocks";
    private static String url = "jdbc:mysql://192.168.230.124:3306/test";
    
    private static Connection conn = null;
    
    public static void main(String[] args) throws Exception{
        testMetadataOrder();
        
    }
    
    public static void testMetadataOrder() throws Exception{
        initConnection();
        
        String sql = "select * from patient";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int n = rsmd.getColumnCount();
        for(int i=1; i<=n; i++){
            System.out.print(rsmd.getColumnName(i) + "\t\t");
        }
        System.out.println();
    }
    
    public void testTransaction() throws Exception{
        initConnection();
        
        // 设置隔离级别
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        conn.setAutoCommit(false);
        
        Statement stmt = conn.createStatement();
        String sql = "insert into patient(name,age) values('Sam',19)";
        for(int i=0; i<10; i++){
            stmt.execute(sql);
        }
        
        System.out.println("start to sleep...");
        Thread.sleep(60000);
        
        conn.commit();
        
        stmt.close();
        conn.close();
    }
    
    public static void initConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, userName, password);
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }
    
}
