package com.model;

public class Address {
	private int user;
	private String country;
	private String name;
	private String surname;
	private String street;
	private int number;
	private String moreInfo;
	private int zipCode;
	private String city;
	private String state;
	private boolean isBA;
	private boolean isDefault;
	
	public Address() {
		
	}
	
	public Address(int user, String country, String name, String surname, String street, int number, String moreInfo, int zipCode, String city, String state, boolean isBA, boolean isDefault) {
		this.setUser(user);
		this.setCountry(country);
		this.setName(name);
		this.setSurname(surname);
		this.setStreet(street);
		this.setNumber(number);
		this.setMoreInfo(moreInfo);
		this.setZipCode(zipCode);
		this.setCity(city);
		this.setState(state);
		this.setBA(isBA);
		this.setDefault(isDefault);
		
	}
	
	//SET Methods
	
	public void setUser(int user) {
		this.user = user;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setBA(boolean isBA) {
		this.isBA = isBA;
	}
	
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	//GET Methods

	public int getUser() {
		return user;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}
	
	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public boolean getBA() {
		return isBA;
	}	

	public boolean getDefault() {
		return isDefault;
	}

	public String getCountry() {
		return country;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public int getZipCode() {
		return zipCode;
	}

	public String getState() {
		return state;
	}
	
}