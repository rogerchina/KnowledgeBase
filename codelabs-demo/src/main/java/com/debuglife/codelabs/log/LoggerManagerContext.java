/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.log;


public interface LoggerManagerContext {
    
    void init() throws Exception;
    
    void before();
    
    void after();
}
