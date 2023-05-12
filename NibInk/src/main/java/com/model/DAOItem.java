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
		con = super.getConnection();
	}
	
	
	public void addItemToDB(Item item)
	{
		String sql="INSERT INTO Items (ID,title,brand,price,VAT,color,dimensions,weight,description) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, item.getCodenumber());
			stmt.setObject(2, item.getTitle());
			stmt.setObject(3, item.getBrand());
			stmt.setObject(4, item.getPrice());
			stmt.setObject(5, item.getVat());
			stmt.setObject(6, item.getColor());
			stmt.setObject(7, item.getDimensions());
			stmt.setObject(8, item.getWeight());
			stmt.setObject(9, item.getDescription());
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
            //System.out.println("Oggetto: "+item.getTitle());
		}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("totalSize: "+items.size());
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
            item.setDescription(rs.getString("description"));
		}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}
}

