package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOItem extends DAOConnection {
	PreparedStatement stmt;
	Connection con;

	public DAOItem () {
		super();
		try {
			con = super.getConnection();
		} catch (SQLException e) {
			System.out.println("Error getting connection in DAOItem!");
			e.printStackTrace();
		}
	}
	
	public void addItemToDB(Item item)
	{
		String sql="INSERT INTO Items (ID,title,price,VAT,color,dimensions,weight,description,type) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, item.getCodenumber());
			stmt.setObject(2, item.getTitle());
			stmt.setObject(3, item.getPrice());
			stmt.setObject(4, item.getVat());
			stmt.setObject(5, item.getColor());
			stmt.setObject(6, item.getDimensions());
			stmt.setObject(7, item.getWeight());
			stmt.setObject(8, item.getDescription());
			stmt.setObject(9, item.getType());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Item> getItems(int startIndex, int count) {
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Items LIMIT ?, ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, startIndex);
            stmt.setInt(2, count);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getFromResultSet(rs);
    }
	
	public void removeItemFromDB(Item item)
	{
		String sql="DELETE FROM Items WHERE ID = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, item.getCodenumber());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public <T> void modifyItemToDB(Item item, String field, T value)
	{
		String sql="UPDATE Items SET ? = ? WHERE ID = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, field);
			stmt.setObject(2, value);
			stmt.setObject(3, item.getCodenumber());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifyItemInDB(Item item) {
	    String sql = "UPDATE Items SET title = ?, price = ?, VAT = ?, color = ?, dimensions = ?, weight = ?, description = ?, type = ? WHERE ID = ?;";
	    try {
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, item.getTitle());
	        stmt.setFloat(2, item.getPrice());
	        stmt.setFloat(3, item.getVat());
	        stmt.setString(4, item.getColor());
	        stmt.setString(5, item.getDimensions());
	        stmt.setFloat(6, item.getWeight());
	        stmt.setString(7, item.getDescription());
	        stmt.setString(8, item.getType());
	        stmt.setString(9, item.getCodenumber());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<Item> getAllItemsFromDB()
	{
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Items";
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch(SQLException e) {
				e.printStackTrace();
		}
		return getFromResultSet(rs);
	}
	
	//orderBy contiene il nome di una colonna. filterField contiene il nome di una colonna. filterValue contiene il valore della colonna
	// 	public ArrayList<Item> getAllItemsFromDB(String orderBy, String filterField, String filterValue, int startIndex, int count){
	public ArrayList<Item> getAllItemsFromDB(String orderBy, String filterField, String filterValue){
		ResultSet rs = null;
		try {
			String sql= "SELECT * FROM Items";
			if(filterField != null && filterValue != null){
				sql += " WHERE " + filterField + " = ?";
			}
			if(orderBy != null){
				sql += " ORDER BY " + orderBy; 
			}
			/* int paramIndex = 1;
			 * sql += " LIMIT ?, ?";
			 */
			stmt = con.prepareStatement(sql);
			if(filterField != null && filterValue != null){
				stmt.setString(1, filterValue);
				//paramIndex++;
			}
			//stmt.setInt(paramIndex, startIndex);
			//stmt.setInt(paramIndex+1, count);
			rs = stmt.executeQuery();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return getFromResultSet(rs);
	}
	
	public Item getItemFromDB(String id)
	{
		ResultSet rs=null;
		String sql="Select * FROM Items WHERE ID = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			rs=stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return getItemFromResultSet(rs);
	}
	
	public int getItemsNumber()
	{
		int number = 0;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) AS Quantity FROM Items ";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
				number = rs.getInt("Quantity");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return number;
	}

	public ArrayList<Item> getFromResultSet(ResultSet rs){
		ArrayList<Item> items = new ArrayList<Item>();
		try {
		while (rs.next())
		{
			Item item = new Item();
            item.setCodenumber(rs.getString("ID"));
            item.setTitle(rs.getString("Title"));
            item.setPrice(rs.getFloat("Price"));
            item.setVat(rs.getFloat("VAT"));
            item.setColor(rs.getString("Color"));
            item.setDimensions(rs.getString("Dimensions"));
            item.setWeight(rs.getFloat("Weight"));
            item.setDescription(rs.getString("description"));
            item.setType(rs.getString("Type"));
            items.add(item);
            System.out.println("Oggetto caricato: "+item.getTitle());
		}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("totalSize: "+items.size());
			return items;
	}
	
	public ArrayList<OrderedItem> getFromResultSet2(ResultSet rs){
		ArrayList<OrderedItem> items = new ArrayList<OrderedItem>();
		try {
			while (rs.next())
			{
				OrderedItem item = new OrderedItem();
				item.setItemId(rs.getString("Item"));
				item.setSize(rs.getString("Size"));
				item.setName(rs.getString("Name"));
	            item.setPrice(rs.getFloat("Price"));
	            item.setVAT(rs.getFloat("VAT"));
	            item.setQuantity(rs.getInt("Quantity"));
	            items.add(item);
	            //System.out.println("Oggetto: "+item.getTitle());
			}
			rs.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		System.out.println("totalSize: "+items.size());
		return items;
	}
	
	private Item getItemFromResultSet(ResultSet rs){
		Item item = new Item();
		try {
		while (rs.next())
		{
			item.setCodenumber(rs.getString("ID"));
			item.setTitle(rs.getString("Title"));
            item.setPrice(rs.getFloat("Price"));
            item.setVat(rs.getFloat("VAT"));
            item.setColor(rs.getString("Color"));
            item.setDimensions(rs.getString("Dimensions"));
            item.setWeight(rs.getInt("Weight"));
            item.setType(rs.getString("Type"));
            item.setDescription(rs.getString("description"));
		}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}
	
	public ArrayList<Item> getRelatedFromDB(float filterValue){
		ResultSet rs = null;
		try {
			String sql= "SELECT * FROM Items";
				sql += " WHERE price >= ?";		
				sql += " ORDER BY price ASC"; 
				sql += " LIMIT 4";
			stmt = con.prepareStatement(sql);
			stmt.setFloat(1, filterValue);
			rs = stmt.executeQuery();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return getFromResultSet(rs);
	}
	
	public ArrayList<Item> getFilteredItems(int startIndex, int count, String filter) {
        ResultSet rs = null;
        try {
        	if(!filter.contains("-")) {
        		String sql = "SELECT * FROM Items WHERE type = ? LIMIT ?, ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, filter);
                stmt.setInt(2, startIndex);
                stmt.setInt(3, count);
        	}
        	else {
        		String[] filters=filter.split("-");
        		String sql = "SELECT * FROM Items WHERE (type = ? OR type = ?) LIMIT ?, ?";
        		stmt = con.prepareStatement(sql);
        		stmt.setString(1, filters[0]);
        		stmt.setString(2, filters[1]);
                stmt.setInt(3, startIndex);
                stmt.setInt(4, count);
        	}
            
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getFromResultSet(rs);
    }
	
	public int getFilteredItemsNumber(String filter)
	{
		int number = 0;
		ResultSet rs = null;
		try {
			if(!filter.contains("-")) {
        		String sql = "SELECT COUNT(*) AS Quantity FROM Items WHERE type = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, filter);
        	}
        	else {
        		String[] filters=filter.split("-");
        		String sql = "SELECT COUNT(*) AS Quantity FROM Items WHERE (type = ? OR type = ?)";
        		stmt = con.prepareStatement(sql);
        		stmt.setString(1, filters[0]);
        		stmt.setString(2, filters[1]);
        	}
			rs = stmt.executeQuery();
			if(rs.next())
				number = rs.getInt("Quantity");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return number;
	}

	public ArrayList<Item> getItemsLike(String filterValue) {
		ResultSet rs = null;
		try {
			String sql= "SELECT * FROM Items WHERE title LIKE ? LIMIT 4";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%"+filterValue+"%");
			rs = stmt.executeQuery();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return getFromResultSet(rs);
		
	}
}