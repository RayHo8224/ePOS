<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>全部詢價單資料</title>

<link href="<c:url value="../resources/css/bootstrap.css" />"
	rel="stylesheet">


</head>
<style>

</style>
<body>
	<section id="container"> <section class="wrapper">
	<div class="row mt">

		<nav class="nav navbar-default">
		<div class="container-fluid"
			style="float: left;">
			<ul class="nav navbar-nav"
				style="float: left;">
				<li><a id="bop1" target="selectOfY.do" href="javascript: void(0)">新增進貨單</a></li>
				<li><a id="bop2" target="SelectBOP0.jsp" href="javascript: void(0)">單筆查詢</a></li>
				<li><a id="bop3" target="getAllBop.do" href="javascript: void(0)">全部查詢</a></li>
				<li><a id="bop4" target="SelectbyDate.jsp" href="javascript: void(0)">依日期查詢</a></li>
				<li><a id="bop5" target="selectOfN.do" href="javascript: void(0)">審核</a></li>
				<li><a id="bop6" target="selectOfY2.do" href="javascript: void(0)">到貨確認</a></li>
				<li style="background-color: rgba(221, 15, 15, 0.8);"><a
					id="bop7" style="color: white;">單月進貨金額</a>
			</ul>
		</div>
		</nav>
		<div
			style="background-image: url(../resources/img/anonymous-dark.jpg); background-size: cover;">
			<div
				style="background-color: rgba(0, 0, 0, 0.1); position: relativve; height: 750px; overflow: auto;">
				<div align=center>

					<div style="position: absolute; right: 10px; padding: 20px;">
						<select id="month"
							style="width: 130px; background-color: #00000; color: black; border: 0px; font-size: 18px;"><c:forEach
								var='s1' items='${set }' varStatus='status'>
								<option>${s1 }</option>
							</c:forEach></select>
					</div>
					<div id="container2" 
						style="height: 700px; width: 1050px; margin:0 auto;top:15%;left:26%;position:absolute; "></div>
					<div id="costdiv" style="padding:10px;background-color: rgba(0, 0, 0, 0.5);color:white;font-size:24px;position:absolute;right:20px; bottom:120px;">
						總金額：<span id="cost"></span>元
						</div>

				</div>
			</div>
		</div>
		<script>
			$(function() {
				$('#month').val("");
				
				$('#costdiv').hide();

				$('#month').change(function() {
					var url = "getRatio.do";
					var cost;
					var bop_month0 = $(this).val();
					var qy = bop_month0.substring(0,4);
					var qm = bop_month0.substring(7,9);
					var bop_month = qy + qm;
// 					alert(bop_month);
					var url0 = "getMonthCost.do";
					$.ajax({
						type : "POST",
						url : url0,
						data : {"bop_month":bop_month},
						success : function(data) {
							cost = data;
							$('#costdiv').fadeOut(200,function(){
								$('#cost').text(data);
							});
							$('#costdiv').fadeIn(1300);
						}
					})

					$.ajax({
						type : "POST",
						url : url,
						data : {"bop_month":bop_month},
						dataType : "json",
						"success" : function(data) {
// 							alert(data);
// 							var jsonObj = JSON.parse(data);
// 							alert(jsonObj);
// 							var d = JSON.stringify(data);
// 							alert(d);
// 							alert(data.length);
// 							var name = Array();
// 							var y = Array();
// 							var dataArrayFinal = Array();
// 							for (i=0;i<data.length;i++){
// 								name[i] = data[i].name;
// 								y[i] = data[i].y;
// 							}
							
// 							for(j=0;j<name.length;j++){
// 								var temp = new Array(name[j],y[j]);
// 								dataArrayFinal[j] = temp;
// 							}
// 							alert(dataArrayFinal);
// 							alert(jsonObj.length);
// 							var processedJson = new Array();
// 							alert(jsonObj[0]);
// 							$.map(JSONStr, function(obj, i){
// 								processedJson.push([obj.name, parseInt(obj.y)]);
// 							})
// 							alert(processedJson);
							
							Highcharts.chart('container2', {
						        chart: {
						            backgroundColor: null,
						            plotBorderWidth: null,
						            plotShadow: false,
						            type: 'pie'
						        },
						        title: {
						            text: ''
						        },
						        tooltip: {
						            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
						        },
						        plotOptions: {
						            pie: {
						                allowPointSelect: true,
						                cursor: 'pointer',
						                dataLabels: {
						                    enabled: true,
						                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
						                    style: {
						                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'white',
						                        fontSize: '24px'
						                    }
						                }
						            }
						        },
						        series: [{
						            name: 'Brands',
						            colorByPoint: true,
						            data: data
						        }]
						    });
						}
					})
				})

				$('#bop1').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
						$('#main-content').html(data);
					})
				})

				$('#bop2').on('click', function() {
					var t1 = $(this).attr('target');
					$.get(t1, function(data) {
						$('#main-content').html(data);
					})
				})

				$('#bop3').on('click', function() {
					var t1 = $(this).attr('target');
					$.ajax({
						type : "post",
						url : t1,
						success : function(data) {
							$('#main-content').html(data);
						}
					})
				})
				$('#bop4').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
						$('#main-content').html(data);
					})
				})
				$('#bop5').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
						$('#main-content').html(data);
					})
				})
				$('#bop6').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
						$('#main-content').html(data);
					})
				})
			})
// 			$("#table1").dataTable();
		</script>
</body>
</html>