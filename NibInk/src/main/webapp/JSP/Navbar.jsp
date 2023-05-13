<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/NibInk/CSS/Navbar.css">

</head>
<body>
<div class="page">
	<div class="menu">
		<a>Home</a>
		<div class="prodotti">
  			<a href="#">Prodotti</a>
 			<div class="submenu">
    			<a href="#">Inchiostri</a>
    			<a href="#">Penne</a>
    			<a href="#">Quaderni</a>
  			</div>
		</div>
		<img alt="" src="/NibInk/images/logo.png">
		<a>Brand</a>
		<a>Cerca</a>
	</div>
	
	<div class="menu2">
	<img alt="" src="/NibInk/images/logo.png" id="logo">
	<div class="link">
		<a>Home</a>
  		<div class="prodotti">
  			<a>Prodotti</a>
 			<div class="submenu">
    			<a>Inchiostri</a>
    			<a>Penne</a>
    			<a>Quaderni</a>
  			</div>
		</div>
  		<a>Brand</a>
		<a>Cerca</a>
	</div>
		<img alt="" src="/NibInk/images/menu.png" class="scorrimento">
	</div>
</div>
</body>
<script>
	const menu=document.querySelector(".scorrimento");
	const links=document.querySelector(".link");
	
	menu.addEventListener('click',()=>
	{
		links.classList.toggle('scorrimento');
	})
</script>
</html>