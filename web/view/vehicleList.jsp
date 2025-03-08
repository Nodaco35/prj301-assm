<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Vehicle"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Danh sách phương tiện</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <h2>Danh sách phương tiện</h2>
        <table border="1">
            <tr>
                <th>Biển số xe</th>
                <th>Hãng xe</th>
                <th>Model</th>
                <th>Năm sản xuất</th>
                <th>Số máy</th>
            </tr>
            <%
                ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) request.getAttribute("vehicleList");
                if (vehicles != null && !vehicles.isEmpty()) {
                    for (Vehicle v : vehicles) {
            %>
            <tr>
                <td><%= v.getPlateNumber() %></td>
                <td><%= v.getBrand() %></td>
                <td><%= v.getModel() %></td>
                <td><%= v.getManufactureYear() %></td>
                <td><%= v.getEngineNumber() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5" style="text-align: center;">Không có phương tiện nào</td>
            </tr>
            <%
                }
            %>
        </table>
            <a href="<%= request.getContextPath()%>/index.jsp" class="button">Trở về</a>
            <button onclick="location.href = 'user?action=1'" class="button">Thêm phương tiện</button>
    </body>
</html>
