<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.DAOOrder" %>
<%@ page import="com.model.Order" %>
<%@ page import="com.model.DAOCustomer" %>
<%@ page import="com.model.Customer" %>
<%@ page import="com.model.OrderedItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.DAOItem" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html lang="it">
<%
int id=Integer.parseInt(request.getParameter("id"));
int userId= (int) session.getAttribute("id");
DAOOrder db = new DAOOrder();
Order order = db.loadOrder(id);
if(session.getAttribute("userType").toString().equals("unregistered")) { //se non sei registrato non possiamo mai essere certi che sia lui l'acquirente. Non può accedervi
	response.sendError(HttpServletResponse.SC_FORBIDDEN);
}

if(session.getAttribute("userType").toString().equals("registered")) {	//se è un utente registrato e l'ID utente dell'ordine non coincide con quello che lo visualizza
	if(order.getUser()!=userId) {	//non hai i permessi per visualizzare questo riepilogo!
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}
}

//DecimalFormat decimalFormat = new DecimalFormat("#.##");
float IVA = Math.round(order.getAmount() * 0.22f * 100) / 100f;
float spedizione = Math.round(order.getShippingCost() * 100) / 100f;
float IVASpedizione = Math.round(spedizione * 0.22f * 100) / 100f;
float netto = Math.round(((order.getAmount()/100)*78) * 100) / 100f;

DAOCustomer db2 = new DAOCustomer();
Customer customer = db2.getCustomerById(order.getUser());

String invoiceAddress = order.getInvoiceAddress();
String title = "La tua Fattura";
String fileName = "Fattura";
boolean isInvoice = true;
if(invoiceAddress.length()<2){	//se il campo dell'indirizzo di fatturazione è inferiore a 2 caratteri, non può essere una fattura
	isInvoice = false;
	title = "Riepilogo Ordine";
	fileName = "Ordine";
}
%>
<head>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>
  <meta charset="utf-8">
  <title>NibInk - <%=title %></title>
  <link rel="icon" type="image/x-icon" href="/NibInk/images/favicon.ico">
  <link rel="stylesheet" href="/NibInk/CSS/fattura.css" media="all" />
</head>
<body>
	<div id=toPrint>
	  <header class="clearfix">
	    <div id="logo">
	      <img src="/NibInk/images/logo.png">
	    </div>
	    <h1><%=fileName%> N°<%= order.getId() %></h1>
	    <div id="company" class="clearfix">
	      <div>NibInk</div>
	      <div>Via inesistente 123<br /> SA 84121, IT</div>
	      <div>(+39) 123-4567890</div>
	      <div><a href="mailto:company@example.com">info@nibink.com</a></div>
	    </div>
	    <div id="project">
	      <div><span>ACQUIRENTE</span> <%= customer.getName() %> <%= customer.getSurname() %></div>
	      <div><span>INDIRIZZO</span> <%= order.getShippingAddress() %></div>
	      <%if(isInvoice) { %>
	      <div><span>FATTURAZIONE</span> <%= order.getInvoiceAddress() %></div>
	      <% } %>
	      <div><span>EMAIL</span> <a href="mailto:<%= order.getEmail() %>"><%= order.getEmail() %></a></div>
	      <div><span>ACQUISTATO IL</span> <%= order.getOrderDate() %></div>
	    </div>
	  </header>
	  <main>
	    <table>
	      <thead>
	        <tr>
	          <th class="service">ARTICOLO</th>
	          <th class="desc">TITOLO</th>
	          <th>PREZZO</th>
	          <th>IVA</th>
	          <th>QNT.</th>
	          <th>TOTALE</th>
	        </tr>
	      </thead>
	      <tbody>
	        <% for (OrderedItem orderedItem : order.getPurchasedItems(order.getId())) { %>
	        <tr>
	          <td class="service"><%= orderedItem.getItemId() %></td>
	          <td class="desc"><%= orderedItem.getName() %></td>
	          <td class="unit"><%= orderedItem.getPrice() %></td>
	          <td class="total"><%= orderedItem.getVAT() %></td>
	          <td class="qty"><%= orderedItem.getQuantity() %></td>
	          <td class="qty"><%= orderedItem.getQuantity()*orderedItem.getPrice() %></td>
	          
	        </tr>
	        <% } %>
	        <tr>
	          <td colspan="5">SUBTOTALE</td>
	          <td class="total"><%= netto %></td>
	        </tr>
	        <tr>
	          <td colspan="5">SPEDIZIONE (incluso in subtotale)</td>
	          <td class="total"><%= spedizione %></td>
	        </tr>
	        <tr>
	          <td colspan="5">IVA</td>
	          <td class="total"><%= IVA %></td>
	        </tr>
	        <tr>
	          <td colspan="5" class="grand total">TOTALE</td>
	          <td class="grand total"><%=order.getAmount() %></td>
	        </tr>
	      </tbody>
	    </table>
	    <div id="notices">
	      <div class="notice">La fattura sarà sempre disponibile sul nostro sito.</div>
	    </div>
	  </main>
  </div>
  <footer>
    La ringraziamo per l'acquisto.
    <input type="button" class="btn_print" value="Download <%=fileName%>">
  </footer>
  <script>
    $(document).ready(function() {
      $(document).on('click', '.btn_print', function(event) {
        event.preventDefault();
        var element = document.getElementById('toPrint');

        var opt = {
          margin: 1,
          filename: 'fattura.pdf',
          image: { type: 'jpeg', quality: 0.98 },
          html2canvas: { scale: 2 },
          jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }
        };

        html2pdf().set(opt).from(element).save();
      });
    });
  </script>
</body>
</html>
