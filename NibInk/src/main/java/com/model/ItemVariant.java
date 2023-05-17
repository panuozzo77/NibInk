package com.model;

import java.util.HashMap;
import java.util.Map;

public class ItemVariant {
    private Item item;
    private Map<String, Integer> quantityByVariation; // Map from variation to quantity

    public ItemVariant(Item item) {
        this.item=item;
        this.quantityByVariation = new HashMap<String, Integer>();
    }
    
    public ItemVariant() {}

    public void addVariation(String size, int quantity) {
        quantityByVariation.put(size, quantity);
    }

    public int getQuantity(String size) {
        Integer quantity = quantityByVariation.get(size);
        return quantity != null ? quantity : 0;
    }

    public Map<String, Integer> getVariants() {
        return quantityByVariation;
    }

	public Item getItem() {
		return item;
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
	
	  public void removeVariants() {
	        quantityByVariation.clear();
	    }
	    
	    public void removeVariant(String size) {
	        quantityByVariation.remove(size);
	    }
	    
	    
	    
	    
	    public void loadVariantsOf(String item) {
			DAOVariant db = new DAOVariant();
			DAOItem db2 = new DAOItem();
			this.item = db2.getItemFromDB(item);
			quantityByVariation = db.loadSingleItemVariants(this.item);
		}
	    
	    
	    
	    
	  //Roba aggiunta da me
	    
	    public void loadVariants() {
	    	DAOVariant db = new DAOVariant();
	    	quantityByVariation.putAll(db.loadSingleItemVariants(item));
	    }
	    
	    
	    public boolean isEmpty() {
	    	return quantityByVariation.isEmpty();
	    }
	    
	    
	    
	    
	    
	    
	    
	
}