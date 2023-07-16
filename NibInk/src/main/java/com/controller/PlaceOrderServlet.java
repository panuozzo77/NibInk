package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.model.DAOOrder;
import com.model.Item;
import com.model.ItemInTheCart;
import com.model.Order;
import com.model.OrderedItem;
import com.model.CartManager;
import com.model.Cart;
import com.model.DAOVariant;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PlaceOrderServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	//dati dell'utente
    	int userId = -1; //utente guest
    	String email = request.getParameter("email");
    	if(session.getAttribute("id")!=null)
    		userId = (int) session.getAttribute("id"); //utente registrato
    	
    	//indirizzi di spedizione
    	String shippingAddress = (String) session.getAttribute("addr");
    	String invoiceAddress = (String) session.getAttribute("addrBA");
    	if(invoiceAddress.equals("false")) 
    		invoiceAddress = "";
    	
    	//metodo di spedizione
    	String shippingMethod = request.getParameter("shippingMethod");
    	float shippingPrice = Float.parseFloat(request.getParameter("shippingPrice"));
    	
    	//metodo di pagamento
    	String paymentMethod = (String) session.getAttribute("paymentMethod");
    	
    	//oggetti da salvare
    	CartManager cm = new CartManager();
    	Cart userCart = cm.getCart((String) session.getAttribute("sessionId"));
    	
    	ArrayList<ItemInTheCart> list = userCart.getCart();
    	ArrayList<OrderedItem> purchased = new ArrayList<OrderedItem>();	//devo spostare dal carrello a questa nuova lista
    	
    	float total = userCart.getTotal(); //totale provvisorio
    	
    	DAOVariant db = new DAOVariant();
    	
    	for(ItemInTheCart toProcess : list) {			//carica dal carrello i parametri essenziali che definiscono il singolo articolo acquistato
    		OrderedItem whereSave = new OrderedItem();
    		Item extracted = toProcess.getItem();
    		int quantityInDB = db.getQuantity(extracted, toProcess.getSize());
    		whereSave.setItemId(extracted.getCodenumber());
    		whereSave.setSize(toProcess.getSize());
    		whereSave.setName(extracted.getTitle());
    		whereSave.setPrice(extracted.getPrice());
    		whereSave.setVAT(extracted.getVat());
    		whereSave.setQuantity(toProcess.getQuantity());
    		purchased.add(whereSave);
    		
    		quantityInDB -=toProcess.getQuantity();
    		db.updateVariant(extracted.getCodenumber(), toProcess.getSize(), String.valueOf(quantityInDB));
    	}
    	float taxes = 0;
    	if(paymentMethod.equals("cashOnDelivery"))		//NON MI RICORDO COME SI CHIAMA IL CONTRASSEGNO
    		 taxes = (float) (total/100*2.5);
		shippingPrice += taxes;		//costi di spedizione + eventuali costi per contrassegno
    	total += shippingPrice;		//totale definitivo
		
    	DAOOrder db2 = new DAOOrder();
		Order toSave = new Order();
		toSave.setUser(userId);
		toSave.setEmail(email);
		toSave.setShippingAddress(shippingAddress);
		toSave.setInvoiceAddress(invoiceAddress);
		toSave.setPaymentMethod(paymentMethod);
		toSave.setAmount(total);
		toSave.setShippingMethod(shippingMethod);
		toSave.setShippingCost(shippingPrice);
		toSave.setPurchased(purchased);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		if(db2.saveOrder(toSave)) {
			sendEmail(email);
			cm.cleanCart((String) session.getAttribute("sessionId"));
			if(session.getAttribute("userType").equals("unregistered"))
				response.getWriter().write("/NibInk/JSP/home.jsp");
			else 
				response.getWriter().write("/NibInk/JSP/myOrders.jsp");
		}
		else {
			response.getWriter().write(request.getHeader("referer")+"?error=generic");
		}
		
    }
    void sendEmail(String sendTo) {
    	String siteName="NibInk";
    	String subject=generateSubject(siteName);
    	String message=generateMsg(siteName);
        try {
        	Email email = new SimpleEmail();
        	email.setHostName("smtp.googlemail.com");
        	email.setSmtpPort(465);
        	email.setAuthenticator(new DefaultAuthenticator("nibinkhelper@gmail.com", "etnvtvwwbhbeqlkv"));
        	email.setSSLOnConnect(true);
        	email.setFrom("nibinkhelper@gmail.com");
        	email.setSubject(subject);
            email.setMsg(message);
            email.addTo(sendTo);
        	email.send();
        } catch(EmailException e) {}

    }

    private String generateSubject(String siteName) {
    	String msg="[SiteName] - Conferma dell'ordine!";
    	msg = msg.replace("[SiteName]", siteName);
    	
    	return msg;
    }

    private String generateMsg(String siteName) {

    	String msg="Gentile Cliente,\n"
    			+ "\n"
    			+ "Grazie per il tuo ordine su NibInk! Siamo lieti di confermare che l'operazione è stata completata con successo e che i tuoi articoli saranno presto pronti per la spedizione.\n"
    			+ "Il nostro team si occuperà dell'imballaggio dei tuoi articoli con cura e attenzione per garantire che arrivino in perfette condizioni.\n"
    			+ "Se hai domande o necessiti di assistenza riguardo al tuo ordine, ti preghiamo di contattare il nostro team di supporto clienti. Siamo sempre disponibili per aiutarti.\n"
    			+ "\n"
    			+ "Grazie ancora per aver scelto [SiteName] come il tuo negozio di fiducia per penne, inchiostri e taccuini. Speriamo che tu possa apprezzare appieno i prodotti che hai selezionato.\n"
    			+ "Cordiali saluti,\n"
    			+ "\n"
    			+ "Il team di [SiteName]";   	
    	msg = msg.replace("[SiteName]", siteName);
    	
    	return msg;
    }    	
}
