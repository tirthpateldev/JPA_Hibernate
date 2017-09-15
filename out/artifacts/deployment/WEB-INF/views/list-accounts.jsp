<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>


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

<%@ include file="common/footer.jspf" %>

