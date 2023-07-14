package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Address;
import com.model.AddressManager;

@WebServlet("/AjaxAddressServlet")
public class AjaxAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AjaxAddressServlet() {
        super();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession sessione = request.getSession();
    	
    	String toDo = request.getParameter("toDo");
    		
    	System.out.println("ToDo: " + toDo );
    	
    	if(toDo.equals("saveInBoth")) { //metodo di spedizione e fatturazione uguali
    		if(request.getParameter("isBA").equals("true")) {
    				System.out.println("SIB, isBA TRUE: " + request.getParameter("addrToSave") );
    			
    				sessione.setAttribute("addrBA", request.getParameter("addrToSave"));
        	}
    			System.out.println("SIB, isBA FALSE: " + request.getParameter("addrToSave") );
    			sessione.setAttribute("addr", request.getParameter("addrToSave"));
    			
    	}else if(toDo.equals("saveInOne")) { //indirizzi differenti
    		if(request.getParameter("isBA").equals("true")) { //se è indirizzo di spedizione
    				System.out.println("SIO, isBA TRUE: " + request.getParameter("addrToSave") );
    			sessione.setAttribute("addrBA", request.getParameter("addrToSave")); //salva invoiceAddress
        	}else {
        		sessione.setAttribute("addr", request.getParameter("addrToSave"));  //salva shippingAddress
        			System.out.println("SIO, isBA false: " + request.getParameter("addrToSave") );
        	}    		
    	}
    	
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json"); 
		response.setCharacterEncoding("UTF-8");
		
		String toDo = request.getParameter("toDo");
		
		String nameSurname=request.getParameter("nameSurname");
		String[] temp=nameSurname.split("\\s+");
		
		String name="";
		for(int i=0; i<temp.length-1; i++) {
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
		
		if(toDo.equals("add")) {
	    	am.addAddr(addr);
	    	response.getWriter().write("Aggiunta Riuscita!");
		}
		
		else if(toDo.equals("modify")) {
			int index=Integer.valueOf(request.getParameter("index"));
	    	am.modifyAddr(am.getAddresses().get(index), addr);
	    	response.getWriter().write("Modifica Riuscita!");
		}
		
		else if(toDo.equals("remove")) {
			am.removeAddr(addr);
			response.getWriter().write("Rimozione Riuscita!");
		}
		
	}	
}
