<%-- 
    Document   : index
    Created on : Feb 17, 2025, 11:08:11 AM
    Author     : NC PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
           <% response.sendRedirect(request.getContextPath() + "/login"); %>
    </body>
</html>
