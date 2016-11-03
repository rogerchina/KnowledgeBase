/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.corejava.log;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SLF4JTest {
    private static Logger logger = LoggerFactory.getLogger("TestLogger");
//    static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SLF4JTest.class);
    static{
        //PropertyConfigurator.configure(SLF4JTest.class.getResource("log4j.properties"));
    }
    
    public static void main(String[] args){
        
        logger.info("[info message]");
        logger.error("[error message]");
        //logger.info("[info message]{},{},{},{}", "abc", false, 123,new SLF4JTest());
    }
}
