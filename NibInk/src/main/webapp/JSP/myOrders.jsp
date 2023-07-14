<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.DAOOrder" %>
<%@ page import="com.model.Order" %>
<%@ page import="com.model.OrderedItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.DAOItem" %>
<%@ page errorPage="/JSP/login.jsp?error=3" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>I miei Ordini</title>
<link href="/NibInk/CSS/myOrders.css" rel="stylesheet" type="text/css"/>
</head>
<body>

	<div class="page">
		<div class="title">
			<h2>I miei ordini</h2>
		</div>
		
		<% 
		DAOOrder DaoOrder= new DAOOrder();
		DAOItem db = new DAOItem();
		
		String utente = session.getAttribute("id").toString();
		if(utente==null)
		{
			throw new Exception("Utente non autenticato");
		}
		ArrayList<Order> orders = DaoOrder.loadAllOrder(utente);
		if(orders==null)
		{
			System.out.println("non ci sono ordini per questo utente");
		%>
			<div class="informazione_ordine">
				<h2>Non hai ordini</h2>
			</div>
		<%
		}
		else
		{
			for(Order ord : orders){
				Integer orderId = ord.getId();
				System.out.println(orderId);
				String status = ord.getStatus();
				boolean canViewInvoice = false;		//0 = no. 1 = si. 
				switch(status) {
				case "pending":
					status = "Da Confermare";
					break;
				case "confirmed":
					status = "Confermato";
					canViewInvoice = true;
					break;
				case "canceled":
					status = "Cancellato";
					break;
				case "shipped":
					status = "Spedito";
					canViewInvoice = true;
					break;
				case "delivered":
					status = "Consegnato";
					canViewInvoice = true;
					break;
				case "toBeReturned":
					status = "Avvio Reso";
					break;
				case "refund":
					status = "Reso Completo";
					break;
				}
		%>
		<div class="prodotto">
			<div class="informazione_ordine">
				<div class="info">
					<h3>Numero Ordine : <%= orderId%></h3>
				</div>
				<div class="info">
					<h3>Data Ordine</h3>
					<p><%= ord.getOrderDate()%></p>
				</div>
				<div class="info">
					<h3>Totale Ordine</h3>
					<p><%= ord.getAmount()%></p>
				</div>
				<div class="info">
					<h3>Stato</h3>
					<p><%=status %></p>
				</div>
				<%if(canViewInvoice){ %>
				<div class="info">
					<h3>Visualizza PDF</h3>
					<p><button onclick="location.href='http://localhost:8080/NibInk/JSP/invoice.jsp?id=<%=orderId%>'">Vedi</button></p>
				</div>
				<%} %>
			</div>
			<%
			ArrayList<OrderedItem> list = ord.getPurchasedItems(orderId);
			for(OrderedItem item : list) {
			%>
			<div class="informazione_consegna">	<!-- qua c'è l'item -->
				<div class="prodotto">
					<div class="dettagli_ordine">
						<img alt="" src="/NibInk/images/<%=item.getItemId()%>/thumbnail.png">
						<div class="prodotto">
							<h2>Titolo:<%= item.getName()%></h2>
							<h2>Taglia: <%= item.getSize()%></h2>
							<h2>&euro;<%= item.getPrice()%></h2>
							<h2>Quantit&agrave:<%= item.getQuantity()%></h2>
						</div>
					</div>
				</div>
				
				<div class="function">
					<button onclick="location.href='http://localhost:8080/NibInk/JSP/product.jsp?id=<%=item.getItemId()%>'">Vedi Articolo</button>
				</div>
			</div>
		</div>
		<%} } }%>
	</div>
</body>
</html>