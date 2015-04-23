package com.debuglife.codelabs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;



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
    
    /**
     * update "TimeStampSynchronized" in the Logging_DataChanges table
     * with current date time in timestamp style.
     * execute updating once every 200 data.
     * 
     * @param List<loggingDataChangeID>
     * @throws Exception
     */
//    public void updateLoggingDataChangeTimeStampSynchronizedByBatch(List<Integer> ids) throws Exception {
//        if(ids == null || ids.size() <= 0){
//            return;
//        }
//        
//        Connection conn = null;
//        Statement stmt = null;
//        try {
//            conn = cm.getConnection();
//            stmt = cm.createStatement(conn);
//            String sql = null;
//            Integer id = null;
//            for(int i=0; i<ids.size(); i++){
//                id = ids.get(i);
//                sql = "update " + GlobalVariable.DATA_BASE_TABLE_NAMESPACE + "." + loggingTableName + " t set t.TimeStampSynchronized='" + new Timestamp(new Date().getTime()) + "' where t.LDC_ID=" + id.intValue();
//                stmt.addBatch(sql);
//                
//                if(i == ids.size()-1){
//                    stmt.executeBatch();
//                    break;
//                }
//                if(i % 200 == 0 && i != 0){
//                    stmt.executeBatch();
//                }
//            }
//            cm.executeUpdateByBatch(stmt);;
//        } catch(Exception e) {
//            logger.error("failed to update TimeStampSynchronized in " + loggingTableName + " table!", e);
//            throw new Exception("failed to update TimeStampSynchronized in " + loggingTableName + " table!", e);
//        } finally {
//            cm.closeStatement(stmt);
//        }
//    }
    
}
