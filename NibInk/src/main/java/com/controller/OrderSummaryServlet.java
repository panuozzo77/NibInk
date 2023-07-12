package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/toSummary")
public class OrderSummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public OrderSummaryServlet() {
        super();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paymentMethod = request.getParameter("paymentRadio");
		HttpSession session = request.getSession();
		if(paymentMethod.equals("card")) {
			String card=request.getParameter("cardNumber");
			String cardName=request.getParameter("cardName");
			String privacyCard="xxxx-xxxx-xxxx-"+card.substring(12);
			String saveCard = request.getParameter("saveCard")==null? "off":"on";
			if(saveCard.equals("true")) {
				//TODO Procedura per salvare la carta nel db, senza metterla di default
			}
			session.setAttribute("cardNumber", privacyCard);
			session.setAttribute("cardName", cardName);
		}
		session.setAttribute("paymentMethod", paymentMethod);
		response.sendRedirect("/NibInk/JSP/orderSummary.jsp");
			
	}
}
