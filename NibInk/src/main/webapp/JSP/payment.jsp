<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NibInk - Pagamento</title>
<link href="/NibInk/CSS/payment.css" rel="stylesheet" type="text/css"/>

</style>
</head>
<body>
<script src="/NibInk/JavaScript/jquery.js"></script>
<script src="/NibInk/JavaScript/payment.js"> </script>

<jsp:include page="navbar.jsp"/>
	
	<div class="container0">
		<div class="container1">
		<h2>Pagamento ordine</h2><br>
			<form id="paymentForm" action="/NibInk/toSummary" method="post" onkeydown="preventSubmitWithEnter(event)" onsubmit="console.log('SUBMIT!!');">
			
				<h4>Metodo di pagamento</h4>
				<div class="paymentMethods">
				<div>
					<input name="paymentRadio" id="pm1" type="radio" value="card" onclick="showCard()">
					<label for="pm1">Carta di Credito</label></div>
					<br>
					<div>
					<input name="paymentRadio" id="pm2" type="radio" value="cashOnDelivery" onclick="hideCard()">
					<label for="pm2">Contrassegno</label></div>
				</div>

				<div id="inputCard" class="Hidden">
					<h4>Dati di pagamento</h4>
					<p class="required">Intestatario Carta</p>	
					<input name="cardName" type="text" id="cardName"><br>
					<p class="required">Numero della Carta</p>
					<input name="cardNumber" type="text" id="cardNumber"><br>
					<div  class="secondHalf">
						<div class="shContent">
							<p class="required">Data di Scadenza</p>
							<br>
							<select name="expMonth" id="expMonth" class="selectColor" onclick="revertColor()"></select><select name="expYear" id="expYear" class="selectColor"></select>
						</div>
						<div class="shContent">
							<p class="required">Codice di Sicurezza</p>
							<input name="cardCode" type="text" id="cardCode">	
						</div>
					</div>
				</div>
				
				<div class="checkBoxes">
					<div id="firstCheckBox" class="billingAddr">
						<input type="checkbox" onclick="toggleSecondCB()" id="BACheckBox"> 
						<div class="cbText">
							<div><p>Desidero ricevere la fattura</p></div>
						</div>
					</div>
					<div  id="secondCheckBox" class="Hidden">
						<input type="checkbox" onclick="toggleBillingAddr()" id="addrCheckbox" checked> 
						<div class="cbText">
							<div><p>Indirizzo di fatturazione e spedizione coincidono</p></div>
							<div><p id="userSavedAddr"> <%= session.getAttribute("addr") %><p></div>
						</div>	
					</div>
				</div>
				
				<div id="inputBAddr" class="Hidden">
					<h3>Indirizzo di Fatturazione</h3>
					<p class="required">Paese</p>
					<br>
					<select id="Country" onchange="toggleItaly()">
					</select>
					
					<p class="required">Nome e Cognome</p>
					<input id="baNameSurname" type="text">
					
					<div class="addr">
						<div class="addrFirst">
							<p class="required">Via</p>
							<input id="baStreet"type="text">
						</div>
						<div class="addrSecond">
							<p class="required">N° Civico</p>
							<input id="baNumber"type="text">
						</div>
					</div>
					
					<p>Appartamento/Interno/Altro</p>
					<input id="baMoreInfo" type="text">
					
					<p class="required italyOnly">Codice Postale</p>
					<input id="baZipCode" class="italyOnly" type="text">
					
					<p class="required">Città</p>
					<input id="baCity" type="text">
					
					<p class="required italyOnly">Provincia</p>
					<br>
					<select id="State" class="italyOnly"></select>
				</div>
					
				<div class="shButtons">	
					<button id="submit" onclick="checkAndSubmit(event)">Rivedi il tuo Ordine</button>
					<input id="realSubmit" type="submit" class="Hidden">
				</div>	
			</form>
		</div>
	</div>
	
</body>
</html>