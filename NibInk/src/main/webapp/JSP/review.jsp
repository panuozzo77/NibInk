<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.model.DAOCustomer" %>
<%@page import="com.model.Customer" %>
<%@page import="com.model.Review" %>
<%@page import="com.model.ReviewManager" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/NibInk/CSS/review.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<%
ReviewManager rm = new ReviewManager();
rm.loadAllReviewsOf(Integer.parseInt(request.getParameter("id")), "items");
int[] statistics = rm.calculateReviewStatistics(rm.list);
%>

<div id="stelle" class="row">
<span class="heading">Dicono di questo articolo</span>
<% for (int i = 0; i < statistics[0]; i++) { %>
<span class="fa fa-star checked"></span>
<% } %>
<span id="nota">"<%= statistics[0] %> stelle": media basata su <%= statistics[6] %> recensioni.</span>
<hr style="border:3px solid #f1f1f1">
</div>
<div class="row">
  <div class="side">
    <div>5 stelle</div>
  </div>
  <div class="middle">
    <div class="bar-container">
      <div class="bar-5"></div>
    </div>
  </div>
  <div class="side right">
    <div><%= statistics[5] %></div>
  </div>
  <div class="side">
    <div>4 stelle</div>
  </div>
  <div class="middle">
    <div class="bar-container">
      <div class="bar-4"></div>
    </div>
  </div>
  <div class="side right">
    <div><%= statistics[4] %></div>
  </div>
  <div class="side">
    <div>3 stelle</div>
  </div>
  <div class="middle">
    <div class="bar-container">
      <div class="bar-3"></div>
    </div>
  </div>
  <div class="side right">
    <div><%= statistics[3] %></div>
  </div>
  <div class="side">
    <div>2 stelle</div>
  </div>
  <div class="middle">
    <div class="bar-container">
      <div class="bar-2"></div>
    </div>
  </div>
  <div class="side right">
    <div><%= statistics[2] %></div>
  </div>
  <div class="side">
    <div>1 stella</div>
  </div>
  <div class="middle">
    <div class="bar-container">
      <div class="bar-1"></div>
    </div>
  </div>
  <div class="side right">
    <div><%= statistics[1] %></div>
  </div>
</div>
<script> 
window.addEventListener('DOMContentLoaded', (event) => {
  // Retrieve the review statistics from the server
  var statistics = [<%= statistics[0] %>, <%= statistics[1] %>, <%= statistics[2] %>, <%= statistics[3] %>, <%= statistics[4] %>, <%= statistics[5] %>, <%= statistics[6] %>];

  // Calculate the width percentages for each bar
  var totalReviews = statistics[6];
  var bar1Width = (statistics[1] / totalReviews) * 100;
  var bar2Width = (statistics[2] / totalReviews) * 100;
  var bar3Width = (statistics[3] / totalReviews) * 100;
  var bar4Width = (statistics[4] / totalReviews) * 100;
  var bar5Width = (statistics[5] / totalReviews) * 100;

  // Update the CSS width property of the bars
  var bar1Element = document.querySelector('.bar-1');
  var bar2Element = document.querySelector('.bar-2');
  var bar3Element = document.querySelector('.bar-3');
  var bar4Element = document.querySelector('.bar-4');
  var bar5Element = document.querySelector('.bar-5');

  bar1Element.style.width = bar1Width + "%";
  bar2Element.style.width = bar2Width + "%";
  bar3Element.style.width = bar3Width + "%";
  bar4Element.style.width = bar4Width + "%";
  bar5Element.style.width = bar5Width + "%";
});
</script>
<div class="reviewList">
<% DAOCustomer db = new DAOCustomer(); 
for (Review rev : rm.list) { %>
	<div>
	<span><%= db.getCustomerById(rev.getUserId()).getName() %></span>
	<% for (int i = 0; i < rev.getStarRating(); i++) { %>
	<div class="fa fa-star checked"></div>
	<% } %>
	<div><%= rev.getDate() %></div>
	<div><%= rev.getText() %></div>
	<% } %>
	</div>
