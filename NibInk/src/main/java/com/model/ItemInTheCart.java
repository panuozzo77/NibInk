package com.model;

public class ItemInTheCart {
	Item item;
	String size;
	int quantity;

	public ItemInTheCart() {}
	
	public ItemInTheCart(Item item, int quantity, String size) {
		this.item=item;
		this.quantity=quantity;
		this.size=size;
	}
	
	public void setItem(Item item)
	{
		this.item=item;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity=quantity;
	}
	
	public void setSize(String size)
	{
		this.size=size;
	}
	
	public Item getItem()
	{
		return this.item;
	}
	
	public int getQuantity()
	{
		return this.quantity;
	}
	
	public String getSize()
	{
		return this.size;
	}
}
