<%-- 
    Document   : header
    Created on : Feb 27, 2025, 4:08:48 PM
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
        <div class="header">
            <table>
                    <tr>
                        <% 
                            User user = (User)(session.getAttribute("user"));
                            if(user!=null){
                        %>
                        <td><b>User: <%= user.getFullName() %></b></td>
                        <%
                            }else{
                        %>
                        <td><b>Vui lòng đăng nhập</b></td>
                        <%}%>
                    </tr>
            </table>
        </div>
    </body>
</html>
