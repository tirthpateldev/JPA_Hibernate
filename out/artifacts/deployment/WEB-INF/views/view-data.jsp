<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Fintech Application</title>

  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

  <script src="http://code.highcharts.com/highcharts.js"></script>

  <!--Local copy of jQuery js file
    <script src="js/jquery.min.js"></script> -->

  <!-- Local copy of Highcharts js file
    <script src="js/highcharts.js"></script> -->


  <script type="text/javascript">
      $(function () {
          $('#container').highcharts({
              chart: {
                  type: 'bar'
              },
              title: {
                  text: 'Fruit Consumption'
              },
              xAxis: {
                  categories: ['Apples', 'Bananas', 'Oranges']
              },
              yAxis: {
                  title: {
                      text: 'Fruit eaten'
                  }
              },
              series: [{
                  name: 'Julie',
                  data: [1, 0, 4]
              }, {
                  name: 'James',
                  data: [5, 7, 3]
              }],
          });
      });
  </script>
</head>
<body>
<div id="container" style="width:100%; height: 400px; margin: 0 auto"></div>

<div class="container">
  <table class="table table-striped">

    <tr>
      <th>open</th>
      <th>high</th>
      <th>low</th>
      <th>close</th>
      <th>volume</th>
      <th>time</th>

    </tr>

    <c:forEach var="bar" items="${barData}">
      <tr>
        <td> ${bar.getOpen()} </td>
        <td> ${bar.getHigh()} </td>
        <td> ${bar.getLow()} </td>
        <td> ${bar.getClose()} </td>
        <td> ${bar.getVolume()} </td>
        <td> ${bar.getDateTime()} </td>

      </tr>
    </c:forEach>

  </table>


</div>


</body>
</html>
