package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.DAOCustomer;
import com.model.Customer;

/**
 * Servlet implementation class Iscrizione
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // raccolgo le informazioni della form
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String cap = request.getParameter("cap");
        String password = request.getParameter("password");
        
        // Salvo le informazioni nella classe Customer
        Customer newCustomer = new Customer();
        newCustomer.setEmail(email);
        newCustomer.setName(name);
        newCustomer.setSurname(surname);
        newCustomer.setAddress(address);
        newCustomer.setCity(city);
        newCustomer.setCap(cap);
        newCustomer.setPassword(password);
        
        // Salvo il Customer nel DB
        DAOCustomer dao = new DAOCustomer();
        dao.addCustomer(newCustomer);
        //if(result)
        	response.sendRedirect("login.html"); //correctly registered
        //else
        	//response.sendRedirect("login.html"); //retry
        
    }

}
