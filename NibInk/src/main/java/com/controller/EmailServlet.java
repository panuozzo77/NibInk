package com.controller;

import org.apache.commons.mail.DefaultAuthenticator;
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
        String recipient = request.getParameter("recipient");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        
        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator("nibinkhelper@gmail.com", "progettotsw"));
            email.setStartTLSEnabled(true);
            email.setFrom("nibinkhelper@gmail.com");
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(recipient);

            email.send();

            response.getWriter().println("Email inviata con successo!");
        } catch (Exception e) {
            response.getWriter().println("Si è verificato un errore durante l'invio dell'email: " + e.getMessage());
        }
        
    }
}