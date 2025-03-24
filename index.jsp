<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Fashion Store</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; }
        .product { display: inline-block; padding: 20px; margin: 10px; border: 1px solid black; }
        .cart-btn { margin-top: 10px; padding: 5px 10px; background: blue; color: white; border: none; }
    </style>
</head>
<body>
    <h2>Welcome to The Fashion Store</h2>
    
    <form action="/FashionStore/CartServlet" method="post">  
        <div class="product">
            <h3>T-Shirt</h3>
            <p>Price: ₹500</p>
            <button type="submit" name="product" value="T-Shirt,500" class="cart-btn">Add to Cart</button>
        </div>

        <div class="product">
            <h3>Jeans</h3>
            <p>Price: ₹1200</p>
            <button type="submit" name="product" value="Jeans,1200" class="cart-btn">Add to Cart</button>
        </div>
    </form>

    <br>
    <a href="cart.jsp">View Cart</a>
</body>
</html>
