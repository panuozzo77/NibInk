package com.model;

public class ItemInTheCart {
	Item item;
	String variant;
	int quantity;

	public ItemInTheCart() {}
	
	public ItemInTheCart(Item item, int quantity, String variant) {
		this.item=item;
		this.quantity=quantity;
		this.variant=variant;
	}
	
	public void setItem(Item item)
	{
		this.item=item;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity=quantity;
	}
	
	public void setVariant(String variant)
	{
		this.variant=variant;
	}
	
	public Item getItem()
	{
		return this.item;
	}
	
	public int getQuantity()
	{
		return this.quantity;
	}
	
	public String getVariant()
	{
		return this.variant;
	}
}
