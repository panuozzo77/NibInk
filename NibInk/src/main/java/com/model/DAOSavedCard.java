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
        String sql = "INSERT INTO SavedCards (UserID, CardNumber, CensoredCardNumber, NameOnCard, isDefault) VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, savedCard.getUserId());
            stmt.setString(2, savedCard.getCardNumber());
            stmt.setString(3, savedCard.getCensoredCardNumber());
            stmt.setString(4, savedCard.getNameOnCard());
            stmt.setBoolean(5, savedCard.getBoolean());
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<SavedCard> getFullSavedCardsByUserId(int userId) {
        List<SavedCard> savedCards = new ArrayList<>();
        String sql = "SELECT * WHERE UserID = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String cardNumber = resultSet.getString("CardNumber");
                String censoredCardNumber = resultSet.getString("CensoredCardNumber");
                String nameOnCard = resultSet.getString("NameOnCard");
                boolean value = resultSet.getBoolean("isDefault");
                SavedCard savedCard = new SavedCard(id, userId, cardNumber, censoredCardNumber, nameOnCard, value);
                savedCards.add(savedCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return savedCards;
    }
    
    public List<SavedCard> getCensoredSavedCardsByUserId(int userId) {
        List<SavedCard> savedCards = new ArrayList<>();
        String sql = "SELECT ID, CensoredCardNumber, NameOnCard, isDefault FROM SavedCards WHERE UserID = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String censoredCardNumber = resultSet.getString("CensoredCardNumber");
                String nameOnCard = resultSet.getString("NameOnCard");
                boolean value = resultSet.getBoolean("isDefault");
                SavedCard savedCard = new SavedCard(id, userId, censoredCardNumber, nameOnCard, value);
                savedCards.add(savedCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return savedCards;
    }
    
    public SavedCard getDefaultSavedCardsByUserId(int userId) {
        SavedCard savedCard = null;
        String sql = "SELECT ID, CensoredCardNumber, NameOnCard FROM SavedCards WHERE UserID = ? AND isDefault = true";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String censoredCardNumber = resultSet.getString("CensoredCardNumber");
                String nameOnCard = resultSet.getString("NameOnCard");
                savedCard = new SavedCard(id, userId, censoredCardNumber, nameOnCard, true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return savedCard;
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