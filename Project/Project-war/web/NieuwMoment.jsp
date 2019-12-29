<%-- 
    Document   : NieuwMoment
    Created on : Nov 24, 2019, 3:30:49 PM
    Author     : jelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nieuw Moment Toevoegen</title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <h1>Toevoegen extra moment van: ${requestScope.machine.naam}</h1>
        serienummer: ${requestScope.machine.serienummer}
        <form method="post" action="">
            Gelieve alles in getallen in te vullen:<br>
            Dag: <input type="text" name="dag"><br>
            Maand: <input type="text" name="maand"><br>
            Jaar: <input type="text" name="jaar"><br>
            Uur: <input type="text" name="uur"><br>
            <input type="hidden" value="${requestScope.machine.serienummer}" name="serie">
            <input type="hidden" value="Toegevoegd" name="actie">
            <input type="submit" value="VoegToe">
        </form>
    </body>
</html>
