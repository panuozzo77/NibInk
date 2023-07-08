<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/NibInk/CSS/MioAccount.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="pagina">
	<h1>Mio Account</h1>
	
		<div class="riga">
				<div class="opzione" onClick="Ordini()">
					<img alt="" src="/NibInk/images/ordini.png">
					<div class="spiegazione">
						<h2 class="title"> Ordini</h2>
						<p>Ordini effettuati.</p>
					</div>
				</div>
				
				<div class="opzione" onClick="AccessoeSicurezza()">
					<img alt="" src="/NibInk/images/accesso.png">
					<div class="spiegazione">
						<h2 class="title">Accesso e Sicurezza</h2>
						<p>Metodi di accesso.</p>
					</div>
				</div>
				
				<div class="opzione">
					<img alt="" src="/NibInk/images/comunicazioni.png">
					<div class="spiegazione">
						<h2 class="title">Comunicazioni</h2>
						<p>Avvisi e comunicazioni.</p>
					</div>
				</div>
		</div>
		
		<div class="riga">
				<div class="opzione">
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
<script type="text/javascript">
function Ordini()
{
	window.location.href = "/NibInk/JSP/ordini.jsp";	
}

function AccessoeSicurezza()
{
	window.location.href = "/NibInk/JSP/Accesso.jsp";	
}

function Indirizzi()
{
	window.location.href = "/NibInk/JSP/indirizzi.jsp";	
}
</script>
</body>
</html>