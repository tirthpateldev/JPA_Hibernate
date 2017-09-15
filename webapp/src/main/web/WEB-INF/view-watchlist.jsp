<%--
  Created by IntelliJ IDEA.
  User: denisigoshev
  Date: 7/28/2017
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
<table>

  <c:forEach var="theStock" items="${stockSymbolsList}">
    <tr>
      <td>Stock : ${theStock}  </td>
      <td> <a type="button" class="btn btn-primary"
              href="<c:url value="/view-data?watchListId=${watchListId}&stockSymbol=${theStock}"/>">View</a></td>
    </tr>

  </c:forEach>

</table>
</div>
<%@ include file="common/footer.jspf" %>
