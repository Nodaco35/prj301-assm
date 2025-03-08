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
</head>
<body>
    <h2>Danh sách yêu cầu kiểm định</h2>
    <% ArrayList<InspectionRequest> requests = (ArrayList<InspectionRequest>) request.getAttribute("requests"); %>
    <% ArrayList<InspectionStation> stations = (ArrayList<InspectionStation>) request.getAttribute("stations"); %>
    <% ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) request.getAttribute("vehicles"); %>
    <%
    %>
    <table border="1">
        <tr>
            <th>Biển số xe</th>
            <th>Tên xe</th>
            <th>Ngày đăng ký</th>
            <th>Tên trạm đăng ký</th>
            <th>Địa chỉ trạm</th>
            <th>Trạng thái</th>
        </tr>
        <% if (requests != null && stations!=null && vehicles != null) { %>
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
                    <td><%= vehicle != null ? vehicle.getPlateNumber() : "Không xác định" %></td>
                    <td><%= vehicle != null ? vehicle.getBrand() + " " + vehicle.getModel() : "Không xác định" %></td>
                    <td><%= requestItem.getRequestedDate() %></td>
                    <td><%= station != null ? station.getName() : "Không xác định" %></td>
                    <td><%= station != null ? station.getAddress() : "Không xác định" %></td>
                    <td><%= requestItem.getComments() != null ? requestItem.getComments() : "Không có ghi chú" %></td>
                </tr>
            <% } %>
        <% } else{%>
                <tr>Không có yêu cầu đăng kí/ Lỗi danh sách</tr>   
                <%}%>
    </table>
    <a href="<%= request.getContextPath()%>/index.jsp" class="button">Trở về</a>
    <button onclick="location.href = 'user?action=3'" class="button">Đăng kí kiểm định</button>
</body>
</html>