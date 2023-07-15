package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class DAOMessage extends DAOConnection {
    private PreparedStatement stmt;
    private Connection con;

    public DAOMessage() {
        try {
            con = getConnection();
        } catch (SQLException e) {
            System.out.println("Error getting connection in DAOMessage!");
            e.printStackTrace();
        }
    }
    
    public List<Message> getAllConversationsHeaders() {
    	List<Message> messages = new ArrayList<>();
    	String sql = "SELECT * FROM Messages where messageNumber = 0 ORDER BY CASE WHEN status = 'open' THEN 0 ELSE 1 END, sent DESC;";
    	try {
    		Statement statement = con.createStatement();
    		ResultSet rs = statement.executeQuery(sql);
    		while(rs.next()) {
    			messages.add(getFromResultSet(rs));
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return messages;
    }
    
    public List<Message> getUserConversationsHeaders(int userId, String email) {
        List<Message> messages = new ArrayList<>();
    	String sql = "SELECT * FROM Messages where messageNumber = 0 AND (userEmail = ? OR userId = ?) ORDER BY CASE WHEN status = 'open' THEN 0 ELSE 1 END, sent DESC;";
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, email);
    		stmt.setInt(2, userId);
    		ResultSet rs = stmt.executeQuery();
    		while (rs.next()) {
                messages.add(getFromResultSet(rs));
            }
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return messages;
    }
    
    public int getUnreadMessageCount(int conversationId, String reader) {
    	int unreadCount = 0;
    	String column = reader.equals("admin") ? "hasAdminReadIt" : "hasUserReadIt";
    	String sql = "SELECT COUNT(*) AS unreadCount FROM Messages WHERE id = ? AND " +column+ " = FALSE;";
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setInt(1, conversationId);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()) {
    			unreadCount = rs.getInt("unreadCount");
    		}
    		rs.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return unreadCount;
    }    

    public List<Message> getMessagesOf(int conversationId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Messages WHERE id = ?";
        try {
             stmt = con.prepareStatement(sql);
             stmt.setInt(1, conversationId);
             ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                messages.add(getFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }
    
    private Message getMessageIntestationOf(int conversationId) {
    	String sql = "SELECT * FROM Messages WHERE id = ? LIMIT 1";
        try {
             stmt = con.prepareStatement(sql);
             stmt.setInt(1, conversationId);
             ResultSet resultSet = stmt.executeQuery();
             if(resultSet.next()) {
            	 return getFromResultSet(resultSet);
             }
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
        System.out.println("Sono qui getMessageIntestationOf");
        return null;
	}
    
    
    private Message getFromResultSet(ResultSet rs) {
    	try {
	    	int id = rs.getInt("id");
	        int userId = rs.getInt("userId");
	        String userEmail = rs.getString("userEmail");
	        LocalDateTime sent = rs.getTimestamp("sent").toLocalDateTime();
	        String subject = rs.getString("subject");
	        String text = rs.getString("text");
	        String status = rs.getString("status");
	        int messageNumber = rs.getInt("messageNumber");
	        boolean admin = rs.getBoolean("hasAdminReadIt");
	        boolean user = rs.getBoolean("hasUserReadIt");
	        return new Message(id, userId, userEmail, sent, subject, text, status, messageNumber, admin, user);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    
    //per l'utente
    public Map<Integer, List<Message>> getConversationsByUser(int userId, String email) {
        Map<Integer, List<Message>> conversations = new HashMap<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Messages WHERE userId = ? OR userEmail = ?");
            stmt.setInt(1, userId);
            stmt.setString(2, email);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Message message = getFromResultSet(resultSet);
                conversations.computeIfAbsent(message.getId(), k -> new ArrayList<>()).add(message);
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conversations;
    }
    
    //restituisce l'ultimo ID presente nel db
    public int getLatestConversationId() {
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT id FROM Messages ORDER BY id DESC LIMIT 1");
    		if(rs.next()) {
    			return rs.getInt("id");
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
		}
    	return 0;
    }
    
    //restituisce il numero dell'ultimo messaggio presente nella conversazione
    public int getLatestMessageNumber(int conversationId) {
    	try {
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT messageNumber FROM Messages WHERE id = "+conversationId+" ORDER BY sent DESC LIMIT 1");
    		if(rs.next()) {
    			return rs.getInt("messageNumber");
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
		}
    	return 0;
    }
    
    public boolean newConversation(Message message, String sender) {
    	boolean status = false;
    	String sql = "INSERT INTO Messages (id, userId, userEmail, subject, text, ";
    	if(sender.equals("admin")) {
    		sql += "hasAdminReadIt)";
    		message.setUserId(0); //se il valore è 0 è admin
    	}
    	else
    		sql += "hasUserReadIt)";
    	sql += " VALUES (?, ?, ?, ?, ?, ?);";
    	try {
    		stmt = con.prepareStatement(sql);
    		int id = getLatestConversationId() + 1;
    		stmt.setInt(1, id);
    		stmt.setInt(2, message.getUserId()); //NOTA: l'userID è stato cambiato se è l'admin a rispondere
    		stmt.setString(3, message.getUserEmail());
    		stmt.setString(4, message.getSubject());
    		stmt.setString(5, message.getText());
    		stmt.setBoolean(6, true);
    		status = stmt.execute();
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return status;
    }
    
    public boolean answerMessage(int conversationId, String answer, String sender) {
    	boolean status = false;
		Message message = getMessageIntestationOf(conversationId);
		System.out.println(message);
    	String sql = "INSERT INTO Messages (id, userId, userEmail, subject, text, messageNumber, ";
    	if(sender.equals("admin")) { 
    		sql += "hasAdminReadIt)";
    		message.setUserId(0); // se valore 0 è admin
    	}
    	else
    		sql += "hasUserReadIt)";
    	sql += " VALUES (?, ?, ?, ?, ?, ?, ?);";
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setInt(1, conversationId);
    		stmt.setInt(2, message.getUserId()); //NOTA: l'userID è stato cambiato se è l'admin a rispondere
    		stmt.setString(3, message.getUserEmail());
    		stmt.setString(4, message.getSubject());
    		stmt.setString(5, answer);
    		stmt.setInt(6, getLatestMessageNumber(message.getId())+1);
    		stmt.setBoolean(7, true);
    		status = stmt.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return status;
    }
    
    public int setReadStatus(int conversationId, String reader) {
    	String columnToUpdate = reader.equals("admin") ? "hasAdminReadIt" : "hasUserReadIt";
    	String sql = "UPDATE Messages SET "+ columnToUpdate +" = true WHERE id = ?";
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setInt(1, conversationId);
    		return stmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
    }
    
    public void setConversationStatusAsClosed(int conversationId) {
    	String sql = "UPDATE Messages SET status = 'closed' WHERE (`id` = ?)";
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setInt(1, conversationId);
    		stmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    //----------------------------------------------------------------------------
    
    public int getUnreadMessageCountForUser(String userEmail) {
    	int unreadCount = 0;
    	String sql = "SELECT COUNT(*) AS unreadCount FROM Messages WHERE UserEmail=? AND hasUserReadIt = FALSE;";
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, userEmail);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()) {
    			unreadCount = rs.getInt("unreadCount");
    		}
    		rs.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return unreadCount;
    }
}