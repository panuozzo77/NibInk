package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DAOOrder extends DAOConnection {
    private PreparedStatement stmt;
    private Connection con;

    public DAOOrder() {
        try {
            con = getConnection(); // Assuming you have a method named getConnection() to establish the database connection
        } catch (SQLException e) {
            System.out.println("Error getting connection in DAOOrder!");
            e.printStackTrace();
        }
    }

    // Method to load an order from the database by ID
    public Order loadOrder(int orderId) {
        String query = "SELECT * FROM Orders WHERE ID = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("ID");
                int user = rs.getInt("User");
                String email = rs.getString("Email");
                int shippingAddress = rs.getInt("Shipping_address");
                int invoiceAddress = rs.getInt("Invoice_address");
                String paymentMethod = rs.getString("Payment_method");
                float amount = rs.getFloat("Amount");
                String status = rs.getString("status");
                String shippingMethod = rs.getString("Shipping_Method");
                Date orderDate = rs.getDate("Order_Date");
                int Progressive_N = rs.getInt("Progressive_N");

                // Create an Order object with the retrieved data
                Order order = new Order(id,Progressive_N, user, email, shippingAddress, invoiceAddress, paymentMethod, amount, status, shippingMethod, orderDate);
                return order;
            }
        } catch (SQLException e) {
            System.out.println("Error loading order from the database!");
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return null; // Return null if the order is not found
    }
    
    public ArrayList<Order> loadAllOrder(int UserId) {
        String query = "SELECT * FROM Orders WHERE User = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, UserId);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Order> order= new ArrayList();
            while(rs.next()) {
            
                int id = rs.getInt("ID");
                int user = rs.getInt("User");
                int Progressive_N = rs.getInt("Progressive_N");
                String email = rs.getString("Email");
                int shippingAddress = rs.getInt("Shipping_address");
                int invoiceAddress = rs.getInt("Invoice_address");
                String paymentMethod = rs.getString("Payment_method");
                float amount = rs.getFloat("Amount");
                String status = rs.getString("status");
                String shippingMethod = rs.getString("Shipping_Method");
                Date orderDate = rs.getDate("Order_Date");

                order.add(new Order(id, Progressive_N, user, email, shippingAddress, invoiceAddress, paymentMethod, amount, status, shippingMethod, orderDate));
                // Create an Order object with the retrieved data
                //Order order = new Order(id, user, email, shippingAddress, invoiceAddress, paymentMethod, amount, status, shippingMethod, orderDate);
                //return order;
            }
            
            return order;
            
        }
        
        catch (SQLException e) {
            System.out.println("Error loading order from the database!");
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return null; // Return null if the order is not found
        
    }
    
    private void closeResources() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing database resources!");
            e.printStackTrace();
        }
    }
    
    public ArrayList<Order> loadOrdersByMostRecent(int startIndex, int counter) {
        ArrayList<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM Orders ORDER BY Order_Date DESC LIMIT ?, ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, startIndex);
            stmt.setInt(2, counter);
            ResultSet rs = stmt.executeQuery();

            orderList = getFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Error loading orders by most recent with pagination!");
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return orderList;
    }
    
    private ArrayList<Order> getFromResultSet(ResultSet rs) {
    	ArrayList<Order> list = new ArrayList<Order>();
    	try {
    		while(rs.next()) {
	            int id = rs.getInt("ID");
	            int user = rs.getInt("User");
	            String email = rs.getString("Email");
	            int shippingAddress = rs.getInt("Shipping_address");
	            int invoiceAddress = rs.getInt("Invoice_address");
	            String paymentMethod = rs.getString("Payment_method");
	            float amount = rs.getFloat("Amount");
	            String status = rs.getString("status");
	            String shippingMethod = rs.getString("Shipping_Method");
	            Date orderDate = rs.getDate("Order_Date");
	            int Progressive_N = rs.getInt("Progressive_N");
	            Order order = new Order(id, Progressive_N, user, email, shippingAddress, invoiceAddress, paymentMethod, amount, status, shippingMethod, orderDate);
	            list.add(order);
    		} 
    	} catch (SQLException e) {
    			System.out.println("Error loading orders!");
                e.printStackTrace();
    		}
    	return list;
    }
    
    public int getOrderCount() {
        int count = 0;

        String sql = "SELECT COUNT(*) AS count FROM Orders";
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error getting order count!");
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return count;
    }
    
    public int getOpenOrdersCount() {
        int count = 0;

        String sql = "SELECT COUNT(*) AS count FROM Orders WHERE status = 'Open'";
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error getting count of open orders!");
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return count;
    }
    
}
