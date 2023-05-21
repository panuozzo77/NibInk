package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DAOCustomer extends DAOConnection {
	PreparedStatement stmt;
	Connection con;
	
	public DAOCustomer () {
		super();
		try {
			con = super.getConnection();
		} catch (SQLException e) {
			System.out.println("Error getting connection in DAOCustomer!");
			e.printStackTrace();
		}
	}
	
	public int checkLogin(String email, String password)
	{
		System.out.println("email inserita:"+email+";");
		System.out.println("password inserita:"+password+";");
		String field = null;
		ResultSet rs = null;
		String sql="SELECT password FROM Users WHERE email = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, email);
			rs = stmt.executeQuery();
			if(rs.next()) {
				field = rs.getString("password");
				System.out.println("field trovato:"+field+";");
			}
			else return 0; //user does not exists
		} catch (Exception e) {
			e.printStackTrace();
		} 
			if(Objects.equals(field, password)) {
				return 1; //user correctly logged
				}
			if(!Objects.equals(field, password)) {
				return 2; //the user exists but password mismatch
				}
			return 0;
	}
	
	public Customer getCustomerByEmail(String email) {
		String sql="SELECT * FROM Users WHERE email = ?;";
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, email);
			rs = stmt.executeQuery();
			if(rs.next()){
				Customer customer = new Customer();
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setType(rs.getString("type"));
                customer.setPassword(rs.getString("password"));
                customer.setEmail(rs.getString("email"));
                return customer;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addCustomer(Customer customer)
	{
		boolean status = true;
		String sql="INSERT INTO Users (email, password, name, surname, address, city, cap, type) VALUES ( ?, ?, ?, ?, ?, ?, ?, 'registered');";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, customer.getEmail());
			stmt.setObject(2, customer.getPassword());
			stmt.setObject(3, customer.getName());
			stmt.setObject(4, customer.getSurname());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}
}