package br.com.pence.jdbc.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {

	private static final String OBJECT_TYPE_CONNECTION = "connection";
	private static final String OBJECT_TYPE_PREPARED_STATEMENT = "prepared statement";
	private static final String OBJECT_TYPE_RESULT_SET = "result set";
	
	/*
	public static void close(Connection connection) throws SQLException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new SQLException("Cannot close the connection!");
			}
		}
	}

	public static void close(Statement statement) throws SQLException {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new SQLException("Cannot close the connection!");
			}
		}
	}

	public static void close(ResultSet resultSet) throws SQLException {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new SQLException("Cannot close the connection!");
			}
		}
	}
	*/
	
	public static void close(Object object) throws SQLException {
		if (object != null) {
			String objectType = null;
			try {
				if (object instanceof Connection) {
					objectType = OBJECT_TYPE_CONNECTION;
					((Connection) object).close();
				} else if (object instanceof PreparedStatement) {
					objectType = OBJECT_TYPE_PREPARED_STATEMENT;
					((PreparedStatement) object).close();
				} else if (object instanceof ResultSet) {
					objectType = OBJECT_TYPE_RESULT_SET;
					((ResultSet) object).close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException("error: Unable to close the " + objectType + ".");
			}

		}
	}
}