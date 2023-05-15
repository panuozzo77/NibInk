package com.controller;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FilterServlet")
public class FilterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public FilterServlet(){
		super();
	}
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String choice = null;
		choice += request.getParameter("onlyPens");
		choice += request.getParameter("onlyInks");
		choice += request.getParameter("onlyNotebooks");
		choice=choice.replace("null", "");
		
		if(choice!=null) {
			if(choice.contains("Pens") && choice.contains("Ink") && choice.contains("Notebook")){
				//tutti e 3
				response.sendRedirect("/NibInk/JSP/catalog.jsp");
				
			}
			else if(!choice.contains("Pens") && !choice.contains("Ink") && !choice.contains("Notebook")){
				//nessuno dei 3
				response.sendRedirect("/NibInk/JSP/catalog.jsp");
				
			}
			
			else if(choice.equals("Pens")) {
				//solo penne
				System.out.println("solo penne");
				response.sendRedirect("/NibInk/JSP/catalog.jsp?filter=pen");
			}
			else if(choice.equals("Inks")) {
				//solo inchiostro
				System.out.println("solo ink");
				response.sendRedirect("/NibInk/JSP/catalog.jsp?filter=ink");
			}
			else if(choice.equals("Notebooks")) {
				//solo notebook
				System.out.println("solo notebook");
				response.sendRedirect("/NibInk/JSP/catalog.jsp?filter=notebook");
			}
			else if(choice.contains("Pens") && choice.contains("Ink") && !choice.contains("Notebook")) {
				//penne e inchiostro
				response.sendRedirect("/NibInk/JSP/catalog.jsp?filter=pen-ink");
			}
			else if(choice.contains("Pens") && !choice.contains("Ink") && choice.contains("Notebook")) {
				//penne e notebook
				response.sendRedirect("/NibInk/JSP/catalog.jsp?filter=pen-notebook");
			}
			else if(!choice.contains("Pens") && choice.contains("Ink") && choice.contains("Notebook")) {
				//inchiostro e notebook
				response.sendRedirect("/NibInk/JSP/catalog.jsp?filter=ink-notebook");
			}
		}

	}
	
	protected void doGet  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}