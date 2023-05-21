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
	<form action="LoginServlet" id="form_login" method="POST">
		<img alt="" src="/NibInk/images/logo.png">
		<input type="text" placeholder="Email" id="email" name="j_username">
		<input type="password" placeholder="Password" id="password" name="j_password">
				<input type="submit" value="Accedi">
		<div class="scelta_azione">
			<input type="button" id="loginBtn" value="Login" onclick="login()">
			<input type="button" id="registerBtn" value="Iscrizione" onclick="register()">
			<br><br>
		</div>
	</form>
</div>

<div id="campi_input_reg">
	<form action="RegistrationServlet" id="form_login" method="POST">
		<img alt="" src="/NibInk/images/logo.png">
		<input type="text" placeholder="Email" id="email2" name="j_username">
		<input type="password" placeholder="Password" id="password2" name="J_password">
		<input type="text" placeholder="Nome Utente" id="nome2" name="name">
		<input type="text" placeholder="Cognome" id="cognome2" name="surname">
		<input type="text" placeholder="Indirizzo" id="indirizzo2" name="address">
		<input type="text" placeholder="Citt�" id="citta2" name="city">
		<input type="text" placeholder="Cap" id="cap" name="cap">
				<input type="submit" value="Iscriviti">
		<div class="scelta_azione">
			<input type="button" id="LogIn" value="LogIn" onclick="login()">
			<input type="button" id="Iscrizione" value="Iscrizione" onclick="register()">
			<br><br>
		</div>

	</form>
</div>

<div>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>