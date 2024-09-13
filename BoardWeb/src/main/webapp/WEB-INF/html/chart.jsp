<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
    	  
    	let dataArray = [['작성자', '작성글 숫자']];
    	
    	// [13-10] Ajax 호출
    	// 여기서 Fetch는 비동기이므로, 호출만 되고 실행은 나중에 되는 특성이 있음.
    	
    	fetch('chart.do')
    		.then(resolve => resolve.json())
    		.then(result => {
    			console.log("▼▼▼ ===== [chart.do] result ===== ▼▼▼");
    			console.log(result);
    			
    			result.forEach(member => {
    				dataArray.push([member.memberName, member.count]);		// ['user01', 30] 의 형태로 각 행을 dataArray에 음
    			})
    			
    			
    			// [ ['작업', '시간'], ['일', 8], []]
    			var data = google.visualization.arrayToDataTable(dataArray);
    	        var options = {
    	          title: '게시글 작성자별 작성글 수'
    	        };
    	        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
    	        chart.draw(data, options);
    			
    		})
    		.catch(err => {
    			console.log("▼▼▼ ===== [chart.do] err ===== ▼▼▼");
    			console.log(err);	
    		})

      }
    </script>
  </head>
  <body>
    <div id="piechart" style="width: 900px; height: 500px;"></div>
  </body>
</html>
