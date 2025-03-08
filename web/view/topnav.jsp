<%-- 
    Document   : topnav
    Created on : Feb 26, 2025, 8:27:09 AM
    Author     : NC PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            User user = (User) (session.getAttribute("user"));
            if (user != null) { //Người dùng đã đăng nhập
        %>
        <div class="topnav">
            <a href="Accounts">Accounts</a>
            <a href="Logout">Log out</a>
        </div>
        <%
        } else { //Người dùng chưa đăng nhập
        %>
        <div class="topnav">
            <a href="login?action=login">Đăng nhập</a>
            <a href="login?action=login">Đăng kí</a>
        </div>
        <%}%>
    </body>
</html>
