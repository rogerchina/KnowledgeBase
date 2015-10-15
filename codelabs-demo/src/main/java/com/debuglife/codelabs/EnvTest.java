/**
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs;

import java.util.Map;


public class EnvTest {
    public static void main(String[] args){
        System.out.println(System.getenv("G4M_CONFIG"));
//        Map<String, String> map = System.getenv();
//        for(Map.Entry<String,String> entry : map.entrySet()){
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
    }
}
