<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: denisigoshev
  Date: 6/15/2017
  Time: 4:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Search data </title>
  <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<h1>Let's make some search ! </h1>
<body>
<form action="home.jsp" method="post">
  <input type="submit" value="back home">
</form>
<br>
<form action="DataController" method="get">
  <div class="container">
    <div class="form-group">
      <div class="col-md-10">
        <div class="input-group">
          <input type="text" name="searchParam" class="form control" size="44"
                 placeholder="Type instrument id or first chars of id with '%' symbol"
                 autocomplete="off">
          <button type="submit" value="Search"
                  class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>
            Search
          </button>

        </div>
      </div>
    </div>
    <br><br>
    <table class="table table-bordered table-striped" style="width: 70%">
      <tr>
        <th style="width: 15%">instrument id</th>
        <%--  <th style="width: 15%">watchlist (data set) name</th>
          <th style="width: 15%">records number</th>--%>
        <c:forEach var="tempSearchRecord" items="${THE_SEARCH_RESULT_LIST}">

      <tr>
        <td> ${tempSearchRecord} </td>
          <%--<td> ${tempSearchRecord.getValue1()} </td>
          <td> ${tempSearchRecord.getValue2()} </td>--%>
      </tr>
      </c:forEach>
      </tr>
    </table>
  </div>
</form>

</body>
</html>
