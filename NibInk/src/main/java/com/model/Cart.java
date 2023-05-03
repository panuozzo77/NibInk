package com.model;

import java.util.ArrayList;

public class Cart {
	ArrayList<ItemInTheCart> cart; 
	float total = 0;
	
	public Cart() {} 
	
	public void addToCart(Item item, int quantity)
	{
		ItemInTheCart toAdd = new ItemInTheCart();
		toAdd.setItem(item);
		toAdd.setQuantity(quantity);
		cart.add(toAdd);
		total+=quantity*item.getPrice();
	}
	
	public void removeFromCart(ItemInTheCart item)
	{
		cart.remove(item);
	}
	
	public void modifyQuantity(ItemInTheCart item, int newQuantity)
	{
		for(ItemInTheCart cartItem : cart)
		{
	        if (cartItem.getItem().equals(item.getItem())) {
	            total -= cartItem.getQuantity() * item.getItem().getPrice();
            	cartItem.setQuantity(newQuantity);
                total += newQuantity * item.getItem().getPrice();
	        }
		}
	}
	
	public float getTotal()
	{
		return total;
	}
	
	public void setTotal(float newPrice)
	{
		this.total = newPrice;
	}
	
}
