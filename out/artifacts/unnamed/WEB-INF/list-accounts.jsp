<%--<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <title>Fintech Application</title>
  <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css"
        rel="stylesheet">
  <%-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>--%>
  <script src="https://code.highcharts.com/highcharts.js"></script>
  <%-- <link rel="stylesheet"
         href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>--%>
  <script type="text/javascript"
          src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>
  <script type="text/javascript"
          src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js">
  </script>


  <%-- <script
      src="webjars/bootstrap-datepicker/1.0.1/js/bootstrap-datepicker.js"></script>
      this is for calendar
      --%>

</head>

<body>


<div id="wrapper">
  <div id="header">
    <h2>YAFINTECH Web Service</h2>
  </div>
</div>


<div class="container">

  <br/>
  <c:if test="${param.success eq true}">
    <div class="alert alert-success"> changes applied successfully </div>
  </c:if>
  <table class="table table-striped">

    <tr>
      <th>AccountName</th>
      <th>Email</th>
      <th>AdditionalInfo</th>
      <th>CreationDate</th>
      <th>Action</th>
    </tr>
    <div>
      <a type="button" class="btn btn-success" href="/add-account">Add New Account</a>
    </div>

    <c:forEach var="currAccount" items="${ACCOUNT_LIST}">

      <tr>
        <td> ${currAccount.getAccountName()} </td>
        <td> ${currAccount.getEMail()} </td>
        <td> ${currAccount.getAdditionalInfo()} </td>
        <td id="data"> ${currAccount.getCreationDate()} </td>
        <td>

          <a type="button" class="btn btn-primary"
             href="<c:url value="/view-account?id=${currAccount.id}"/>">View</a>

          <a type="button" class="btn btn-primary"
             href="<c:url value="/edit-account?id=${currAccount.id}"/>">Edit</a>

          <a type="button" class="btn btn-warning"
             href="<c:url value="/delete-account?id=${currAccount.id}"/>"
             onclick="if (!(confirm('Are you sure you want to delete this account?')))
                 return false">
            Delete</a>

        </td>
      </tr>

    </c:forEach>

  </table>


</div>


<form action="other-jsp/home.jsp">
  <input type="submit" value="back home">
</form>


<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
  src="webjars/bootstrap-datepicker/1.0.1/js/bootstrap-datepicker.js"></script>

</body>
</html>

<%--<%@ include file="common/footer.jspf" %>--%>

