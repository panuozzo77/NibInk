package com.controller;
 
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import com.model.Customer;
import com.model.DAOCustomer;
 
@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public ChangePasswordServlet() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String siteName="NibInk";
        boolean status = false;
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("userType");
        DAOCustomer db = new DAOCustomer();
        if(user.equals("admin")) {
            int userId = request.getAttribute("id")!= null ? (int) request.getAttribute("id") : Integer.valueOf(request.getParameter("id"));
            String email = request.getAttribute("email")!=null? (String) request.getAttribute("email") : request.getParameter("email");
            Customer customer = db.getCustomerByEmail(email);
            userId = customer.getID();
            if(userId != 0) {
            	String newPassword = request.getAttribute("newPassword")!= null ? (String) request.getAttribute("newPassword") : (String) request.getParameter("newPassword");
                status = db.changePassword(userId, newPassword);
            }
        }else  { //l'utente deve inserire la password provvisoria o la vecchia. Se corretta può cambiarla
            status = changeByEmail(request);
        }
        if(status) {
        	request.setAttribute("message", generateMsg(siteName));
	        request.setAttribute("sendTo", request.getParameter("email"));
	        request.setAttribute("subject", generateSubject(siteName));
	        
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/sendEmail");
            dispatcher.forward(request, response);
        }
        response.sendRedirect(request.getHeader("referer")+"?result="+status);
    }
    
    protected boolean changeByEmail(HttpServletRequest request) {
    	String email = request.getAttribute("email")!=null? (String) request.getAttribute("email") : request.getParameter("email");
    	//la password inserita dall'utente
    	String insertedPassword = request.getAttribute("oldPassword")!=null ? (String) request.getAttribute("oldPassword") : request.getParameter("oldPassword");
    	//la nuova password che l'utente desidera inserire
    	String newPassword = request.getAttribute("newPassword")!= null ? (String) request.getAttribute("newPassword") : (String) request.getParameter("newPassword");
    	
        boolean status = false;
        DAOCustomer db = new DAOCustomer();
        Customer customer = db.getCustomerByEmail(email);
        int id = customer.getID();
        String temporaryPassword = customer.getPassword();  //la password vecchia o provvisoria presente nel DB
        if(temporaryPassword.equals(insertedPassword)) {
            status = db.changePassword(id, newPassword);
        }
        return status;
    }
    
    private String generateSubject(String siteName) {
    	String msg="Cambio password per il tuo account su [SiteName]";
    	msg = msg.replace("[SiteName]", siteName);
    	
    	return msg;
    }
	
    private String generateMsg(String siteName) {
   
    	String msg="Gentile Cliente,\n"
    			+ "\n"
    			+ "\tTi scriviamo per informarti che la tua richiesta di cambio della password su [SiteName] è stata completata con successo.\n"
    			+ "\tSe non hai richiesto il cambio della password, ti consigliamo di contattarci al fine di garantire la sicurezza del tuo account.\n"
    			+ "\tPer farlo, puoi anche rispondere a questa email, inserendo come oggetto 'Non ho richiesto il cambio della password'.\n"
    			+ "\tSaremo lieti di assisterti e risolvere qualsiasi problema tu possa avere.\n"
    			+ "\n"
    			+ "\tTi ringraziamo per la tua comprensione e cooperazione. Siamo qui per fornirti un'esperienza di utilizzo sicura e affidabile su [SiteName].\n"
    			+ "\n"
    			+ "\tCordiali saluti,\n"
    			+ "\tIl Team di [SiteName]";
    	
    	msg = msg.replace("[SiteName]", siteName);
    	
    	return msg;
    }
 
}