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
	 
	
	
	
	
	public String searchDefaultItemForCart(String item){
		 ItemVariant load = new ItemVariant();
		 load.loadVariantsOf(item);
		 Map<String, Integer> hash = load.getVariants();
		 String key = null;
		 Integer value;
		 int state=0;
		 for ( Map.Entry<String, Integer> entry : hash.entrySet()) {
			 key = entry.getKey();
			 value = entry.getValue();
			 if (value>0) {
				 state=1;
				 break;
			 }
		 }
		 
		 if(state!=1) {
			 key=null;
		 }
		 
		 return key;
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
	 
	 public boolean[] loadDisponibility(int startIndex, int count, String filter){
		 DAOItem db = new DAOItem();
		 DAOVariant db2 = new DAOVariant();
	     ArrayList<Item> al=db.getFilteredItems(startIndex, count, filter);
	     boolean [] array = new boolean[count];
	     int j=0;
		 for(Item i : al) {
			 
			 array[j]=db2.getQuantity(i)>0;
		 }
			
		 return array;
	 }
	 
	 public boolean[] loadDisponibility(int startIndex, int count){
		 DAOItem db = new DAOItem();
		 DAOVariant db2 = new DAOVariant();
	     ArrayList<Item> al=db.getItems(startIndex, count);
	     boolean [] array = new boolean[count];
	     int j=0;
		 for(Item i : al) {
			 array[j]=db2.getQuantity(i)>0;
			 j++;
		 }
		 return array;
	 }
}
