<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.model.ItemManager" %>
<%@page import="com.model.Item" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.model.DAOItem" %>
<%@page import="com.model.ItemVariant" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/NibInk/CSS/admin.css">
<title>ADMIN - Prodotti</title>
<link rel="icon" type="image/x-icon" href="/NibInk/images/favicon.ico">

<script>
    function updateQuantityInput(index) {
        var sizeSelect = document.getElementById("sizeSelect" + index);
        var quantityInput = document.getElementById("quantityInput" + index);
        var span = document.getElementById("currQnt" + index);
        var param = document.getElementById("itemSize"+ index);
        var button = document.getElementById("modify" + index);

        var selectedSize = sizeSelect.value;
        var maxQuantity = parseInt(sizeSelect.options[sizeSelect.selectedIndex].getAttribute("data-quantity"));

        if (maxQuantity > -1) {
            span.textContent = maxQuantity.toString();
            param.value = selectedSize.toString();
            button.innerHTML = "Aggiorna Quantità"
        }
    }
</script>

</head>
<body>
<%//Admin check
String admin = (String) request.getSession().getAttribute("userType");
if (admin != null && !admin.equals("admin")) {
	response.sendError(HttpServletResponse.SC_FORBIDDEN);
}
%>
<%
DAOItem db=new DAOItem();
int items;
int itemPerPage=30;
int pageNumber=request.getParameter("pageNumber")== null ? 1 : Integer.parseInt(request.getParameter("pageNumber"));
int startIndex=request.getParameter("startIndex")== null || request.getParameter("pageNumber").equals(String.valueOf(1)) ? 0 : Integer.parseInt(request.getParameter("startIndex"));
String filter=request.getParameter("filter");
ItemManager im = new ItemManager();
ArrayList<Item> itemsLoaded;
itemsLoaded=im.loadItems(startIndex, itemPerPage);
items = db.getItemsNumber();
%>

<body>
<div class="navbar">
	<jsp:include page="adminNavbar.jsp"/>
</div>
<table>
  <tr>
    <th>ID</th>
    <th>Copertina</th>
    <th>Titolo</th>
    <th>Prezzo</th>
    <th>Taglia</th>
    <th>Quantità In Magazzino</th>
    <th>Nuova Quantità</th>
  </tr>

  <%
    int i = 0;
    for (Item item : itemsLoaded) {
        %>
        <tr>
            <td><%=item.getCodenumber() %></td>
            <td><img alt="immagineProdotto" class="product_img" src="/NibInk/images/<%=item.getCodenumber()%>/thumbnail.png"></td>
            <td><%=item.getTitle() %></td>
            <td><%=item.getPrice() %></td>
            <% ItemVariant sizeLoaded = new ItemVariant();
               sizeLoaded.loadVariantsOf(item.getCodenumber());
            %>
            <td>
                <select id="sizeSelect<%=i %>" name="size" onChange="updateQuantityInput(<%=i %>)">
                    <% for (Map.Entry<String, Integer> entry : sizeLoaded.getVariants().entrySet()) {
                        int quantityAvailable = entry.getValue();
                    %>
                        <option value="<%=entry.getKey()%>" data-quantity="<%= quantityAvailable%>"><%=entry.getKey()%></option>
                    <% } %>
                    <option hidden="" value="stop" selected>Scegli la taglia</option>
                </select>
            </td>
            <td><span id="currQnt<%=i %>"></span></td>
            <td><form action="/NibInk/setQuantities" method="get">
            	<input type="number" name="quantity" id="quantityInput<%=i %>" onkeydown="handleKeyPress(event)">
            	<input type="hidden" id="itemId" value="<%=item.getCodenumber() %>" name=id>
            	<input type="hidden" id="itemSize<%=i %>" name=size>
            	</form>
            </td>
            <td>
            <form action="/NibInk/JSP/itemEntry.jsp" method="get">
			<input type="hidden" value="<%=item.getCodenumber() %>" name="id" class="invisibleButtons">
            <button type="submit">Modifica Articolo</button>
            </form>
            </td>
        <%
        i++;
    }
%>


  
</table>

				<div class="navigationKeys">
				<% 
					
					for(int k=0, j=1; k<items; k+=itemPerPage, j++)
					{
				%>
						<form action="/NibInk/nextPage" method="get">
							<input type="hidden" value="admin" name="isAdmin" class="invisibleButtons">
							<input type="hidden" value="<%=k%>" name="startIndex" class="invisibleButtons">
							<input type="hidden" value="<%=j%>" name="pageNumber" class="invisibleButtons">
							<input type="submit" value="Pagina <%=j%>" class="navButtons">
						</form>
				<%
					}
				%>
	   			</div>
						
</body>
</html>