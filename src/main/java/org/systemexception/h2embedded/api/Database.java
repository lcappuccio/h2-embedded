package org.systemexception.h2embedded.api;

import java.sql.SQLException;

/**
 * @author leo
 * @date 07/06/15 21:29
 */
public interface Database {

    /**
     * Check is database is running and valid
     *
     * @return the value
     */
    boolean isRunning();

    /**
     * Shuts down the database
     *
     * @return the value
     * @throws SQLException
     */
    boolean shutdown() throws SQLException;

    /**
     * Returns the current date
     *
     * @return the date
     */
    String getCurrentTimeStamp();
}
