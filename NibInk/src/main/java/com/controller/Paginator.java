package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.DAOItem;

/**
 * Servlet implementation class Paginator
 */
@WebServlet("/nextPage")
public class Paginator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Paginator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int index = Integer.parseInt(request.getParameter("startIndex"));
		DAOItem db = new DAOItem();
		int maxIndex = db.getItemsNumber();
		if(index<maxIndex) {
			String par1 = String.valueOf(index);
			String par2 = request.getParameter("pageNumber");
			response.sendRedirect("http://localhost:8080/NibInk/JSP/catalog.jsp?startIndex="+par1+"&"+"pageNumber="+par2);
		} //http://localhost:8080/NibInk/JSP/catalog.jsp
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
