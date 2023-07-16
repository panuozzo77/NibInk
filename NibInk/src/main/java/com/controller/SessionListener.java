package com.controller;

import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import com.model.CartManager;

@WebListener
public class SessionListener implements HttpSessionListener {

    
    public SessionListener() {}

    public void sessionCreated(HttpSessionEvent event)  { 
        HttpSession session = event.getSession();
        CartManager cm = new CartManager();
    	String code = UUID.randomUUID().toString();
        cm.addNewCart(code);
        session.setAttribute("sessionId", code);
        session.setAttribute("userType", "unregistered");
        session.setAttribute("addr", "");
        session.setAttribute("addrBA", "");
        session.setAttribute("savedCard", "");
		session.setAttribute("savedCardName", "");
    }
	
}
