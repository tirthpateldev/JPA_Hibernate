
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Location details</title>

    <link rel = "stylesheet" type="text/css" href="../others/style1.css"/>
    <link rel = "stylesheet" type="submit/css" href="../others/style1.css"/>
  </head>
  <body>
    
    <h2 style="color: #DF0101">
      <c:out value="${greeting}"></c:out>
    </h2>
    <hr/>
    <h2>Submit your location for a list of Organizations</h2>
    <div style="text-align: center">
      <form action="listOrgs">
        <input type="text" name="locationName"><br/>
          <input type="submit" name="Get organizations"><br/>
          </form>
        </div>
      </body>
    </html>
