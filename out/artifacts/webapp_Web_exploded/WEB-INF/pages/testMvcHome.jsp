
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
  <title>Mvc Home</title>
  <link rel="stylesheet" href="css/style.css">
  <style>
    body{
      background: url(img/bg.jpg);
      background-size: 100% 100%;
    }
    h2 {
      color: #08298A;
      text-align: center;
    }

  </style>
  <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
</head>
<body>
<h2> MVC home </h2>


<div class="container"><br/>
  <div class="alert alert-success">
    <a href="${contextPath}/list-accounts" class="close" data-dismiss="alert" aria-label="close">x</a>
    <strong>Success!</strong> It is working as we expected.
  </div>





<div style="text-align: center">
  <a href="${contextPath}/list-accounts" style="font-size: 20px"> Click here
    to go to account name page</a>
</div>

</div>

</body>
</html>

