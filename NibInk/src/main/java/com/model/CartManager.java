package com.model;

import java.util.HashMap;
import java.util.Map;

public class CartManager {
    private static Map<String, Cart> activeCarts;

    public CartManager() {
        activeCarts = new HashMap<>();
    }

    public void addCart(String cartId, Cart cart) {
        activeCarts.put(cartId, cart);
    }

    public void removeCart(String cartId) {
        activeCarts.remove(cartId);
    }

    public Cart getCart(String cartId) {
        return activeCarts.get(cartId);
    }
}