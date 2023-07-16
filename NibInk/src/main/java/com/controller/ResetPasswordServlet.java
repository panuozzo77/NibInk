package com.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Customer;
import com.model.DAOCustomer;

import java.io.IOException;
import java.util.Random;

@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String chars = "abcdefghijklmnopqrstuvwxyz"
									  + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
								      + "0123456789"
							          + "!@#$%^&*()_+{}[]";
	private Random random = new Random();
	
	private static final String siteName="NibInk";

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String) request.getParameter("email");
		String name = (String) request.getParameter("name");
		String surname = (String) request.getParameter("surname");
		
		if(checkUser(email, name, surname)) {
			String subject = generateSubject(siteName);
			String newPassword = "";
			
			for(int i=0; i<30; i++) {
				int r = this.random.nextInt(chars.length());
				newPassword+=chars.charAt(r);
			}
	        request.setAttribute("message", generateMsg(name, newPassword, siteName));
	        request.setAttribute("sendTo", email);
	        request.setAttribute("subject", subject);
	        
	        if(changeByEmail(email, newPassword)) {
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/sendEmail");
	            dispatcher.forward(request, response);
	        }
		}
    }
	
	private String generateSubject(String siteName) {
    	String msg="Nuova password provvisoria per il tuo account su [SiteName]";
    	msg = msg.replace("[SiteName]", siteName);
    	
    	return msg;
    }
	
    private String generateMsg(String userName, String pwd, String siteName) {
   
    	String msg="Gentile [Name],\n"
    			+ "\n"
    			+ "\tTi scriviamo per confermarti che la tua richiesta di reimpostazione della password su [SiteName] è stata completata con successo.\n"
    			+ "\tDi seguito, troverai le informazioni necessarie per accedere al tuo account:\n"
    			+ "\n"
    			+ "\tNuova Password: [newPassword]\r\n"
    			+ "\n"
    			+ "\tTi consigliamo di procedere immediatamente alla modifica della password provvisoria al fine di garantire la sicurezza del tuo account.\n"
    			+ "\tPer farlo, effettua il login utilizzando la password fornita e accedi alle impostazioni del tuo profilo.\n"
    			+ "\tNella sezione \"Cambia password\", potrai inserire una nuova password personalizzata e confermarla.\n"
    			+ "\n"
    			+ "\tSe non hai richiesto questa reimpostazione della password o hai dubbi sulla sicurezza del tuo account, ti invitiamo a contattare il nostro servizio clienti il prima possibile.\n"
    			+ "\tSaremo lieti di assisterti e risolvere qualsiasi problema tu possa avere.\n"
    			+ "\n"
    			+ "\tTi ringraziamo per la tua comprensione e cooperazione. Siamo qui per fornirti un'esperienza di utilizzo sicura e affidabile su [SiteName].\n"
    			+ "\n"
    			+ "\tCordiali saluti,\n"
    			+ "\tIl Team di [SiteName]";
    	
    	msg = msg.replace("[Name]", userName!=null? userName:"Null");
    	msg = msg.replace("[newPassword]", pwd);
    	msg = msg.replace("[SiteName]", siteName);
    	
    	return msg;
    }
    
    protected boolean changeByEmail(String email, String newPassword) {
        DAOCustomer db = new DAOCustomer();
        Customer customer = db.getCustomerByEmail(email);
        int id = customer.getID();
        return db.changePassword(id, newPassword);
    }
    
    protected boolean checkUser(String email, String name, String surname) {
        DAOCustomer db = new DAOCustomer();
        Customer customer = db.getCustomerByEmail(email);
        if(customer.getName().equals(name) && customer.getSurname().equals(surname)) {
        	return true;
        }
        return false;
    }
}