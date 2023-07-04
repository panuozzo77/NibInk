package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.model.Address;
import com.model.AddressManager;

@WebServlet("/AjaxAddressServlet")
public class AjaxAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AjaxAddressServlet() {
        super();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json"); 
		response.setCharacterEncoding("UTF-8");
    	
		
		String nameSurname=request.getParameter("nameSurname");
		String[] temp=nameSurname.split("\\s+");
		
		String name="";
		for(int i=0; i<temp.length-2; i++) {
			name+=(temp[i] + " ");
		}
		String surname=temp[temp.length-1];
		
		
		int user=Integer.valueOf(request.getParameter("id"));
		String country=request.getParameter("country");
    	String street=request.getParameter("street");
    	int number=Integer.valueOf(request.getParameter("number"));
    	String moreInfo=request.getParameter("moreInfo");
    	int zipCode=Integer.valueOf(request.getParameter("zipCode"));
    	String city=request.getParameter("city");
    	String state=request.getParameter("state");
    	boolean isBA=Boolean.valueOf(request.getParameter("isBA"));
    	boolean isDefault=Boolean.valueOf(request.getParameter("isDefault"));
    	
    	Address addr = new Address(user, country, name, surname, street, number, moreInfo, zipCode, city, state, isBA, isDefault);
    	AddressManager am = new AddressManager(user);
    	am.addAddr(addr);
	}	
}

