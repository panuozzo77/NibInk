package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.SavedCard;
import com.model.SavedCardManager;


@WebServlet("/cardManager")
public class CardManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CardManager() {
        super();
      
    }

	//elimina e imposta come predefinita
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		int cardId = Integer.parseInt(request.getParameter("cardId"));
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		SavedCardManager db = new SavedCardManager();
		if(action.equals("remove")) {
			db.deleteSavedCard(cardId, userId);
		}
		if(action.equals("default")) {
			db.setDefault(userId, cardId);
		}
		response.sendRedirect(request.getHeader("referer"));
	}

	//carica una nuova carta
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SavedCardManager db = new SavedCardManager();
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("id");
		String cardNumber = (String) request.getParameter("number");
		String privacyCard="xxxx-xxxx-xxxx-"+cardNumber.substring(12);
		String nameOnCard=(String) request.getParameter("user");
		SavedCard card = new SavedCard(0, userId, cardNumber, privacyCard, nameOnCard, false);
		db.addSavedCard(card);
		response.sendRedirect(request.getHeader("referer"));
	}

}
