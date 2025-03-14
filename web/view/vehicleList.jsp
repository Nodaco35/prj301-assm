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
        <jsp:include page="header.jsp" />
        <jsp:include page="topnav.jsp" />
        <table class="tab">
            <thead>
                <tr>
                    <th>Biển số xe</th>
                    <th>Hãng xe</th>
                    <th>Model</th>
                    <th>Năm sản xuất</th>
                    <th>Số máy</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) request.getAttribute("vehicleList");
                    if (vehicles != null && !vehicles.isEmpty()) {
                        for (Vehicle v : vehicles) {
                %>
                <tr>
                    <td><%= v.getPlateNumber()%></td>
                    <td><%= v.getBrand()%></td>
                    <td><%= v.getModel()%></td>
                    <td><%= v.getManufactureYear()%></td>
                    <td><%= v.getEngineNumber()%></td>
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
            </tbody>
        </table>
        <a href="<%= request.getContextPath()%>/index.jsp" class="button">Trở về</a>
        <button onclick="location.href = 'user?action=1'" class="button">Thêm phương tiện</button>
        <style>
            .tab {
                border-spacing: 1;
                border-collapse: collapse;
                background: white;
                border-radius: 10px;
                overflow: hidden;
                width: 90%;
                margin: 30px auto;
                position: relative;
            }
            .tab * {
                position: relative;
            }
            .tab td, .tab th {
                padding-left: 8px;
            }
            .tab thead tr {
                height: 60px;
                background: #36304a;
                color: white;
            }
            .tab tbody tr {
                height: 50px;
            }
            .tab tbody tr:last-child {
                border: 0;
            }
            .tab td, .tab th {
                text-align: left;
            }
            .tab td.l, .tab th.l {
                text-align: right;
            }
            .tab td.c, .tab th.c {
                text-align: center;
            }
            .tab td.r, .tab th.r {
                text-align: center;
            }
        </style>
    </body>
</html>
