package com.example.controller;

import java.io.*;
import com.example.model.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;

/**
 * Servlet that prints out the Beers of the selected color</a>.
 */

@WebServlet("/SelectBeer")
public class SelectBeer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession ssn = request.getSession(true);
		if (ssn != null) {
			String ssnId = ssn.getId();
			System.out.println("Your session Id is : " + ssnId);
		}

		String c = request.getParameter("color");

		BeerExpert be = new BeerExpert();
		
		if(be.isValid(c)) {		
		
			List<String> result = be.getBrands(c);
			request.setAttribute("styles", result);
		} else {
		     response.sendError(HttpServletResponse.SC_NOT_FOUND, "Missing parameter.");
		     return;
		}	

		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		view.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String c = request.getParameter("color");

		if (c != null && c.equals("dark")) {
			throw new ServletException("POST method with color=dark is not supported.");
		}
		
		PrintWriter out = response.getWriter();
		InputStream is = request.getInputStream();

		BufferedReader in = new BufferedReader(new InputStreamReader(is));

		out.println("<html><body>");
		out.println("Contenuto del body del pacchetto: ");
		String line;
		while ((line = in.readLine()) != null)
			out.println(line);

		out.println("</body></html>");
	}
}
