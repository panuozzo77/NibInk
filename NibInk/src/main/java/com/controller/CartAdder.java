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
@WebServlet("/AddToCart")
public class CartAdder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CartAdder() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("sessionId");
		CartManager cm = new CartManager();
		cm.addNewCart(code);
		String size = request.getParameter("size");
		String id = request.getParameter("product");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		cm.addItemToCart(code, id, quantity, size);
		response.sendRedirect("/NibInk/JSP/carrello.jsp");
		
	}

}
