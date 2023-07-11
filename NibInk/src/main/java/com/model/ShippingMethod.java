package com.model;

public class ShippingMethod {
	String name;
	float amount;
	String courier;
	float percentage = (float) 2.5;
	
	public String getName(){
		return this.name;
	}
	
	public float getAmount(){
		return this.amount;
	}
	
	public String getCourier(){
		return this.courier;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public void setCourier(String courier) {
		this.courier = courier;
	}
}
