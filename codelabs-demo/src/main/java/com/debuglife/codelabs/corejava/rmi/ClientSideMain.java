package com.debuglife.codelabs.corejava.rmi;

import java.rmi.Naming;
import java.util.List;


public class ClientSideMain{
    public static void main(String[] args){
        try {  
            PersonService personService=(PersonService)Naming.lookup("rmi://127.0.0.1:6600/PersonService");
            List<PersonEntity> personList = personService.getList();
            for(PersonEntity person:personList){  
                System.out.println("ID:"+person.getId()+" Age:"+person.getAge()+" Name:"+person.getName());  
            }  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }
}
