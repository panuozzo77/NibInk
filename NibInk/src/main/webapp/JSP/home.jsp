<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="com.model.ItemManager" %>
<%@ page import="com.model.Item" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>NibInk - Homepage </title>
	<link href="/NibInk/CSS/home.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<script src="/NibInk/JavaScript/jquery.js"></script>
<script src="/NibInk/JavaScript/home.js"> </script>

<div class=navbar>
<jsp:include page="navbar.jsp"/>
</div>
	<%
		ItemManager im = new ItemManager();
		int numberOfNewItems = 10;
	
	%>
	<div class="container">
		<div id="welcomeUser">
			<!-- Div nel caso l'utente sia registrato -->
		</div>
		<div class="title">
			<h3>Nuovi arrivi</h3>
		</div>
		<div id="newItems" class="itemsShowcase">
			
			<%	ArrayList<Item> newItems = im.loadNewItems(numberOfNewItems);
				int i=0;
				for(Item item : newItems){
					if(i<4){
						//Card Visibile
					%>
						<div id="item<%=i%>" class="cardContainer">
							<div class="cardImg">
								<img class="itemImg" src="/NibInk/images/<%=item.getCodenumber() %>/thumbnail.png">
							</div>
							<div class="cardText">
								<div class="cardTitle">
									<p><%=item.getTitle() %></p>
								</div>
								
								<div class="cardPrice">
									<fmt:formatNumber value="<%=item.getPrice() %>" type="currency"/>
								</div>
							</div>
						</div>
					<%
					}else{
						//Card Nascosta
					%>
						<div id="item<%=i%>" class="Hidden">
							<div class="cardImg">
								<img class="itemImg" src="/NibInk/images/<%=item.getCodenumber() %>/thumbnail.png">
							</div>
							<div class="cardText">
								<div class="cardTitle">
									<p><%=item.getTitle() %></p>
								</div>
								<div class="cardDescription">
									<p><%=item.getDescription() %></p>
								</div>
								<div class="cardPrice">
									<fmt:formatNumber value="<%=item.getPrice() %>" type="currency"/>
								</div>
							</div>
						</div>
					<%
					}
				i++;};
			%>
		</div>
		<div class="categories">
		
		</div>
		<div id="mostSoldItems" class="itemsShowcase">
		
		</div>
	</div>

<div class="footer">
	<jsp:include page="footer.jsp"/>
</div>
</body>
</html>