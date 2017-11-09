<%--
  Created by IntelliJ IDEA.
  User: smatveev
  Date: 09.11.2017
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>

<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="POST" action="meal">

    <input type="hidden" name="id" value="${meal.id}">

    DateTime : <input
        type="datetime-local" name="datetime"
        value="${meal.dateTime}" /> <br />
    Description : <input
        type="text" name="description"
        value="${meal.description}" /> <br />
    Calories : <input
        type="number" name="calories"
        value="${meal.calories}" /> <br />

    <button type="submit">Save</button>
    <button onclick="window.history.back()">Cancel</button>

</form>
</body>
</html>
