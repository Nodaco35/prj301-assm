<%@page import="java.util.ArrayList"%>
<%@page import="model.Vehicle"%>
<%@page import="model.InspectionStation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký kiểm định</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <h2>Đăng ký kiểm định</h2>
        <% String error = request.getParameter("error"); %>
        <% if (error != null) { %>
        <% if (error.equals("missingData")) { %>
        <p style="color: red;">Vui lòng chọn lại phương tiện.</p>
        <% } else if (error.equals("invalidVehicle")) {%>
        <p style="color: red;">Vui lòng chọn phương tiện.</p>
        <%      }
        }%>
        <form action="user;jsessionid=<%= session.getId()%>" method="get">
            <input type="hidden" name="action" value="3Done">
            <table>
                <tr>
                    <td><label for="vehicle">Chọn phương tiện:</label></td>
                    <td><% ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) request.getAttribute("vehicleList"); %>
                        <% if (vehicleList != null) { %>
                        <select id="vehicle" name="vehicle" required>

                            <% for (Vehicle vehicle : vehicleList) {%>
                            <option value="<%= vehicle.getPlateNumber()%>">
                                <%= vehicle.getPlateNumber()%> - <%= vehicle.getBrand()%> <%= vehicle.getModel()%>
                            </option>
                            <% }
                            } %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="station">Chọn cơ sở kiểm định:</label></td>
                    <td><% ArrayList<InspectionStation> stationList = (ArrayList<InspectionStation>) request.getAttribute("stationList"); %>
                        <% if (stationList != null) { %>
                        <select id="station" name="station" required>

                            <% for (InspectionStation station : stationList) {%>
                            <option value="<%= station.getStationId()%>">
                                <%= station.getName()%> - <%= station.getAddress()%>
                            </option>
                            <% }
                            }%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <button type="submit">Đăng ký</button>
                    </td>
                </tr>
            </table>
        </form>
                            <a href="<%= request.getContextPath()%>/index.jsp" class="button">Trở về</a>
    </body>
</html>
