<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DetailMachine</title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <h1>Detail Machine</h1>
        <table>
            <tr><th>Serienummer</th><th>Naam</th><th>Lokaal</th></tr>
            <tr><td>${sessionScope.machine.serienummer}</td><td>${sessionScope.machine.naam}</td><td>${sessionScope.machine.lokaal}</td></tr>
            <tr><th>Aankoopprijs</th><th>Huurprijs</th><th>Opleiding</th></tr>
            <tr><td>${sessionScope.machine.aankoopprijs}</td><td>${sessionScope.machine.huurprijs}</td><td>${sessionScope.machine.opleiding}</td></tr>
            <tr><th colspan="3">Omschrijving</th></tr>
            <tr><td colspan="3">${sessionScope.machine.omschrijving}</td></tr>
        </table>
        <form method="post" action="">
            <input type="hidden" value="reservatie" name="actie">
            <input type="hidden" value="${machine.serienummer}" name="serie">
            <input type="submit" value="Reserveer">
        </form>
        <c:if test="${sessionScope.Rol == 'Docent' and sessionScope.docentopleiding == sessionScope.machine.opleiding}">
            <form method="post" action="">
                <input type="hidden" value="${sessionScope.machine.serienummer}" name="serie">
                <input type="hidden" value="bewerkMachine" name="actie">
                <input type="submit" value="Bewerk">
            </form>
        </c:if>
    </body>
</html>
