<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<h1>Spring MVC file upload example</h1>

<%--<form:form method="post" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">--%>
<%--<form method="post" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">--%>
<form:form method="post" modelAttribute="account" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
  <input type="file" name="file"/><br/><br/>
  <input type="submit" value="Submit"/>
</form:form>

</body>
</html>