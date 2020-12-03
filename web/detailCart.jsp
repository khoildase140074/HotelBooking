<%-- 
    Document   : detailCart
    Created on : Oct 25, 2020, 4:33:29 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Your Order</h1><br/>
        <table border="1">
            <thead>
                <tr>
                    <th>Room's Type</th>
                    <th>Quantity In Stock</th>
                    <th>Quantity In Cart</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="sum" value="${0}"/>
                <c:forEach var="cart" items="${sessionScope.CART.bookingDetailList}">
                    <c:set var="sum" value="${sum + cart.roomID.price * cart.quantity}"/>
                    <tr>
                <form action="UpdateCart" method="POST">
                    <td>${cart.roomID.type}</td>
                    <td>${cart.roomID.quantity}</td>
                    <td><input type="number" name="txtCartQty" value="${cart.quantity}"</td>
                    <td>${cart.roomID.price}</td>
                    <td>${cart.roomID.price * cart.quantity}</td>
                    <input type="hidden" name="txtId" value="${cart.roomID.roomID}"/>
                    <input type="hidden" name="txtCartQty" value="${cart.quantity}"/>
                    <input type="hidden" name="txtQuantity" value="${cart.roomID.quantity}"/>
                    <td><input type="submit" value="Update"<td/>
                </form>
                <form action="DeleteCart" method="POST">
                    <td><input type="submit" value="Delete"/></td>
                    <input type="hidden" name="txtId" value="${cart.roomID.roomID}"/>
                </form>
            </tr>
        </c:forEach>
        <tr>
            <td></td><td></td><td></td><td></td><td>${sum}</td>
        </tr>
    </tbody>
</table>
<form action="Confirm" method="POST">
    Discount <input type="text" name="txtDiscount" size="6" /><br/>
    CheckIn Date:<input type="date" name="txtCheckIn" /></br>
    CheckOut Date:<input type="date" name="txtCheckOut" /></br>
    <c:out value="${requestScope.DATE}"/><br/>
    <input type="hidden" name="txtSum" value="${sum}" />
    <input type="submit" value="Confirm"/>
</form>

<font color="red">
<h2>${requestScope.NOTIFY}</h2>
</font>
<form action="RoomDetail" method="POST">
    <input type="hidden" name="txtHotelName" value="${requestScope.NAME}"/>
    <input type="hidden" name="txtHotelID" value="${requestScope.IDHOTEL}"/>
    <input type="submit" value="Choose More?" />
</form>
</body>
</html>
