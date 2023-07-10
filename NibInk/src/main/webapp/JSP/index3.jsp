<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Item" %>
<%@ page import="com.model.DAOItem" %>

<!DOCTYPE html>
<head>
    <meta charset="ISO-8859-1">
    <title>NibInk - Home</title>
    <link rel="stylesheet" href="/NibInk/CSS/styles.css">
    <script type="text/javascript" src="NibInk/JavaScript.js"></script>
</head>


<body>

  <% DAOItem daoItem = new DAOItem(); %>

    <div class="userNavbar">
        
    </div>
    <div>
        <jsp:include page="navbar.jsp"/>
    </div>

    <main>
        <section>
          <h2>Articoli pi&#249 venduti</h2>
    
          <div class="product-list">


          <a href="product.jsp?id=4">
            <div class="product" onclick="redirectToProductPage('Fontain Pen C')">
              <img src="../images/Product_4.jpg" alt="Fountain Pen C">
              <p>Fountain Pen C</p> <br>
              <p class="price"> <%= daoItem.getItemFromDB("4").getPrice() %> </p>
            </div>
          </a>
    
          <a href="product.jsp?id=22?">
            <div class="product" onclick="redirectToProductPage('Fountain Pen B')">
                <img src="../images/Product_22.jpg" alt="Fountain Pen B">
                <p>Fountain Pen C</p> <br>
                <p class="price"> <%= daoItem.getItemFromDB("22").getPrice() %> </p>
            </div>
          </a>
    
          <a href="product.jsp?id=15?">
            <div class="product" onclick="redirectToProductPage('Ink Bottle G')">
                <img src="../images/Product_15.jpg" alt="Ink Bottle G">
                <p>Ink Bottle G</p> <br>
                <p class="price"> <%= daoItem.getItemFromDB("15").getPrice() %> </p>
            </div>
          </a>
    
          <a href="product.jsp?id=20">
            <div class="product" onclick="redirectToProductPage('Fountain Pen K')">
                <img src="../images/Product_20.jpg" alt="Fountain Pen K">
                <p>Fountain Pen K </p> <br>
                <p class="price"> <%= daoItem.getItemFromDB("20").getPrice() %> </p>
            </div>
          </a>
    
          <a class="catalogo" href="catalog.jsp"> Scopri di pi&#249...</a>
    
        </div>
        </section>
    
        <section>
        
        <h2>Nuovi arrivi</h2>
        <div class="product-list">
    
          <a href="product.jsp?id=19">
            <div class="product" onclick="redirectToProductPage('Ink Bottle I')">
              <img src="../images/Product_4.jpg" alt="Ink Bottle I">
              <p>Ink Bottle I</p> <br>
              <p class="price"> <%= daoItem.getItemFromDB("4").getPrice() %> </p>
            </div>
          </a>
    
          <a href="product.jsp?id=2">
            <div class="product" onclick="redirectToProductPage('Product B')">
                <img src="../images/Product B.jpg" alt="Product B">
                <p> Product B</p> <br>
                <p class="price"> <%= daoItem.getItemFromDB("2").getPrice() %> </p>
            </div>
          </a>
    
          <a href="product.jsp?id=3">
            <div class="product" onclick="redirectToProductPage('Product C')">
                <img src="../images/Product C.jpg" alt="Product C">
                <p> Product C</p> <br>
                <p class="price"> <%= daoItem.getItemFromDB("3").getPrice() %> </p>
            </div>
          </a>
    
          <a href="product.jsp?id=7">
            <div class="product" onclick="redirectToProductPage('Ink Bottle C')">
                <img src="../images/Product_7.jpg" alt="Ink Bottle C">
                <p>Ink Bottle C</p> <br>
                <p class="price"> <%= daoItem.getItemFromDB("7").getPrice() %> </p>
            </div>
          </a>
    
          <a class="catalogo" href="catalog.jsp"> Scopri di pi&#249...</a>
    
        </div>
        </section>
    
        <section>
        <h2>Trend del momento</h2>
    
          <div class="product-list">
    
            
            <a href="product.jsp?id=20">
            <div class="product" onclick="redirectToProductPage('Fountain Pen K')">
              <img src="../images/Product_20.jpg" alt="Fountain Pen K">
              <p>Fountain Pen K</p> <br>
              <p class="price"> <%= daoItem.getItemFromDB("20").getPrice() %> </p>
            </div>
            </a>
    
            <a href="product.jsp?id=4">
            <div class="product" onclick="redirectToProductPage('Fountain Pen C')">
                <img src="../images/Product_4.jpg" alt="Fountain Pen C">
                <p>Fountain Pen C</p> <br>
                <p class="price"> <%= daoItem.getItemFromDB("4").getPrice() %> </p>
            </div>
            </a>
    
            <a href="product.jsp?id=7">
            <div class="product" onclick="redirectToProductPage('Ink Bottle C')">
                <img src="../images/Product_7.jpg" alt="Ink Bottle C">
                <p>Ink Bottle C</p> <br>
                <p class="price"> <%= daoItem.getItemFromDB("7").getPrice() %> </p>
            </div>
            </a>
    
    
            <a href="product.jsp?id=19">
                <div class="product" onclick="redirectToProductPage('Ink Bottle I')">
                  <img src="../images/Product_19.jpg" alt="Ink Bottle I">
                  <p>Ink Bottle I</p> <br>
                  <p class="price"> <%= daoItem.getItemFromDB("19").getPrice() %> </p>
                </div>
            </a>
    
    
            <a class="catalogo" href="catalog.jsp" > Scopri di pi&#249...</a>
    
        </div>
        </section>
      </main>

    <div>
        <jsp:include page="footer.jsp"/>
    </div>

</body>
</html>
