<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservatie</title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <h1>Bevestig hier je reservatie!</h1>
        De prijs is ${prijs}
        
        <form method="post" action="">
            <input type="hidden" value="${requestScope.serie}" name="serie">
            <input type="hidden" value="${requestScope.jaar}" name="jaar">
            <input type="hidden" value="${requestScope.maand}" name="maand">
            <input type="hidden" value="${requestScope.dag}" name="dag">
            <input type="hidden" value="${requestScope.uur}" name="uur">
            <input type="hidden" value="goed" name="actie">
            <input type="submit" value="Reserveer">
        </form>
        <form method="post" action="">
            <input type="hidden" value="${requestScope.serie}" name="serie">
            <input type="hidden" value="terug" name="actie">
            <input type="submit" value="Reserveer NIET">
        </form>
        
    </body>
</html>
