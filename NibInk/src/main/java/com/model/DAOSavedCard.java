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
        String sql = "INSERT INTO SavedCards (UserID, CardNumber, NameOnCard, isDefault) VALUES (?, ?, ?, ?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, savedCard.getUserId());
            stmt.setString(2, savedCard.getCardNumber());
            stmt.setString(3, savedCard.getNameOnCard());
            stmt.setBoolean(4, savedCard.getBoolean());
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
                String obscuredNumber = "xxxx-xxxx-xxxx-" + cardNumber.substring(12);
                String nameOnCard = resultSet.getString("NameOnCard");
                boolean value = resultSet.getBoolean("isDefault");
                SavedCard savedCard = new SavedCard(id, userId, obscuredNumber, nameOnCard, value);
                savedCards.add(savedCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return savedCards;
    }

    public boolean deleteSavedCard(int savedCardId, int userId) {
        String sql = "DELETE FROM SavedCards WHERE ID = ? AND UserID = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, savedCardId);
            stmt.setInt(2, userId);
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean setDefaultCard(int userId, int cardId) {
        String updateDefaultSql = "UPDATE SavedCards SET isDefault = false WHERE UserID = ?";
        String updateNonDefaultSql = "UPDATE SavedCards SET isDefault = true WHERE ID = ?";

        try {
            stmt = con.prepareStatement(updateDefaultSql);
            stmt.setInt(1, userId);
            stmt.executeUpdate();

            stmt = con.prepareStatement(updateNonDefaultSql);
            stmt.setInt(1, cardId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}