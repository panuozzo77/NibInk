package com.model;

import java.util.List;

public class ShippingMethodManager {
	DAOShippingMethod db = new DAOShippingMethod();
	
	public List<ShippingMethod> getAllShippingMethods() {
		return db.getAllShippingMethods();
	}
	
	public boolean deleteShippingMethod(String name) {
		return db.deleteShippingMethod(name);
	}
	
	public boolean addShippingMethod(ShippingMethod shippingMethod) {
		return db.addShippingMethod(shippingMethod);
	}
}
