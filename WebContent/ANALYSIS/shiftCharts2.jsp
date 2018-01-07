<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>shiftCharts2</title>
<style>

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
 	.my-valid-class{
		color:#3a51e8;
	}
	
	.my-error-class{
		color:red;
	}
  
</style>
</head>
<body>
<div class="titlelist">來客數分析</div>
	<div class="col-lg-12 main">
	<p class="distance">
		<form method="post" class="oneshift form-horizontal style-form" id="people_analysis">
		<div class="form-group">
			<label class="col-lg-1 col-lg-offset-2 control-label">日期(起):</label>
			<div class="col-lg-2">
				<input type="Date" name="date3">
			</div>
			
			<label class="col-lg-1 control-label">日期(訖):</label>
			<div class="col-lg-2">
				<input type="Date" name="date4">
			</div>
			<div class="col-lg-2">

				<input type="button" value="早班" name='C' class="btn btn-theme02">
				<input type="button" value="晚班" name='D' class="btn btn-theme02">
			</div>

		</div>
		</form>
	<p class="distance">
<!-- 早班 -->
	<div id="C_charts"></div>
	<p class="distance">
<!-- 晚班 -->
	<div id="D_charts"></div>
	</div>
</body>
<script>
//驗證
$("#people_analysis").validate({
	errorClass:"my-error-class",
	validClass:"my-valid-class",
	
	rules:{
		date3:{required:true},
		date4:{compareDate:$("input[name='date3']"),required:true},
	},
	messages:{
		date3:{
			required:"【請輸入起始日】"
		},
		date4:{
			required:"【請輸入結束日】",
			compareDate:"【結束日必须大於起始日】"
		}
	}				
})
$(document).ready(function() {
	$(":button").click(function(){ 
		if($("#people_analysis").valid()){
			if('C'==$(this).attr('name')){
//抓資料			
				$.ajax({
					"type" : "post",
					"url" : "alljson.do",
					"data" : {
						shift:"A",
						date1:$('input[name="date3"]').val(),
						date2:$('input[name="date4"]').val()						
					},
//成功的話執行以下					
					"success" : function(data) {
//新增空陣列來裝各個資料
						var people_sum =[];
						var day_data =[];
						$.each($.parseJSON(data), function() {
							var Date = this.Date;
							var deal_num = this.deal_num;
							day_data.push(Date);
							people_sum.push(deal_num);
							})
//圖表塞入div_C
							$("#C_charts").highcharts({                   
						        chart: {
						            type: 'line'                         
						        },
						        title: {
						            text: '每日早班來客數'     
						        },
						        subtitle: {
				 				  text: '範圍 :'+ $('input[name="date3"]').val() +'~' + $('input[name="date4"]').val()
				 				},
						        xAxis: {
						        	title: {
					 				      text: '時間 '
					 				},
					 				categories: day_data
				 				},
						        yAxis: {
						        	title: {
					 				      text: '人 '
					 				   },
					 				   plotLines: [{
					 				      value: 0,
					 				      width: 1,
					 				      color: '#808080'
					 				   }]
						        },
								tooltip: {
				 				   valueSuffix: '人'
				 				},
								legend: {
				 				   layout: 'vertical',
				 				   align: 'right',
				 				   verticalAlign: 'middle',
				 				   borderWidth: 0
				 				},
				 				credits:{
				 					enabled:false	
				 				},
						        series: [                               
						           {
			 		                  name: '來客數',
			 		                  data:people_sum
			 		               } 	 
				               ]
						    });
					}
				});
			}
			if('D'==$(this).attr('name')){
//抓資料			
				$.ajax({
					"type" : "post",
					"url" : "alljson.do",
					"data" : {
						shift:"B",
						date1:$('input[name="date3"]').val(),
						date2:$('input[name="date4"]').val()						
					},
//成功的話執行以下					
					"success" : function(data) {
//新增空陣列來裝各個資料
						var people_sum =[];
						var day_data =[];
						$.each($.parseJSON(data), function() {
							var Date = this.Date;
							var deal_num = this.deal_num;
							day_data.push(Date);
							people_sum.push(deal_num);

							})
//圖表塞入div_D
							$("#D_charts").highcharts({                   
						        chart: {
						            type: 'line'                         
						        },
						        title: {
						            text: '每日晚班來客數'     
						        },
						        subtitle: {
				 				  text: '範圍 :'+ $('input[name="date1"]').val() +'~' + $('input[name="date2"]').val()
				 				},
						        xAxis: {
						        	title: {
					 				      text: '時間 '
					 				},
					 				categories: day_data
				 				},
						        yAxis: {
						        	title: {
					 				      text: '人 '
					 				   },
					 				   plotLines: [{
					 				      value: 0,
					 				      width: 1,
					 				      color: '#808080'
					 				   }]
						        },
								tooltip: {
				 				   valueSuffix: '人'
				 				},
								legend: {
				 				   layout: 'vertical',
				 				   align: 'right',
				 				   verticalAlign: 'middle',
				 				   borderWidth: 0
				 				},
				 				credits:{
				 					enabled:false	
				 				},
						        series: [                               
						           {
			 		                  name: '來客數',
			 		                  data:people_sum
			 		               }, 
 
				               ]
						    });
					}
				});
			}
		}
	})				
})
</script>	
</html>