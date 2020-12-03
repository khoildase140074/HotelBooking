<%-- 
    Document   : index
    Created on : Oct 11, 2020, 1:53:39 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LoginPage</title>
        <link href="css/login.css" rel="stylesheet" type="text/css">
    </head>
    <body class="bg">

        <form action="Login" method="POST">
            <div class="imgcontainer">
                <img src="css/login.png" alt="Avatar" class="avatar">
            </div>

            <div class="container">
                <label for="txtUsername"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="txtUsername" required>

                <label for="txtPassword"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="txtPassword" required>

                <input type="hidden" name="txtSearch" value="">
                <button type="submit">Login</button>
            </div>
        </form>
        <form action="register.jsp" method="POST">
            <button type="submit">Register</button>
        </form>

        ${requestScope.INVALID.idError}
        ${requestScope.INVALID.passwordError}
    </body>
</html>
