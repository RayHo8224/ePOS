<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>
<script type="text/javascript">
    
	$(function () {
		
//抓資料			
		$.ajax({
			"type" : "post",
			"url" : "getOrdByWeather.do",
			"data" : {
				weather:'雨'
			},
//成功的話執行以下					
			"success" : function(data) {
//新增空陣列來裝各個資料
				var pro_name =[];
				var pro_quantity =[];
				$.each($.parseJSON(data), function() {
					var prod_name = this.prod_name;
					var prod_quantity = this.prod_quantity;
					
					pro_name.push(prod_name);
					pro_quantity.push(prod_quantity);
					})
//圖表塞入div_A
					$('#container').highcharts({
				        chart: {
				            type: 'line'
				        },
				        title: {
				            text: '雨天的商品排行'
				        },
				        xAxis: {
				            categories: pro_name
				        },
				        yAxis: {
				            title: {
				                text: '數量'
				            }
				        },
				        credits:{
		 					enabled:false	
		 				},
				        series: [
				                 {
				            name: '品項',
				            data: pro_quantity
				        }
				        ]
					})
		}
	})
});


</script>
<title>weather</title>

<style type="text/css">

body{
	background: gray;
}

#container{
	margin-top: 200px;
}

</style>
</head>
<body>
<center>
<div id="container" style="min-width: 310px; height: 400px;"></div>
</center>

</body>
</html>