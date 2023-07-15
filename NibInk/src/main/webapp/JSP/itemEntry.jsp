<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.model.DAOItem" %>
<%@page import="com.model.Item" %>
<%@page import="com.model.ItemVariant" %>
<%@page import="com.model.DAOVariant" %>
<%@page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>ADMIN - Item Entry</title>
	<script type="text/javascript" src="/NibInk/JavaScript/itemEntryJS.js"></script>
	<link href="/NibInk/CSS/itemEntry.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%//Admin check
String admin = (String) request.getSession().getAttribute("userType");
if (admin != null && !admin.equals("admin")) {
	response.sendError(HttpServletResponse.SC_FORBIDDEN);
}
%>

<%
String id = request.getParameter("id");
DAOItem db = new DAOItem();
Item item = db.getItemFromDB(id);
String type = "undefined";
if(item.getType()!= null)
	type = item.getType();
%>

<div class="container">
	<form action="/NibInk/AddProductServlet" method="post" class="containerForm" onsubmit="saveSizes()">
		<div class="firstHalfForm">
			<h3>Scheda prodotto</h3>
			<input name="title" id="title" type="text" onkeydown="handleKeyPress(event)" placeholder="Titolo articolo" <% if (id != null) { %>value="<%= item.getTitle() %>"<% } %>><br>
			<input name="price" id="price" step="0.01" type="number" min="0" placeholder="Prezzo articolo" <% if (id != null) { %>value="<%= item.getPrice() %>"<% } %>><br>
			<input name="vat" id="vat" step="0.01" type="number" min="0" placeholder="IVA articolo" <% if (id != null) { %>value="<%= item.getVat() %>"<% } %>><br>
			<input name="color" id="color" type="text" onkeydown="handleKeyPress(event)" placeholder="Colore articolo" <% if (id != null) { %>value="<%= item.getColor() %>"<% } %>><br>
			<input name="weight" id="weight" type="number" step="0.01" onkeydown="handleKeyPress(event)" placeholder="Peso articolo" <% if (id != null) { %>value="<%= item.getWeight() %>"<% } %>><br>
			<input name="dimensions" id="dimensions" type="text" onkeydown="handleKeyPress(event)" placeholder="Dimensioni articolo" <% if (id != null) { %>value="<%= item.getDimensions() %>"<% } %>><br>
			<input name="description" id="description" type="text" onkeydown="handleKeyPress(event)" placeholder="Descrizione articolo" <% if (id != null) { %>value="<%= item.getDescription() %>"<% } %>><br>
			<br>
			<fieldset>
				<legend>Tipologia prodotto:</legend>
				<script type="text/javascript">
			    window.onload = function() {
			        var type = "<%= type %>"; 
			        // Check the appropriate radio button based on the 'type' value
			        if (type === "pen") {
			            document.getElementById("pen").checked = true;
			            enableSecondHalf();
			        } else if (type === "ink") {
			            document.getElementById("ink").checked = true;
			            enableSecondHalf();
			        } else if (type === "notebook") {
			            document.getElementById("notebook").checked = true;
			            enableSecondHalf();
			        }
			    };
			    </script>
				<input type="radio" name="type" id="pen" value="pen" onchange="enableSecondHalf()">
				<label for="pen">Penna</label><br>
				<input type="radio" name="type" id="ink" value="ink" onchange="enableSecondHalf()">
				<label for="ink">Inchiostro</label><br>
				<input type="radio" name="type" id="notebook" value="notebook" onchange="enableSecondHalf()">
				<label for="notebook">Taccuino</label><br>
			</fieldset>
		</div>
		<div class="disabled" id="secondHalf">
			<p class="sizesText">Taglie:<br>
				<input id="textBox" type="text" style="display: inline" onkeydown="handleKeyPress(event)">
				<select id="options">
					<%
					if (id != null) {
						DAOVariant dbsize = new DAOVariant();
						Map<String, Integer> variants = dbsize.loadSingleItemVariants(item);
						for (Map.Entry<String, Integer> entry : variants.entrySet()) {
							String size = entry.getKey();
					%>
					<option value="<%= size %>"><%= size %></option>
					<%
						}
					}
					%>
				</select>
				<button id="addButton" style="display: inline" onclick="addOption()" type="button">+</button>
				<button id="removeButton" style="display: none" onclick="removeOption()" type="button">-</button>
				<button id="undoButton" style="display: inline" onclick="undoOption()" type="button">Undo</button>
				<br>
				<button id="textButton" style="display:none" onclick="showTextBox()" type="button">Mostra Input</button>
				<button id="optionButton" style="display:inline" onclick="showOptions()" type="button">Mostra Opzioni</button>
			</p>
		</div>
		<div class="submitButton">
			<%
			String text = "Aggiungi al Catalogo";
			if(id!=null){ text = "Modifica"; %>
			<input type="hidden" id="itemId" name="item" value="<%= id%>">
			<%} %>
			<input type="hidden" id="optionValues" name="optionValues" value="">

			<button type="submit" onclick="saveSizes()"><%=text %></button>
		</div>
	</form>
	<%if(id!=null){ %>
		<div class="images">
			<form id="uploadForm" enctype="multipart/form-data" action="/NibInk/UploadServlet" method="post">
  			<h3>Carica Copertina:</h3>
	          <input type="file" name="thumbnail" class="files">
			  <input type="hidden" id="itemId" name="itemId" value="<%=id%>">
  			
  			<h3>Carica altre foto:</h3>
  			<input type="file" name="photos[]" class="files" multiple>
  			<div class="submitButton">
			<input type="hidden" id="itemId" name="item" value="id">
  			<button type="submit">Carica Immagini</button>
  			</div>
  			</form>
  			<button id="showPopup" onclick="showUploadedPhotos()" type="button">Mostra foto caricate</button>
		</div>
		<%} %>
		<div id="popup">
			<h3>Anteprima immagini:</h3>
			<div id="thumbnailPreview"></div>
			<div id="photosPreview"></div>
			<button id="closePopup" onclick="closePopUp()">Chiudi</button>
		</div>
	</div>
		
</body>
</html>