package com.model;

import java.sql.*;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public  class DAOConnection {
	static PoolProperties p = new PoolProperties();
	static Connection con = null;
	static Statement stmt = null;
	static String username = null;
	static String password = null;


   public static Connection sqlConnection()
   {
	   p.setUrl("jdbc:mysql://localhost:3306/TSW");
	   p.setDriverClassName("com.mysql.cj.jdbc.Driver");
	   p.setUsername(username);
	   p.setPassword(password);
	   DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource(p);
	   try {
		   con = ds.getConnection();
	   } catch(Exception e)
	   {
		   System.out.println("Error Connecting to the DB");
	   }

	   return con;    
   }
   
   public static Statement getStatement()
   {
	   if(con==null)
		   DAOConnection.sqlConnection();
	   if(stmt==null) {
		   try {
			stmt=con.createStatement();
		   } catch (SQLException e) {
			e.printStackTrace();
		   }
	   }
	   return stmt;
   }
   
   public void queryInsert(String query)
   {
	   if(stmt==null)
		   DAOConnection.getStatement();
	   try {
	   stmt.executeUpdate(query); //only INSERT, UPDATE and DELETE commands
	   } catch (SQLException e) {
		   e.printStackTrace();
	   } 
   }
    
   public static void setUsername(String name) {DAOConnection.username=name;}
   
   public static void setPassword(String password) {DAOConnection.password=password;}
    
}

