package com.model;

import java.util.List;

public class SavedCardManager {
DAOSavedCard db = new DAOSavedCard();

	public boolean addSavedCard(SavedCard savedCard) {
		return db.addSavedCard(savedCard);
	}
	
	public List<SavedCard> getSavedCardsByUserId(int userId) {
		return db.getSavedCardsByUserId(userId);
	}
	
	public boolean deleteSavedCard(int savedCardId, int userId) {
		return db.deleteSavedCard(savedCardId, userId);
	}
	
	public boolean setDefault(int userId, int cardId) {
		return db.setDefaultCard(userId, cardId);
	}
}
