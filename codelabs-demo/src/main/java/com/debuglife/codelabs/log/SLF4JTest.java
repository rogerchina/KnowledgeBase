/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SLF4JTest {
    private static Logger logger = LoggerFactory.getLogger(SLF4JTest.class);
    
    public static void main(String[] args){
        
        logger.error("[info message]");
        logger.info("[info message]{},{},{},{}", "abc", false, 123,new SLF4JTest());
        logger.debug("[debug message]");
        logger.trace("[trace message]");
        System.out.println("hello");
    }
}
