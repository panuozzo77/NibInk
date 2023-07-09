package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOAddress extends DAOConnection {
	PreparedStatement stmt;
	Connection con;

	public DAOAddress () {
		super();
		try {
			con = super.getConnection();
		} catch (SQLException e) {
			System.out.println("Error getting connection in DAOItem!");
			e.printStackTrace();
		}
	}
	
	
	public void addAddressToDB(Address addr)
	{
		String sql="INSERT INTO Addresses (User, Country, Name, Surname, Street, Number, MoreInfo, ZipCode, City, State, isBillingAddress, isDefault) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, addr.getUser());
			stmt.setObject(2, addr.getCountry());
			stmt.setObject(3, addr.getName());
			stmt.setObject(4, addr.getSurname());
			stmt.setObject(5, addr.getStreet());
			stmt.setObject(6, addr.getNumber());
			stmt.setObject(7, addr.getMoreInfo());
			stmt.setObject(8, addr.getZipCode());
			stmt.setObject(9, addr.getCity());
			stmt.setObject(10, addr.getState());
			stmt.setObject(11, addr.getBA());
			stmt.setObject(12, addr.getDefault());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeAddrFromDB(Address addr)
	{
		String sql="DELETE FROM Addresses WHERE User=? AND City=? AND Street=? AND Number=?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, addr.getUser());
			stmt.setObject(2, addr.getCity());
			stmt.setObject(3, addr.getStreet());
			stmt.setObject(4, addr.getNumber());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public <T> void modifyAddrToDB(Address addr, String field, T value)
	{
		String sql="UPDATE Addresses SET "+field+" = ? WHERE User=? AND City=? AND Street=? AND Number=?;";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, value);
			stmt.setObject(2, addr.getUser());
			stmt.setObject(3, addr.getCity());
			stmt.setObject(4, addr.getStreet());
			stmt.setObject(5, addr.getNumber());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Address> getAllAddressesFromDB()
	{
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM Addresses";
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch(SQLException e) {
				e.printStackTrace();
		}
		return getFromResultSet(rs);
	}
	
	public ArrayList<Address> getAllUserAddrsFromDB(int User)
	{
		ResultSet rs = null;
		String sql = "SELECT * FROM Addresses WHERE User = ?;";
		try {	
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, User);
			rs = stmt.executeQuery();
		} catch(SQLException e) {
				e.printStackTrace();
		}
		return getFromResultSet(rs);
	}
	
	public Address getDefaultAddrFromDB(String User) {
		ResultSet rs = null;
		String sql = "SELECT * FROM Addresses WHERE User = ? AND isDefault=true";
		try {	
			stmt = con.prepareStatement(sql);
			stmt.setObject(1, User);
			rs = stmt.executeQuery();
		} catch(SQLException e) {
				e.printStackTrace();
		}
		ArrayList<Address> addr = getFromResultSet(rs);
		if(addr.isEmpty()) {
			return null;
		}
		return addr.get(0);
	}
	
	private ArrayList<Address> getFromResultSet(ResultSet rs){
		ArrayList<Address> addrs = new ArrayList<Address>();
		if(rs==null) {
			return null;
		}
		try {
		while (rs.next())
		{
			Address addr = new Address();
			addr.setUser(rs.getInt("User"));
			addr.setCountry(rs.getString("Country"));
			addr.setName(rs.getString("Name"));
            addr.setSurname(rs.getString("Surname"));
            addr.setStreet(rs.getString("Street"));
            addr.setNumber(rs.getInt("Number"));
            addr.setMoreInfo(rs.getString("MoreInfo"));
            addr.setZipCode(rs.getInt("ZipCode"));
			addr.setCity(rs.getString("City"));
            addr.setState(rs.getString("State"));
            addr.setBA(rs.getBoolean("isBillingAddress"));
            addr.setDefault(rs.getBoolean("isDefault"));
            addrs.add(addr);
		}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return addrs;
	}
}