package com.model;

import java.util.HashMap;
import java.util.Map;

public class CartManager {
    private static Map<String, Cart> activeCarts;

    public CartManager() {
        //activeCarts = new HashMap<String, Cart>();
    	if(activeCarts==null) {
    		activeCarts = new HashMap<String, Cart>();
    	}
    }

    public void addNewCart(String cartId) {
        activeCarts.put(cartId, new Cart());
    }

    public void removeCart(String cartId) {
        activeCarts.remove(cartId);
    }

    public Cart getCart(String cartId) {
        return activeCarts.get(cartId);
    }
    
    public void addItemToCart(String cartId, String item, int quantity, String size) {
        Cart cart = activeCarts.get(cartId);
        DAOItem db = new DAOItem();
        ItemInTheCart itemInTheCart = new ItemInTheCart(db.getItemFromDB(item), quantity, size);
        cart.addToCart(itemInTheCart);
    }

    public void removeItemFromCart(String cartId, String item, int quantity, String size) {
    	Cart cart = activeCarts.get(cartId);
        DAOItem db = new DAOItem();
        ItemInTheCart itemInTheCart = new ItemInTheCart(db.getItemFromDB(item), quantity, size);
        cart.removeFromCart(itemInTheCart);
    }
    
    public void modifyQuantityFromCart(String cartId, String item, int quantity, String size) {
    	Cart cart = activeCarts.get(cartId);
        DAOItem db = new DAOItem();
        ItemInTheCart itemInTheCart = new ItemInTheCart(db.getItemFromDB(item), quantity, size);
        cart.modifyQuantity(itemInTheCart, quantity);
    }
    
    public void cleanCart(String cartId) {
    	Cart c = activeCarts.get(cartId);
    	c.cart.clear();
    	c.setTotal(0);
    }
    
    
    
    
    
    public boolean containsKey(String key) {
    	return activeCarts.containsKey(key);
    }
    
}