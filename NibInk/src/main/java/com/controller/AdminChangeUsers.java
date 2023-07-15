package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.model.DAOCustomer;
import com.model.AddressManager;
import com.model.Address;

@WebServlet("/adminChange")
public class AdminChangeUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminChangeUsers() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean status = false;
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("userType");
		DAOCustomer db = new DAOCustomer();
		if(user.equals("admin")) {
			int userId = request.getParameter("id")!= null ? Integer.parseInt(request.getParameter("id"))  : 0;
			status = db.changePassword(userId, (String) request.getParameter("newPassword"));
		}
		String url = request.getHeader("referer");
		String[] parts = url.split("\\?");
		String partBeforeQuestionMark = parts[0];
		response.sendRedirect(partBeforeQuestionMark+"?result="+status);
	}
	
	protected void doGet  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean status = false;
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("userType");
		String mode = request.getParameter("mode");
		int userId = request.getParameter("userId")!= null ? Integer.parseInt(request.getParameter("userId"))  : 0;
		if(user.equals("admin")) {
			DAOCustomer db = new DAOCustomer();
			if(mode.equals("delete")) {
				db.deleteCustomerById(userId);
				String url = request.getHeader("referer");
				String[] parts = url.split("\\?");
				String partBeforeQuestionMark = parts[0];
				response.sendRedirect(partBeforeQuestionMark);
			}
			if(mode.equals("showAddresses")) {
				AddressManager addressManager = new AddressManager(userId);
				ArrayList<Address> addresses = addressManager.getAddresses();
				System.out.println(addresses.size());
				Gson gson = new Gson();
				String jsonAddresses = gson.toJson(addresses);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(jsonAddresses);
				out.flush();
				
			}
		}
		
		
	}

}