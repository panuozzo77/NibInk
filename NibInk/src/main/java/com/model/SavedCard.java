package com.model;

public class SavedCard {
    private int id;
    private int userId;
    private String cardNumber="noCardNumber"; 	//formato 1234123412341234
    private String censoredCardNumber;  //formato xxxx-xxxx-xxxx-YYYY
    private String nameOnCard;
    private boolean isPrimary;

    //Costruttori con id
    public SavedCard(int id, int userId, String cardNumber, String CensoredCardNumber, String nameOnCard, boolean isPrimary) {
        this.id = id;
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.censoredCardNumber = CensoredCardNumber;
        this.nameOnCard = nameOnCard;
        this.isPrimary = isPrimary;
    }
    
    public SavedCard(int id, int userId, String censoredCardNumber, String nameOnCard, boolean isPrimary) {
        this.id = id;
        this.userId = userId;
        this.censoredCardNumber = censoredCardNumber;
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
    
	public String getCensoredCardNumber() {
		return censoredCardNumber;
	}
	public void setCensoredCardNumber(String censoredCNumber) {
		censoredCardNumber = censoredCNumber;
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
                ", censoredCardNumber='" + censoredCardNumber + '\'' +
                ", nameOnCard='" + nameOnCard + '\'' +
                '}';
    }
}