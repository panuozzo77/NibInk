package com.model;

public class Addresses 
{
	String City;
	String Street;
	int Number;
	String Name;
	String Cognome;
	boolean BillingAddresses;
	
	public Addresses(String City, String Street, int Number, String Name, String Cognome, boolean BillingAddresses)
	{
		this.City = City;
		this.Street = Street;
		this.Number = Number;
		this.Name = Name;
		this.Cognome = Cognome;
		this.BillingAddresses = BillingAddresses;
	}
	
	public void setCity(String City)
	{
		this.City=City;
	}
	
	public void setStreet(String Street)
	{
		this.Street=Street;
	}
	
	public void setNumber(int Number)
	{
		this.Number=Number;
	}
	
	public void setName(String Name)
	{
		this.Name=Name;
	}
	
	public void setCognome(String Cognome)
	{
		this.Cognome=Cognome;
	}
	
	public void setBillingAddresses(boolean BooleangAddresses)
	{
		this.BillingAddresses = BooleangAddresses;
	}
	
	public String getCity()
	{
		return City;
	}
	
	public String getStreet()
	{
		return Street;
	}
	
	public int getNumber()
	{
		return Number;
	}
	
	public String getName()
	{
		return Name;
	}
	
	public String getCognome()
	{
		return Cognome;
	}
	
	public boolean getBillingAddresses()
	{
		return BillingAddresses;
	}
}
