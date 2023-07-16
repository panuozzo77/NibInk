package com.model;

import java.util.ArrayList;

public class Cart {
	ArrayList<ItemInTheCart> cart; 
	float total = 0;
	
	public Cart() {
		cart=new ArrayList<ItemInTheCart>();
	} 
	
	public void addToCart(Item item, int quantity, String size)
	{
		boolean itemExists = false;
		for(ItemInTheCart c : cart) {
			if(c.getItem().getCodenumber().equals(item.getCodenumber()) && c.getSize().equals(size)) {
				c.setQuantity(c.getQuantity()+quantity);
				itemExists = true;
				break;
			}
		}
		if(!itemExists) {
			ItemInTheCart toAdd = new ItemInTheCart();
			toAdd.setItem(item);
			toAdd.setQuantity(quantity);
			toAdd.setSize(size);
			cart.add(toAdd);
		}
		total+=quantity*item.getPrice();
	}
	
	public void addToCart(ItemInTheCart item)
	{
		boolean itemExists = false;
		for(ItemInTheCart c : cart) {
			if(c.getItem().getCodenumber().equals(item.getItem().getCodenumber()) && c.getSize().equals(item.getSize())) {
				c.setQuantity(c.getQuantity()+item.getQuantity());
				itemExists = true;
				break;
			}
		}
		if(!itemExists) 
			cart.add(item);
		total+=item.getQuantity()*item.getItem().getPrice();
	}
	
	public float getTotal()
	{
		return total;
	}
	
	public void setTotal(float newPrice)
	{
		this.total = newPrice;
	}
		
	public ArrayList<ItemInTheCart> getCart() {
		return cart;
	}
	
	
	//Fatto da me
	
	public boolean isEmpty() {
		return cart.isEmpty();
	}
	
	
	public void removeFromCart(ItemInTheCart item)
	{ 
		for(ItemInTheCart i : cart) {
			if(i.isEqual(item)) {
				cart.remove(i);
				total -= item.getQuantity() * item.getItem().getPrice();
				break;
			}
		}
	}
	
	public void modifyQuantity(ItemInTheCart item, int newQuantity)	{
		for(ItemInTheCart i: cart) {
			if(i.getItem().getCodenumber().equals(item.getItem().getCodenumber()) && i.getSize().equals(item.getSize())) {
				int index=cart.indexOf(i);
				ItemInTheCart it = cart.get(index);
		        total -= it.getQuantity() * it.getItem().getPrice();
		    	it.setQuantity(newQuantity);
		        total += newQuantity * it.getItem().getPrice();
		        cart.remove(i);
		        cart.add(it);
		        break;
		        
			}
		}
	}
	
}