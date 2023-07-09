package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;
import com.model.Item;
import com.model.DAOItem;
import java.util.ArrayList;

@WebServlet("/AjaxSearchServlet")
public class AjaxSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AjaxSearchServlet() {
        super();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("application/json"); 
		response.setCharacterEncoding("UTF-8");
    	
    	String searchValue=request.getParameter("value");
		//System.out.println("Cerco: " + searchValue);
		DAOItem db = new DAOItem();
		ArrayList<Item> result = db.getItemsLike(searchValue);
		String resp="";
		for(Item i : result) {
			//System.out.println(i.getTitle());
			resp+=i.getTitle();
			resp+="::";
			resp+=i.getCodenumber();
			resp+= ",";
		}
		resp = resp.replaceAll(",$", "");
		//System.out.println("output "+resp);
		response.getWriter().write(new Gson().toJson(resp));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}	
}
