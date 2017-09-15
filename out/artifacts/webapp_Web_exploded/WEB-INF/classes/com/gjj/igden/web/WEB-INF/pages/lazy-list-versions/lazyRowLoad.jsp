<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Insert title here</title>

</head>
<body>--%>
<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

<div class="container">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h3 class="panel-title">User details </h3>
    </div>
    <div class="panel-body">
      <form:form modelAttribute="user" action="lazyRowAdd.web" class="form-horizontal" method="post"
                 id="lazyList">


        <%--  <div class="col-sm-5">
            <li>
              <label for=name>Name</label>
              <form:input path="name" type="text" placeholder="First and last name" id="username1"/>
            </li>
          </div>--%>
        <div class="form-group">
          <form:label path="name" class="col-sm-4 control-label" for="username1">Name</form:label>
          <div class="col-sm-5">
            <input class="form-control" name="username1" id="username1"/>
          </div>
        </div>
        <div class="form-group">
          <form:label path="email" class="col-sm-4 control-label" for="email">Email</form:label>
          <div class="col-sm-5">
            <input class="form-control" name="email1" id="email1"/>
          </div>
        </div>
        <div class="form-group">
          <form:label path="email" class="col-sm-4 control-label" for="phone">some-info</form:label>
          <div class="col-sm-5">
            <input class="form-control"/>
          </div>
        </div>

        <li>
          <input type="button" value="Add Row" onclick="addRow('dataTable')"/>
          <input type="button" value="Delete Row" onclick="deleteRow('dataTable')"/>
          <div class="col-sm-5">
            <table id="dataTable" name="dataTable" width="350px" border="1">
              <tr>
                <td><input type="checkbox" name="chk"/></td>
                <td> 1</td>
                <td><input name="operationParameterses[0].name"/></td>
              </tr>
            </table>
          </div>
        </li>


        Dynamically data entered below:
        <%--  <ol>
            <c:forEach items="${user.operationParameterses}" var="value">
              <li><c:out value="${value.name}"/></li>
            </c:forEach>
          </ol>--%>

        <fieldset>
          <button>Save User Details!</button>
        </fieldset>
      </form:form>
    </div>
  </div>



</div>

<%--</body>
</html>--%>

<%@ include file="../js/lazy-list.js"%>


<%--<%@ include file="js/lazy-list-validation.js" %>--%>

