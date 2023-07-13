package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
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
		//System.out.println("email inserita:"+email+";");
		//System.out.println("password inserita:"+password+";");
		String field = null;
		ResultSet rs = null;
		String sql="SELECT password FROM Users WHERE email = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, email);
			rs = stmt.executeQuery();
			if(rs.next()) {
				field = rs.getString("password");
				//System.out.println("field trovato:"+field+";");
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
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
			return getFromResultSet(rs);
	}
	
	public Customer getCustomerById(int id) {
		String sql="SELECT * FROM Users WHERE ID = ?;";
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
			return getFromResultSet(rs);
	}
	
	private Customer getFromResultSet(ResultSet rs) {
		try {
		if(rs.next()){
			Customer customer = new Customer();
            customer.setName(rs.getString("name"));
            customer.setSurname(rs.getString("surname"));
            customer.setType(rs.getString("type"));
            customer.setPassword(rs.getString("password"));
            customer.setEmail(rs.getString("email"));
            customer.setID(rs.getInt("ID"));
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
		String sql="INSERT INTO Users (email, password, name, surname, type) VALUES (?, ?, ?, ?, ?);";															 
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, customer.getEmail());
			stmt.setObject(2, customer.getPassword());
			stmt.setObject(3, customer.getName());
			stmt.setObject(4, customer.getSurname());
			stmt.setObject(5, customer.getType());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}
	
	public boolean changePassword(int userId, String newPassword) {
        boolean status = false;
		String updateQuery = "UPDATE Users SET Password = ? WHERE ID = ?";
        try {
            stmt = con.prepareStatement(updateQuery);
            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                status = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
	
	public ArrayList<Customer> getAllUsers() {
		String sql="SELECT * FROM Users;";
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
			return getMultipleFromResultSet(rs);
	}
	
	private ArrayList<Customer> getMultipleFromResultSet(ResultSet rs) {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			while(rs.next()){
				Customer customer = new Customer();
	            customer.setName(rs.getString("name"));
	            customer.setSurname(rs.getString("surname"));
	            customer.setType(rs.getString("type"));
	            customer.setPassword(rs.getString("password"));
	            customer.setEmail(rs.getString("email"));
	            customer.setID(rs.getInt("ID"));
	            customers.add(customer);
			}        
		} catch (Exception e) {
		e.printStackTrace();
		}
		return customers;
	}
	
	public boolean deleteCustomerById(int id) {
	    boolean status = false;
	    String sql = "DELETE FROM Users WHERE ID = ?";
	    try {
	        stmt = con.prepareStatement(sql);
	        stmt.setInt(1, id);
	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            status = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return status;
	}
}