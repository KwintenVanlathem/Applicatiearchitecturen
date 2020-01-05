<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table>
    <tr>
        <td>
            <form method="post" action="">
                <input type="submit" value="Overzicht">
            </form>
        </td>
        <td>
            <form method="post" action="">
                <input type="hidden" value="mijnReservaties" name="actie">
                <input type="submit" value="Mijn reservaties">
            </form>
        </td>
        <td>
            <form method="post" action="">
                <input type="hidden" value="logout" name="actie">
                <input type="submit" value="Logout">
            </form>
        </td>
    </tr>
</table>