package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		if(paymentMethod.equals("card")) {
			String card=request.getParameter("cardNumber");
			String privacyCard="xxxx-xxxx-xxxx-"+card.substring(12);
			
		}
		
		//response.sendRedirect("/NibInk/JSP/orderSummary.jsp");	
	}

}
