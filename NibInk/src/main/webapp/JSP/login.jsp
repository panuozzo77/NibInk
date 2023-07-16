<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NibInk - Accesso Utente</title>
<link rel="icon" type="image/x-icon" href="/NibInk/images/favicon.ico">
<link rel="stylesheet" href="/NibInk/CSS/login.css">
<script type="text/javascript" src="/NibInk/JavaScript/library.js"></script>
</head>
<body>
<div>
<script src="/NibInk/JavaScript/jquery.js"></script>
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
		<div class="scelta_azione">
			<input type="button" id="forgotPassword" value="Password Dimenticata?" onclick="togglePopup()">
			<br><br>
		</div>
	</form>
</div>

<div id="campi_input_reg">
	<form action="/NibInk/RegistrationServlet" id="form_registration" method="POST">
		<img alt="" src="/NibInk/images/logo.png">
		<input type="text" placeholder="Email" id="email2" name="email">
		<div id="emailError" style="display: none; color: red; font-size: 12px;">Inserisca un'email accettabile.</div>
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

<div class="popup-overlay" id="popupOverlay"></div>
    <div class="popup-container" id="popupContainer">
        <h2>Recupero password</h2>
        <div id="passwordResetForm">
            <label for="FPname">Nome utente:</label>
            <input type="text" id="FPname" name="FPname">
            <label for="FPsurname">Cognome:</label>
            <input type="text" id="FPsurname" name="FPsurname">
            <label for="FPemail">Email:</label>
            <input type="email" id="FPemail" name="FPemail">
            <button class="FPbttns" onclick="passwordResetCheckAndSubmit()"> Richiedi reset password </button>
        </div>
        <button class="FPbttns" onclick="togglePopup()">Indietro</button>
</div>
    
<script>
        function togglePopup() {
            var overlay = document.getElementById("popupOverlay");
            var container = document.getElementById("popupContainer");
            overlay.style.display = overlay.style.display === "block" ? "none" : "block";
            container.style.display = container.style.display === "block" ? "none" : "block";
        }
        
        function passwordResetCheckAndSubmit(){
        	var nameSurnameTest=/^([A-Za-zàèéìòóù]+\s?)+$/;
        	var emailTest = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        	if( nameSurnameTest.test($("#FPname").val()) && nameSurnameTest.test($("#FPsurname").val()) && emailTest.test($("#FPemail").val())){
        		ajaxResetPassword();
        		setTimeout(function (){window.location.reload();}, 5000);
        		alert("Se i dati inseriti corrispondono, riceverai un'email con la nuova password provvisoria!");
        		
        	}
        	else{
        		alert("Controlla bene i dati inseriti!");
        	}
        }
        
        function ajaxResetPassword(){
        	var name=$("#FPname").val();
        	var surname=$("#FPsurname").val();
        	var email=$("#FPemail").val();
        	$.post('/NibInk/resetPassword', {"name": name, "surname": surname, "email": email});
        }
</script>


