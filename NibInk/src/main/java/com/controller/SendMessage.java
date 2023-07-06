package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Message;
import com.model.MessageManager;

/**
 * Servlet implementation class sendMessage
 */
@WebServlet("/sendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageManager mm = new MessageManager();
		int senderUserId = 0;
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String text = request.getParameter("text");
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		switch (userType) {
		case "unregistered":
			senderUserId = -1;
			break;
		case "registered":
			senderUserId = (int) session.getAttribute("id");
			break;
		case "admin": 
			senderUserId = 0;
			break;
		default:
			//errore 
		}
		if(request.getParameter("conversationId")==null) {
			Message toSend = new Message(senderUserId, email, subject, text);
			mm.newConversation(toSend, userType);
		}
		else
			mm.answerMessage(Integer.parseInt(request.getParameter("conversationId")), text, userType);
	}

}
