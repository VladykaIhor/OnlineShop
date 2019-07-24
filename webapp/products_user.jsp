<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="./style/products_user.css">
</head>
<body>
<div style="text-align: center; background-color: aqua">
    <a href="index.jsp">Home </a>
    <center>
        <br>
        <form action="/logout" method="post">
            <button type="submit">Logout</button>
        </form>
        <table border="1">
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
            </tr>
            Items in shopping cart : ${count}
            <br>
            <c:forEach var="products" items="${products}">
            <tr>
                <td>${products.name}</td>
                <td>${products.description}</td>
                <td>${products.price}</td>
                <td>
                    <form action="/users/add_to_cart" method="post">
                        <input type="hidden" name="id" value="${products.getId()}">
                        <button type="submit">To Cart</button>
                    </form>
                </td>
            </tr>
            </c:forEach>
            <br>
            <form>
                <button class="cart-toggle"
                        type="submit"
                        name="submit"
                        formaction="/users/order_confirmation" formmethod="post"
                        value="Cart page">${count}
                </button>
            </form>
    </center>
</div>
<div>

</div>
</body>
</html>
