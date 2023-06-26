package com.model;

public class OrderedItem {
	String itemId;
	String size;
	String name;
	float price;
	float VAT;
	Integer quantity;
	
	public String getItemId() {
		return this.itemId;
	}
	
	public String getSize() {
		return this.size;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public float getVAT() {
		return this.VAT;
	}
	
	public Integer getQuantity() {
		return this.quantity;
	}
	
	public void setItemId(String id) {
		this.itemId = id;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void setVAT(float VAT) {
		this.VAT = VAT;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
