package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

public class DAOVariant extends DAOConnection {
	PreparedStatement stmt;
	Connection con;
	
	public DAOVariant()
	{
		super();
		con = super.getConnection();
	}
	
	public void addVariantToDB(ItemVariant variant)
	{
		String sql="INSERT INTO Quantities (Item,size,quantity) VALUES(?,?,?);";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, variant.getItem().getCodenumber());
			for(Entry<String, Integer> entry : variant.getVariants().entrySet()) {
				stmt.setObject(2, entry.getKey());
				stmt.setObject(3, entry.getValue());
				stmt.executeUpdate();
			} 
		}catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	public void addVariantToDB(String item, String size, int quantity)
	{
		String sql="INSERT INTO Quantities (Item,size,quantity) VALUES(?,?,?);";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, item);
			stmt.setObject(2, size);
			stmt.setObject(2, quantity);
			stmt.executeUpdate();
		}catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	//it removes all the variants of an item
	public void removeVariantsFromDB(ItemVariant variant)
	{
		String sql="DELETE FROM Quantities WHERE Item = ? AND size = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, variant.getItem().getCodenumber());
			for(Entry<String, Integer> entry : variant.getVariants().entrySet()) {
				stmt.setObject(2, entry.getKey());
				stmt.executeUpdate();
			} 
		}catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	//it removes all the variants of an item
		public void removeVariantsFromDB(String itemId)
		{
			String sql="DELETE FROM Quantities WHERE Item = ?;";
			try {
				stmt = con.prepareStatement(sql);
				stmt.setObject(1, itemId);
				stmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		
	//it only removes one variant of the item
	public void removeVariantFromDB(String item, String size)
	{
		String sql="DELETE FROM Quantities WHERE Item = ? AND size = ?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, item);
			stmt.setObject(2, size);
			stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	public Map<String, ItemVariant> getAllVariantsFromDB()
	{
		Map<String, ItemVariant> itemVariants = new HashMap<String, ItemVariant>();
		String sql = "SELECT * FROM Quantities JOIN Items ON Quantities.Item = Items.ID";
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			while(rs.next()) {
				String itemId = rs.getString("ID");
				String size = rs.getString("size");
				int quantity = rs.getInt("quantity");
				
				ItemVariant variant;
				if(itemVariants.containsKey(itemId)) //if the element exists
					variant = itemVariants.get(itemId); //i load from the itemVariants Map the element to get the 
				else { //else it doesn't exists yet
					DAOItem db = new DAOItem();
					Item item = db.getItemFromDB(String.valueOf(itemId)); //i load the single item from the db
					variant = new ItemVariant(item); //i define a new variant class
				}
				variant.addQuantity(size, quantity); //i define it's size and quantity
				itemVariants.put(String.valueOf(itemId), variant); //i re-add it to the map with the new sizes and quantities
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemVariants;
	}
	
	public int getVariantsNumber()
	{
		int number = 0;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) AS Quantity FROM Quantities";
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
	
	
	
	
	
	
	
	//Roba aggiunta da me
	public Map<String, Integer> loadSingleItemVariants(Item item) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		ResultSet rs = null;
		String sql = "SELECT size, quantity FROM Quantities WHERE item = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, item.getCodenumber());
			rs=stmt.executeQuery();
			while(rs.next()){
				map.put(rs.getString("size"), rs.getInt("quantity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public int getQuantity(Item item) {
		ResultSet rs = null;
		String sql = "Select SUM(Quantity) AS Disponibility from Quantities WHERE item = ? GROUP BY item";
		int quantity=0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, item.getCodenumber());
			rs=stmt.executeQuery();
			if(rs.next()) {
				quantity = rs.getInt("Disponibility");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantity;
	}
	
}
