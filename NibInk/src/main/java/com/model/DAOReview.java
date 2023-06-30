package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DAOReview extends DAOConnection {
	PreparedStatement stmt;
	Connection con;

	public DAOReview () {
		super();
		try {
			con = super.getConnection();
		} catch (SQLException e) {
			System.out.println("Error getting connection in DAOReview!");
			e.printStackTrace();
		}
	}

	private Review getFromResultSet(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setUserId(resultSet.getInt("userId"));
        review.setItemId(resultSet.getInt("itemId"));
        review.setStarRating(resultSet.getInt("starRating"));
        review.setDate(resultSet.getDate("date"));
        review.setText(resultSet.getString("text"));
        return review;
    }
	
	public List<Review> loadAllReviewsOf(int itemId) {
        List<Review> reviews = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Review WHERE itemId = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, itemId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
            	reviews.add(getFromResultSet(resultSet));
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
	
	public List<Review> loadAllUserReviewsOf(int userId) {
        List<Review> reviews = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Review WHERE userId = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
            	reviews.add(getFromResultSet(resultSet));
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
	
	public Review loadReviewOf(int itemId, int userId) {
		Review review = null;
		try {
            String sql = "SELECT * FROM Review WHERE itemId = ? AND userId = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, itemId);
            stmt.setInt(2, userId);

            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            	review = getFromResultSet(resultSet);
            
            resultSet.close();
            stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return review;
	}
	
	public void addReview(Review review) {
		try {
			String sql="INSERT INTO Review (userId, itemId, starRating, date, text) VALUES (?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, review.getUserId());
	        stmt.setInt(2, review.getItemId());
	        stmt.setInt(3, review.getStarRating());
	        stmt.setDate(4, new java.sql.Date(review.getDate().getTime()));
	        stmt.setString(5, review.getText());
	        stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifyReviewUser(Review review) {
		try {
            String sql = "UPDATE Review SET starRating = ?, date = ?, text = ? WHERE userId = ? AND itemId = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, review.getStarRating());
            stmt.setDate(2, new java.sql.Date(review.getDate().getTime()));
            stmt.setString(3, review.getText());
            stmt.setInt(4, review.getUserId());
            stmt.setInt(5, review.getItemId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void removeReview(Review review) {
		try {
            String sql = "DELETE FROM Review WHERE userId = ? AND itemId = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, review.getUserId());
            stmt.setInt(2, review.getItemId());
            stmt.executeUpdate();
            stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeReview(int userId, int itemId) {
		try {
            String sql = "DELETE FROM Review WHERE userId = ? AND itemId = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, itemId);
            stmt.executeUpdate();
            stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	boolean hasThisUserReviewedIt(int userId, int itemId) {
        boolean hasReviewed = false;
        
        try {
            String sql = "SELECT COUNT(*) FROM Review WHERE userId = ? AND itemId = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, itemId);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                hasReviewed = count > 0;
            }

            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hasReviewed;
	}
	
	boolean canThisUserReviewIt(int userId, int itemId) {
		boolean canReview = false;
		
		try {
			String sql="SELECT * FROM Orders WHERE User = ? AND status = 'delivered' AND ID IN" +
	                   "(SELECT OrderNumber FROM OrderedProducts WHERE Item = ?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setInt(2, itemId);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next())
				canReview = true;
			
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return canReview;
	}
}
	
