package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOShippingMethod extends DAOConnection {
    private PreparedStatement stmt;
    private Connection con;

    public DAOShippingMethod() {
        super();
        try {
            con = super.getConnection();
        } catch (SQLException e) {
            System.out.println("Error getting connection in DAOShippingMethod!");
            e.printStackTrace();
        }
    }
    
    private ShippingMethod getFromResultSet(ResultSet rs) {
    	ShippingMethod sm = new ShippingMethod();
    	try {
    		sm.setName(rs.getString("Name"));
    		sm.setAmount(rs.getFloat("Amount"));
    		sm.setCourier(rs.getString("Courier"));
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return sm;
    }
    
    public List<ShippingMethod> getAllShippingMethods() {
        List<ShippingMethod> shippingMethods = new ArrayList<>();

        try {
            String query = "SELECT * FROM ShippingMethods";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	            shippingMethods.add(getFromResultSet(rs));
	            }
            } catch (SQLException e) {
            	e.printStackTrace();
            }
        return shippingMethods;
        }
    
    public boolean deleteShippingMethod(String name) {
        try {
            String query = "DELETE FROM ShippingMethods WHERE Name=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addShippingMethod(ShippingMethod shippingMethod) {
        try {
            String query = "INSERT INTO ShippingMethods (Name, Amount, Courier) VALUES (?, ?, ?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, shippingMethod.getName());
            stmt.setFloat(2, shippingMethod.getAmount());
            stmt.setString(3, shippingMethod.getCourier());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
