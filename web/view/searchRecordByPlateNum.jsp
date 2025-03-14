<%-- 
    Document   : searchRecordByPlateNum
    Created on : Mar 10, 2025, 2:37:39 PM
    Author     : Dai Hoang
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Vehicle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <h2>Theo dõi lịch sử kiểm định</h2>
        <%ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) request.getAttribute("vehicleList");%>
        <jsp:include page="header.jsp" />
        <jsp:include page="topnav.jsp" />

        <form action="user" method="get">
            <input type="hidden" name="action" value="4Done">
            <table>
                <tr>
                    <td>Choose your vehicle:</td>
                    <td><select name="plateNum">
                            <%for (Vehicle vehicle : vehicles) {%>
                            <option value="<%=vehicle.getPlateNumber()%>"><%=vehicle.getPlateNumber() + " " + vehicle.getBrand()%></option>                               
                            <% }%>
                        </select></td>
                    <td><input type="submit" value="Search" class="button"></td>
                </tr>
            </table>
        </form>
        <a href="user?action=9" class="button">Trở về</a>
    </body>
</html>
