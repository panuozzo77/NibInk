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
				<input type="submit" value="Accedi" id="invio">
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
		<div id="emailError" style="display: none; color: red; font-size: 12px;">Inserisca un email accettabile.</div>
		<input type="password" placeholder="Password" id="password2" name="password">
		<div id="lengthError" style="display: none; color: red; font-size: 12px;">La password deve contenere almeno 12 caratteri, un carattere maiuscolo, un numero e un carattere speciale.</div>
		<input type="text" placeholder="Nome Utente" id="nome2" name="name">
		<input type="text" placeholder="Cognome" id="cognome2" name="surname">
				<input type="submit" value="Iscriviti" id="invio2">
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

<script type="text/javascript">
window.onload = function() {
    var passwordRegistration = document.getElementById('password2');
    var invio = document.getElementById('invio2');
    var lengthError = document.getElementById('lengthError');
    passwordRegistration.addEventListener('blur', function() {
        var password = passwordRegistration.value;
        var contiene_maiuscolo = /[A-Z]/.test(passwordRegistration.value);
        var contiene_carattere_speciale = /[\/\\_\-=%$*°§]/.test(passwordRegistration.value);
        if (contiene_maiuscolo && password.length >= 12 && contiene_carattere_speciale) {
            lengthError.style.display = 'none';
            invio.disabled = false;
        } else {
            lengthError.style.display = 'block';
            invio.disabled = true;
        }
    });
}
</script>

<script type="text/javascript">
window.onload = function() {
    var email = document.getElementById('email2');
    var emailError = document.getElementById('emailError');
    var invio = document.getElementById('invio2');
    email.addEventListener('blur', function() {
        var isEmail = /[@]/.test(email.value);
        if (isEmail) {
            emailError.style.display = 'none';
            invio.disabled = false;
        } else {
            emailError.style.display = 'block';
            invio.disabled = true;
        }
    });
}
</script>

</body>
</html>