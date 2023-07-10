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
	<link rel="stylesheet" href="/NibInk/CSS/stelle.css">
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

  // Update the CSS width property of the bars TEST PUSH
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
<% if (session.getAttribute("userType").equals("unregistered")) { System.out.println("recensione: utente non registrato");%>
	<div class="userReviewLimit">
    <h2>Effettua il login per recensire questo articolo</h2>
</div>
	<% } else { System.out.println("recensione: utente  registrato");
      if (rm.canThisUserReviewIt((int) session.getAttribute("id"), Integer.parseInt(request.getParameter("id")))) { System.out.println("recensione: utente  ha acquistato l'articolo");
          if (rm.hasThisUserReviewedIt((int) session.getAttribute("id"), Integer.parseInt(request.getParameter("id")))) { System.out.println("recensione: utente  ha già recensito l'articolo");%>
              <% Review review = rm.loadReviewOf((int) session.getAttribute("id"), Integer.parseInt(request.getParameter("id")));
                 if (review != null) { %>
                  <div class="userReviewModify">
                      <h2>Modifica la recensione</h2>
                      <form action="/NibInk/AddReviewServlet" method="post">
                          <div class="rating">
						  <input value="5" name="rating" id="star-1" type="radio">
						  <label for="star-1">
						    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12,17.27L18.18,21L16.54,13.97L22,9.24L14.81,8.62L12,2L9.19,8.62L2,9.24L7.45,13.97L5.82,21L12,17.27Z" pathLength="360"></path></svg>
						  </label>
						  <input value="4" name="rating" id="star-2" type="radio">
						  <label for="star-2">
						    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12,17.27L18.18,21L16.54,13.97L22,9.24L14.81,8.62L12,2L9.19,8.62L2,9.24L7.45,13.97L5.82,21L12,17.27Z" pathLength="360"></path></svg>
						  </label>
						  <input value="3" name="rating" id="star-3" type="radio">
						  <label for="star-3">
						    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12,17.27L18.18,21L16.54,13.97L22,9.24L14.81,8.62L12,2L9.19,8.62L2,9.24L7.45,13.97L5.82,21L12,17.27Z" pathLength="360"></path></svg>
						  </label>
						  <input value="2" name="rating" id="star-4" type="radio">
						  <label for="star-4">
						    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12,17.27L18.18,21L16.54,13.97L22,9.24L14.81,8.62L12,2L9.19,8.62L2,9.24L7.45,13.97L5.82,21L12,17.27Z" pathLength="360"></path></svg>
						  </label>
						  <input value="1" name="rating" id="star-5" type="radio">
						  <label for="star-5">
						    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12,17.27L18.18,21L16.54,13.97L22,9.24L14.81,8.62L12,2L9.19,8.62L2,9.24L7.45,13.97L5.82,21L12,17.27Z" pathLength="360"></path></svg>
						  </label>
					</div>
					<div class="reviewdiv">
					<textarea id="review" name="review" rows="4" placeholder="<%= review.getText() %>"></textarea><br>
					</div>
			        <input type="hidden" name="modify" value="yes">
			        <input type="hidden" name="itemId" value="<%= request.getParameter("id") %>">
			        <input type="submit" value="Modifica" class="button">
                      </form>
                  </div>
              <% } %>
          <% } else { System.out.println("recensione: utente deve recensire");%>%>
              <div class="userReviewWrite">
                  <h2>Scrivi una recensione</h2>
                  <form action="/NibInk/AddReviewServlet" method="post">
                      <div class="rating">
					  <input value="5" name="rating" id="star-1" type="radio">
					  <label for="star-1">
					    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12,17.27L18.18,21L16.54,13.97L22,9.24L14.81,8.62L12,2L9.19,8.62L2,9.24L7.45,13.97L5.82,21L12,17.27Z" pathLength="360"></path></svg>
					  </label>
					  <input value="4" name="rating" id="star-2" type="radio">
					  <label for="star-2">
					    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12,17.27L18.18,21L16.54,13.97L22,9.24L14.81,8.62L12,2L9.19,8.62L2,9.24L7.45,13.97L5.82,21L12,17.27Z" pathLength="360"></path></svg>
					  </label>
					  <input value="3" name="rating" id="star-3" type="radio">
					  <label for="star-3">
					    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12,17.27L18.18,21L16.54,13.97L22,9.24L14.81,8.62L12,2L9.19,8.62L2,9.24L7.45,13.97L5.82,21L12,17.27Z" pathLength="360"></path></svg>
					  </label>
					  <input value="2" name="rating" id="star-4" type="radio">
					  <label for="star-4">
					    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12,17.27L18.18,21L16.54,13.97L22,9.24L14.81,8.62L12,2L9.19,8.62L2,9.24L7.45,13.97L5.82,21L12,17.27Z" pathLength="360"></path></svg>
					  </label>
					  <input value="1" name="rating" id="star-5" type="radio">
					  <label for="star-5">
					    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12,17.27L18.18,21L16.54,13.97L22,9.24L14.81,8.62L12,2L9.19,8.62L2,9.24L7.45,13.97L5.82,21L12,17.27Z" pathLength="360"></path></svg>
					  </label>
					</div>
					<textarea id="review" name="review" rows="4" placeholder="Scrivi una recensione..."></textarea><br>
			        <input type="hidden" name="itemId" value="<%= request.getParameter("id") %>">
			        <input type="submit" value="Invia" class="button">
                  </form>
              </div>
          <% } } }%>
</body>
</html>