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
        <style>
            .topnav {
                display: flex;
                justify-content: space-between; /* Đặt các nút ở hai đầu */
                background-color: #333; /* Màu nền */
                padding: 10px; /* Khoảng cách bên trong */
            }

            .topnav a {
                color: white; /* Màu chữ */
                text-decoration: none; /* Bỏ gạch chân */
                padding: 10px 15px; /* Khoảng cách bên trong các nút */
                border-radius: 5px; /* Bo góc */
                transition: background-color 0.3s; /* Hiệu ứng chuyển màu */
                margin-right: 5px; /* Khoảng cách giữa các nút */
            }

            .topnav a:last-child {
                margin-right: 0; /* Không có khoảng cách bên phải nút cuối cùng */
            }

            .topnav a:hover {
                background-color: #575757; /* Màu khi hover */
            }

            .notify {
                margin-left: auto; /* Đẩy nút thông báo sang bên phải */
                background-color: #28a745; /* Màu nền nút thông báo */
                border-radius: 5px; /* Bo góc */
            }

            .notify:hover {
                background-color: #218838; /* Màu khi hover trên nút thông báo */
            }

            /* Căn chỉnh nút Đăng nhập và Đăng kí gần nhau hơn */
            .auth-links {
                display: flex;
                gap: 10px; /* Điều chỉnh khoảng cách giữa hai nút */
            }
        </style>
    </head>
    <body>
        <% 
            User user = (User) (session.getAttribute("user"));
            if (user != null) { //Người dùng đã đăng nhập
        %>
        <div class="topnav">
            <div>
                <a href="Accounts">Accounts</a>
                <a href="Logout">Log out</a>
            </div>
            <a class="notify" onclick="location.href = 'user?action=100'">Thông báo</a>
        </div>
        <%
        } else { //Người dùng chưa đăng nhập
        %>
        <div class="topnav">
            <div class="auth-links">
                <a href="login?action=login">Đăng nhập</a>
                <a href="login?action=login&optionLoginOrRegister=register">Đăng kí</a> 

            </div>
            <a class="notify" onclick="location.href = 'login?action=login'">Thông báo</a>
        </div>
        <%}%>
    </body>
</html>
