<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accesso Utente</title>
<link rel="stylesheet" href="/NibInk/CSS/login.css">
<script type="text/javascript" src="/NibInk/JavaScript/library.js"></script>
</head>
<body>
<div>
<jsp:include page="navbar.jsp"/>
</div>


<div id="campi_input">
	<form action="/NibInk/LoginServlet" id="form_login" method="POST">
		<img alt="" src="/NibInk/images/logo.png">
		<input type="text" placeholder="Email" id="email" name="email">
		<input type="password" placeholder="Password" id="password" name="password">
				<input type="submit" value="Accedi">
		<div class="scelta_azione">
			<input type="button" id="loginBtn" value="Login" onclick="login()">
			<input type="button" id="registerBtn" value="Iscrizione" onclick="register()">
			<br><br>
		</div>
	</form>
</div>

<div id="campi_input_reg">
	<form action="/NibInk/RegistrationServlet" id="form_registration" method="POST">
		<img alt="" src="/NibInk/images/logo.png">
		<input type="text" placeholder="Email" id="email2" name="email">
		<input type="password" placeholder="Password" id="password2" name="password">
		<input type="text" placeholder="Nome Utente" id="nome2" name="name">
		<input type="text" placeholder="Cognome" id="cognome2" name="surname">
				<input type="submit" value="Iscriviti">
		<div class="scelta_azione">
			<input type="button" id="loginBtn2" value="LogIn" onclick="login()">
			<input type="button" id="registerBtn2" value="Iscrizione" onclick="register()">
			<br><br>
		</div>
	</form>
</div>

<%
	String errorHandler = request.getParameter("error");
	if(errorHandler!=null) {
		switch(errorHandler) {
			case("1"):%>
			<script>
				alert("Errore di Registrazione!\nQuesta email è già utilizzata.\nNon ricordi la password?");
				register();	
			</script>
			<%break; 
			case("2"):%>
			<script>
				alert("Errore di Accesso!\nEmail o Password errata.");
				login();	
			</script>
			<%break; 
			case("3"):%>
			<script>
				alert("Errore di Accesso!\nAccount inesistente.");
				register();	
			</script>
			<%break;
		}
	}%>
<div>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>