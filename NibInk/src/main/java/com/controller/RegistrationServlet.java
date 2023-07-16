package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.model.DAOCustomer;
import com.model.Customer;


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrationServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // raccolgo le informazioni della form
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");
        
        // Salvo le informazioni nella classe Customer
        Customer newCustomer = new Customer();
        newCustomer.setEmail(email);
        newCustomer.setName(name);
        newCustomer.setSurname(surname);
        newCustomer.setPassword(password);
        newCustomer.setType("registered");
        
        // Salvo il Customer nel DB
        DAOCustomer dao = new DAOCustomer();
        if(dao.addCustomer(newCustomer)) {
        	sendEmail(email, name);
        	response.sendRedirect("/NibInk/JSP/login.jsp?regSucc=1"); //correctly registered
        }else
        	response.sendRedirect("/NibInk/JSP/login.jsp?error=1"); //retry
        
    }
	
void sendEmail(String sendTo, String name) {
	String siteName="NibInk";
	String subject=generateSubject(siteName);
	String message=generateMsg(name, siteName);
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
	String msg="Benvenuto nel mondo di [SiteName]!";
	msg = msg.replace("[SiteName]", siteName);
	
	return msg;
}

private String generateMsg(String name, String siteName) {

	String msg="Ciao [Name],\n"
			+ "\n"
			+ "Siamo entusiasti di darti il benvenuto nel meraviglioso mondo di [SiteName]!\n"
			+ "Siamo un team appassionato e dedicato, impegnato a fornire ai nostri clienti prodotti di prima classe, selezionati con cura tra i migliori brand e artigiani del settore. La nostra missione è offrirti un'esperienza di acquisto eccezionale, che ti permetta di esplorare l'eleganza senza tempo delle penne, la magia dei colori degli inchiostri e la gioia di scrivere su taccuini impeccabili.\n"
			+ "\n"
			+ "Se hai domande, richieste o hai bisogno di assistenza, il nostro team di supporto clienti è sempre a tua disposizione. Siamo qui per garantire che tu abbia una meravigliosa esperienza di acquisto e che le tue aspettative siano superate.\n"
			+ "Grazie ancora per aver scelto [SiteName]. Siamo felici di averti come membro della nostra comunità.\n"
			+ "\n"
			+ "Cordiali saluti,\n"
			+ "\n"
			+ "Il team di [SiteName].";
	
	msg = msg.replace("[SiteName]", siteName);
	msg = msg.replace("[Name]", name);
	
	return msg;
}
	
}

