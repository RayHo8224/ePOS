<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>report</title>
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

	.distance{
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
 	.navbar-default .navbar-nav > .active > a, .navbar-default .navbar-nav > .active > a:hover, .navbar-default .navbar-nav > .active > a:focus{
 		background: #fbe4c3;
 	}
  
</style>

</head>
<body>
<div class="titlelist">月總營業額分析</div>
	<div class="col-lg-12 main">
	<p class="distance">
		<form method="post" class="form-horizontal" role="form">
			<div class="form-group">					
				<label id="label" class="col-lg-1 col-lg-offset-2 control-label">月份:　</label>
				<div class="col-lg-2">
					<select id="sel" class="form-control">
						<option >請選擇月份</option>
						<option value="9">9月</option>
						<option value="10">10月</option>
						<option value="11">11月</option>
					</select>
				</div>
				<div class="col-lg-７"></div>
			</div>
		</form>
	<p class="distance">

	<div id="ten"></div>
	<p class="distance">
</div>

<script type="text/javascript">
$("#sel").change(function(){ 
// 	if('open'==$(this).attr('name')){   
		
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
					$('#ten').highcharts({
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
	
})

</script>

</body>
</html>