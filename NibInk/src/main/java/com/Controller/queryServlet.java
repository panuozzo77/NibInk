package com.Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name="queryServlet", urlPatterns="/test")
public class queryServlet extends HttpServlet
{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/connessioneDB.jsp");
		dispatcher.forward(request, response);
	}
}