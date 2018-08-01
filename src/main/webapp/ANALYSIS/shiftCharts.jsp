<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>shiftCharts</title>
<style>
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

	.my-valid-class{
		color:#3a51e8;
	}
	
	.my-error-class{
		color:red;
	}

</style>
</head>
<body>
<div class="titlelist">營業額分析</div>
	<div class="col-lg-12 main">
	<p class="distance">
		<form method="post" action="alljson.do" class="oneshift form-horizontal style-form" id="money_analysis">
		<div class="form-group">
			<label class="col-lg-1 col-lg-offset-2 control-label">日期(起):</label>
			<div class="col-lg-2">
				<input type="Date" name="date1">
			</div>
			
			<label class="col-lg-1 control-label">日期(訖):</label>
			<div class="col-lg-2">
				<input type="Date" name="date2">
			</div>
			<div class="col-lg-2">

				<input type="button" value="早班" name='A' class="btn btn-theme02">
				<input type="button" value="晚班" name='B' class="btn btn-theme02">
			</div>

		</div>
		</form>
	<p class="distance">
<!-- 早班 -->
	<div id="A_charts"></div>
	<p class="distance">
<!-- 晚班 -->
	<div id="B_charts"></div>
	</div>
</body>
<script>
//驗證
	$("#money_analysis").validate({
		errorClass:"my-error-class",
		validClass:"my-valid-class",
		
		rules:{
			date1:{required:true},
			date2:{compareDate:$("input[name='date1']"),required:true},
		},
		messages:{
			date1:{
				required:"【請輸入起始日】"
			},
			date2:{
				required:"【請輸入結束日】",
				compareDate:"【結束日必须大於起始日】"
			}
		}				
	})
$(document).ready(function() {

	$(":button").click(function(){ 
		if($("#money_analysis").valid()){
			if('A'==$(this).attr('name')){
//抓資料			
				$.ajax({
					"type" : "post",
					"url" : "alljson.do",
					"data" : {
						shift:"A",
						date1:$('input[name="date1"]').val(),
						date2:$('input[name="date2"]').val()						
					},
//成功的話執行以下					
					"success" : function(data) {
//新增空陣列來裝各個資料
						var day_data =[];
						var cash_data =[];
						var A_deal_sum=[];
						var A_discount=[];
						$.each($.parseJSON(data), function() {
							var Date = this.Date;
							var cash = this.cash;
							var deal_sum = this.deal_sum;
							var discount = this.discount;
// 							var shift_sum = this.shift_sum;
							day_data.push(Date);
							cash_data.push(cash);
							A_deal_sum.push(deal_sum);
							A_discount.push(discount);
							})
//圖表塞入div_A
							$("#A_charts").highcharts({                   
						        chart: {
						            type: 'column'                         
						        },
						        title: {
						            text: '每日早班營業表'     
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
					 				      text: '金額 '
					 				   },
					 				   plotLines: [{
					 				      value: 0,
					 				      width: 1,
					 				      color: '#808080'
					 				   }]
						        },
								tooltip: {
				 				   valueSuffix: '$'
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
			 		                  name: '營業額',
			 		                  data:A_deal_sum
			 		               }, 
			 		               {
			 		                  name: '折讓',
			 		                  data: A_discount
			 		               }, 
			 		               {
			 		                  name: '現金',
			 		                  data: cash_data
			 		               }, 
				               ]
						    });
					}
				});
			}
			//A end
			if('B'==$(this).attr('name')){
//抓資料			
				$.ajax({
					"type" : "post",
					"url" : "alljson.do",
					"data" : {
						shift:"B",
						date1:$('input[name="date1"]').val(),
						date2:$('input[name="date2"]').val()						
					},
//成功的話執行以下					
					"success" : function(data) {
//新增空陣列來裝各個資料
						var day_data =[];
						var cash_data =[];
						var A_deal_sum=[];
						var A_discount=[];
						$.each($.parseJSON(data), function() {
							var Date = this.Date;
							var cash = this.cash;
							var deal_sum = this.deal_sum;
							var discount = this.discount;
// 							var shift_sum = this.shift_sum;
							day_data.push(Date);
							cash_data.push(cash);
							A_deal_sum.push(deal_sum);
							A_discount.push(discount);
						})
//圖表塞入div_B
							$("#B_charts").highcharts({                   
						        chart: {
						            type: 'column'                         
						        },
						        title: {
						            text: '每日晚班營業表'     
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
					 				      text: '金額 '
					 				   },
					 				   plotLines: [{
					 				      value: 0,
					 				      width: 1,
					 				      color: '#808080'
					 				   }]
						        },
								tooltip: {
				 				   valueSuffix: '$'
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
			 		                  name: '營業額',
			 		                  data:A_deal_sum
			 		               }, 
			 		               {
			 		                  name: '折讓',
			 		                  data: A_discount
			 		               }, 
			 		               {
			 		                  name: '現金',
			 		                  data: cash_data
			 		               }, 
				               ]
						    });
					}
				});
			}
				//B end
		}
	})
})	
</script>	
</html>