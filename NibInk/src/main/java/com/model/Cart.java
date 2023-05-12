package com.model;

import java.util.ArrayList;

public class Cart {
	ArrayList<ItemInTheCart> cart; 
	float total = 0;
	
	public Cart() {} 
	
	public void addToCart(Item item, int quantity, String size)
	{
		ItemInTheCart toAdd = new ItemInTheCart();
		toAdd.setItem(item);
		toAdd.setQuantity(quantity);
		toAdd.setSize(size);
		cart.add(toAdd);
		total+=quantity*item.getPrice();
	}
	
	public void addToCart(ItemInTheCart item)
	{
		cart.add(item);
		total+=item.getQuantity()*item.getItem().getPrice();
	}
	
	public void removeFromCart(ItemInTheCart item)
	{
        total -= item.getQuantity() * item.getItem().getPrice();
		cart.remove(item);
	}
	
	public void modifyQuantity(ItemInTheCart item, int newQuantity)
	{
		int index=cart.indexOf(item);
		ItemInTheCart it = cart.get(index);
        total -= it.getQuantity() * it.getItem().getPrice();
    	it.setQuantity(newQuantity);
        total += newQuantity * it.getItem().getPrice();
        cart.add(index, it);
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
