package com.model;

import java.util.List;

public class ReviewManager {
	DAOReview db = new DAOReview();
	public List<Review> list;
	
	public List<Review> loadAllReviewsOf(int object, String which) {
		if(which.equals("items")) 
				return list = db.loadAllReviewsOf(object);
		else if(which.equals("users"))
				return list = db.loadAllUserReviewsOf(object);
		else
			return null;
	}
	
	public Review loadReviewOf(int userId, int itemId) {
		return db.loadReviewOf(userId, itemId);
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
	
	 public int[] calculateReviewStatistics(List<Review> reviews) {
	        int[] statistics = new int[7];

	        int totalStars = 0;
	        int Stars1 = 0;
	        int Stars2 = 0;
	        int Stars3 = 0;
	        int Stars4 = 0;
	        int Stars5 = 0;

	        for (Review review : reviews) {
	            int starRating = review.getStarRating();
	            totalStars += starRating;

	            if (starRating <= 1) {
	                Stars1++;
	            } else if (starRating <= 2) {
	                Stars2++;
	            } else if (starRating <= 3) {
	                Stars3++;
	            } else if (starRating <= 4) {
	                Stars4++;
	            } else {
	                Stars5++;
	            }
	        }

	        int averageStars = 0;
	        if (!reviews.isEmpty()) {
	            averageStars = totalStars / reviews.size();
	        }

	        statistics[0] = averageStars;
	        statistics[1] = Stars1;
	        statistics[2] = Stars2;
	        statistics[3] = Stars3;
	        statistics[4] = Stars4;
	        statistics[5] = Stars5;
	        statistics[6] = reviews.size();

	        return statistics;
	    }
}
