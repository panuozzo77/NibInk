package com.model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int id;
    private int user;
    private String email;
    private String shippingAddress;
    private String invoiceAddress;
    private String paymentMethod;
    private float amount;
    private String status;
    private String shippingMethod;
    private float shippingCost;
    private Date orderDate;
    ArrayList<OrderedItem> purchased;

    public Order() {}
    
    public Order(int id, int user, String email, String shippingAddress, String invoiceAddress, String paymentMethod, float amount, String status, String shippingMethod, float shippingCost, Date orderDate) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.invoiceAddress = invoiceAddress;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
        this.shippingMethod = shippingMethod;
        this.shippingCost = shippingCost;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }
    
    public float getShippingCost() {
    	return shippingCost;
    }
    
    public void setShippingCost(float cost) {
    	shippingCost = cost;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<OrderedItem> getPurchasedItems(int id) {
    	DAOOrder db = new DAOOrder();
    	return db.loadOrderedItem(id);
    }
    
    public ArrayList<OrderedItem> getPurchased() {
    	return this.purchased;
    }
    
    public void setPurchased(ArrayList<OrderedItem> list) {
    	this.purchased = list;
    }
    
}