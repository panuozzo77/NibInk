package com.model;

import java.util.List;

public class SavedCardManager {
DAOSavedCard db = new DAOSavedCard();

	public boolean addSavedCard(SavedCard savedCard) {
		return db.addSavedCard(savedCard);
	}
	
	public List<SavedCard> getCensoredSavedCardsByUserId(int userId) {
		return db.getCensoredSavedCardsByUserId(userId);
	}
	
	public SavedCard getDefaultSavedCardsByUserId(int userId) {
		return db.getDefaultSavedCardsByUserId(userId);
	}
	
	public List<SavedCard> getFullSavedCardsByUserId(int userId) {
		return db.getFullSavedCardsByUserId(userId);
	}
	
	public boolean deleteSavedCard(int savedCardId, int userId) {
		return db.deleteSavedCard(savedCardId, userId);
	}
	
	public boolean setDefault(int userId, int cardId) {
		return db.setDefaultCard(userId, cardId);
	}
}
