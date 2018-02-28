package br.com.pence.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

	private static Connection connection = null;
	
	public static Connection getConnection() {

		String url = "jdbc:mysql://localhost:3306/grocerylist";
		String username = "root";
		String password = "afgspspc";

		System.out.println("Loading driver...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}

		System.out.println("Connecting database...");
		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

		return connection;
	}

	public static void closeConnection() {
		System.out.println("Closing connection...");
		try {
			if (connection != null) {
				connection.close();
			}
			System.out.println("Connection closed!");
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

}
