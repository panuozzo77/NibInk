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
		String sql="INSERT INTO Items (ID,title,price,VAT,color,dimensions,weight,description) VALUES( ?, ?, ?, ?, ?, ?, ?, ?);";
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
		String sql="DELETE FROM Items WHERE ID = ?;";
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
		String sql="UPDATE Items SET ? = ? WHERE ID = ?;";
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
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Items";
			stmt = DAOConnection.con.prepareStatement(sql);
			rs = stmt.executeQuery();
		} catch(SQLException e) {
				e.printStackTrace();
		}
		return getFromResultSet(rs);
	}
	
	//orderBy contiene il nome di una colonna. filterField contiene il nome di una colonna. filterValue contiene il valore della colonna
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
			stmt = DAOConnection.con.prepareStatement(sql);
			if(filterField != null && filterValue != null){
				stmt.setString(1, filterValue);
			}
			rs = stmt.executeQuery();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return getFromResultSet(rs);
	}
	
	public int getItemsNumber()
	{
		int number = 0;
		ResultSet rs = null;
		String sql = "SELECT COUNT * FROM Items AS Quantity";
		try {
			stmt = DAOConnection.con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
				number = rs.getInt("Quantity");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return number;
	}

	private ArrayList<Item> getFromResultSet(ResultSet rs){
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
            item.setWeight(rs.getInt("Weight"));
            item.setDescription(rs.getString("description"));
            items.add(item);
		}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return items;
	}
}

