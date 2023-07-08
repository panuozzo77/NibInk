package com.model;

import java.util.List;
import java.util.Map;

public class MessageManager {
	DAOMessage db = new DAOMessage();
	
	public List<Message> getMessagesOf(int conversationId) {
		return db.getMessagesOf(conversationId);
	}
	
	//riservato per l'admin
	public List<Message> getAllConversationsHeaders() {
		return db.getAllConversationsHeaders();
	}
	
	public List<Message> getUserConversationsHeaders(int userId, String email) {
		return db.getUserConversationsHeaders(userId, email);
	}
	
	public int getUnreadMessagesCount(int conversationId, String reader) {
		return db.getUnreadMessageCount(conversationId, reader);
	}
	
	//principalmente usato dagli utenti
	public Map<Integer, List<Message>> getConversationByUser(int userId, String email) {
		return db.getConversationsByUser(userId, email);
	}
	
	//String user VA NECESSARIAMENTE PRESO DALLA SESSIONE DALL'ATTRIBUTO USERTYPE
	public boolean newConversation(Message message, String user) {
		return db.newConversation(message, user);
	}
	
	//String user VA NECESSARIAMENTE PRESO DALLA SESSIONE DALL'ATTRIBUTO USERTYPE
	public boolean answerMessage(int conversationId, String answer, String user) {
		return db.answerMessage(conversationId, answer, user);
	}
	
	//String user VA NECESSARIAMENTE PRESO DALLA SESSIONE DALL'ATTRIBUTO USERTYPE
	public int setReadStatus(int conversationId, String user) {
		return db.setReadStatus(conversationId, user);
	}
	
	public void closeConversation(int conversationId) {
		db.setConversationStatusAsClosed(conversationId);
	}
}
