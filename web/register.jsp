<%-- 
    Document   : register
    Created on : Oct 24, 2020, 5:44:22 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/login.css" rel="stylesheet" type="text/css">
    </head>
    <body class="bg">
        
        <form action="SignUp" method="POST">
            <div class="imgcontainer">
                <img src="css/login.png" alt="Avatar" class="avatar">
            </div>

            <div class="container">
                <label for="txtUserID"><b>Email</b></label>
                <input type="text" placeholder="Enter Email" name="txtUserID" required>

                <label for="txtPassword"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="txtPassword" required>

                <label for="txtConfirm"><b>Confirm Password</b></label>
                <input type="password" placeholder="Re-enter Password" name="txtConfirm" required>
                
                <label for="txtName"><b>Name</b></label>
                <input type="text" placeholder="Enter Name" name="txtName" required>
                
                <label for="txtPhone"><b>Phone</b></label>
                <input type="text" placeholder="Enter Phone" name="txtPhone" required>
                
                <label for="txtAddress"><b>Address</b></label>
                <input type="text" placeholder="Enter Address" name="txtAddress" required>
                
                <button type="submit">Sign Up</button>
            </div>
        </form>
    </body>
</html>
