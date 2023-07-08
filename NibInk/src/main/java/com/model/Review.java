package com.model;

import java.sql.Date;

public class Review {
	int userId;
	int itemId;
	int starRating;
	Date date;
	String text;
	
	public int getUserId() {
		return this.userId;
	}
	public int getItemId() {
		return this.itemId;
	}
	public int getStarRating() {
		return this.starRating;
	}
	public Date getDate() {
		return this.date;
	}
	public String getText() {
		return this.text;
	}
	
	public void setUserId(int userId) {
		this.userId=userId;
	}
	
	public void setItemId(int itemId) {
		this.itemId=itemId;
	}
	
	public void setStarRating(int starRating) {
		this.starRating=starRating;
	}
	
	public void setDate(Date date) {
		this.date=date;
	}
	
	public void setText(String text) {
		this.text=text;
	}
}
