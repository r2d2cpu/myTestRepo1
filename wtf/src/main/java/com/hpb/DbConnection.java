package com.hpb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	private String url;
	private String user;
	private String password;

	public DbConnection(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public Connection connect() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
