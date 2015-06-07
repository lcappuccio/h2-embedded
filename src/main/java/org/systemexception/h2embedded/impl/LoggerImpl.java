package org.systemexception.h2embedded.impl;

import org.apache.logging.log4j.LogManager;
import org.systemexception.h2embedded.api.Logger;

/**
 * @author leo
 * @date 06/06/15 02:12
 */
public class LoggerImpl implements Logger {

    private final org.apache.logging.log4j.Logger logger;

    private LoggerImpl(org.apache.logging.log4j.Logger logger) {
        this.logger = logger;
    }

    public static LoggerImpl getFor(Class clazz) {
        org.apache.logging.log4j.Logger logger = LogManager.getLogger(clazz);
        return new LoggerImpl(logger);
    }

    @Override
    public void info(String message) {
        this.logger.info(message);
    }

    @Override
    public void debug(String message) {
        this.logger.debug(message);
    }

    @Override
    public void error(String message, Exception exception) {
        this.logger.error("Error: "+ message + ", Exception: " + exception);
    }
}
