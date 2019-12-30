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
        <h1>Reservatie van: ${requestScope.machine.naam}</h1>
        <table>
            <tr>
                <th></th><th>Maandag</th><th>Dinsdag</th><th>Woensdag</th><th>Donderdag</th><th>Vrijdag</th><th>Zaterdag</th><th>Zondag</th>
            </tr>
            <tr>
                <th>Uur:</th>
                <c:forEach var="index" begin="0" end="6">
                <th>${requestScope.dates.get(3*index)}/${requestScope.dates.get(3*index+1)}</th>
                </c:forEach>
            </tr>
            <c:forEach var="uur" begin="8" end="19">
                <tr>
                    <th>
                        ${uur} uur:
                    </th>
                    <c:forEach var="dag" begin="0" end="6" varStatus="loop">
                        <th>
                            <c:forEach var="res" items="${requestScope.reservaties}">
                                <c:if test="${res[1]==requestScope.dates.get(3*dag)}">
                                    <c:if test="${res[2]==uur}">
                                        <c:if test="${res[0]=='Vrij'}">
                                            <form method="post" action="">
                                                <input type="hidden" value="${requestScope.machine.serienummer}" name="serie">
                                                <input type="hidden" value="Reserveer" name="actie">
                                                
                                                <input type="hidden" value="${requestScope.dates.get(3*dag+2)}" name="jaar">
                                                <input type="hidden" value="${requestScope.dates.get(3*dag+1)}" name="maand">
                                                <input type="hidden" value="${res[1]}" name="dag">
                                                <input type="hidden" value="${res[2]}" name="uur">
                                                <input type="submit" value="
            
                                        </c:if>
                                        ${res[0]}
                                        <c:if test="${res[0]=='Vrij'}">
                                                ">
                                            </form>
                                        </c:if>
                                        
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </th>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${sessionScope.Rol == 'Docent' and sessionScope.docentopleiding == requestScope.machine.opleiding}">
            <form method="post" action="">
                <input type="hidden" value="${requestScope.machine.serienummer}" name="serie">
                
                <input type="hidden" value="VoegMomentToe" name="actie">
                <input type="submit" value="Voeg moment toe">
            </form>
        </c:if>
        <c:forEach var="res" items="${requestScope.reservaties}">
                user: ${res[0]}
                dag: ${res[1]}
                uur: ${res[2]}
                <br>
        </c:forEach>
    </body>
</html>