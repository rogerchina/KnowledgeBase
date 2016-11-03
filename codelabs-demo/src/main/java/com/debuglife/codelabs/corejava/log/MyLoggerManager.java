/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.corejava.log;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 * better refer to the implementation in the vaddin-jdk for slf4j adapter.
 */
public class MyLoggerManager extends LogManager {
    
    private static String pattern = "[%-5p] %d [%c] (%t) - %m%n";
    
    static{
        try {
            // do some customized configuration.
            // here, add some logger according to some id dynamically.
            initLogger();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void initLogger() throws Exception{
        createNewLoggerByID("TestLogger");
    }
    
    public static Logger getLoggerById(String id) throws Exception{
        return Logger.getLogger(id);
    }
    
    /*
     * dynamically to add Logger according to some argument.
     * e.g. id.
     */
    private static void createNewLoggerByID(String id) throws Exception{
        Logger logger = getLoggerById(id);
        Layout patternLayoutForAll = new PatternLayout(pattern);
        String logFileFullPathForAll = "d:/test/log/all.log";
        RollingFileAppender fileAppenderForAll = new RollingFileAppender(patternLayoutForAll, logFileFullPathForAll, true);
        fileAppenderForAll.setMaxFileSize("10MB");
        fileAppenderForAll.setMaxBackupIndex(20);
        logger.addAppender(fileAppenderForAll);
        logger.setLevel(Level.INFO);
        
        Layout patternLayoutForLimited = new PatternLayout(pattern);
        String logFileFullPathForLimited = "d:/test/log/error.log";
        RollingFileAppender fileAppenderForLimited = new RollingFileAppender(patternLayoutForLimited, logFileFullPathForLimited, true);
        fileAppenderForLimited.setMaxFileSize("10MB");
        fileAppenderForLimited.setMaxBackupIndex(20);
        fileAppenderForLimited.setThreshold(Level.WARN);
        logger.addAppender(fileAppenderForLimited);
    }
}
