package com.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class DAOItem extends DAOConnection {
	PreparedStatement stmt;

	public DAOItem () {
		if(DAOConnection.con==null)
			DAOConnection.sqlConnection();
	}
	
	
	public void addItemToDB(Item item)
	{
		String sql="INSERT INTO Product (ID,title,price,VAT,color,dimensions,weight,description) VALUES( ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			stmt = DAOConnection.con.prepareStatement(sql);
			stmt.setObject(1, item.getCodenumber());
			stmt.setObject(2, item.getTitle());
			stmt.setObject(3, item.getPrice());
			stmt.setObject(4, item.getVat());
			stmt.setObject(5, item.getColor());
			stmt.setObject(6, item.getDimensions());
			stmt.setObject(7, item.getWeight());
			stmt.setObject(8, item.getDescription());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void removeItemFromDB(Item item)
	{
		String sql="DELETE FROM Product WHERE ID = ?;";
		try {
			stmt = DAOConnection.con.prepareStatement(sql);
			stmt.setObject(1, item.getCodenumber());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public <T> void modifyItemToDB(Item item, String field, T value)
	{
		String sql="UPDATE Product SET ? = ? WHERE ID = ?;";
		try {
			stmt = DAOConnection.con.prepareStatement(sql);
			stmt.setObject(1, field);
			stmt.setObject(2, value);
			stmt.setObject(3, item.getCodenumber());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Item> getAllItemsFromDB()
	{
		ArrayList<Item> items = new ArrayList<Item>();
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Product";
			stmt = DAOConnection.con.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				Item item = new Item();
				item.setCodenumber(rs.getString("ID"));
				item.setTitle(rs.getString("Title"));
	            item.setPrice(rs.getFloat("Price"));
	            item.setVat(rs.getFloat("VAT"));
	            item.setColor(rs.getString("Color"));
	            item.setDimensions(rs.getString("Dimensions"));
	            item.setWeight(rs.getInt("Weight"));
	            item.setDescription(rs.getString("description"));
	            items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return items;
	}
}
