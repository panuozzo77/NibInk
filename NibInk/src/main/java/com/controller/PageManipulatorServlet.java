package com.controller;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/nextPage")
public class PageManipulatorServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public PageManipulatorServlet(){
		super();
	}
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}
	
	protected void doGet  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/NibInk/JSP/catalog.jsp?";
		String filter = null;
		filter = request.getParameter("filters"); //dal bottone delle pagine (filtri già applicati e puliti)
		if(filter == null || filter.contains("null")) { //prelevo allora dalle checkbox dei filtri
			filter += request.getParameter("onlyPens"); 
			filter += request.getParameter("onlyInks");
			filter += request.getParameter("onlyNotebooks");
			filter = filter.replace("null", "");
			int index = filter.lastIndexOf("-");
			if(index!= -1 && !filter.contains("notebook"))
				filter = filter.substring(0, index) + "";
			if(filter != "") //teoricamente si potrebbe togliere ma secondo me meglio di no perché funziona così ed amen
				url += "filter="+filter+"&";
				//System.out.println(url);
		}
		else //è già presente e si sta cambiando pagina
			url += "filter="+filter+"&";
		String startIndex = request.getParameter("startIndex"); 
        String pageNumber = request.getParameter("pageNumber");
        url = startIndex != null ? url += "startIndex="+ startIndex +"&pageNumber="+ pageNumber : url ;
        response.sendRedirect(url);
        
	}
	
}