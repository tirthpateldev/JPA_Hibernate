<%--
  Created by IntelliJ IDEA.
  User: denisigoshev
  Date: 7/14/2017
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Organization home page </title>
</head>
<body>

<h2 align="center" style="color: #DF0101"> Welcome! <c:out value="${greeting}"></c:out></h2>
<hr/>
<h1 align="center">Organization details</h1>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div style="text-align: center">
    <a href="${contextPath}/location" style="font-size: 30px">Click here to get organization
        detail for your location</a>
</div>

</body>
</html>
