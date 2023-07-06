package com.model;

import java.time.LocalDateTime;

public class Message {
    private int conversationId;
    private int userId;
    private String userEmail;
    public LocalDateTime sent;
    private String subject;
    private String text;
    private String status;
    private int messageNumber;
    private boolean hasAdminReadIt = false;
    private boolean hasUserReadIt = false;

    public Message(int id, int userId, String userEmail, LocalDateTime sent, String subject, String text, String status, int messageNumber, boolean admin, boolean user) {
        this.conversationId = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.sent = sent;
        this.subject = subject;
        this.text = text;
        this.status = status;
        this.messageNumber = messageNumber;
        this.hasAdminReadIt = admin;
        this.hasUserReadIt = user; 
    }

    //costruttore per i messaggi da inviare per aprire una nuova conversazione
    public Message(int userId, String email, String subject, String text) {
    	this.userId = userId;
    	this.userEmail = email;
    	this.subject = subject;
    	this.text = text;
    }
    public int getId() {
        return conversationId;
    }

    public void setId(int id) {
        this.conversationId = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getSent() {
        return sent;
    }

    public void setSent(LocalDateTime sent) {
        this.sent = sent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }
    
    public void setAdminRead() {
    	this.hasAdminReadIt = true;
    }
    
    public boolean getAdminRead() {
    	return this.hasAdminReadIt;
    }
    
    public boolean getUserRead() {
    	return this.hasUserReadIt;
    }
    
    public void setUserRead() {
    	this.hasUserReadIt = true;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + conversationId +
                ", userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", sent=" + sent +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", status='" + status + '\'' +
                ", messageNumber=" + messageNumber +
                '}';
    }
}
