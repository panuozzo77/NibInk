package com.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class Catalog {
	static ArrayList<Item> allItems;
	
	public void loadArticlesInCatalog() 
	{
		DAOItem db = new DAOItem();
		allItems = db.getAllItemsFromDB();
	}
}
