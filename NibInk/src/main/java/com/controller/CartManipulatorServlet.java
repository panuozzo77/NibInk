package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.CartManager;

@WebServlet("/CartServlet")
public class CartManipulatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartManipulatorServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	if (request.getParameter("removeButton")!= null){
    		HttpSession session = request.getSession();
    		String code = (String) session.getAttribute("sessionId");
    		CartManager cm = new CartManager();
    		
    		String item=request.getParameter("item");
    		int quantity=Integer.parseInt(request.getParameter("quantity"));
    		String size=request.getParameter("size");
    		
    		cm.removeItemFromCart(code, item, quantity, size);
    		
    	 }
    	else if (request.getParameter("updateButton") != null){
    		HttpSession session = request.getSession();
    		String code = (String) session.getAttribute("sessionId");
    		CartManager cm = new CartManager();
    		
    		String item=request.getParameter("item");
    		int quantity=Integer.parseInt(request.getParameter("quantityInput"));
    		String size=request.getParameter("size");
    		
    		cm.modifyQuantityFromCart(code, item, quantity, size);
    	 }
    		response.sendRedirect("/NibInk/JSP/cart.jsp");
    		
	}

}
