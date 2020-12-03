<%-- 
    Document   : admin
    Created on : Oct 24, 2020, 5:23:01 PM
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
        <link href="css/home.css" rel="stylesheet" type="text/css">
    </head>
    <body class="bg">

        <div class="navbar">
            <c:if test="${not empty sessionScope.USER}" var="checkLogin">            
                <a href="Logout">Logout</a>
                <a>Welcome, ${sessionScope.USER.name}.</a>
            </c:if>

            <c:if test="${!checkLogin}">
                <a href="login.jsp">Login</a>
            </c:if>
        </div>

        <main>
            <aside class="left">

                <c:if test="${not empty sessionScope.CART}">

                    <h2>Your Cart:</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>Rooms</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cart" items="${sessionScope.CART.bookingDetailList}">
                                <tr>
                                    <td>${cart.roomID.type}<br/>x ${cart.roomID.quantity}</td>
                                    <td>${cart.roomID.price * cart.roomID.quantity}$</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <form action="detailCart.jsp" method="POST">
                        <input type="submit" value="View Cart Detail"/>
                    </form>
                </c:if>
            </aside>

            <section class="right">
                <c:forEach var="item" items="${requestScope.INFO}">
                    <form action="RoomDetail" method="POST">
                        <button value="RoomDetail">
                            ${item.name}<br/>
                            ${item.area}<br/>
                            <img src="${item.image}"/>
                            <input type="hidden" name="txtHotelID" value="${item.hotelID}"/>
                            <input type="hidden" name="txtHotelName" value="${item.name}"/>
                        </button>
                    </form>
                </c:forEach>
            </section>
            <font color="red">
            <h2>
                ${requestScope.NOTI}
            </h2>
            </font>
        </main>
    </body>
</html>
