package com.model;

import java.util.ArrayList;

public class ReviewManager {
	DAOReview db = new DAOReview();
	ArrayList<Review> list;
	
	public void loadAllReviewsOf(int itemId) {
		list = db.loadAllReviewsOf(itemId);
	}
	
	public void loadAllUserReviewsOf(int userId) {
		list = db.loadAllUserReviewsOf(userId);
	}
	
	public boolean hasThisUserBoughtIt(int userId, int itemId) {
		return db.hasThisUserBoughtIt(userId, itemId);
	}
	
	public void addReview(Review review) {
		db.addReview(review);
	}
	
	public void removeReview(Review review) {
		db.removeReview(review);
	}
	
	public void removeReview(int userId, int itemId) {
		db.removeReview(userId, itemId);
	}
	
	public void modifyReviewUser(Review review) {
		db.modifyReviewUser(review);
	}
}
