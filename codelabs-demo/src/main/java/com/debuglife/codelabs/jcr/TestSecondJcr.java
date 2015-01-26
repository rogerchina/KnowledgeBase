package com.debuglife.codelabs.jcr;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;


public class TestSecondJcr {
    public static void  main(String[] args) throws Exception{
        Repository repository = JcrUtils.getRepository();
        Session session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
        try {
            Node root = session.getRootNode();
            
            // Store the content
            Node hello = root.addNode("hello");
            Node world = hello.addNode("world");
            world.setProperty("message", "Hello, World!!!");
            
            // Retrieve the content
            Node node = root.getNode("hello/world");
            System.out.println(node.getPath());
            System.out.println(node.getProperty("message").getString());
            
            // Remove the content
            root.getNode("hello").remove();
            session.save();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.logout();
        }
    }
}
