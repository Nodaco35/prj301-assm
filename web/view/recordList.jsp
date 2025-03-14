<%@page import="java.util.ArrayList"%>
<%@page import="model.ShowRecord"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <jsp:include page="topnav.jsp" />
        <h2>Lịch sử kiểm định của phương tiện</h2>

        <% ArrayList<ShowRecord> records = (ArrayList<ShowRecord>) request.getAttribute("record");%>
        <%if (records == null) {%>
        <h2>None record was found!</h2>
        <%} else {%>
        <table border="1">
            <thead>
                <tr>
                    <th>Full Name</th>
                    <th>Inspection Date</th>
                    <th>Result</th>
                    <th>CO2 Emission</th>
                    <th>HC Emission</th>
                    <th>Comments</th>                                      
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%for (ShowRecord record : records) {%>

                    <td><%=record.getFullName()%></td>
                    <td><%=record.getInspectionDate()%></td>
                    <td><%=record.getResult()%></td>
                    <td><%=record.getCo2Emission()%></td>
                    <td><%=record.getHcEmission()%></td>
                    <td><%=record.getComments()%></td>
                    <%}%>
                    <%}%>
                </tr>
            </tbody>
        </table>
                <a href="<%= request.getContextPath()%>/user?action=4" class="button">Trở về</a>
    </body>
</html>
