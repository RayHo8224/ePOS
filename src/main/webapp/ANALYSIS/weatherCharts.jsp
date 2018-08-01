<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>weatherCharts</title>
<style>
	.navbar-default .navbar-nav > li > a{
		color:#56ad7b;
	}

/* nav */
	.navbar-default{
		background: #E6F9AF;
		border-color:#E6F9AF;
		border-radius: 8px;
	}
/* background */
 	.main{ 
  		height: 800px;  
 		border-radius: 8px; 
 		background:	#A0DBB9; 
 	}
/*  title	 */
 	.titlelist {
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #384E77;
		padding-left: 10px;
		font-size: 23px;
		border-radius: 2px;
	}

	.distance {
		margin: 20px;
	}
	
	.form-horizontal .control-label { 
	     text-align: right; 
	 }
	 
	 .btn-theme02 {
	    color: #fff;
	    background-color: #229abd;
	    border-color: #31535d;
	}
  
</style>

</head>
<body>
<div class="titlelist">商品排行榜分析</div>
	<div class="col-lg-12 main">
	<p class="distance">
		<form method="post" action="alljson.do" class="oneshift form-horizontal style-form">
		<div class="form-group">		
			<label class="col-lg-1 col-lg-offset-2 control-label">天氣:</label>
			<div class="col-lg-2">		
				<input type="button" name="rain" value="雨天"  class="btn btn-theme02">
				<input type="button" name="sunny" value="晴天"  class="btn btn-theme02">
			</div>

		</div>
		</form>
	<p class="distance">

	<div id="rainy"></div>
	<p class="distance">

	<div id="sunny"></div>
	</div>
	
<script>
$(":button").click(function(){ 
	if('rain'==$(this).attr('name')){
		
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
					$('#rainy').highcharts({
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
		}
	if('sunny'==$(this).attr('name')){
		
		//抓資料			
				$.ajax({
					"type" : "post",
					"url" : "getOrdByWeather.do",
					"data" : {
						weather:'晴'
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
							$('#sunny').highcharts({
						        chart: {
						            type: 'line'
						        },
						        title: {
						            text: '晴天的商品排行'
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
				}
	})

</script>
</body>
</html>