<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<script src="${pageContext.request.contextPath}/js/loader.js"></script>
<!--     <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script> -->
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart', 'bar']});
      google.charts.setOnLoadCallback(drawStuff);
      console.log('${chartData}');
      
      
      var header = [{'test':'zzz','test':'zzz','test':'zzz'}];
      var chardata = '${chartData}';
      
      const objParam = JSON.parse(chardata);
      
      
      contactArray = [];
      header = ['2024년 매출', '매출액', '관광객 수']
      
      contactArray.push(header);
      for (var i = 0; i < objParam.length; i++) {
		  
    	  contact = [];
    	  
    	  contact.push(objParam[i].contentsTypeName);
    	  contact.push(objParam[i].totalSales);
    	  contact.push(objParam[i].visitorCount);
    	  contactArray.push(contact);
  	  }
      
      console.log(contactArray);
      
      function drawStuff() {
        var button = document.getElementById('change-chart');
        var chartDiv = document.getElementById('chart_div');
        
        var data = google.visualization.arrayToDataTable(contactArray);

        var materialOptions = {
          width: 900,
          chart: {
            title: 'J가 되고싶은 P 매출현황',
            subtitle: '2024년 분야별 매출, 관광객 분석'
          },
          series: {
            0: { axis: 'distance' }, 
            1: { axis: 'brightness' } 
          },
          axes: {
            y: {
              distance: {label: '매출액'}, 
              brightness: {side: 'right', label: '관광객'} 
            }
          }
        };

        var classicOptions = {
          width: 900,
          series: {
            0: {targetAxisIndex: 0},
            1: {targetAxisIndex: 1}
          },
          title: '2024년 분야별 매출, 관광객 분석!!!',
          vAxes: {
            0: {title: '매출액'},
            1: {title: '관광객 수'}
          }
        };

        function drawMaterialChart() {
          var materialChart = new google.charts.Bar(chartDiv);
          materialChart.draw(data, google.charts.Bar.convertOptions(materialOptions));
          button.innerText = 'Change to Classic';
          button.onclick = drawClassicChart;
        }

        function drawClassicChart() {
          var classicChart = new google.visualization.ColumnChart(chartDiv);
          classicChart.draw(data, classicOptions);
          button.innerText = 'Change to Material';
          button.onclick = drawMaterialChart;
        }

        drawMaterialChart();
      }
    </script>
    
    <style type="text/css">
    	.chatStyle {
    		display: flex; 
    		flex-direction: column; 
    		align-items: center;
    		height: 450px;
    	}
    </style>
  </head>
  <body>
    <br><br>
    <div id="chart_div" class="chatStyle"></div>
  </body>
</html>