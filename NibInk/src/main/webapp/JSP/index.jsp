<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>NibInk - Home</title>
<link rel="stylesheet" href="index.css">
</head>

<body>
<header>
    <div>
    <h1 id="titolo">NibInk</h1>
    <nav class="menu">
      <ul>
        <li><a class="menu-link" href="/NibInk/JSP/homepage.jsp">Home</a></li>
        <li><a class="menu-link" href="/NibInk/JSP/products.jsp">Prodotti</a></li>
        <li><img id="logo" src="logo.jpg" title="logo" alt="logo"></li> <!-- Non so se le misure sono corrette e quale sia la directory del logo sia quella-->
        <li><a class="menu-link" href="/NibInk/JSP/cart.jsp">Carrello</a></li>
        <li><a class="menu-link" href="/NibInk/JSP/login.jsp">Login</a></li>
      </ul>
    </nav>
    </div>
</header>

  <main>
    <section>
      <h2>Articoli più venduti</h2>

      <div class="product-list">
      <a href="product.jsp">
        <div class="product" onclick="redirectToProductPage('PennaX')">
          <img src="PennaX.jpg" alt="Penna X">
          <p> Penna X</p>
        </div>
      </a>

      <a href="product.jsp">
        <div class="product" onclick="redirectToProductPage('PennaY')">
            <img src="PennaY.jpg" alt="Penna Y">
            <p> Penna Y</p>
        </div>
      </a>

      <a href="product.jsp">
        <div class="product" onclick="redirectToProductPage('PennaSigma')">
            <img src="PennaSigma.jpg" alt="Penna Sigma">
            <p> Penna Sigma</p>
        </div>
      </a>

      <a href="product.jsp">
        <div class="product" onclick="redirectToProductPage('PennaOmega')">
            <img src="PennaOmega.jpg" alt="Penna Omega">
            <p> Penna Omega</p>
        </div>
      </a>

      <a class="catalogo" href="catalog.jsp"> Scopri di più...</a>

    </div>
    </section>

    <section>
    
    <h2>Nuovi arrivi</h2>
    <div class="product-list">

      <a href="product.jsp">
        <div class="product" onclick="redirectToProductPage('PennaX')">
          <img src="PennaX.jpg" alt="Penna X">
          <p> Penna X</p>
        </div>
      </a>

      <a href="product.jsp">
        <div class="product" onclick="redirectToProductPage('PennaAlfa')">
            <img src="PennaAlfa.jpg" alt="Penna Alfa">
            <p> Penna Alfa</p>
        </div>
      </a>

      <a href="product.jsp">
        <div class="product" onclick="redirectToProductPage('PennaBeta')">
            <img src="PennaBeta.jpg" alt="Penna Beta">
            <p> Penna Beta</p>
        </div>
      </a>

      <a href="product.jsp">
        <div class="product" onclick="redirectToProductPage('PennaGamma')">
            <img src="PennaGamma.jpg" alt="Penna Gamma">
            <p> Penna Gamma</p>
        </div>
      </a>

      <a class="catalogo" href="catalog.jsp"> Scopri di più...</a>

    </div>
    </section>

    <section>
    <h2>Trend del momento</h2>

      <div class="product-list">

        
        <a href="product.jsp">
        <div class="product" onclick="redirectToProductPage('PennaX')">
          <img src="PennaX.jpg" alt="Penna X">
          <p> Penna X</p>
        </div>
        </a>

        <a href="product.jsp">
        <div class="product" onclick="redirectToProductPage('PennaAlfa')">
            <img src="PennaAlfa.jpg" alt="Penna Alfa">
            <p> Penna Alfa</p>
        </div>
        </a>

        <a href="product.jsp">
        <div class="product" onclick="redirectToProductPage('PennaSigma')">
            <img src="PennaSigma.jpg" alt="Penna Sigma">
            <p> Penna Sigma</p>
        </div>
        </a>


        <a href="product.jsp">
        <div class="product" onclick="redirectToProductPage('PennaGamma')">
            <img src="PennaGamma.jpg" alt="Penna Gamma">
            <p> Penna Gamma</p>
        </div>
        </a>


        <a class="catalogo" href="catalog.jsp" > Scopri di più...</a>

    </div>
    </section>
  </main>

  <footer>
    <p>Serve aiuto? Contattaci: </p> <!-- Non ho trovato i link ai contatti/eventuali numeri -->
    <p>© 2023 NibInk. All rights reserved </p>
  </footer>


</body>
</html>
