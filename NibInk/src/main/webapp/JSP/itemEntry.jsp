<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.model.Item" %>
<%@page import="com.model.DAOItem" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>ADMIN - Item Entry</title>
	<script type="text/javascript" src="/NibInk/JavaScript/itemEntryJS.js"></script>
	<link href="/NibInk/CSS/itemEntry.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">
	<%int id_url=0;//prendilo dall'url
		DAOItem db_Item= new DAOItem();
		Item item= db_Item.getItemFromDB(request.getParameter("id")); 
	%>
	<form action="/NibInk/AddProductServlet" method="post" class="containerForm" onsubmit="saveSizes()" enctype="multipart/form-data">
		<div class="firstHalfForm">
		<h3>Informazioni prodotto</h3>
				<input name="title" id="title" type="text" onkeydown="handleKeyPress(event)" placeholder="Titolo articolo" value="<%= item.getTitle()%>"><br>
				<input name="price" id="price" step="0.01" type="number" min="0" placeholder="Prezzo articolo" value="<%= item.getPrice()%>"><br>
				<input name="vat" id="vat" step="0.01" type="number" min="0" placeholder="IVA articolo" value="<%= item.getVat()%>"><br>
				<input name="color" id="color" type="text" onkeydown="handleKeyPress(event)" placeholder="Colore articolo" value="<%= item.getColor()%>"><br>
				<input name="weight" id="weigth" type="text" onkeydown="handleKeyPress(event)" placeholder="Peso articolo" value="<%= item.getWeight()%>"><br>
				<input name="dimensions" id="dimensions" type="text" onkeydown="handleKeyPress(event)" placeholder="Dimensioni articolo" value="<%= item.getDimensions()%>"><br>
				<input name="description" id="description" type="text" onkeydown="handleKeyPress(event)" placeholder="Descrizione articolo" value="<%= item.getDescription()%>"><br>
				<br>
			<fieldset>
			<legend>Tipologia prodotto:</legend>
				<input type="radio" name="type" id="pen" value="pen" onchange="enableSecondHalf()">
				<label for="pen">Penna</label><br>
				<input type="radio" name="type" id="ink" value="ink" onchange="disableSecondHalf()">
				<label for="ink">Inchiostro</label><br>
				<input type="radio" name="type" id="notebook" value="notebook" onchange="disableSecondHalf()">
				<label for="notebook">Taccuino</label><br>
			</fieldset>
		</div>
		<input type="file" name="imageFile">
		<div class="disabled" id="secondHalf">
		<p class="sizesText">Sizes:<br> 
					
					<input id="textBox" type="text" style="display: inline" onkeydown="handleKeyPress(event)">
					
					<select id="options" style="display: none"></select>
					
					<button id="addButton" style="display: inline" onclick="addOption()" type="button">+</button>
					<button id="removeButton" style="display: none" onclick="removeOption()" type="button">-</button>
					<button id="undoButton" style="display: inline" onclick="undoOption()" type="button">Undo</button>
					<br>
					<button id="textButton" style="display:none" onclick="showTextBox()" type="button">Mostra Input</button>
					<button id="optionButton" style="display:inline" onclick="showOptions()" type="button">Mostra Opzioni</button>
		</p>		
		</div>	
		<div class="submitButton">
			<input type="hidden" id="optionValues" name="optionValues" value="">
			<button type="submit">Aggiungi al catalogo</button>	
		</div>
				
	</form>



</div>
<script type="text/javascript">
window.onload = function() 
{
	var url = window.location.href;
	var url_oggetto= new URL(url);
	var parametri=url_oggetto.searchParams;
	var type=parametri.get("type");
	switch (type)
	{
		case "pen":
			console.log("eccomi1");
			document.getElementById("pen").checked=true;
		break;
		
		case "ink":
			console.log("eccomi2");
			document.getElementById("ink").checked=true;
		break;
		
		case "notebook":
			console.log("eccomi3");
			document.getElementById("notebook").checked=true;
		break;
	}
}
	
</script>
</body>
</html>