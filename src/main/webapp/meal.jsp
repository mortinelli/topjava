<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Meal</title>
    <style>
        .normal {color: green}
        .exceeded {color: red}
    </style>
</head>
<body>

<a href="meal?action=create">Add Meal</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Exceed</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <c:forEach  items="${requestScope.meal}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <th>${meal.id}</th>
                <th width="150" ><javatime:format value="${meal.dateTime}" pattern="yyyy-mm-dd hh:mm" /></th>
                <th>${meal.description}</th>
                <th>${meal.calories}</th>
                <th>${meal.exceed}</th>
                <th><a href="meal?action=update&id=${meal.id}">Update</a></th>
                <th><a href="meal?action=delete&id=${meal.id}">Delete</a></th>
            </tr>

    </c:forEach>

</table>



</body>
</html>
