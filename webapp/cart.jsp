<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<div name="cart" align="center">
    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            </th>
        </tr>
        <c:set var="check" scope="page" value="${flag}"/>
        <c:if test="${check > 0}">
            <c:forEach var="product" items="${order}">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
<div name="order" align="center">
    <form action="/users/order_confirmation" method="get" class="decor">
        <div class="form-left-decoration"></div>
        <div class="form-right-decoration"></div>
        <div class="circle"></div>
        <div class="form-inner">
            <h3>Order information: </h3><br>
            <input name="delivery" type="text" placeholder="Delivery address" value="${delivery}">
            <input name="email" type="text" placeholder="Email" value="${email}">
            <h4>${error}</h4>
            <form>
                <input type="submit" name="submit" formaction="/users/order_confirmation" formmethod="get" value="Pay">
            </form>
            <form>
                <input type="submit" name="submit" formaction="/users/products" formmethod="get" value="Store">
            </form>
        </div>
    </form>
</div>
</body>
</html>
