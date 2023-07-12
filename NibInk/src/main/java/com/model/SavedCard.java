package com.model;

public class SavedCard {
    private int id;
    private int userId;
    private String cardNumber; //formato xxxx-xxxx-xxxx-YYYY
    private String nameOnCard;
    private boolean isPrimary;

    public SavedCard(int userId, String cardNumber, String nameOnCard, boolean isPrimary) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.isPrimary = isPrimary;
    }
    public SavedCard(int id, int userId, String cardNumber, String nameOnCard, boolean isPrimary) {
        this.id = id;
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.isPrimary = isPrimary;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }
    
    public boolean getBoolean() {
    	return isPrimary;
    }
    
    public void setIsPrimary(boolean value) {
    	isPrimary = value;
    }
    
    public void setId(int id) {
    	this.id = id;
    } 
    
    public void setUserId(int id) {
    	this.userId = id;
    }
    
    public void setCardNumber(String number ) {
    	this.cardNumber = number;
    }
    
    public void setName(String name) {
    	this.nameOnCard = name;
    }
    
    // Optional: Add setters if you need to modify the properties of a SavedCard object

    @Override
    public String toString() {
        return "SavedCard{" +
                "id=" + id +
                ", userId=" + userId +
                ", cardNumber='" + cardNumber + '\'' +
                ", nameOnCard='" + nameOnCard + '\'' +
                '}';
    }
}