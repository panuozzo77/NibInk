package com.model;

import java.util.List;

public class ReviewManager {
	DAOReview db = new DAOReview();
	List<Review> list;
	
	public List<Review> loadAllReviewsOf(int object, String which) {
		if(which.equals("items")) 
				return list = db.loadAllReviewsOf(object);
		else if(which.equals("users"))
				return list = db.loadAllUserReviewsOf(object);
		else
			return null;
	}
	
	public boolean hasThisUserReviewedIt(int userId, int itemId) {
		return db.hasThisUserReviewedIt(userId, itemId);
	}
	
	public boolean canThisUserReviewIt(int userId, int itemId) {
		return db.canThisUserReviewIt(userId, itemId);
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
