package com.debuglife.codelabs.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class ServerSideMain{
    public static void main(String[] args){
        try {  
            PersonService personService=new PersonServiceImpl();  
            //注册通讯端口  
            LocateRegistry.createRegistry(6600);  
            //注册通讯路径  
            Naming.rebind("rmi://192.168.230.118:6600/PersonService", personService);  
            System.out.println("Service Start!");  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }
}
