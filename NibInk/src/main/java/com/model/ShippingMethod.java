package com.model;

public class ShippingMethod {
	private String name;
	private float amount;
	private String courier;
	private float percentage = 2.5f;
	
	public String getName(){
		return this.name;
	}
	
	public float getAmount(){
		return this.amount;
	}
	
	public String getCourier(){
		return this.courier;
	}
	
	public float getPercentage() {
		return this.percentage;
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

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
}
