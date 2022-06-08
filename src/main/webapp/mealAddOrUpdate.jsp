<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>New meal</h2>

<form method="POST" action='meals' name="frmNewMeal">
    <input
        type="hidden" readonly="readonly" name="id"
        value=${meal.id} > <br>
    Date and Time : <input type="datetime-local" name="dateTime"
                     value=${meal.dateTime} > <br>
    Description : <input
        type="text" name="description"
        value=${meal.description} > <br>
    Calories : <input
        type="text" name="calories"
        value=${meal.calories}> <br>
        <input type="submit" value="Submit">
</form>

</body>
</html>