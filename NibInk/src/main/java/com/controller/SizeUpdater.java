package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.DAOVariant;

/**
 * Servlet implementation class SizeUpdater
 */
@WebServlet("/setQuantities")
public class SizeUpdater extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SizeUpdater() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String quantity = request.getParameter("quantity");
		String id = request.getParameter("id");
		String size = request.getParameter("size");
		DAOVariant db = new DAOVariant();
		db.updateVariant(id, size, quantity);
		response.sendRedirect("/NibInk/JSP/admin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}