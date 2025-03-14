<%@page import="java.util.ArrayList"%>
<%@page import="model.InspectionRequest"%>
<%@page import="model.InspectionStation"%>
<%@page import="model.Vehicle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Yêu cầu kiểm định của bạn</title>
        <link rel="stylesheet" href="css/style.css"/>
        <style>
            .action-button {
                padding: 10px 20px;
                font-size: 16px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin: 0 5px;
            }
            .accept-button:hover {
                background-color: green;
                color: white;
            }
            .reject-button:hover {
                background-color: darkred;
                color: white;
            }
        </style>
    </head>
    <body>
        <h2>Danh sách yêu cầu kiểm định</h2>
        <% ArrayList<InspectionRequest> requests = (ArrayList<InspectionRequest>) request.getAttribute("requests"); %>
        <% ArrayList<InspectionStation> stations = (ArrayList<InspectionStation>) request.getAttribute("stations"); %>
        <% ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) request.getAttribute("vehicles"); %>
        <jsp:include page="header.jsp" />
        <jsp:include page="topnav.jsp" />
        <table class="tab">
            <thead>
                <tr>
                    <th>Biển số xe</th>
                    <th>Tên xe</th>
                    <th>Thời gian</th>
                    <th>Tên trạm đăng ký</th>
                    <th>Địa chỉ trạm</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                    <th>Thời gian kiểm định</th>
                </tr>
            </thead>
            <tbody>
                <% if (requests != null && stations != null && vehicles != null) { %>
                <% for (InspectionRequest requestItem : requests) { %>
                <%
                    Vehicle vehicle = null;
                    for (Vehicle v : vehicles) {
                        if (v.getVehicleId() == requestItem.getVehicleId()) {
                            vehicle = v;
                            break;
                        }
                    }

                    InspectionStation station = null;
                    if (requestItem.getStationId() != null) {
                        for (InspectionStation s : stations) {
                            if (s.getStationId() == requestItem.getStationId()) {
                                station = s;
                                break;
                            }
                        }
                    }
                %>
                <tr>
                    <td><%= vehicle != null ? vehicle.getPlateNumber() : "Không xác định"%></td>
                    <td><%= vehicle != null ? vehicle.getBrand() + " " + vehicle.getModel() : "Không xác định"%></td>
                    <td><%= requestItem.getRequestedDate()%></td>
                    <td><%= station != null ? station.getName() : "Không xác định"%></td>
                    <td><%= station != null ? station.getAddress() : "Không xác định"%></td>
                    <td><%= requestItem.getComments() != null ? requestItem.getComments() : "Không có ghi chú"%></td>
                    <td>
                        <% if ("Confirm".equals(requestItem.getStatus())) {%>
                        <form action="user" style="display: block; margin-bottom: 8px;margin-bottom: 8px;">
                            <input type="hidden" name="action" value="confirm">
                            <input type="hidden" name="requestId" value="<%= requestItem.getRequestId()%>">
                            <button type="submit" name="response" value="accept" class="action-button accept-button">Đồng ý</button>
                        </form>
                        <form action="user" style="display: block; margin-bottom: 8px;margin-bottom: 8px;">
                            <input type="hidden" name="action" value="confirm">
                            <input type="hidden" name="requestId" value="<%= requestItem.getRequestId()%>">
                            <button type="submit" name="response" value="reject" class="action-button reject-button">Từ chối</button>
                        </form>
                        <% }%>

                    </td>
                    <td>
                        <%if ("Successfully".equals(requestItem.getStatus()) || "Confirm".equals(requestItem.getStatus())) {%>
                        <%= requestItem.getPreferredDate()%>
                        <%}%>
                    </td>
                </tr>
                <% } %>
                <% } else { %>
                <tr>
                    <td colspan="7">Không có yêu cầu đăng kí/ Lỗi danh sách</td>
                </tr>
                <% }%>
            </tbody>

        </table>
        <a href="<%= request.getContextPath()%>/index.jsp" class="button">Trở về</a>
        <button onclick="location.href = 'user?action=3'" class="button">Đăng kí kiểm định</button>
        <button onclick="location.href = 'user?action=4'" class="button">Theo dõi lịch sử kiểm định</button>
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
            .tab td{
                border: 1px solid black;
            }
            .tab th{
                border: 1px solid white;
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