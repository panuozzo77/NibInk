<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.model.SavedCardManager" %>
<%@page import="com.model.SavedCard" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="it">
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>NibInk - Metodi di pagamento</title>
<link rel="icon" type="image/x-icon" href="/NibInk/images/favicon.ico">
<% 
        String str = (String) request.getSession().getAttribute("userType");
        List<SavedCard> list = new ArrayList<>(); // Initialize the list with an empty list
        if (str != null && str.equals("unregistered")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            int userId = (int) request.getSession().getAttribute("id");
            SavedCardManager scm = new SavedCardManager();
            list = scm.getCensoredSavedCardsByUserId(userId);
        } 
    %>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div class=navbar>
<jsp:include page="navbar.jsp"/>
</div>

<section class="main-content">
  <div class="container">
    <h1 class="text-uppercase text-center">Opzioni di pagamento</h1>
    <br>
    <br>
    <div class="row">
    <c:forEach items="<%= list %>" var="card" >
      <div class="col-md-3 col-sm-6 mb-4">
        <div class="payment-card rounded-lg shadow bg-white text-center h-100">
          <div class="payment-card__type px-4 py-3 d-flex justify-content-center align-items-center"> <img src="/NibInk/images/cardIcon2.png" alt="Card"> </div>
          <div class="payment-card__info p-4">
            <h4>${card.getNameOnCard()}</h4>	<!-- nome e cognome -->
            <p class="text-muted">Carta</p>	<!-- lascia fisso a carta -->
            <p>${card.getCensoredCardNumber()}</p> <!-- codice della carta oscurato -->
            <hr>
            <c:choose>
            <c:when test = "${card.getBoolean()}">
            <div class="d-flex justify-content-between">
              <div><span class="badge badge-pill bg-success py-1 px-2">Principale</span></div>	<!-- tag principale se è impostata a principale-->
              <div> <a href="#!" class="text-danger" data-toggle="tooltip" data-placement="top" title="Elimina"><i class="fa fa-trash"></i></a> </div>
            </div> 
             </c:when>
             <c:otherwise>
               <div class="d-flex justify-content-between"> <a href="/NibInk/cardManager?action=default&cardId=${card.getId()}" class="text-link">Imposta come Principale</a> <!-- tasto per impostare a principale se non lo è-->
               <div> <a href="/NibInk/cardManager?action=remove&cardId=${card.getId()}" class="text-danger" data-toggle="tooltip" data-placement="top" title="Elimina"><i class="fa fa-trash"></i></a> </div>
            </div> 
             </c:otherwise>
            </c:choose>
          </div>
        </div>
       </div>
      </c:forEach>
      <div class="col-md-3 col-sm-6 mb-4">
        <div class="add-payment rounded-lg shadow d-flex justify-content-center align-items-center h-100 border">
          <button type="button" class="border-0" data-bs-toggle="modal" data-bs-target="#staticBackdrop"> <i class="fa fa-plus fa-3x text-muted"></i> </button>
        </div>
      </div>
    </div>
  </div>
</section>
<script>
  $(document).ready(function() {
    const cardNumberRegex = /^\d{16}$/;
    const nameRegex = /^[A-Z][a-zA-Z]+\s[A-Z][a-zA-Z]+$/;
  
    const cardNumberInput = $('.card-number');
    const nameInput = $('.card-name');
    const submitButton = $('.btn');
  
    cardNumberInput.on('input', validateForm);
    nameInput.on('input', validateForm);
  
    function validateForm() {
      const cardNumber = cardNumberInput.val();
      const name = nameInput.val();
  
      const isValidCardNumber = cardNumberRegex.test(cardNumber);
      const isValidName = nameRegex.test(name);
  
      if (isValidCardNumber && isValidName) {
        enableSubmitButton();
      } else {
        disableSubmitButton();
      }
    }
  
    function enableSubmitButton() {
      submitButton.prop('disabled', false);
    }
  
    function disableSubmitButton() {
      submitButton.prop('disabled', true);
    }
  });
</script>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title text-center" id="staticBackdropLabel">
		  <img src="/NibInk/images/py-logo.jpg" alt="payment-card" style="width: 75%;">
		  </h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       <form class="credit-card mb-5" method="post" action="/NibInk/cardManager">
      <div class="form-header">
        <h4 class="title">Informazioni carta di debito o credito</h4>
      </div>
	  <div class="form-body">
        <input type="text" class="card-number" placeholder="Numero Carta" name="number" maxlength="16">
		<input type="text" class="card-name" placeholder="Nome e Cognome" name="user">
        <button type="submit" class="btn btn-success mt-1 mb-3" disabled>Aggiungi Carta</button>
      </div>
    </form>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script> 
<script>
		$(function () {
			$('[data-toggle="tooltip"]').tooltip()
		})
	</script>
</body>
</html>