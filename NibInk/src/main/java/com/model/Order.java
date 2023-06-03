package com.model;

import java.util.Date;

public class Order {
    private int id;
    private int user;
    private String email;
    private int shippingAddress;
    private int invoiceAddress;
    private String paymentMethod;
    private float amount;
    private String status;
    private String shippingMethod;
    private Date orderDate;

    public Order(int id, int user, String email, int shippingAddress, int invoiceAddress, String paymentMethod, float amount, String status, String shippingMethod, Date orderDate) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.invoiceAddress = invoiceAddress;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
        this.shippingMethod = shippingMethod;
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

    public int getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(int shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(int invoiceAddress) {
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}