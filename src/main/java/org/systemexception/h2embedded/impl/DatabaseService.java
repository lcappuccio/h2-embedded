package org.systemexception.h2embedded.impl;

import org.systemexception.h2embedded.api.Database;
import org.systemexception.h2embedded.api.Logger;
import org.systemexception.h2embedded.enums.DatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author leo
 * @date 07/06/15 21:30
 */
public class DatabaseService implements Database {

	private static final Logger logger = LoggerImpl.getFor(DatabaseService.class);
	private Connection connection;

	public DatabaseService() {
		try
		{
			Class.forName("org.h2.Driver");
			String url = DatabaseProperties.DATABASE_URL.toString();
			String user = DatabaseProperties.DATABASE_USER.toString();
			String password = DatabaseProperties.DATABASE_PASSWORD.toString();
			connection = DriverManager.getConnection(url, user, password);
			logger.info("Starting database");
		}
		catch(ClassNotFoundException | SQLException e )
		{
			logger.error("Database error", e);
		}
	}

	@Override
	public boolean isRunning() throws SQLException {
		return connection.isValid(500);
	}

	@Override
	public boolean shutdown() throws SQLException {
		connection.close();
		logger.info("Shutting down database");
		return connection.isClosed();
	}
}
