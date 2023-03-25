package com.example.model;

import java.util.*;

public class BeerExpert {
	List<String> colors = new ArrayList<String>();
	
	public BeerExpert() {
		colors.add("light");
		colors.add("brown");
		colors.add("amber");
		colors.add("dark");		
	}	
	
	public List<String> getBrands(String color) {
		List<String> brands = new ArrayList<String>();
		if (color.equals("amber")) {
			brands.add("Jack Amber");
			brands.add("Red Moose");
		} else {
			brands.add("Jail Pale Ale");
			brands.add("Gout Stout");
		}
		return (brands);
	}
	
	public List<String> getColors() {
		return colors; 
	}
	
	public boolean isValid(String color) {
		if(colors.contains(color)) return true;
		
		return false;
	}	
}
