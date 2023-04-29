package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/TSW";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static Connection connection;
	
	static {
	    try {
	        Class.forName(DRIVER_CLASS_NAME);
	        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	    } catch (ClassNotFoundException | SQLException e) {
	        System.err.println("Error connecting to the database: " + e.getMessage());
	    }
	}

	public static Connection getConnection() {
	    return connection;
	}

	public DAOConnection() {}
}



