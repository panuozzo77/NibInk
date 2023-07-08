package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.DAOCustomer;

/**
 * Servlet implementation class DatiUtente
 */
@WebServlet("/DatiUtente")
public class DatiUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatiUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//fffffffffffffffffffffffffffff
		DAOCustomer utente = new DAOCustomer();
		HttpSession session = request.getSession();
		String id = session.getAttribute("id").toString();
		String opzione = request.getParameter("opzione");
		String cambio = request.getParameter("cambio");
		switch(opzione)
		{
			case "Nome":
				
				if(utente.setCustomerName(cambio, id))
				{
					System.out.println("Cambio nome riuscito con successo");
					response.sendRedirect("/NibInk/JSP/Accesso.jsp");
				}
				else
				{
					System.out.println("Cambio nome non riuscito");
				}
				
			break;
			
			case "Password":
				
				if(utente.setCustomerPassword(cambio, id))
				{
					System.out.println("Cambio password riuscito con successo");
					response.sendRedirect("/NibInk/JSP/Accesso.jsp");
				}
				else
				{
					System.out.println("Cambio nome non riuscito");
				}
				
			break;
			
			case "Email":
				
				if(utente.setCustomerEmail(cambio, id))
				{
					System.out.println("Cambio email riuscito con successo");
					response.sendRedirect("/NibInk/JSP/Accesso.jsp");
				}
				else
				{
					System.out.println("Cambio nome non riuscito");
				}
				
			break;
		}
	}

}
