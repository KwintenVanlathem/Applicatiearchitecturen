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
        ${sessionScope.actie}
        <c:if test="${sessionScope.Rol == 'Docent'}">
            <form action=" " method="post">
                <input type="hidden" value="voegMachineToe" name="actie">
                <input type="submit" value="Voeg machine toe"
            </form>
        </c:if>
    </body>
</html>
