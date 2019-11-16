<%-- 
    Document   : overzicht
    Created on : 13-nov-2019, 13:33:35
    Author     : r0661567
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welkom ${sessionScope.Username} (${sessionScope.Rol})</h1>
        <table>
            <tr><th>Naam</th><th>Omschrijving</th><th>Lokaal</th></tr>${applicationScope.Machines}
            <c:forEach var="machine" items="${applicationScope.Machines}">
                <tr><td>${machine.naam}</td><td>${machine.omschrijving}</td><td>${machine.lokaal}</td></tr>
            </c:forEach>
        </table>
        <c:if test="${sessionScope.Rol == 'Docent'}">
            <form method="post" action="">
                <input type="hidden" value="voegMachineToe" name="actie">
                <input type="submit" value="Voeg machine toe">
            </form>
        </c:if>
    </body>
</html>
