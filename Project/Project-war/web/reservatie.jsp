<%-- 
    Document   : Reservatie
    Created on : Nov 23, 2019, 9:57:16 PM
    Author     : jelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservatie</title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <h1>Reservatie van: ${sessionScope.machine.naam}</h1>
        <table>
            <tr>
                <th>Maandag</th><th>Dinsdag</th><th>Woensdag</th><th>Donderdag</th><th>Vrijdag</th><th>Zaterdag</th><th>Zondag</th>
            </tr>
            <tr>
                <c:forEach var="index" items="${requestScope.index}">
                <th>${requestScope.dates.get(2*index)}/${requestScope.dates.get(2*index+1)}</th>
                </c:forEach>
            </tr>
            
        </table>
    </body>
</html>