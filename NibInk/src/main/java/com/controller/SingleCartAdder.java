package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.CartManager;

/**
 * Servlet implementation class CartAdder
 */
@WebServlet("/SingleAddToCart")
public class SingleCartAdder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SingleCartAdder() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("sessionId");
		CartManager cm = new CartManager();
		cm.addNewCart(code);
		String size;
		String id = request.getParameter("product");
		System.out.println("prova"+id);
		int quantity;
		//cm.addItemToCart(code, id, quantity, size);
		response.sendRedirect("/NibInk/JSP/carrello.jsp");
		
	}

}
