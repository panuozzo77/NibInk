package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.MessageManager;
import com.model.JSONAdapters.MessageAdapter;
import com.model.JSONAdapters.MessageListAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.model.Message;

@WebServlet("/retrieveMessage")
public class RetrieveMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RetrieveMessage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userIdParam = request.getParameter("id");
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
        if (userIdParam != null) {
            int id = Integer.parseInt(userIdParam);
            MessageManager messageManager = new MessageManager();
            List<Message> messages = messageManager.getMessagesOf(id);
            messageManager.setReadStatus(id, userType);
            // Convert messages to JSON and send the response
            Gson gson = new GsonBuilder().registerTypeAdapter(Message.class, new MessageAdapter())
                    .registerTypeAdapter(new TypeToken<List<Message>>() {}.getType(), new MessageListAdapter(new MessageAdapter()))
                    .create();
            String json = gson.toJson(messages);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
