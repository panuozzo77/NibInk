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
		<div class="scelta_azione">
			<input type="button" id="loginBtn" value="Login" onclick="login()">
			<input type="button" id="registerBtn" value="Iscrizione" onclick="register()">
			<br><br>
		</div>
		<input type="submit" value="Accedi" id="invio">
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
		<div class="scelta_azione">
			<input type="button" id="loginBtn2" value="LogIn" onclick="login()">
			<input type="button" id="registerBtn2" value="Iscrizione" onclick="register()">
			<br><br>
		</div>
		<input type="submit" value="Iscriviti" id="invio2">
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

</div>

<script type="text/javascript">
window.onload = function() {
	var passwordRegistration = document.getElementById('password2');
	var invio = document.getElementById('invio2');
	var lengthError = document.getElementById('lengthError');

	passwordRegistration.addEventListener('blur', function() {
	    var password = passwordRegistration.value;
	    var contiene_maiuscolo = /[A-Z]/.test(password);
	    var contiene_carattere_speciale = /[\/\\_\-+=%$*°§@!#$^&(){}[\]]/.test(password);

	    if (contiene_maiuscolo && password.length >= 12 && contiene_carattere_speciale) {
	        lengthError.style.display = 'none';
	        invio.disabled = false;
	    } else if (contiene_maiuscolo && password.length >= 12) {
	        lengthError.style.display = 'none';
	        invio.disabled = false;
	    } else if (contiene_maiuscolo && contiene_carattere_speciale) {
	        lengthError.innerHTML = "La password deve essere lunga minimo 12 caratteri";
	        lengthError.style.display = 'block';
	        invio.disabled = true;
	    } else if (password.length >= 12 && contiene_carattere_speciale) {
	        lengthError.innerHTML = "La password deve contenere almeno un carattere Maiuscolo";
	        lengthError.style.display = 'block';
	        invio.disabled = true;
	    } else if (contiene_maiuscolo) {
	        lengthError.innerHTML = "La password deve essere almeno di 12 caratteri e avere un carattere speciale";
	        lengthError.style.display = 'block';
	        invio.disabled = true;
	    } else if (password.length >= 12) {
	        lengthError.innerHTML = "La password deve avere almeno un carattere maiuscolo e un carattere speciale";
	        lengthError.style.display = 'block';
	        invio.disabled = true;
	    } else if (contiene_carattere_speciale) {
	        lengthError.innerHTML = "La password deve essere di 12 caratteri e avere almeno un numero";
	        lengthError.style.display = 'block';
	        invio.disabled = true;
	    } else {
	        lengthError.style.display = 'block';
	        invio.disabled = true;
	    }
	});
    
    var email = document.getElementById('email2');	//non possono essere presenti più eventi window.onload
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