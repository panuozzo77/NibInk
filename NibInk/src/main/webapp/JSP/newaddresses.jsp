<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page errorPage="/JSP/login.jsp?error=3" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/NibInk/CSS/newaddresses.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%
	if(session.getAttribute("id").toString()==null)
	{
		throw new Exception("Utente non autenticato");
	}
	String id = session.getAttribute("id").toString();
%>
	<div class=page>
		<h2>Aggiungi nuovo indirizzo</h2>
			<form action="/NibInk/NewAddresses" method="POST">
				<div class="container">
				  <input required="" type="text" name="Name" class="input">
				  <label class="label">Nome</label>
				</div>
				<div class="container">
				  <input required="" type="text" name="Cognome" class="input">
				  <label class="label">Cognome</label>
				</div>
				<div class="container">
				  <input required="" type="text" name="Country" class="input">
				  <label class="label">Paese</label>
				</div>
				<div class="container">
				  <input required="" type="text" name="Citta" class="input">
				  <label class="label">Città</label>
				</div>
				<div class="container">
				  <input required="" type="text" name="Street" class="input">
				  <label class="label">Strada</label>
				</div>
				<div class="container">
				  <input required="" type="text" name="ZipCode" class="input">
				  <label class="label">ZipCode</label>
				</div>
				<div class="container">
				  <input required="" type="text" name="Number" class="input">
				  <label class="label">Numero</label>
				</div>
				<h3>L'indirizzo è l'indirizzo di fatturazione?</h3>
				<div class="radio-inputs">
				  <label class="radio">
				    <input type="radio" name="radio" checked="" value="true">
				    <span class="name">SI</span>
				  </label>
				  <label class="radio">
				    <input type="radio" name="radio" value="false">
				    <span class="name">NO</span>
				  </label>
				</div>
				<input type="hidden" value="<%= id%>" name="id">
				<input type="submit" class="custom-button" value="Aggiungi nuovo indirizzo">
			</form>
	</div>
</body>
</html>