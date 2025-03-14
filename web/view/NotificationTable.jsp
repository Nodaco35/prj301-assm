<%-- 
    Document   : NotificationTable
    Created on : Mar 04, 2025, 8:08:40 AM
    Author     : Snowy
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Notification" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Danh sách Thông báo</title>
        <link rel="stylesheet" href="css/style.css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 
    </head>

    <body>
        <h2>Danh sách Thông báo</h2>
        <jsp:include page="header.jsp" />
        <jsp:include page="topnav.jsp" />
        <div>
            <button id="markAllAsRead" class="button">Đánh dấu tất cả đã đọc</button>
        </div>  
        <table border="1" id="notificationTable">
            <thead>
                <tr>
                    <th>Thông báo</th>
                    <th>Thời gian gửi</th>
                    <th>Trạng thái</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Notification> notifications
                            = (ArrayList<Notification>) request.getAttribute("notifications");
                    if (notifications == null || notifications.isEmpty()) {
                %>
                <tr>
                    <td colspan="3">Không có thông báo nào.</td>
                </tr>
                <%
                } else {
                    for (Notification notification : notifications) {
                %>
                <tr data-notification-id="<%= notification.getNotificationId()%>
                    " class="<%= notification.getIsRead() == 1 ? "read" : "unread"%>">
                    <td><%= notification.getMessage()%></td>
                    <td><%= notification.getSentDate()%></td>
                    <td>
                        <% if (notification.getIsRead() == 0) { %>
                        Chưa đọc
                        <% } else { %>
                        Đã đọc
                        <% } %>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>

        <br>
        <a href="<%= request.getContextPath()%>/index.jsp" class="button">Trở về</a>

        <script>
            $(document).ready(function () {
                $('#markAllAsRead').click(function () {
                    $.post('<%= request.getContextPath()%>/user',
                            {action: 'markAllAsRead'}, function (response) {
                        // Update the table without reloading
                        $('#notificationTable tbody tr.unread')
                                .each(function () {
                                    $(this).find('td:last').text('Đã đọc'); // Update status cell
                                    $(this).removeClass('unread').addClass('read'); // Update class
                                });
                    });
                });
            });
        </script>
    </body>
</html>