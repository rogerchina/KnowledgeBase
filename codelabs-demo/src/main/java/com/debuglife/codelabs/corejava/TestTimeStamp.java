package com.debuglife.codelabs.corejava;

import java.util.Date;

import javax.xml.crypto.Data;


public class TestTimeStamp {
    public static void main(String[] args){
        Date d = new Date();
        java.sql.Timestamp ts = new java.sql.Timestamp(d.getTime());
        System.out.println(ts);
    }
}
