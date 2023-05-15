package com.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name="QueryServlet", urlPatterns="/test")
public class QueryServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/connessioneDB.jsp");
		dispatcher.forward(request, response);
	}
}