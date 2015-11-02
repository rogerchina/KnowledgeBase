/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.log;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;


public class Log4j2LoggerFactory implements ILoggerFactory {
    private ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap<>();

    public Logger getLogger(String name) {
        // protect against concurrent access of loggerMap
        synchronized(this){
            Logger slf4jLogger = loggerMap.get(name);
            if (slf4jLogger != null) {
                return slf4jLogger;
            } else {
                org.apache.log4j.Logger log4jLogger;
                if (name.equalsIgnoreCase(Logger.ROOT_LOGGER_NAME))
                    log4jLogger = MyLoggerManager.getRootLogger();
                else
                    log4jLogger = MyLoggerManager.getLogger(name);
    
                Logger newInstance = new Log4j2LoggerAdapter(log4jLogger, name);
                Logger oldInstance = loggerMap.putIfAbsent(name, newInstance);
                return oldInstance == null ? newInstance : oldInstance;
            }
        }
    }
}
