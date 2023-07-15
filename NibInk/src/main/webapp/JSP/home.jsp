<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.model.ItemManager" %>
<%@ page import="com.model.DAOCustomer" %>
<%@ page import="com.model.Customer" %>
<%@ page import="com.model.MessageManager" %>
<%@ page import="com.model.CartManager" %>
<%@ page import="com.model.Cart" %>
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
		int numberOfMostSoldItems = 10;
		HttpSession s = request.getSession();
	
	%>
	<div class="container">
		<% if(!(s.getAttribute("userType").equals("unregistered"))){
	        DAOCustomer db = new DAOCustomer();
	        int userId = (int)s.getAttribute("id");
	        Customer c = db.getCustomerById(userId);
	        String userEmail = c.getEmail();
			CartManager cm = new CartManager();
			Cart carrello = cm.getCart((String)s.getAttribute("sessionId"));	
		%>
		<div id="welcomeUser">
			<div class="title">
				<h3>Bentornato, <%=s.getAttribute("name")%></h3>
			</div>	
			<%if (carrello!=null && !carrello.isEmpty()){ %>
				<p>Hai attualmente <%=carrello.getCart().size()%> <%if(carrello.getCart().size()==1){ %> prodotto<%}else{ %> prodotti <%} %> nel carrello.</p>
			<%}else{ %>
				<p>Il tuo carrello è vuoto.</p>
			<%} %>
			<%if(new MessageManager().getUnreadMessagesCountForUser(userEmail)>0) {%>
				<p>Hai dei messaggi da leggere!</p>
			<%} %>
		</div>
		<%} %>
		<div class="title">
			<h3>Nuovi arrivi</h3>
		</div>
		<div id="newItems" class="itemsShowcase">
			<button id="prevBtn" class="showcaseBttns" onclick="showPrev()">&lt;</button>
			<%	ArrayList<Item> newItems = im.loadNewItems(numberOfNewItems);
				int i=0;
				int visibleCards=4;
				for(Item item : newItems){
					if(i<visibleCards){
						//Card Visibile
					%>
						<div id="item<%=i%>" class="cardContainer" onclick="location.href='/NibInk/JSP/product.jsp?id=<%=item.getCodenumber()%>'">
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
						<div id="item<%=i%>" class="Hidden" onclick="location.href='/NibInk/JSP/product.jsp?id=<%=item.getCodenumber()%>'">
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
					}
				i++;};
			%>
			<button id="nextBtn" class="showcaseBttns" onclick="showNext()">></button>
			<input id="firstHiddenItem" type="hidden" value="<%=visibleCards%>">
			<input id="newItemsNumber" type="hidden" value="<%=i%>">
		</div>
		<div class="categories">
			<div class="buttonsDiv">
				<button id="inkButton" class="smallButton" onclick="location.href='/NibInk/JSP/catalog.jsp?filter=ink'"></button>
				<button id="penButton" class="bigButton" onclick="location.href='/NibInk/JSP/catalog.jsp?filter=pen'"></button>
				
			</div>
			<div class="vuttonsDiv">		
				<button id="noteButton" class="bigButton" onclick="location.href='/NibInk/JSP/catalog.jsp?filter=notebook'"></button>	
				<button id="contactButton" class="smallButton" onclick="location.href='/NibInk/JSP/contactUs.jsp'"></button>
				
			</div>
		</div>
		<div class="title">
			<h3>Oggetti più venduti</h3>
		</div>
		<div id="mostSoldItems" class="itemsShowcase">
			<%	ArrayList<Item> mostSoldItems = im.loadMostSoldItems(numberOfMostSoldItems);
				for(Item item : mostSoldItems){
				%>
					<div id="MSitem<%=i%>" class="cardContainer" onclick="location.href='/NibInk/JSP/product.jsp?id=<%=item.getCodenumber()%>'">
						<div class="cardImg">
							<img class="itemImg" src="/NibInk/images/<%=item.getCodenumber()%>/thumbnail.png">
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
				<%}%>
		</div>
	</div>

<div class="footer">
	<jsp:include page="footer.jsp"/>
</div>
</body>
</html>