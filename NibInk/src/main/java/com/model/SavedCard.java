package com.model;

public class SavedCard {
    private int id;
    private int userId;
    private String cardNumber; //formato xxxx-xxxx-xxxx-YYYY
    private String nameOnCard;

    public SavedCard(int id, int userId, String cardNumber, String nameOnCard) {
        this.id = id;
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
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