<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Trang chủ</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>

        <h1>Chào mừng bạn đến với trang chủ</h1>

        <% 
            if (session.getAttribute("user") != null) { 
                // Người dùng đã đăng nhập
        %>

        <jsp:include page="header.jsp" />
        <jsp:include page="topnav.jsp" />
<!--        <button onclick="location.href = 'user?action=1'" class="button">Thêm phương tiện</button>-->
        <button onclick="location.href = 'user?action=2'" class="button">Phương tiện</button>
<!--        <button onclick="location.href = 'user?action=3'" class="button">Đăng kí kiểm định</button>-->
        <button onclick="location.href = 'user?action=9'" class="button">Đơn đăng kí kiểm định</button>

        <% 
            } else {
                // Người dùng chưa đăng nhập
        %>
        <jsp:include page="header.jsp" />
        <jsp:include page="topnav.jsp" />
        <!--        <button onclick="location.href = 'user?action=1'" class="button">Thêm phương tiện</button>-->
        <button onclick="location.href = 'login?action=login'" class="button">Phương tiện</button>
<!--        <button onclick="location.href = 'user?action=3'" class="button">Đăng kí kiểm định</button>-->
        <button onclick="location.href = 'login?action=login'" class="button">Đơn đăng kí kiểm định</button>


        <% 
            }
        %>

    </body>
</html>
