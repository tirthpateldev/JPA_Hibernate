<%--
  Created by IntelliJ IDEA.
  User: denisigoshev
  Date: 7/16/2017
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>@RequestMapping and RequestParam test bed</title>
    <style>
        input[type=text] {
            padding: 10px;
            margin: 10px;
            font-size: 20px;
        }

        input [type =submit ] {
            border: none;
            background-color: #4CAF50;
            color: white;
            padding: 16px;
            margin: 16px;
            cursor: pointer;
            font-size: 20px;
        }

        h2 {
            color: #08298A;
            text-align: center;
        }
    </style>
</head>
<body>
<div align="left">
    <h2 align="center"> Hello , Welcome to the @RequentMapping & RequestParam Test Bed</h2>
    <hr/>
    <form action="test1">
        <h3>test 1: Testing @RequestParam wo explicit attributes </h3>
        <label id="organization-name">Organization Name</label>
        <input type="text" name="orgname" placeholder="Enter organization name" size="40">
        <input type="submit" value="Submit"/>
    </form>
    <br/><br>
    <form action="test2">
        <h3>test 2: Testing @RequestMapping 'method' attributes </h3>
        <label id="organization-name">Organization Name</label>
        <input type="text" name="orgname" placeholder="Enter organization name" size="40">
        <input type="submit" value="Submit"/>
    </form>
    <form action="test3">
        <h3>test 3: Testing @RequestMapping fallback feature </h3>
        <label id="organization-name">Organization Name</label>
        <input type="submit" value="Submit"/>
    </form>
    <br/><br>
    <form action="test4">
        <h3>test 4: Testing @RequestParam 'default value' attributes </h3>
        <label id="organization-name">Organization Name</label>
        <input type="text" name="orgname" placeholder="Enter organization name" size="40">
        <input type="submit" value="Submit"/>
    </form>

    <br/><br>
    <form action="test5">
        <h3>test 5: Testing @RequestParam wo  'name' or 'value' attribute </h3>
        <label id="organization-name">Organization Name</label>
        <input type="text" name="orgname" placeholder="Enter organization name" size="40">
        <input type="submit" value="Submit"/>
    </form>
</div>


</body>
</html>
