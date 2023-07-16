package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.model.DAOOrder;
import com.model.Order;
/**
 * Servlet implementation class AdminGetOrders
 */
@WebServlet("/getOrders")
public class AdminGetOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminGetOrders() {
        super();
        
    }

    //restituisci tutti gli ordini in base ad alcune opzioni
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		DAOOrder db = new DAOOrder();
		ArrayList<Order> list = null;
		if(request.getParameter("mode")!=null) {	//controllo la modalità
			if(request.getParameter("mode").equals("all")) {	//se deve caricare tutti gli ordini
				int total = db.getOrderCount();
				list = db.loadOrdersByMostRecent(0, total);
			}
			if(request.getParameter("mode").equals("byDate")) {	//se deve caricare gli ordini per data
				list = db.loadOrdersByDateRange(request.getParameter("sd"), request.getParameter("ed"));
			}
			if(request.getParameter("mode").equals("byStatus")) {	//se deve caricare gli ordini per stato
				list = db.getOrdersByStatus(request.getParameter("st"));
			}
		}
		if(request.getParameter("userId")!=null) {
			String id = request.getParameter("userId");		//se deve caricare gli ordini dato un ID utente
			list = db.loadAllOrder(id);
		}
		Gson gson = new Gson();
		String orders = gson.toJson(list);
		PrintWriter out = response.getWriter();
		out.print(orders);
		out.flush();
	}


	//effettua modifiche sullo stato di un ordine
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOOrder db = new DAOOrder();
		boolean status = db.changeOrderStatus(request.getParameter("parameter1"), request.getParameter("parameter2"));
		String message;
		if(status)
			message = "modifica avvenuta con successo";
		else 
			message = "la modifica ha incontrato degli errori";
		response.setContentType("text/plain");
		response.getWriter().write(message);
	}

}
