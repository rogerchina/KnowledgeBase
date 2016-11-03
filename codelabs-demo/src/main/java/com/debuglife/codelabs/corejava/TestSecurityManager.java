package com.debuglife.codelabs.corejava;

import java.io.FilePermission;


public class TestSecurityManager {

    public static void main(String[] args) {
        setSecurityManagerForApp();
    }

    public static void setSecurityManagerForApp(){
        System.out.println(System.getSecurityManager());
        SecurityManager safeManager = System.getSecurityManager();
        safeManager.checkPermission(new FilePermission("c:\\", "read"));
        System.out.println("end");
    }
}
