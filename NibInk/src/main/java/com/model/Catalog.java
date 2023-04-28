package com.model;

import java.util.ArrayList;

public class Catalog {
	static ArrayList<Item> allItems;
	
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
		if(allItems.size()!=db.getItemsNumber())
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
	
}