</div>
<%
if (!session.getAttribute("userType").equals("unregistered")) {
	if (rm.canThisUserReviewIt((int) session.getAttribute("id"), Integer.parseInt(request.getParameter("id")))) {
		if (rm.hasThisUserReviewedIt((int) session.getAttribute("id"), Integer.parseInt(request.getParameter("id")))) { %>
			<% Review review = rm.loadReviewOf((int) session.getAttribute("id"), Integer.parseInt(request.getParameter("id"))); %>
			<!--  PER QUALCHE RAGIONE QUESTO PEZZO DI CODICE NON VIENE PROPRIO UTILIZZATO. MODIFICARE DIRETTAMENTE LO STESSO CODICE PIÙ IN BASSO NEL SECONDO ELSE -->
			<div class="userReviewModify">
				<h2>Modifica la recensione</h2>
			    <form action="/NibInk/AddReviewServlet" method="post">
			        <textarea id="review" name="review" rows="4" placeholder="<%= review.getText() %>"></textarea><br>
			        <label for="stars">Rating:</label><br>
			        <div class="stars">
			            <input type="radio" id="star5" name="rating" value="5" />
			            <label for="star5">&#9733;</label>
			            <input type="radio" id="star4" name="rating" value="4" />
			            <label for="star4">&#9733;</label>
			            <input type="radio" id="star3" name="rating" value="3" />
			            <label for="star3">&#9733;</label>
			            <input type="radio" id="star2" name="rating" value="2" />
			            <label for="star2">&#9733;</label>
			            <input type="radio" id="star1" name="rating" value="1" />
			            <label for="star1">&#9733;</label>
			        </div><br>
			        <input type="hidden" name="modify" value="yes">
			        <input type="hidden" name="itemId" value="<%= request.getParameter("id") %>">
			        <input type="submit" value="Modifica">
			    </form>
			</div>
		<% } else { %>
			<div class="userReviewWrite">
				<h2>Scrivi una recensione</h2>
			    <form action="/NibInk/AddReviewServlet" method="post">
			        <textarea id="review" name="review" rows="4" placeholder="Scrivi una recensione..."></textarea><br>
			        <label for="stars">Rating:</label><br>
			        <div class="stars">
			            <input type="radio" id="star5" name="rating" value="5" />
			            <label for="star5">&#9733;</label>
			            <input type="radio" id="star4" name="rating" value="4" />
			            <label for="star4">&#9733;</label>
			            <input type="radio" id="star3" name="rating" value="3" />
			            <label for="star3">&#9733;</label>
			            <input type="radio" id="star2" name="rating" value="2" />
			            <label for="star2">&#9733;</label>
			            <input type="radio" id="star1" name="rating" value="1" />
			            <label for="star1">&#9733;</label>
			        </div><br>
			        <input type="hidden" name="itemId" value="<%= request.getParameter("id") %>">
			        <input type="submit" value="Invia">
			    </form>
			</div>
		<% } %>
	<% } else { %>
		<div class="userReviewLimit">
			<h2>Hai già recensito questo articolo</h2>
			<% Review review = rm.loadReviewOf((int) session.getAttribute("id"), Integer.parseInt(request.getParameter("id"))); %>
			<div class="userReviewModify">
				<h2>Modifica la recensione</h2>
			    <form action="/NibInk/AddReviewServlet" method="post">
			        <textarea id="review" name="review" rows="4" placeholder="<%= review.getText() %>"></textarea><br>
			        <label for="stars">Rating:</label><br>
			        <div class="stars">
			            <input type="radio" id="star5" name="rating" value="5" />
			            <label for="star5">&#9733;</label>
			            <input type="radio" id="star4" name="rating" value="4" />
			            <label for="star4">&#9733;</label>
			            <input type="radio" id="star3" name="rating" value="3" />
			            <label for="star3">&#9733;</label>
			            <input type="radio" id="star2" name="rating" value="2" />
			            <label for="star2">&#9733;</label>
			            <input type="radio" id="star1" name="rating" value="1" />
			            <label for="star1">&#9733;</label>
			        </div><br>
			        <input type="hidden" name="modify" value="yes">
			        <input type="hidden" name="itemId" value="<%= request.getParameter("id") %>">
			        <input type="submit" value="Modifica">
			    </form>
			</div>
		</div>
	<% } %>
<% } else { %>
	<div class="userReviewLimit">
		<h2>Effettua il login per recensire questo articolo</h2>
	</div>
<% } %>
</body>
</html>
