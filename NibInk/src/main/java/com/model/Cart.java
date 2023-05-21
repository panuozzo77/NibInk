package com.model;

import java.util.ArrayList;

public class Cart {
	ArrayList<ItemInTheCart> cart; 
	float total = 0;
	
	public Cart() {
		cart=new ArrayList<ItemInTheCart>();
	} 
	
	public void addToCart(Item item, int quantity, String size) //per adesso inutilizzato
	{
		boolean itemExists = false;
		for(ItemInTheCart c : cart) {
			System.out.println("item: "+c.getItem().getCodenumber() +"size: "+c.getSize() );
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
		
	public ArrayList<ItemInTheCart> getCart() {
		return cart;
	}
	
}
