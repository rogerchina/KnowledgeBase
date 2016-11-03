/*
 * The contents of this file are copyright (c) 2015 by medavis GmbH, Karlsruhe, Germany
 */
package com.debuglife.codelabs.corejava.log;


import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

public class Log4j2LoggerAdapter extends MarkerIgnoringBase implements Logger {

    private static final long serialVersionUID = 3608399745407058043L;
    private final transient org.apache.log4j.Logger logger;
    private final String name;

    public Log4j2LoggerAdapter(org.apache.log4j.Logger logger, String name) {
        this.logger = logger;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void warn(String msg) {
        logger.warn(msg);
    }

    @Override
    public void warn(String format, Object arg) {
        if (logger.isEnabledFor(Level.WARN)) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.warn(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        if (logger.isEnabledFor(Level.WARN)) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.warn(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void warn(String format, Object... arguments) {
        if (logger.isEnabledFor(Level.WARN)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            logger.warn(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    @Override
    public void error(String format, Object arg) {
        if (logger.isEnabledFor(Level.ERROR)) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.error(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        if (logger.isEnabledFor(Level.ERROR)) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.error(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void error(String format, Object... arguments) {
        if (logger.isEnabledFor(Level.ERROR)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            logger.error(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }

    @Override
    public void debug(String format, Object arg) {
        if (logger.isEnabledFor(Level.DEBUG)) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.debug(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        if (logger.isEnabledFor(Level.DEBUG)) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.debug(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (logger.isEnabledFor(Level.DEBUG)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            logger.debug(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void debug(String msg, Throwable t) {
        logger.debug(msg, t);
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        logger.trace(msg);
    }

    @Override
    public void trace(String format, Object arg) {
        if (logger.isEnabledFor(Level.TRACE)) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.trace(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        if (logger.isEnabledFor(Level.TRACE)) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.trace(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void trace(String format, Object... arguments) {
        if (logger.isEnabledFor(Level.TRACE)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            logger.trace(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void trace(String msg, Throwable t) {
        logger.trace(msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void info(String format, Object arg) {
        if (logger.isEnabledFor(Level.INFO)) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.info(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        if (logger.isEnabledFor(Level.INFO)) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.info(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void info(String format, Object... arguments) {
        if (logger.isEnabledFor(Level.INFO)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            logger.info(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void info(String msg, Throwable t) {
        logger.info(msg, t);
    }
}