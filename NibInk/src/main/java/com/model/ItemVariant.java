package com.model;

import java.util.HashMap;
import java.util.Map;

public class ItemVariant {
    private Item item;
    private Map<String, Integer> quantityByVariation; // Map from variation to quantity

    public ItemVariant(Item item) {
        this.setItem(item);
        this.quantityByVariation = new HashMap<String, Integer>();
    }

    public void addVariation(String variation, int quantity) {
        quantityByVariation.put(variation, quantity);
    }

    public int getQuantity(String variation) {
        Integer quantity = quantityByVariation.get(variation);
        return quantity != null ? quantity : 0;
    }

    public Map<String, Integer> getVariants() {
        return quantityByVariation;
    }

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void modifyQuantity(String size, int quantity) {
		quantityByVariation.put(size, quantity);
	}
	
	public void addQuantity(String size, int quantity) {
	    Integer currentQuantity = quantityByVariation.get(size);
	    if (currentQuantity == null) {
	        quantityByVariation.put(size, quantity);
	    } else {
	        quantityByVariation.put(size, currentQuantity + quantity);
	    }
	}
	
}