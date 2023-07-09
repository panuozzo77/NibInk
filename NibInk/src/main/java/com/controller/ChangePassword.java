package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Customer;
import com.model.DAOCustomer;

@WebServlet("/changePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangePassword() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean status = false;
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("userType");
		DAOCustomer db = new DAOCustomer();
		if(user.equals("admin")) {
			int userId = request.getAttribute("id")!= null ? (int) request.getAttribute("id") : 0;
			//Customer customer = db.getCustomerByEmail((String) request.getAttribute("email"));
			//userId = customer.getID();
			//dipende da come si vuole implementare per l'admin... ti lascio entrambi i modi
			if(userId != 0)
				status = db.changePassword(0, (String) request.getAttribute("newPassword"));
		}
		else  { //l'utente deve inserire la password provvisoria o la vecchia. Se corretta può cambiarla
			status = changeByEmail(request);
		}
		response.sendRedirect(request.getHeader("referer")+"?result="+status);
	}
	
	protected boolean changeByEmail(HttpServletRequest request) {
		boolean status = false;
		DAOCustomer db = new DAOCustomer();
		Customer customer = db.getCustomerByEmail((String) request.getAttribute("email"));
		int id = customer.getID();
		String temporaryPassword = customer.getPassword();	//la password vecchia o provvisoria presente nel DB
		String insertedPassword = (String) request.getAttribute("oldPassword"); //la password inserita dall'utente
		String newPassword = (String) request.getAttribute("newPassword"); //la nuova password che l'utente desidera inserire
		if(temporaryPassword.equals(insertedPassword)) {
			status = db.changePassword(id, newPassword);
		}
		return status;
	}

}
