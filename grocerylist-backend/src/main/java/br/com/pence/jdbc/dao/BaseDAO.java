package br.com.pence.jdbc.dao;

import java.sql.Connection;

import br.com.pence.jdbc.database.ConnectionFactory;

public class BaseDAO {

	public BaseDAO() {
	}

	public Connection getConnection() {
		return ConnectionFactory.getConnection();
	}

}
