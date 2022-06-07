<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p><a href="meals?action=insert">Add User</a></p>
<table border=1>
    <thead>
    <tr>
        <th>Date - Time</th>
        <th>Description</th>
        <th>calories</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <tr style="color: ${meal.excess ? 'red' : 'green'}">
            <td><c:out value="${meal.date}"/> <c:out value="${meal.time}"/></td>
            <td><c:out value="${meal.description}" /></td>
            <td><c:out value="${meal.calories}" /></td>
            <td><a href="meals?action=edit&userId=${meal.dateTime}">Update</a></td>
            <td><a href="MealServlet?action=delete&userId=<c:out value="${meal.dateTime}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>