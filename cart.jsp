<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%
    HttpSession sessionCart = request.getSession();
    List<String> cart = (List<String>) sessionCart.getAttribute("cart");

    if (cart == null || cart.isEmpty()) {
%>
        <h2>Your Cart is Empty</h2>
<%
    } else {
        int total = 0;
%>
        <h2>Your Shopping Cart</h2>
        <%
            for (String item : cart) {
                String[] details = item.split(",");
                total += Integer.parseInt(details[1]);
        %>
            <p><%= details[0] %> - ₹<%= details[1] %></p>
        <%
            }
        %>
        <h3>Total Price: ₹<%= total %></h3>
<%
    }
%>
    <br>
    <a href="index.jsp">Continue Shopping</a>
</body>
</html>
