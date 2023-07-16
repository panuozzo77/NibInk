<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<%@ page import="javax.servlet.http.*" %>
<%@ page import="com.model.*"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Email/Password reset tester</title>
</head>
<body>

<%//Admin check
String admin = (String) request.getSession().getAttribute("userType");
if (admin != null && !admin.equals("admin")) {
	response.sendError(HttpServletResponse.SC_FORBIDDEN);
}
%>
	<%
	HttpSession sessione = request.getSession();
    String user = (String) sessione.getAttribute("userType");
    DAOCustomer db = new DAOCustomer();
	%>


	<h3>Test invio email</h3>
	<form method="post" action="/NibInk/sendEmail">
		<input name="sendTo" type="text" placeholder="email">
		<input name="subject" type="text" placeholder="subject">
		<input name="message" type="text" placeholder="message">
		<input type="submit">
	</form>
	<h3>Test reset Password</h3>
	<form method="post" action="/NibInk/resetPassword">
		<input name="email" type="text" placeholder="email">
		<input name="name" type="text" placeholder="name">
		<input type="submit">
	</form>
	
	<% if(user.equals("admin")){
	
	%>
	
	<h3>Test cambio Password ADMIN</h3>
	<form method="post" action="/NibInk/changePassword">
		<input name="id" type="text" placeholder="id utente">
		<input name="email" type="text" placeholder="Email">
		<input name="newPassword" type="text" placeholder="newPassword">
		<input type="submit">
	</form>
	<%}else{ %>
	
	<h3>Test cambio Password USER</h3>
	<form method="post" action="/NibInk/changePassword">
		<input name="email" type="text" placeholder="Email">
		<input name="oldPassword" type="text" placeholder="oldPassword">
		<input name="newPassword" type="text" placeholder="newPassword">
		<input type="submit">
	</form>
	<%} %>
	

</body>
</html>