<%
	String errorHandler = request.getParameter("error");
	if(errorHandler!=null) {
		switch(errorHandler) {
			case("1"):%>
			<script>
				alert("Errore di Registrazione!\nQuesta email Ã¨ giÃ  utilizzata.\nNon ricordi la password?");
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
window.onload = function() { //non possono essere presenti piï¿½ eventi window.onload
	var nome = document.getElementById('nome2');
	var cognome = document.getElementById('cognome2');
    var passwordRegistration = document.getElementById('password2');
    var invio = document.getElementById('invio2');
    var lengthError = document.getElementById('lengthError');
    var email = document.getElementById('email2');
    var passwordValidate;
    var emailValidate;
    var nomeValidate;
    var cognomeValidate;
    
    
    if(nome.value === '')
    	{
    		nomeValidate=false;
    	}
    else
    		nomeValidate =true;
    
    if(cognome.value === '')
    	{
    		cognomeValidate = false;
    	}
    else
    		cognomeValidate = true;
    
    if(!(password.lenght >=12 && /[A-Z]/.test(passwordRegistration.value) && /[\/\\_\-=%$*ï¿½ï¿½]/.test(passwordRegistration.value)))
    	{
    		passwordValidate= false;
    	}
    else
    		passwordValidate = true;
    
    if(!(/[@]/.test(email.value)))
    	emailValidate=false;
    else
    	emailValidate=true;
    
    if(!(passwordValidate && cognomeValidation && nomeValidate && emailValidation))
    	invio.disabled=true;
    else
    	invio.disabled=false;
    
    var emailError = document.getElementById('emailError');
    var invio = document.getElementById('invio2');
    email.addEventListener('input', function() {
        var isEmail = /[@]/.test(email.value);
        if (isEmail) {
            emailError.style.display = 'none';
            emailValidate = true;
            if(passwordValidate && cognomeValidate && nomeValidate)
            	invio.disabled = false;
            else
            	invio.disabled = true;
        } else {
        	emailValidate=false;
            emailError.style.display = 'block';
            invio.disabled = true;
        }
    });
    
    passwordRegistration.addEventListener('input', function() {
        var password = passwordRegistration.value;
        var contiene_maiuscolo = /[A-Z]/.test(passwordRegistration.value);
        var contiene_carattere_speciale = /[\/\\_\-=%$*ï¿½ï¿½#@!&^(){}[\]<>?|~`.,:;_+]/.test(passwordRegistration.value);        								  
        if (contiene_maiuscolo && password.length >= 12 && contiene_carattere_speciale) {
            lengthError.style.display = 'none';
            passwordValidate=true;
            if(emailValidate && nomeValidate && cognomeValidate)
            	{
            		invio.disabled = false;
            	}
            else
            	invio.disabled = true;
        }
        else if(contiene_maiuscolo && password.length >= 12)
        	{
        		lengthError.innerHTML = "La password deve contenere un carattere speciale";
        		passwordValidate = false;
	        	lengthError.style.display = 'block';
	            invio.disabled = true;
        	}
        else if(contiene_maiuscolo && contiene_carattere_speciale)
    	{
        	passwordValidate = false;
        	lengthError.innerHTML = "La password deve essere lunga minimo 12 caratteri";
        	lengthError.style.display = 'block';
            invio.disabled = true;
    	}
        else if(password.length >= 12 && contiene_carattere_speciale)
    	{
        	passwordValidate = false;
        	lengthError.innerHTML = "La password deve contenere almeno un carattere Maiuscolo";
        	lengthError.style.display = 'block';
            invio.disabled = true;
    	}
	    else if(contiene_maiuscolo)
		{
	    	passwordValidate = false;
	    	lengthError.innerHTML = "La password deve essere almeno di 12 caratteri e avere un carattere speciale";
        	lengthError.style.display = 'block';
            invio.disabled = true;
		}
	    else if(password.length >= 12)
		{
	    	passwordValidate = false;
	    	lengthError.innerHTML = "La password deve avere almeno un carattere maiuscolo e un carattere speciale";
        	lengthError.style.display = 'block';
            invio.disabled = true;
		}
	    else if(contiene_carattere_speciale)
		{
	    	passwordValidate = false;
	    	lengthError.innerHTML = "La password deve essere di 12 caratteri e avere almeno un numero";
        	lengthError.style.display = 'block';
            invio.disabled = true;
		}
        else {
        	passwordValidate = false;
        	lengthError.innerHTML = "La password deve essere di 12 caratteri avere almeno un numero e un carattere speciale";
            lengthError.style.display = 'block';
            invio.disabled = true;
        }
    });
    
    var emailError = document.getElementById('emailError');
    email.addEventListener('blur', function() {
        var isEmail = /[@]/.test(email.value);
        if (isEmail) {
            emailError.style.display = 'none';
            emailValidate = true;
            if(nomeValidate && cognomeValidate && passwordValidate)
            	{
            		invio.disabled = false;
            	}
            else
            	invio.disabled = true;
        } else {
        	emailValidate=false;
            emailError.style.display = 'block';
            invio.disabled = true;
        }
    });
    
    cognome.addEventListener('input', function()
    		{
    			if(cognome.value!='')
    				{
    					cognomeValidate=true;
    					if(passwordValidate && nomeValidate && emailValidate)
    						invio.disabled=false;
    					else
    						invio.disabled=true;
    				}
    			else
    				{
    					cognomeValidate=false;
    					invio.disabled = true;
    				}
    		});
    nome.addEventListener('input', function()
    		{
				if(nome.value!='')
					{
						nomeValidate=true;
						if(passwordValidate && cognomeValidate && emailValidate)
							invio.disabled=false;
						else
							invio.disabled=true;
					}
				else
					{
						nomeValidate=false;
						invio.disabled = true;
					}
			});
    
    
    var email2 = document.getElementById('email');
    var password2 = document.getElementById('password');
    var invio2 = document.getElementById('invio');
    var emailValidate2;
    var passwordValidate2;

    if (email2.value === '') {
        emailValidate2 = false;
    } else {
        emailValidate2 = true;
    }

    if (password2.value === '') {
        passwordValidate2 = false;
    } else {
        passwordValidate2 = true;
    }

    if (!(emailValidate2 && passwordValidate2)) {
        invio2.disabled = true;
    }

    password2.addEventListener('input', function() {
        if (password2.value !== '') {
            passwordValidate2 = true;
            if (emailValidate2) {
                invio2.disabled = false;
            } else {
                invio2.disabled = true;
            }
        } else {
            passwordValidate2 = false;
            invio2.disabled = true;
        }
    });

    email2.addEventListener('input', function() {
        if (email2.value !== '') {
            emailValidate2 = true;
            if (passwordValidate2) {
                invio2.disabled = false;
            } else {
                invio2.disabled = true;
            }
        } else {
            emailValidate2 = false;
            invio2.disabled = true;
        }
    });

}
</script>

</body>
</html>