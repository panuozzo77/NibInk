package com.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ArrayList;

public class DAOAddresses extends DAOConnection
{
	PreparedStatement stmt;
	Connection con;
	
	public DAOAddresses ()
	{
		super();
		try 
		{
			con = super.getConnection();
		}
		catch(SQLException e) 
		{
			System.out.println("Error getting connection in DAOAddresses!");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Addresses> allAddresses(int UserId)
	{
		String query = "SELECT * FROM addresses WHERE User = ?";
		try
		{
			stmt = con.prepareStatement(query);
			stmt.setInt(1, UserId);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Addresses> listAddresses = new ArrayList<>();
			
			while(rs.next())
			{
				String City = rs.getString("City");
				String Street = rs.getString("Street");
				int Numbers = rs.getInt("Number");
				String Name= rs.getString("Name");
				String Cognome = rs.getString("Surname");
				boolean isBillingAddresses = rs.getBoolean("isBillingAddress");
				listAddresses.add(new Addresses(City, Street, Numbers, Name, Cognome, isBillingAddresses));
			}
			return listAddresses;
		}
		catch (SQLException e) {
            System.out.println("Error loading addresses from the database!");
            e.printStackTrace();
        }
		
		return null;
	}
	
	public boolean setAddressesDB(int User, String City, String Street, int Number, String Name, String Surname, boolean isBillingAddress)
	{
		String query="INSERT INTO addresses(user, city, street, number, name, surname, isbillingaddress) VALUES (?,?,?,?,?,?,?)";
		try
		{
			stmt = con.prepareStatement(query);
			stmt.setInt(1, User);
			stmt.setString(2, City);
			stmt.setString(3, Street);
			stmt.setInt(4, Number);
			stmt.setString(5, Name);
			stmt.setString(6, Surname);
			stmt.setBoolean(7, isBillingAddress);
			int rs = stmt.executeUpdate();
			
			if(rs>0)
			{return true;}
			else
				return false;
		}
		catch(SQLException e)
		{
			System.out.println("Error add addresse in the database!");
            e.printStackTrace();
		}
		return false;
	}
}
