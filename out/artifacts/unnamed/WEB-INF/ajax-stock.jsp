<!DOCTYPE html>
<html>
<head>
  <title>Live Stock Information</title>
  <style>
    #container {
      width: 500px;
      height: 500px;
      border: 2px solid black;
      border-radius: 10px;
      margin: 50px auto;
      padding: 20px;
    }

    h1 {
      text-align: center;
      color: royalblue;
    }

    table {
      width: 400px;
      height: 400px;
      margin: 0px auto;
      padding: 10px;
      background: lightsteelblue;
      border: 1px solid steelblue;
    }

    tr, td {
      padding: 10px;
      width: 50px;
      border: 1px solid aliceblue;
    }

    td {
      text-align: center;
      font-size: 22px;
    }
  </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script>
      var temp = [];
      $(document).ready(function () {
          stockInformation();
          setInterval(stockInformation, 5000);
      });
/*todo css and js in separate file
* service side market data should be
* */
      function stockInformation() {
          $.ajax({
              url: "http://finance.google.com/finance/info?client=ig&" +
              "q=NASDAQ:AAPL,FB,MSFT,NFLX,CSCO,GOOG,TSLA,NVDA",
              dataType: "jsonp",
              jsonp: "callback",
              jsonpCallback: "quote"
          });
          var i = 0;
          var j = 0;
          var status = "";
          quote = function (data) {
              var output = "<table>"
              $.each(data, function (key, value) {
                  if (value.l_cur > temp[j])
                      status = "<td style=color:green>Up</td>";
                  else if (value.l_cur < temp[j])
                      status = "<td style=color:red>Down</td>";
                  else
                      status = "<td>Same</td>";
                  j++;
                  output += "<tr><td>" + value.t + "</td><td>" + value.l_cur + "</td>"
                      + status + "</tr>";
                  temp[i] = value.l_cur;
                  i++;
              })
              output += "</table>";
              $("#result").html(output);
          }
      }
  </script>
</head>
<body>
<div id="container">
  <h1>NYSE Live</h1>
  <div id="result"></div>
</div>
</body>
</html>