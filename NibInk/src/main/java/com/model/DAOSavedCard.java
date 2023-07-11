package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOSavedCard extends DAOConnection {
    private PreparedStatement stmt;
    private Connection con;

    public DAOSavedCard() {
        try {
            con = getConnection();
        } catch (SQLException e) {
            System.out.println("Error getting connection in DAOSavedCard!");
            e.printStackTrace();
        }
    }

    public boolean addSavedCard(SavedCard savedCard) {
        String sql = "INSERT INTO SavedCards (UserID, CardNumber, NameOnCard) VALUES (?, ?, ?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, savedCard.getUserId());
            stmt.setString(2, savedCard.getCardNumber());
            stmt.setString(3, savedCard.getNameOnCard());
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<SavedCard> getSavedCardsByUserId(int userId) {
        List<SavedCard> savedCards = new ArrayList<>();
        String sql = "SELECT * FROM SavedCards WHERE UserID = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String cardNumber = resultSet.getString("CardNumber");
                String nameOnCard = resultSet.getString("NameOnCard");
                SavedCard savedCard = new SavedCard(id, userId, cardNumber, nameOnCard);
                savedCards.add(savedCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return savedCards;
    }

    public boolean deleteSavedCard(int savedCardId) {
        String sql = "DELETE FROM SavedCards WHERE ID = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, savedCardId);
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}