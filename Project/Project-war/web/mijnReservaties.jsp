<%-- 
    Document   : mijnReservaties
    Created on : Nov 30, 2019, 9:19:31 PM
    Author     : jelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservaties</title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <h1>Dit zijn uw reservaties!</h1>
        <table>
            <tr>
                <th>Moment</th>
                <th>Machine</th>
            </tr>
            <c:forEach var="res" items="${requestScope.reservaties}">
            <tr>
                <td>${res[0]}</td>
                <td>${res[1]}</td>
            </tr>
        </c:forEach>

        </table>
        
    </body>
    
</html>
