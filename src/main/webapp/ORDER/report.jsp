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

<title>折線圖</title>

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

<select id="sel">

	<option >請選擇月份</option>
	<option value="9">9月</option>
	<option value="10">10月</option>
	<option value="11">11月</option>
</select>
<div id="container" style="min-width: 310px; height: 400px;">

</div>
</center>
<form action="getdatprice.do">
	
</form>
</body>

<script type="text/javascript">
    
	$(function () {
		$("#sel").change(function() {
			var mon=$("#sel").val();
			
			//抓資料			
			$.ajax({
				"type" : "post",
				"url" : "getOrdPrice.do",
				"data" : {"mon":mon},
	//成功的話執行以下					
				"success" : function(data) {
	//新增空陣列來裝各個資料
					var Date =[];
					var Price =[];
					$.each($.parseJSON(data), function() {
						var Date1 = this.Date;
						var Price1 = this.Price;
						
						Date.push(Date1);
						Price.push(Price1);
						})
	//圖表塞入div_A
						$('#container').highcharts({
					        chart: {
					            type: 'column'
					        },
					        title: {
					            text: mon+'月份營業額'
					        },
					        xAxis: {
					            categories: Date
					        },
					        yAxis: {
					            title: {
					                text: '營業額'
					            }
					        },
					        credits:{
			 					enabled:false	
			 				},
					        series: [{
					            name: mon+'月',
					            data: Price
					        }]
						})
			}
		})
		});

});


</script>
</html>