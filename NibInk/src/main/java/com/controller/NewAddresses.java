package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.DAOAddresses;

/**
 * Servlet implementation class NewAddresses
 */
@WebServlet("/NewAddresses")
public class NewAddresses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAddresses() {
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
		String Name = request.getParameter("Name");
		String Surname = request.getParameter("Cognome");
		String Città = request.getParameter("Citta");
		String Street = request.getParameter("Street");
		int Number = Integer.parseInt(request.getParameter("Number"));
		boolean isBillingAddresse = Boolean.parseBoolean(request.getParameter("radio"));
		int User = Integer.parseInt(request.getParameter("id"));
		int ZipCode = Integer.parseInt(request.getParameter("ZipCode"));
		DAOAddresses addresse = new DAOAddresses();
		System.out.println(Name + " " + Surname  + " " + Città  + " " + Street  + " " + Number  + " " + isBillingAddresse  + " " + User  + " " + ZipCode);
		if(addresse.setAddressesDB(User, Città, Street, Number, Name, Surname, isBillingAddresse))
		{
			response.sendRedirect("/NibInk/JSP/indirizzi.jsp");
		}
		else 
		{
			System.out.println("C'è stato un errore durante l'inserimento del nuovo indirizzo nel DB.");
		}
	}

}
