package com.debuglife.codelabs.appmanage;


public class TestStartUpWithBatCommond {

    public static void main(String[] args) {
        if(args.length>0){
            for(int i=0;i<args.length;i++){
                System.out.println("found config file: " + args[i]);
            }
        } else {
            usage();
        }
        
        printString();
    }
    
    public static void printString(){
        System.out.println("you can see me!!!");
    }

    public static void usage(){
        System.out.println(
                "usage: \r\n"
                + "*********************************************************************************\r\n"
                + "config file is not found, please check to see if there is site-configuration.xml\r\n"
                + "*********************************************************************************\r\n"
        );
    }
}
