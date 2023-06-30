<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NibInk - Pagamento</title>
<link href="/NibInk/CSS/payment.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<script src="/NibInk/JavaScript/jquery.js"></script>
<script src="/NibInk/JavaScript/payment.js"> </script>

<jsp:include page="navbar.jsp"/>
	
	<div class="container0">
		<div class="container1">
		<h3>Pagamento ordine</h3>
			<form>
				<p>Intestatario Carta</p>	
				<input type="text" id="cardName"><br>
				<p>Numero della Carta</p>
				<input type="text" id="cardNumber"><br>
				<div  class="secondHalf">
					<div class="shContent">
						<p>Data di Scadenza</p>
						<br>
						<select id="expMonth"></select><select id="expYear"></select>
					</div>
					<div class="shContent">
						<p>Codice di Sicurezza</p>
						<input type="text" id="cardCode">	
					</div>
				</div>
				<div class="billingAddr">
				<input type="checkbox" onclick="toggleBillingAddr()" checked> 
					<div class="cbText">
						<div><p>Indirizzo di fatturazione e spedizione coincidono</p></div>
						<div><p>''Indirizzo utente''<p></div>
					</div>
				</div>
				<div id="inputBAddr">
							<p>Paese</p>
							<select></select>
							<p>Nome e Cognome</p>
							<input type="text">
							<p>Via</p>
							<input type="text">
							<p>Appartamento/Interno/Altro</p>
							<input type="text">
							<p>Codice Postale</p>
							<input type="text">
							<p>Città</p>
							<input type="text">
							<p>Provincia</p>
							<select></select>
						</div>
					
				<div class="shButtons">	
					<button id="submit" onclick="checkAndSubmit()">Rivedi il tuo Ordine</button>
				</div>	
			</form>
		</div>
	</div>
	
</body>
</html>