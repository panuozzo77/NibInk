package com.controller;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sendEmail")
public class EmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sendTo = request.getParameter("sendTo")!=null? (String)request.getParameter("sendTo"): (String)request.getAttribute("sendTo");
        String subject = request.getParameter("subject")!=null? (String)request.getParameter("subject"): (String)request.getAttribute("subject");
        String message = request.getParameter("message")!=null? (String)request.getParameter("message"): (String)request.getAttribute("message");
        try {
        	
        	Email email = new SimpleEmail();
        	email.setHostName("smtp.googlemail.com");
        	email.setSmtpPort(465);
        	email.setAuthenticator(new DefaultAuthenticator("nibinkhelper@gmail.com", "etnvtvwwbhbeqlkv"));
        	email.setSSLOnConnect(true);
        	email.setFrom("nibinkhelper@gmail.com");
        	email.setSubject(subject);
            email.setMsg(message);
            email.addTo(sendTo);
        	email.send();

            response.getWriter().println("Email sent!");
        } catch (EmailException e) {
            response.getWriter().println("Error: "+e.getMessage());
        }  
    }
}