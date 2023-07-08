<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.ItemManager" %>
<%@ page import="com.model.Item" %>
<%@ page import="com.model.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.DAOItem" %>
<%@ page import="com.model.DAOCustomer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/NibInk/CSS/Accesso.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%
	if(session.getAttribute("id").toString()==null)
	{
		throw new Exception("Utente non autenticato");
	}
	String id = session.getAttribute("id").toString();
	DAOCustomer daocust = new DAOCustomer();
	Customer utente = daocust.getCustomerById(id);
%>
	<div class="page">
		<div class="title">
			<h1>Accesso e Sicurezza</h1>
		</div>
		<div class="opzioni">
		
			<div class="opzione">
				<div>
					<h2>Nome:</h2>
					<h3><%= utente.getName()%></h3>
				</div>
				<form action="/NibInk/DatiUtente" method="POST">
					<input type="hidden" value="Nome" name="opzione">
					<input type="text" name="cambio" placeholder="Nome">
					<input type="submit" value="Modifica">
				</form>
			</div>
			
			<div class="opzione">
				<div>
					<h2>Email:</h2>
					<h3><%= utente.getEmail()%></h3>
				</div>
				<form action="/NibInk/DatiUtente" method="POST">
					<input type="hidden" value="Email" name="opzione">
					<input type="text" name="cambio" placeholder="Email">
					<input type="submit" value="Modifica">
				</form>
			</div>
			
			<div id="ultimo">
				<div>
					<h2>Password:</h2>
					<h3><%= utente.getPassword()%></h3>
				</div>
				<form action="/NibInk/DatiUtente" method="POST">
					<input type="hidden" value="Password" name="opzione">
					<input type="text" name="cambio" placeholder="Password">
					<input type="submit" value="Modifica">
				</form>
			</div>
			
		</div>
	</div>
</body>
</html>