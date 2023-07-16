package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.DAOCustomer;
import com.model.SavedCard;
import com.model.SavedCardManager;
import com.model.Customer;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	DAOCustomer verify = new DAOCustomer();
    	int status = verify.checkLogin(email, password);
    	switch (status) {
    		case 1: 
    			HttpSession session = request.getSession();
    			Customer customer;
    			customer = verify.getCustomerByEmail(email);
    			session.setAttribute("userType", customer.getType());
    			session.setAttribute("name", customer.getName());
    			session.setAttribute("id", customer.getID());

    	    	SavedCard card = null;
    	    	SavedCardManager db = new SavedCardManager();
    	    	card=db.getDefaultSavedCardsByUserId(customer.getID())==null? null: db.getDefaultSavedCardsByUserId(customer.getID());
    	    	if(card!=null) {
    	    		session.setAttribute("savedCardName", card.getNameOnCard());
    	    		session.setAttribute("savedCard", card.getCensoredCardNumber());
    	    	}else {
    	    		session.setAttribute("savedCard", "");
    	    		session.setAttribute("savedCardName", "");
    	    	}
    			
    			response.sendRedirect("/NibInk/JSP/home.jsp"); //login effettuato correttamente
    			break;
    		case 2:
    			response.sendRedirect("/NibInk/JSP/login.jsp?error=2");
    			break;
    		default:
    			response.sendRedirect("/NibInk/JSP/login.jsp?error=3"); //user non registrato
    	}
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getParameter("logout").equals("yes")) {
    		HttpSession session = request.getSession();
    		session.invalidate();
    		response.sendRedirect("/NibInk/JSP/login.jsp");
    	}
    }

}