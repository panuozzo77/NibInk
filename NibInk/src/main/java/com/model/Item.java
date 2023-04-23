package com.model;

public class Item 
{
	private String codenumber;
	private String title;
	private String color;
	private float price;
	private float vat;
	private int weight;
	private String dimensions;
	private String description;
	 
	public Item() {}
	
	public Item(String codenumber, String title, String color, String dimensions, String description, float price, float vat, int weight)
	{
		this.setCodenumber(codenumber);
		this.setTitle(title);
		this.setColor(color);
		this.setPrice(price);
		this.setVat(vat);
		this.setWeight(weight);
		this.setDimensions(dimensions);
		this.setDescription(description);
	}
	
	//SET Methods
	public void setCodenumber(String code) {
		this.codenumber=code; 
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void setVat(float vat) {
		this.vat = vat;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	//GET Methods
	public String getCodenumber() {
		return codenumber;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getColor() {
		return color;
	}

	public float getPrice() {
		return price;
	}

	public float getVat() {
		return vat;
	}

	public int getWeight() {
		return weight;
	}

	public String getDimensions() {
		return dimensions;
	}

	public String getDescription() {
		return description;
	}	
}
