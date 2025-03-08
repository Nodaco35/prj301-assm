<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Phương Tiện Mới</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <h2>Thêm Phương Tiện Mới</h2>
        <% String error = request.getParameter("error"); %>
        <% if (error != null) { %>
        <% if (error.equals("missingData")) { %>
        <p style="color: red; text-align: center;">Vui lòng điền đầy đủ thông tin.</p>
        <% } else if (error.equals("invalidYear")) { %>
        <p style="color: red; text-align: center;">Năm sản xuất không hợp lệ.</p>
        <%      } %>
        <% }%>
        <form action="user;jsessionid=<%= session.getId() %>" method="GET">
            <input type="hidden" name="action" value="1Done">
            <table>
                <tr>
                    <td><label for="plateNumber">Biển số xe:</label></td>
                    <td><input type="text" id="plateNumber" name="plateNumber" required></td>
                </tr>
                <tr>
                    <td><label for="brand">Hãng xe:</label></td>
                    <td><input type="text" id="brand" name="brand" required></td>
                </tr>
                <tr>
                    <td><label for="model">Mẫu xe:</label></td>
                    <td><input type="text" id="model" name="model" required></td>
                </tr>
                <tr>
                    <td><label for="year">Năm sản xuất:</label></td>
                    <td><input type="number" id="year" name="year" min="1900" max="<%= java.time.Year.now().getValue()%>" required></td>
                </tr>
                <tr>
                    <td><label for="engineNumber">Số máy:</label></td>
                    <td><input type="text" id="engineNumber" name="engineNumber" required></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <button type="submit">Thêm Phương Tiện</button>
                    </td>
                </tr>
            </table>
        </form>
                    <a href="<%= request.getContextPath()%>/index.jsp" class="button">Trở về</a>
    </body>
</html>
