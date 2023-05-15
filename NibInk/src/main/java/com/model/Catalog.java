package com.model;

import java.util.ArrayList;

public class Catalog {
	public static ArrayList<Item> allItems;
	
	public Catalog() 
	{
		updateCatalog(); 
	}
	
	public void loadArticlesInCatalog() 
	{
		DAOItem db = new DAOItem();
		allItems = db.getAllItemsFromDB();
	}
	 
	public void updateCatalog()
	{
		DAOItem db = new DAOItem();
		if(allItems==null)
			loadArticlesInCatalog();
		else if(allItems.size()!=db.getItemsNumber())
			loadArticlesInCatalog();
	}
	
	public ArrayList<Item> orderItemsByPriceLowHigh()
	{
		DAOItem db = new DAOItem();
		return db.getAllItemsFromDB("Price", null, null);
	}
	
	public ArrayList<Item> orderItemsByPriceHighLow()
	{
		DAOItem db = new DAOItem();
		return db.getAllItemsFromDB("Price DESC", null, null);
	}
	
	public void removeItem(Item item)
	{
		DAOItem db = new DAOItem();
		db.removeItemFromDB(item);
		updateCatalog();
	}
	
	public void addItem(Item item)
	{
		DAOItem db = new DAOItem();
		db.addItemToDB(item);
		updateCatalog();
	}
}
