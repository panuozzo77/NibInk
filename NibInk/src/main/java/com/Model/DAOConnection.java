package com.Model;

import java.sql.*;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public  class DAOConnection {
	static PoolProperties p = new PoolProperties();
	static Connection con = null;
	static String username = null;
	static String password = null;


   public static Connection sqlConnection()
   {
	   p.setUrl("jdbc:mysql://localhost:3306/TSW");
	   p.setDriverClassName("com.mysql.cj.jdbc.Driver");
	   p.setUsername(username);
	   p.setPassword(password);
	   DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource(p);
	   Connection con = null;
	   try {
		   con = ds.getConnection();
	   } catch(Exception e)
	   {
		   System.out.println("Error Connecting to the DB");
	   }

	   return con;    
   }    
    
   public void setUsername(String name) {this.username=name;}
   
   public void setPassword(String password) {this.password=password;}
   
   //prova
    
}

