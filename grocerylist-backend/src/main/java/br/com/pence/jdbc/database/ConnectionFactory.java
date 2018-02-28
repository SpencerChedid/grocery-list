package br.com.pence.jdbc.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Singleton class that creates and returns a MySQL database connection.
 *  
 * @author Spencer Chedid
 */
public class ConnectionFactory {

	public static final String URL = "jdbc:mysql://localhost/grocerylist";
	public static final String USER = "root";
	public static final String PASSWORD = "afgspspc";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static Connection connection;

	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		} else {
			return createConnection();
		}
	}
	
	private static Connection createConnection() {
		try {
			System.out.println("### Initiating MySQL database connection ###\nLoading driver...");
			Class.forName(DRIVER_CLASS);
			System.out.println("Driver loaded successfully!");
			System.out.println("Stablishing database connection...");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Database connection stablished successfully!");
		} catch (Exception e) {
			System.out.println("ERROR: Unable to connect to Database.");
		}
		
		return connection;
	}

}
