package org.systemexception.h2embedded.api;

/**
 * @author leo
 * @date 06/06/15 02:07
 */
public interface Logger {

    /**
     * Info level
     *
     * @param message the message to log
     */
    void info(String message);

    /**
     * Debug level
     *
     * @param message the message to log
     */
    void debug(String message);

    /**
     * Error level
     *
     * @param message the message to log
     */
    void error(String message, Exception exception);
}
