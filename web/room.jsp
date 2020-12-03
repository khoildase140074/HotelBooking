<%-- 
    Document   : room
    Created on : Oct 24, 2020, 9:53:51 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome To ${requestScope.NAME} Hotel</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Room Type</th>
                    <th>Price</th>
                    <th>Amount</th>
                    <th>Add to Cart</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${requestScope.INFO}">
                    <tr>
                        <td>${item.type}</td>
                        <td>${item.price}</td>
                        <td>${item.quantity}</td>
                        <td>
                            <form action="AddCart" method="POST">
                                <input type="submit" value="Add to Cart"/>
                                <input type="hidden" name="txtId" value="${item.roomID}"/>
                                <input type="hidden" name="txtHotelID" value="${requestScope.IDHOTEL}"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${not empty sessionScope.CART}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Your Cart's items</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cart" items="${sessionScope.CART.bookingDetailList}">
                        <tr>
                            <td>${cart.roomID.type} x ${cart.quantity}</td>
                            <td>${cart.roomID.price * cart.quantity}$</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>Total</td>
                        <td>${sessionScope.CART.total}$</td>
                    </tr>
                </tbody>
            </table>
            <form action="DetailCart" method="POST">
                <input type="hidden" name="txtHotelName" value="${requestScope.NAME}"/>
                <input type="hidden" name="txtHotelID" value="${requestScope.IDHOTEL}"/>
                <input type="submit" value="View Cart Detail"/><br/><br/>
            </form>                   
        </c:if>
        <form action="Search" method="POST">
            <input type="submit" value="Want To Choose More Hotel?" />
        </form>
    </body>
</html>
