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
    <div class="userNavbar">
        <jsp:include page="userNavbar.jsp"/>
    </div>
    <div>
        <jsp:include page="navbar.jsp"/>
    </div>

    <main>
        <section>
          <h2>Articoli più venduti</h2>
    
          <div class="product-list">


          <a href="product.jsp?id=4">
            <div class="product" onclick="redirectToProductPage('Fontain Pen C')">
              <img src="Product_4.jpg" alt="Fountain Pen C">
              <p>Fountain Pen C</p>
            </div>
          </a>
    
          <a href="product.jsp?id=22?">
            <div class="product" onclick="redirectToProductPage('Fountain Pen B')">
                <img src="Product_22.jpg" alt="Fountain Pen B">
                <p>Fountain Pen C</p>
            </div>
          </a>
    
          <a href="product.jsp?id=15?">
            <div class="product" onclick="redirectToProductPage('Ink Bottle G')">
                <img src="Product_15.jpg" alt="Ink Bottle G">
                <p>Ink Bottle G</p>
            </div>
          </a>
    
          <a href="product.jsp?id=20">
            <div class="product" onclick="redirectToProductPage('Fountain Pen K')">
                <img src="Product_20.jpg" alt="Fountain Pen K">
                <p>Fountain Pen K </p>
            </div>
          </a>
    
          <a class="catalogo" href="catalog.jsp"> Scopri di più...</a>
    
        </div>
        </section>
    
        <section>
        
        <h2>Nuovi arrivi</h2>
        <div class="product-list">
    
          <a href="product.jsp?id=19">
            <div class="product" onclick="redirectToProductPage('Ink Bottle I')">
              <img src="Product_4.jpg" alt="Ink Bottle I">
              <p>Ink Bottle I</p>
            </div>
          </a>
    
          <a href="product.jsp?id=2">
            <div class="product" onclick="redirectToProductPage('Product B')">
                <img src="Product B.jpg" alt="Product B">
                <p> Product B</p>
            </div>
          </a>
    
          <a href="product.jsp?id=3">
            <div class="product" onclick="redirectToProductPage('Product C')">
                <img src="Product C.jpg" alt="Product C">
                <p> Product C</p>
            </div>
          </a>
    
          <a href="product.jsp?id=7">
            <div class="product" onclick="redirectToProductPage('Ink Bottle C')">
                <img src="Product_7.jpg" alt="Ink Bottle C">
                <p>Ink Bottle C</p>
            </div>
          </a>
    
          <a class="catalogo" href="catalog.jsp"> Scopri di più...</a>
    
        </div>
        </section>
    
        <section>
        <h2>Trend del momento</h2>
    
          <div class="product-list">
    
            
            <a href="product.jsp?id=20">
            <div class="product" onclick="redirectToProductPage('Fountain Pen K')">
              <img src="Product_20.jpg" alt="Fountain Pen K">
              <p>Fountain Pen K</p>
            </div>
            </a>
    
            <a href="product.jsp?id=4">
            <div class="product" onclick="redirectToProductPage('Fountain Pen C')">
                <img src="Product_4.jpg" alt="Fountain Pen C">
                <p>Fountain Pen C</p>
            </div>
            </a>
    
            <a href="product.jsp?id=7">
            <div class="product" onclick="redirectToProductPage('Ink Bottle C')">
                <img src="Product_7.jpg" alt="Ink Bottle C">
                <p>Ink Bottle C</p>
            </div>
            </a>
    
    
            <a href="product.jsp?id=19">
                <div class="product" onclick="redirectToProductPage('Ink Bottle I')">
                  <img src="Product_4.jpg" alt="Ink Bottle I">
                  <p>Ink Bottle I</p>
                </div>
            </a>
    
    
            <a class="catalogo" href="catalog.jsp" > Scopri di più...</a>
    
        </div>
        </section>
      </main>

    <div>
        <jsp:include page="footer.jsp"/>
    </div>

    <script>

    </script>
    
</body>
</html>
