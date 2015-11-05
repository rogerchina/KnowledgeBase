/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.log;

import org.apache.log4j.LogManager;


abstract class AbstractLoggerManager extends LogManager{
    
    protected void init() throws Exception{
        before();
        
        after();
    }

    protected abstract void before();

    protected abstract void after();

}
