package com.model;

public class ItemInTheCart {
	Item item;
	int quantity;

	public ItemInTheCart() {}
	
	public ItemInTheCart(Item item, int quantity) {
		this.item=item;
		this.quantity=quantity;
	}
	
	public void setItem(Item item)
	{
		this.item=item;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity=quantity;
	}
	
	public Item getItem()
	{
		return this.item;
	}
	
	public int getQuantity()
	{
		return this.quantity;
	}
		
}
