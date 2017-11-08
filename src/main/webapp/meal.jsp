<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Meal</title>
</head>
<body>
<table border="1">
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Exceed</th>
    </tr>
    <c:forEach var="meal" items="${requestScope.meal}">
        <c:if test="${!meal.exceed}">
            <tr>
                <th width="150" ><font color="green"><javatime:format value="${meal.dateTime}" pattern="yyyy-mm-dd hh:mm" /></font></th>
                <th><font color="green">${meal.description}</font></th>
                <th><font color="green">${meal.calories}</font></th>
                <th><font color="green">${meal.exceed}</font></th>
            </tr>
        </c:if>

        <c:if test="${meal.exceed}">
            <tr>
                <th><font color="red"><javatime:format value="${meal.dateTime}" pattern="yyyy-mm-dd hh:mm" /></font></th>
                <th><font color="red">${meal.description}</font></th>
                <th><font color="red">${meal.calories}</font></th>
                <th><font color="red">${meal.exceed}</font></th>
            </tr>
        </c:if>

    </c:forEach>
</table>
</body>
</html>
