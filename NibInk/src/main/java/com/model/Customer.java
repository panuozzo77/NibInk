package com.model;

public class Customer {
	int ID;
	String email;
	String password;
	String name;
	String surname;
	String address;
	String city;
	String cap;
	
	public Customer () {}
	
	public int getID() {
		return this.ID;
	}
	
	public void setID(int ID)
	{
		this.ID=ID;
	}
	
	public String getEmail() {
        return this.email;
    }
	
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
    	return this.password;
    }
    
    public void setPassword(String password) {
    	this.password = password; 
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getAddress() {
        return this.address;
    }
	
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return this.city;
    }
	
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCap() {
        return this.cap;
    }
	
    public void setCap(String cap) {
        this.cap = cap;
    }
    
}