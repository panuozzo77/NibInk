<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.model.DAOCustomer" %>
<%@page import="com.model.Customer" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Invia un Messaggio</title>
  <link rel="stylesheet" href="https://www.coralliegioie.com/themes/classic/assets/css/theme.css" type="text/css" media="all">
  <script>
  document.addEventListener("DOMContentLoaded", function () {
	const urlParams = new URLSearchParams(window.location.search);
	if (urlParams.has('result')) {
	  var popup = document.createElement("div");
	  popup.innerHTML = "Messaggio inviato con successo! Puoi controllare gli aggiornamenti nel centro messaggi.";
	  popup.style.position = "fixed";
	  popup.style.top = "50%";
	  popup.style.left = "50%";
	  popup.style.transform = "translate(-50%, -50%)";
	  popup.style.padding = "10px";
	  popup.style.background = "#ffffff";
	  popup.style.border = "1px solid #000000";
	  popup.style.borderRadius = "4px";
	  popup.style.boxShadow = "0 2px 5px rgba(0, 0, 0, 0.3)";
	  document.body.appendChild(popup);
	
	  // Nascondi il popup dopo 3 secondi
	  setTimeout(function() {
	    popup.style.display = "none";
	  }, 3000);
	}
  });
   </script>
</head>
<body>
<div class="userNavbar">
	<jsp:include page="userNavbar.jsp"/>
</div>
<div class="navbar">
	<jsp:include page="navbar.jsp"/>
</div>
  <section id="wrapper">
    <aside id="notifications">
      <div class="notifications-container container">
      </div>
    </aside>
    <div class="container">
      <div class="row">
        <div id="left-column" class="col-xs-12 col-sm-4 col-md-3">
          <div class="contact-rich">
            <h4>Informazioni negozio</h4>
            <div class="block">
              <div class="icon"><i class="material-icons">&#xE55F;</i></div>
              <div class="data">NibInk<br />Via inesistente, 123<br />84121 Salerno<br />Italia</div>
            </div>
            <hr/>
            <div class="block">
              <div class="icon"><i class="material-icons">&#xE158;</i></div>
              <div class="data email">
                Inviaci un'e-mail:<br/>
              </div>
              <a href="mailto:info@nibink.com">info@nibink.com</a>
            </div>
          </div>
        </div>
        <div id="content-wrapper" class="js-content-wrapper left-column col-xs-12 col-sm-8 col-md-9">
          <section id="main">
            <div id="content" class="page-content card card-block">
              <section class="contact-form">
                <form action="/NibInk/sendMessage" method="post">
                  <section class="form-fields">
                    <div class="form-group row">
                      <div class="col-md-9 col-md-offset-3">
                        <h3>Contattaci</h3>
                      </div>
                    </div>
                    <div class="form-group row">
                      <label class="col-md-3 form-control-label" for="id_contact">Argomento</label>
                      <div class="col-md-6">
                        <select name="subject" id="id_contact" class="form-control form-control-select">
                          <option value="Assistenza Clienti">Assistenza clienti</option>
                          <option value="Metodo di spedizione">Metodo di spedizione</option>
                          <option value="Metodo di pagamento">Metodo di pagamento</option>
                          <option value="Dov'è il mio ordine?">Dov'è il mio ordine?</option>
                          <option value="Richiesta di Reso">Richiesta di Reso</option>
                          <option value="Altro">Altro</option>
                        </select>
                      </div>
                    </div>
                    <div class="form-group row">
                      <label class="col-md-3 form-control-label" for="email">Indirizzo email</label>
                      <div class="col-md-6">
                        <% String user = (String) request.getSession().getAttribute("userType");
                    		String value = "";	  
                    		if(user.equals("registered")) {
                    			DAOCustomer db = new DAOCustomer(); 
                    			value = db.getCustomerById((int) request.getSession().getAttribute("id")).getEmail();
                    		}
                   		%>
                        <input
                          id="email"
                          class="form-control"
                          name="email"
                          type="email"
                          value="<%= value %>"
                          placeholder="latu@email.qui"
                        >
                      </div>
                    </div>
                    <div class="form-group row">
                      <label class="col-md-3 form-control-label" for="contactform-message">Messaggio</label>
                      <div class="col-md-9">
                        <textarea
                          id="contactform-message"
                          class="form-control"
                          name="text"
                          placeholder="Come possiamo aiutarti?"
                          rows="3"
                        ></textarea>
                      </div>
                    </div>
                    <div class="form-group row">
                      <div class="offset-md-3">
                        <div class="col-md-3 form-control-comment"></div>
                      </div>
                    </div>
                    <footer class="form-footer text-sm-right">
                      <input class="btn btn-primary" type="submit" name="submitMessage" value="Invia">
                    </footer>
                  </section>
                </form>
	          </section>
			</div>
            </section>
          </div>
        </div>
      </div>
  </section>
  
  <div class="footer">
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>
