<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.model.DAOAddresses" %>
<%@ page import="com.model.Addresses" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/NibInk/CSS/indirizzi.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<%
		int i=1;
		DAOAddresses db = new DAOAddresses();
		ArrayList<Addresses> indirizzi;
		int id = (int)session.getAttribute("id");
		indirizzi = db.allAddresses(id);
	%>
	<div class="page">
	<h2>I tuoi indirizzi</h2>
		<div class="riga">
			<div class="indirizzo" onClick="Rinderizzamento()">
				<img alt="" src="/NibInk/images/indirizzo.png">
				<h3>Aggiungi nuovo indirizzo</h3>
			</div>
			<c:forEach items="<%= indirizzi%>" var="item">
				<% 
				if(i==0)
					{ 
				%>
    					<div class="riga"> 				
    			<%
    				} 
    			%>
    			<div class="indirizzo">
    				<h3>${item.getName()} ${item.getCognome()}</h3>
    				<h4>${item.getCity()}</h4>
    				<h4>${item.getStreet()} ${item.getNumber()}</h4>
    			</div>
    			
    			<% 
    				i++;
					if (i==3)
						{i=0; 
					%>
					 	</div>
					<% 
						} 
					%>
			</c:forEach>
			</div>
	</div>
	<script type="text/javascript">
		function Rinderizzamento()
		{
			var urlPaginaDestinazione = "/NibInk/JSP/newaddresses.jsp";
            window.location.href = urlPaginaDestinazione;
		}
	</script>
</body>
</html>