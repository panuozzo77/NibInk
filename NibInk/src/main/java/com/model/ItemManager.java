package com.model;

import java.util.ArrayList;
import java.util.Map;

//Map<ItemID, Variants> 	Variants=Item, <NameVariant, quantity> 
public class ItemManager {
	public Map<String, ItemVariant> itemVariants;
	
	public void loadVariants()
	{
		DAOVariant db = new DAOVariant();
		itemVariants = db.getAllVariantsFromDB();
	}
	
	public void updateVariants()
	{
		DAOVariant db = new DAOVariant();
		if(itemVariants.isEmpty())
			loadVariants();
		else if(itemVariants.size()!=db.getVariantsNumber())
			loadVariants();
	}
	
	 public ArrayList<Item> loadItems(int startIndex, int count) {
	        DAOItem db = new DAOItem();
	        return db.getItems(startIndex, count);
	    }
}
