package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.DAOCustomer;
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
    	System.out.println(status);
    	switch (status) {
    		case 1: 
    			HttpSession session = request.getSession();
    			Customer customer;
    			customer = verify.getCustomerByEmail(email);
    			session.setAttribute("userType", customer.getType());
    			session.setAttribute("name", customer.getName());
    			response.sendRedirect("/NibInk/JSP/home.jsp"); //login effettuato correttamente
    			break;
    		case 2:
    			response.sendRedirect("/NibInk/JSP/login.jsp?error=2");
    			break;
    		default:
    			response.sendRedirect("/NibInk/JSP/login.jsp?error=3"); //user non registrato
    	}
	}

}
