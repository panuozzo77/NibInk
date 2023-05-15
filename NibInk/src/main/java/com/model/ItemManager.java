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
        ArrayList<Item> al=db.getItems(startIndex, count);
        //System.out.println("ItemManager: " +al.size());
        return al;
    }
	 
	 
	 
	 
	 //fatto da me
	
	
	
	 public ArrayList<Item> loadRelatedItems(float price){
		 DAOItem db = new DAOItem();
		 ArrayList<Item> al = db.getRelatedFromDB(price);
		 return al;
	 }
	 
	 public ArrayList<Item> loadFilteredItems(int startIndex, int count, String filter){
		 DAOItem db = new DAOItem();
		 ArrayList<Item> al=db.getFilteredItems(startIndex, count, filter);
		 return al;
	 }
}
