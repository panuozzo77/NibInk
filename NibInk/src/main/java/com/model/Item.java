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
		this.codenumber=codenumber;
		this.setTitle(title);
		this.setColor(color);
		this.setPrice(price);
		this.setVat(vat);
		this.setWeight(weight);
		this.setDimensions(dimensions);
		this.setDescription(description);
	}
	public void setCodenumber(String code) {
		this.codenumber=code; 
	
	}
	public String getCodenumber() {
		return codenumber;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getVat() {
		return vat;
	}

	public void setVat(float vat) {
		this.vat = vat;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
		
}
