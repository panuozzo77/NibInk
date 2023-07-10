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
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public ChangePasswordServlet() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean status = false;
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("userType");
        DAOCustomer db = new DAOCustomer();
        if(user.equals("admin")) {
            int userId = request.getAttribute("id")!= null ? (int) request.getAttribute("id") : Integer.valueOf(request.getParameter("id"));
            String email = request.getAttribute("email")!=null? (String) request.getAttribute("email") : request.getParameter("email");
            Customer customer = db.getCustomerByEmail(email);
            userId = customer.getID();
            if(userId != 0) {
            	String newPassword = request.getAttribute("newPassword")!= null ? (String) request.getAttribute("newPassword") : (String) request.getParameter("newPassword");
                status = db.changePassword(userId, newPassword);
            }
        }else  { //l'utente deve inserire la password provvisoria o la vecchia. Se corretta può cambiarla
            status = changeByEmail(request);
        }
        response.sendRedirect(request.getHeader("referer")+"?result="+status);
    }
    
    protected boolean changeByEmail(HttpServletRequest request) {
    	String email = request.getAttribute("email")!=null? (String) request.getAttribute("email") : request.getParameter("email");
    	//la password inserita dall'utente
    	String insertedPassword = request.getAttribute("oldPassword")!=null ? (String) request.getAttribute("oldPassword") : request.getParameter("oldPassword");
    	//la nuova password che l'utente desidera inserire
    	String newPassword = request.getAttribute("newPassword")!= null ? (String) request.getAttribute("newPassword") : (String) request.getParameter("newPassword");
    	
        boolean status = false;
        DAOCustomer db = new DAOCustomer();
        Customer customer = db.getCustomerByEmail(email);
        int id = customer.getID();
        String temporaryPassword = customer.getPassword();  //la password vecchia o provvisoria presente nel DB
        if(temporaryPassword.equals(insertedPassword)) {
            status = db.changePassword(id, newPassword);
        }
        return status;
    }
 
}