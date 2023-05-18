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
	
	/*public void removeFromCart(ItemInTheCart item){		l'ho modificato sotto
			cart.remove(i);
			total -= item.getQuantity() * item.getItem().getPrice();
		}
	*/
	/*
	public void modifyQuantity(ItemInTheCart item, int newQuantity)	{
		int index=cart.indexOf(item);
		ItemInTheCart it = cart.get(index);
        total -= it.getQuantity() * it.getItem().getPrice();
    	it.setQuantity(newQuantity);
        total += newQuantity * it.getItem().getPrice();
        cart.add(index, it);
	}
	*/
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
