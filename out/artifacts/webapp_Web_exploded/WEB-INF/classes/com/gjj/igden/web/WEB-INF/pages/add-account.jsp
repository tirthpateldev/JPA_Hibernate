<%--<!DOCTYPE html>
<html  xmlns:th="http://www.thymeleaf.org>

<head>
  <title>new account registration page</title>
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
  <script type="text/javascript"
          src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>
  <script type="text/javascript"
          src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>

</head>

<body>--%>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
  <div class="row">
    <div class="col-sm-8 col-sm-offset-2">
      <div class="page-header">
        <div class="alert alert-info" role="alert">
          <h4>Please sign up :) Thx </h4>
          <ul>
            <li><a href="https://getbootstrap.com/" class="alert-link">Bootstrap home
              project</a>.
            </li>
          </ul>
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title">It will take just 2 minutes :) </h3>
        </div>
        <div class="panel-body">
          <form action="processAddAccount" method="post" id="signupForm" class="form-horizontal">
            <div class="form-group">
              <label class="col-sm-4 control-label" for="username1">Username</label>
              <div class="col-sm-5">
                <input type="text" class="form-control" id="username13"
                       name="username1" placeholder="Username"/>
              </div>
            </div>
            <%--    <div class="form-group">
                  <label class="col-sm-4 control-label" for="email1">Email</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" id="email1" name="email1"
                           placeholder="Email"/>
                  </div>
                </div>--%>
            <div class="form-group">
              <label class="col-sm-4 control-label" for="password1">Password</label>
              <div class="col-sm-5">
                <input type="password" class="form-control" id="password1"
                       name="password1" placeholder="Password"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-4 control-label" for="confirm_password1">Confirm
                password</label>
              <div class="col-sm-5">
                <input type="password" class="form-control" id="confirm_password1"
                       name="confirm_password1" placeholder="Confirm password"/>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-5 col-sm-offset-4">
                <div class="checkbox">
                  <label>
                    <input type="checkbox" id="agree1" name="agree1"
                           value="agree"/>Please agree to our policy
                  </label>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-9 col-sm-offset-4">
                <%--<a type="button" class="btn btn-primary"
                   href="<c:url value="/add-account"/>">Sign up me</a>--%>
                <button type="submit" class="btn btn-primary" name="signup1"
                        value="Sign-up">Sign up
                </button>
              </div>
            </div>

          </form>

        </div>
      </div>
    </div>
  </div>
</div>

<div class="container">

  <form:form method="post" modelAttribute="account" id="signupForm1">
    <%--<form:hidden path="id"/>--%>
    <div class="form-group">
      <div class="col-sm-5">
        <label class="col-sm-4 control-label" for="username1">Account Name1112</label>
        <input type="text" name="username1" id="username1" class="form-control"
               placeholder="Username"/>
      </div>
    </div>

    <div class="form-group">
      <label class="col-sm-4 control-label" for="username111">Username667</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" id="username111"
               name="username111" placeholder="Username111"/>
      </div>
    </div>
    <button class="btn btn-success">Submit</button>
  </form:form>
</div>

<div class="container">

  <form:form method="post" modelAttribute="account" id="signupForm2">
    <%--<form:hidden path="id"/>--%>
    <fieldset class="form-group">
      <label path="accountName">Account Name</label>
      <input type="text" id="username12" class="form-control" required="required"/>
      <form:errors path="accountName" cssClass="text-warning"/>
    </fieldset>
    <fieldset class="form-group">
      <form:label path="eMail">eMail</form:label>
      <form:input path="eMail" type="text" id="password1" class="form-control"
                  required="required"/>
      <form:errors path="eMail" cssClass="text-warning"/>
    </fieldset>
    <fieldset class="form-group">
      <form:label path="additionalInfo">additionalInfo</form:label>
      <form:input path="additionalInfo" type="text" class="form-control"
                  required="required"/>
      <form:errors path="additionalInfo" cssClass="text-warning"/>
    </fieldset>
    <button class="btn btn-success">Submit</button>
  </form:form>
</div>

<%--<%@ include file="common/footer.jspf" %>
--%>

<%--</body>

</html>--%>

<%@ include file="common/footer-add-account.jspf" %>

<%@ include file="js/add-account-javascript.js" %>


<%--<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <title>Getting Started: Handling Form Submission</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h1>Form</h1>
<form action="#"  th:action="@{/greeting}" id="signupForm1"  th:object="${greeting}" method="post">
  <p>Id: <input type="text" th:field="*{id}"/></p>
  <p>Message: <input type="text" th:field="*{accountName}"/></p>
  <p><input type="submit" value="Submit"/> <input type="reset" value="Reset"/></p>
  <div class="form-group">
    <label class="col-sm-4 control-label" for="username1">Username</label>
    <div class="col-sm-5">
      <input path="additionalInfo" type="text" class="form-control" id="username1"
                  name="username1" placeholder="Username"/>
    </div>
  </div>
</form>
</body>
</html>--%>

<%--
<%@ include file="js/add-account-javascript.js" %>--%>
