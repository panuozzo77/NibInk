package com.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DAOItem extends DAOConnection {
	PreparedStatement stmt;

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
		PreparedStatement stmt;
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
		PreparedStatement stmt;
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
}
