<%-- 
    Document   : voegMachineToe
    Created on : Nov 14, 2019, 3:46:47 PM
    Author     : kwint
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VoegMachineToe</title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <h1>Voeg machine toe</h1>
        <form method="post" action="">
            <table>
                <tr><th>Serienummer</th><th>Naam</th><th>Lokaal</th></tr>
                <tr><td><input type="text" name="serienummer"></td><td><input type="text" name="naam"></td><td><input type="text" name="lokaal"></td></tr>
                <tr><th>Aankoopprijs</th><th>Huurprijs</th><th>Opleiding</th></tr>
                <tr><td><input type="text" name="aankoopprijs"></td><td><input type="text" name="huurprijs"></td><td><input type="text" name="opleiding"></td></tr>
                <tr><th colspan="3">Omschrijving</th></tr>
                <tr><td colspan="3"><textarea name="omschrijving"></textarea></td></tr>
            </table>
            <input type="hidden" value="new" name="actie">
            <input type="submit" value="Bewaar">
        </form>
    </body>
</html>
