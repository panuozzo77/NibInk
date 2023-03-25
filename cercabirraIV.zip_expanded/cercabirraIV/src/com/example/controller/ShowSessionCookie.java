package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowSessionCookie")
public class ShowSessionCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowSessionCookie() {
        super();
    }

	public static String getCookieValue(HttpServletRequest request, 
			String cookieName, String defaultValue) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					return (cookie.getValue());
				}
			}
		}
		return (defaultValue);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String countString = getCookieValue(request,"accessCount", "1");
	    int count = 1;
	    try {
	      count = Integer.parseInt(countString);
	    } catch(NumberFormatException nfe) { }
	    
	    LongLivedCookie c = new LongLivedCookie("accessCount", String.valueOf(count+1));
	    response.addCookie(c);
	    
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println("<!DOCTYPE HTML>" +
	                "<HTML>" +
	                "<HEAD><TITLE>Access Count Servlet</TITLE></HEAD>" +
	                "<BODY>" +
	                "<H1>Access Count Servlet</H1>" +
	                "<H2>This is visit number " + count + " by this browser.</H2>\n" +
	                "</BODY></HTML>");
	  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
