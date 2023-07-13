package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.ShippingMethod;
import com.model.ShippingMethodManager;

@WebServlet("/ShippingManagerServlet")
public class ShippingManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShippingManager() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean status = false;
		String mode = request.getParameter("mode");
		ShippingMethodManager db = new ShippingMethodManager();
		if(mode.equals("delete")) {
			status = db.deleteShippingMethod(request.getParameter("shippingName"));
		}
		if(mode.equals("add")) {
			ShippingMethod shipping = new ShippingMethod();
			shipping.setName(request.getParameter("shippingName"));
			shipping.setCourier(request.getParameter("courier"));
			shipping.setAmount(Float.parseFloat(request.getParameter("amount")));
			status = db.addShippingMethod(shipping);
		}
		
		String url = request.getHeader("referer");

		String[] parts = url.split("\\?");
		String partBeforeQuestionMark = parts[0];
		response.sendRedirect(partBeforeQuestionMark+"?result="+status);
	}

}
