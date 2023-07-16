<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NibInk - Il Mio Account</title>
<link rel="icon" type="image/x-icon" href="/NibInk/images/favicon.ico">
<link href="/NibInk/CSS/myAccount.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<script src="/NibInk/JavaScript/jquery.js"></script>
<div class=navbar>
<jsp:include page="navbar.jsp"/>
</div>

<div class="pagina">
	<h1>Mio Account</h1>
	<% if(session.getAttribute("userType").toString().equals("unregistered")) { 
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
		//TODO aggiungere la card per le carte salvate
	} %>
		<div class="riga">
				<div class="opzione" onClick="Ordini()">
					<img alt="" src="/NibInk/images/ordini.png">
					<div class="spiegazione">
						<h2 class="title"> Ordini</h2>
						<p>Ordini effettuati.</p>
					</div>
				</div>
				
				<div class="opzione" onClick="togglePopup()">
					<img alt="" src="/NibInk/images/accesso.png">
					<div class="spiegazione">
						<h2 class="title">Accesso e Sicurezza</h2>
						<p>Metodi di accesso.</p>
					</div>
				</div>
				
				<div class="opzione" onClick="comunicazioni()">
					<img alt="" src="/NibInk/images/comunicazioni.png">
					<div class="spiegazione">
						<h2 class="title">Comunicazioni</h2>
						<p>Avvisi e comunicazioni.</p>
					</div>
				</div>
		</div>
		
		<div class="riga">
				<div class="opzione" onclick="contattaci()">
					<img alt="" src="/NibInk/images/Contattaci.png">
					<div class="spiegazione">
						<h2 class="title">Contattaci</h2>
						<p>Contatta il nostro <br>servizio clienti</p>
					</div>
				</div>
				
				<div class="opzione" onClick="Indirizzi()">
					<img alt="" src="/NibInk/images/indirizzi.png">
					<div class="spiegazione">
						<h2 class="title">Indirizzi</h2>
						<p>Gestisci gli indirizzi<br> di consegna</p>
					</div>
				</div>
		</div>
		
	</div>
	
	<div class="popup-overlay" id="popupOverlay"></div>
    <div class="popup-container" id="popupContainer">
        <h2>Cambio password</h2>
        <div id="passwordChangeForm">
        	<label for="CPemail">Email:</label>
            <input type="email" id="CPemail" name="CPemail">
            <label for="CPpassword">Passowrd corrente:</label>
            <input type="password" id="CPpassword" name="CPpassword">
            <label for="CPpasswordNew1">Nuova password:</label>
            <input type="password" id="CPpasswordNew1" name="CPpasswordNew1">
            <label for="CPpasswordNew2">Ripeti nuova password:</label>
            <input type="password" id="CPpasswordNew2" name="CPpasswordNew2">
            <button class="CPbttns" onclick="passwordChangeCheckAndSubmit()"> Richiedi reset password </button>
        </div>
        <button class="CPbttns" onclick="togglePopup()">Indietro</button>
</div>
    
<script>
        function togglePopup() {
            var overlay = document.getElementById("popupOverlay");
            var container = document.getElementById("popupContainer");
            overlay.style.display = overlay.style.display === "block" ? "none" : "block";
            container.style.display = container.style.display === "block" ? "none" : "block";
        }
        
        function passwordChangeCheckAndSubmit(){
        	var passwordTest=/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\p{P}\p{S}])(?!.*\s).{12,}$/;
        	var emailTest = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        	if(emailTest.test($("#CPemail").val())){
        		if(passwordTest.test($("#CPpassword").val())){
            		if($("#CPpasswordNew1").val()==$("#CPpasswordNew2").val()){
            			if(passwordTest.test($("#CPpasswordNew1").val())){
            				ajaxChangePassword();
            			}
            		}
            		setTimeout(function (){window.location.reload();}, 5000);
            		alert("Se i dati inseriti corrispondono, riceverai un'email di conferma!");
            		
            	}
        	}
        	
        	else{
        		alert("Controlla bene i dati inseriti!");
        	}
        }
        
        function ajaxChangePassword(){
        	var oldPassword=$("#CPpassword").val();
        	var newPassword=$("#CPpasswordNew1").val();
        	var email=$("#CPemail").val();
        		
        	$.post('/NibInk/changePassword', {"email": email, "oldPassword": oldPassword, "newPassword": newPassword});
        }
</script>  
<script type="text/javascript">
function Ordini()
{
	window.location.href = "/NibInk/JSP/myOrders.jsp";	
}


function Indirizzi()
{
	window.location.href = "/NibInk/JSP/addresses.jsp?fromUser=1";	
}

function comunicazioni()
{
	window.location.href = "/NibInk/JSP/messageCenter.jsp";	
}

function contattaci()
{
	window.location.href = "/NibInk/JSP/contactUs.jsp";	
}
</script>
</body>
</html>