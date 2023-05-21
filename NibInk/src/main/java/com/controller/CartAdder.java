package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.CartManager;
import com.model.ItemManager;

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
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("sessionId");
		String id = request.getParameter("product");
		ItemManager im = new ItemManager();
		String size = im.searchDefaultItemForCart(id);
		if(size!=null) {
			CartManager cm = new CartManager();
			if(cm.getCart(code)==null)
				cm.addNewCart(code);
			cm.addItemToCart(code, id, 1, size);
		}
		
		response.sendRedirect("/NibInk/JSP/cart.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("sessionId");
		CartManager cm = new CartManager();
		String size = request.getParameter("size");
		String id = request.getParameter("product");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		if(cm.getCart(code)==null)
			cm.addNewCart(code);
		cm.addItemToCart(code, id, quantity, size);
		response.sendRedirect("/NibInk/JSP/cart.jsp");	
	}

}
