package com.debuglife.codelabs.jcr;

import javax.jcr.GuestCredentials;
import javax.jcr.Repository;
import javax.jcr.Session;

import org.apache.jackrabbit.commons.JcrUtils;


public class TestFirstJcr {
    public static void  main(String[] args) throws Exception{
        Repository repository = JcrUtils.getRepository();
        Session session = repository.login(new GuestCredentials());
        try {
            String user = session.getUserID();
            String name = repository.getDescriptor(Repository.REP_NAME_DESC);
            System.out.println("Logged in as " + user + " to a " +  name + " repository.");
            
            System.out.println(System.getProperty("org.apache.jackrabbit.repository.conf"));
            System.out.println(System.getProperty("org.apache.jackrabbit.repository.home"));
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.logout();
        }
    }
}
