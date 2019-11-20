<%-- 
    Document   : bewerkMachine
    Created on : Nov 20, 2019, 1:49:21 PM
    Author     : kwint
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BewerkMachine</title>
    </head>
    <body>
        <h1>Bewerk Machine</h1>
        <form method="post" action="">
            <table>
                <tr><th>Serienummer</th><th>Naam</th><th>Lokaal</th></tr>
                <tr><td>${sessionScope.machine.serienummer}</td><td><input type="text" value="${sessionScope.machine.naam}" name="naam"></td><td><input type="text" value="${sessionScope.machine.lokaal}" name="lokaal"></td></tr>
                <tr><th>Aankoopprijs</th><th>Huurprijs</th><th>Opleiding</th></tr>
                <tr><td><input type="text" value="${sessionScope.machine.aankoopprijs}" name="aankoopprijs"></td><td><input type="text" value="${sessionScope.machine.huurprijs}" name="huurprijs"></td><td>${sessionScope.machine.opleiding}</td></tr>
                <tr><th colspan="3">Omschrijving</th></tr>
                <tr><td colspan="3"><textarea name="omschrijving">${sessionScope.machine.omschrijving}</textarea></td></tr>
            </table>
            <input type="hidden" value="${sessionScope.machine.opleiding}" name="opleiding">
            <input type="hidden" value="${sessionScope.machine.serienummer}" name="serie">
            <input type="hidden" value="save" name="actie">
            <input type="submit" value="Bewaar">
        </form>
    </body>
</html>
