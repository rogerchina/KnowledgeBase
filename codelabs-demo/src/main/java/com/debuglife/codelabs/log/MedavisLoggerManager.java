/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.log;

/**
 * 
 */
public class MedavisLoggerManager extends AbstractLoggerManager{

    public void init() throws Exception{
        super.init();
    }
    
    @Override
    public void before() {
        createNewLoggerDynamically();
    }

    @Override
    public void after() {
        //TODO: do some additional operation
    }
    
    private void createNewLoggerDynamically(){
        //TODO: add logger
    }

}
