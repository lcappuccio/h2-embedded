package org.systemexception.h2embedded.impl;

import org.systemexception.h2embedded.api.Database;
import org.systemexception.h2embedded.api.Logger;
import org.systemexception.h2embedded.enums.DatabaseProperties;

import java.sql.*;

/**
 * @author leo
 * @date 07/06/15 21:30
 */
public class DatabaseService implements Database {

	private static final Logger logger = LoggerImpl.getFor(DatabaseService.class);
	private Connection connection;

	public DatabaseService() {
		try {
			Class.forName("org.h2.Driver");
			String url = DatabaseProperties.DATABASE_URL.toString();
			String user = DatabaseProperties.DATABASE_USER.toString();
			String password = DatabaseProperties.DATABASE_PASSWORD.toString();
			connection = DriverManager.getConnection(url, user, password);
			logger.info("Starting database");
		} catch (ClassNotFoundException | SQLException e) {
			exceptionHandler(e);
		}
	}

	@Override
	public boolean isRunning() {
		boolean status = false;
		try {
			status = connection.isValid(500);
		} catch (SQLException e) {
			logger.error("Database error", e);
		}
		return status;
	}

	@Override
	public boolean shutdown() throws SQLException {
		connection.close();
		logger.info("Shutting down database");
		return connection.isClosed();
	}

	@Override
	public String getCurrentTimeStamp() {
		String query = "select CURRENT_TIMESTAMP", result = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				result = resultSet.getString(1);
			}
		} catch (SQLException e) {
			exceptionHandler(e);
		}
		return result;
	}

	private void exceptionHandler(Exception ex) {
		logger.error("Database error", ex);
	}
}
