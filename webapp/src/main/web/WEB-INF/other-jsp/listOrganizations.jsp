<%--
  Created by IntelliJ IDEA.
  User: denisigoshev
  Date: 7/14/2017
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>List organizations</title>
    <style>
        table {
            border-collapse: collapse;
        }

        table, th, td {
            padding: 5px;
            margin: 10px;
            font-size: 20px;
            border: 1px solid black;
        }
    </style>
</head>
<body>
<h2 align="center" style="color: #DF0101">
    <c:out value="${greeting}"/>
</h2>
<hr/>
<div align="center">
    <table>
        <caption style="font-size: 40px; margin-bottom: 10px">
            <c:out value="${locationName}"/> Organization Directory
        </caption>
        <tr>
            <th>Name</th>
            <th>Year</th>
            <th>Zip</th>
            <th>Emp Count</th>
            <th>Slogan</th>
        </tr>
        <c:forEach var="row" items="${orgList}">
            <tr>
                <td><c:out value="${row.companyName}"/></td>
                <td><c:out value="${row.yearOfIncorporation}"/></td>
                <td><c:out value="${row.postalCode}"/></td>
                <td><c:out value=" ${row.employeeCount}"/></td>
                <td><c:out value="${row.slogan}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>
