package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.MessageManager;

@WebServlet("/closeConversation")
public class CloseConversation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CloseConversation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		MessageManager mm = new MessageManager();
		mm.closeConversation(id);
		response.sendRedirect(request.getHeader("referer")); //ricarica la pagina che ha inviato la richiesta con parametro dell'esito
	}

}
