package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DAOOrder extends DAOConnection {
    private PreparedStatement stmt;
    private Connection con;

    public DAOOrder() {
        try {
            con = getConnection(); 
        } catch (SQLException e) {
            System.out.println("Error getting connection in DAOOrder!");
            e.printStackTrace();
        }
    }

    private int getLatestOrderId() {
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT id FROM Orders ORDER BY id DESC LIMIT 1");
    		if(rs.next()) {
    			return rs.getInt("id");
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
		}
    	return 0;
    }
    
    public boolean saveOrder(Order toSave) {
    	boolean status = true;
    	int orderId = getLatestOrderId() + 1;
    	try {
    		String query = "INSERT INTO Orders (ID, User, Email, Shipping_address, Invoice_address, Payment_method, Amount, status, Shipping_Method, Shipping_Cost, Order_Date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE())";
    		stmt = con.prepareStatement(query);
            stmt.setInt(1, orderId);
            stmt.setInt(2, toSave.getUser());
            stmt.setString(3, toSave.getEmail());
            stmt.setString(4, toSave.getShippingAddress());
            stmt.setString(5, toSave.getInvoiceAddress());
            stmt.setString(6, toSave.getPaymentMethod());
            stmt.setFloat(7, toSave.getAmount());
            stmt.setString(8, "pending");
            stmt.setString(9, toSave.getShippingMethod());
            stmt.setFloat(10, toSave.getShippingCost());
            stmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		status = false;
    	}
    	saveOrderedItems(orderId, toSave.getPurchased());
    	return status;
    }
    
    public void saveOrderedItems(int orderNumber, ArrayList<OrderedItem> orderedItems) {
        try {
            String query = "INSERT INTO OrderedProducts (OrderNumber, Item, Size, Name, Price, VAT, Quantity) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);

            for (OrderedItem orderedItem : orderedItems) {
                stmt.setInt(1, orderNumber);
                stmt.setString(2, orderedItem.getItemId());
                stmt.setString(3, orderedItem.getSize());
                stmt.setString(4, orderedItem.getName());
                stmt.setFloat(5, orderedItem.getPrice());
                stmt.setFloat(6, orderedItem.getVAT());
                stmt.setInt(7, orderedItem.getQuantity());
                stmt.addBatch();
            }

            stmt.executeBatch();
        } catch (SQLException e) {
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
                String shippingAddress = rs.getString("Shipping_address");
                String invoiceAddress = rs.getString("Invoice_address");
                String paymentMethod = rs.getString("Payment_method");
                float amount = rs.getFloat("Amount");
                String status = rs.getString("status");
                String shippingMethod = rs.getString("Shipping_Method");
                float shippingCost = rs.getFloat("Shipping_Cost");
                Date orderDate = rs.getDate("Order_Date");
                Order order = new Order(id, user, email, shippingAddress, invoiceAddress, paymentMethod, amount, status, shippingMethod, shippingCost, orderDate);
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
    
    public ArrayList<OrderedItem> loadOrderedItem(int orderId) {
        String query = "SELECT * FROM OrderedProducts WHERE OrderNumber = ?";
        DAOItem db = new DAOItem();
        ArrayList<OrderedItem> orderedItems = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            orderedItems = db.getFromResultSet2(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(); // Close resources after retrieving the ordered items
        }
        return orderedItems;
    }
    
    public ArrayList<Order> loadAllOrder(String UserId) {
        String query = "SELECT * FROM Orders WHERE User = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, UserId);
            ResultSet rs = stmt.executeQuery();
            return getFromResultSet(rs);
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
    
    public ArrayList<Order> loadOrdersByDateRange(String startDate, String endDate) {
        ArrayList<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM Orders WHERE Order_Date >= ? AND Order_Date <= ? ORDER BY Order_Date DESC";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            ResultSet rs = stmt.executeQuery();

            orderList = getFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Error loading orders by date range!");
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return orderList;
    }
    
    public boolean changeOrderStatus(String orderId, String status) {
    	boolean result = false;
    	String query;  
    	if(!status.equals("refund")) 
    		  query = "UPDATE Orders SET status = ? WHERE ID = ?";
    	else
    		query = "UPDATE Orders SET status = ?, Amount = 0 WHERE ID = ?";
    	  try {
    	    stmt = con.prepareStatement(query);
    	    stmt.setString(1, status);
    	    stmt.setString(2, orderId);
    	    int rowsUpdated = stmt.executeUpdate();

    	    if (rowsUpdated > 0) 
    	      result = true;
    	  } catch (SQLException e) {
    	    System.out.println("Error updating order status in the database!");
    	    e.printStackTrace();
    	  } finally {
    	    closeResources();
    	  }
    	  return result;
    	}
    
    private ArrayList<Order> getFromResultSet(ResultSet rs) {
    	ArrayList<Order> list = new ArrayList<Order>();
    	try {
    		while(rs.next()) {
	            int id = rs.getInt("ID");
	            int user = rs.getInt("User");
	            String email = rs.getString("Email");
	            String shippingAddress = rs.getString("Shipping_address");
	            String invoiceAddress = rs.getString("Invoice_address");
	            String paymentMethod = rs.getString("Payment_method");
	            float amount = rs.getFloat("Amount");
	            String status = rs.getString("status");
	            String shippingMethod = rs.getString("Shipping_Method");
	            float shippingCost = rs.getFloat("Shipping_Cost");
	            Date orderDate = rs.getDate("Order_Date");
	            Order order = new Order(id, user, email, shippingAddress, invoiceAddress, paymentMethod, amount, status, shippingMethod, shippingCost, orderDate);
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
        } 
        return count;
    }
    //status = {pending, confirmed, canceled, shipped, delivered, toBeReturned, refund}
    public int getOpenOrdersCount(String status) {
        int count = 0;

        String sql = "SELECT COUNT(*) AS count FROM Orders WHERE status = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, status);
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