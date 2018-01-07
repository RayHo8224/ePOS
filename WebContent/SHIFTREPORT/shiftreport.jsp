<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap core CSS -->
<link href="<c:url value="../resources/css/bootstrap.css" />" rel="stylesheet">
<!--external css-->
<link href="<c:url value="../resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<c:url value="../resources/lineicons/style.css" />">
<!-- Custom styles for this template -->
<link href="<c:url value="../resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="../resources/css/style-responsive.css" />" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.css" />
<title>班別報表維護</title>
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
 	
 	
 	#label{
		margin-left: 27%;
 	}
 	
 	#date{
		margin-left: 70px;
 	}
 	
 	#date1{
		margin-left: 30px;
 	}
 	
 	#ss{
 		margin-left: 30px;
 	}
  
/*   	#search{ */
/*  		margin-left: 30px; */
/*   	} */
</style>
</head>
<body>
	<section id="container">

		<!--header start-->
		<header class="header black-bg">
			<div class="sidebar-toggle-box">
				<div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
			</div>
			<!--logo start-->
			<a href="<%=request.getContextPath()%>/index.jsp" class="logo"><b>ePOS</b></a>
			<!--logo end-->

			<div class="top-menu">
				<ul class="nav pull-right top-menu">
					<li><a class="logout" href="<%=request.getContextPath()%>/LOGIN/logout.jsp">Logout</a>Hi , ${LoginOK.emp_name}</li>
				</ul>
			</div>
		</header>
		<!--header end-->

		<!--sidebar start-->
				<aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu" id="nav-accordion">

			<p class="centered">
				<a href="<%=request.getContextPath()%>/index.jsp"><img
					src="<c:url value="../resources/img/ui-sam.jpg"/>"
					class="img-circle" width="60"></a>
			</p>
			<h5 class="centered">ePOS</h5>

			<li class="mt"><a
				href="<%=request.getContextPath()%>/ORDER/order.jsp"> <i
					class="fa fa-dashboard"></i> <span>收銀結帳</span>
			</a></li>

			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-desktop"></i> <span>基本資料維護</span>
			</a>
				<ul class="sub">
					<li><a href="<%=request.getContextPath()%>/MEMBER/member.jsp">會員資料維護</a></li>
					<li><a
						href="<%=request.getContextPath()%>/EMPLOYEE/employee.jsp">員工資料維護</a></li>
					<li><a
						href="<%=request.getContextPath()%>/COMPANY/company.jsp">廠商資料維護</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-edit"></i> <span>進貨作業</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/REQUISITION/requisition.jsp">請購單維護</a></li>
					<li><a
						href="<%=request.getContextPath()%>/QUOTATION/quotation.jsp">詢價單維護</a></li>
					<li><a href="<%=request.getContextPath()%>/PURCHASE/pur.jsp">採購單維護</a></li>
					<li><a
						href="<%=request.getContextPath()%>/BILL_OF_PURCHASE/bop.jsp">進貨單維護</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="glyphicon glyphicon-shopping-cart"></i> <span>銷貨系統</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/VALUATION/ValuationList.jsp">報價單維護</a></li>
					<li><a href="<%=request.getContextPath()%>/ORDER/ordmain.jsp">訂單維護</a></li>
					<li><a
						href="<%=request.getContextPath()%>/SHIPMENTS/ShipmentsList.jsp">出貨單維護</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i class="fa fa-book"></i> <span>存貨作業</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/PRODUCT/product.jsp">商品管理</a></li>
					<li><a
						href="<%=request.getContextPath()%>/PROMOTING/promoting.jsp">促銷商品管理</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="glyphicon glyphicon-log-out"></i> <span>退貨作業</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/RETURNS/Return_Items.jsp">退貨品管理</a></li>
					<li><a
						href="<%=request.getContextPath()%>/RETURNS/ReturnList.jsp">退貨單管理</a></li>
					<li><a
						href="<%=request.getContextPath()%>/INVO/select_page.jsp">作廢發票管理</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;" class="active"> <i
					class="fa fa-usd"></i> <span>金流管理</span>
			</a>
				<ul class="sub">
					<li class="active"><a href="<%=request.getContextPath()%>/SHIFTREPORT/shiftreport.jsp">班別報表維護</a></li>
					<li><a href="<%=request.getContextPath()%>/COUPON/coupon.jsp">折價券</a></li>
					<li><a href="<%=request.getContextPath()%>/DISCOUNT/discount.jsp">折扣管理</a></li>
				</ul></li>
				<li class="sub-menu"><a href="javascript:;"> <i class="fa fa-bar-chart-o"></i> <span>報表分析</span>
				</a>
				<ul class="sub">
					<li><a href="<%=request.getContextPath()%>/ANALYSIS/analysis.jsp">銷售報表分析</a></li>
				</ul></li>
				<li class="sub-menu"><a href="javascript:;"> <i class="fa fa-users"></i> <span>顧客關係</span>
				</a>
					<ul class="sub">
						<li><a href="<%=request.getContextPath()%>/MAIL/Mail.jsp">寄送系統</a></li>
					</ul></li>
		</ul>
		<!-- sidebar menu end-->
	</div>
	</aside>
		<!--sidebar end--> 
		<section id="main-content"><section class="wrapper">

	<div class="row mt">
		<div class="col-lg-12">
			<div class="nav navbar-default">
				<div class="container-fluid">
					<ul class="nav navbar-nav">
						<li id="shi_search"><a data-toggle="tab" href="#search"><span class="glyphicon glyphicon-search"></span>搜尋</a></li>
						<li id="shi_cofg"><a id="c_cofg" target="shiftCofg.jsp" data-toggle="tab" href="#new"><span class="fa fa-bar-chart-o"></span>設定</a></li>
<!-- 						<li id="shi_analysis"><a id="analysis_shift" target="shiftCharts2.jsp" data-toggle="tab" href="#analysis"><span class="fa fa-bar-chart-o"></span>來客數分析</a></li> -->
						<li id="shi_rel"><a data-toggle="tab" href="#result" id="test"><span class="glyphicon glyphicon-list-alt"></span>查詢結果</a></li>
						<li><a data-toggle="tab" href="#print" class="print"><span class="glyphicon glyphicon-print"></span>列印</a></li>
					</ul>
				</div>
			</div>

			<div class="tab-content">
				<div id="search" class="tab-pane fade">
<%-- 					錯誤表列 --%>
<%-- 					<c:if test="${not empty param.message}"> --%>
<!-- 						<font color='red'>請修正以下錯誤: -->
<!-- 							<ul> -->
<%-- 								<c:forEach var="message" items="${param.message}"> --%>
<%-- 									<li>${message}</li> --%>
<%-- 								</c:forEach> --%>
<!-- 							</ul> -->
<!-- 						</font> -->
<%-- 					</c:if> --%>

					<div class="titlelist">查詢</div>
					<div class="col-lg-12 main">
						<p class="distance">

						<form method="post" action="getOneShiftre.do" class="oneshift form-horizontal style-form">
							<div class="form-group">
								<div>
									<label id="label">依班別報表搜尋</label>
									<label id="date" for="date1">日期</label> 
									<input type="date" name="Date" id="date1">

								<label id="ss" for="shift"> 班別　</label>
									<Select id="sss" name="shift" id="shift" style="width: 80px; height: 35px;">
										<option value="A">早班</option>
										<option value="B">晚班</option>
									</Select>
									<input id="search" type="button" value="搜尋" name='getOne' class="btn btn-theme02">
								</div>
							</div>
						</form>

						<form method="post" action="getAllShiftre.do" class="form-horizontal style-form">

							<div class="form-group">
								<label class="col-lg-offset-3 col-lg-1 control-label">查詢全部班別報表(修改)</label>
								<div class="col-lg-offset-3 col-lg-5">
									<input type="button" value="搜尋" name='getAll' class="btn btn-theme02">
								</div>
							</div>
						</form>

						<form method="post" action="getShiftreByDate.do" class="shiftbydate form-horizontal style-form">

							<div class="form-group">
								<label class="col-lg-offset-3 col-lg-1 control-label">依照日期查詢</label>
								<label class="col-lg-1 control-label" for="date2">日期</label>

								<div class="col-lg-1">
									<input type="date" name="Date" id="date2">
								</div>
								<div class="col-lg-offset-1 col-lg-4">
									<input type="button" value="搜尋" name='getOneByDate' class="btn btn-theme02">
								</div>
							</div>
						</form>
					</div>

				</div>
				<div id="new" class="tab-pane fade">
					<div class="insert-content main"></div>
				</div>
<!-- 				<div id="analysis" class="tab-pane fade"> -->
<!-- 					<div class="analysis-content main"></div> -->
<!-- 				</div> -->
				<div id="result" class="tab-pane fade">
					<div class="rul main"></div>
				</div>
			</div>
		</div>
	</div>
	</section> </section>
<input type="hidden" name="shift" value="${SHIFT}"><input type="hidden" name="emp_id" value="${LoginOK.emp_id}">
	</section>
<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script> 
	!window.jQuery && document.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
 	</script>

	<script type="text/javascript" src="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.js"></script>
   	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script src="<c:url value="../resources/js/jquery.validate.min.js" />"></script>
    <script src="<c:url value="../resources/js/bootstrap.min.js" />"></script>
	<script class="include" type="text/javascript" src="<c:url value="../resources/js/jquery.dcjqaccordion.2.7.js" />"></script>
    <script src="<c:url value="../resources/js/jquery.scrollTo.min.js" />"></script>
    <script src="<c:url value="../resources/js/jquery.nicescroll.js" />" type="text/javascript"></script>
	<!--common script for all pages-->
	<script src="<c:url value="../resources/js/common-scripts.js" />"></script>
	
		<script type="text/JavaScript">
	$(document).ready(function() {

// -------------------------------自動新增----------------------------------
// 		setInterval(function(){
// 			insertTime = new Date();
// 			var hour = insertTime.getHours();
// 			var minutes = insertTime.getMinutes();
// 			var seconds = insertTime.getSeconds();
// 			if((hour==14||hour==20)&&(minutes==0)&&(seconds==0)){
// 				alert(1);
// 				$.ajax({
// 					type : "post",
// 					url : "insertShiftre.do",
// 					data : {"shift":$("input[name='shift']").val(),
// 							"emp_id":$("input[name='emp_id']").val()
// 					}					
// 				});
// 			}else{

// 			}
// 		},1000);
		
		
// -------------------------------載入報表1.2----------------------------------
		$('#c_cofg').on('click', function() {							
				var insertWeb = $(this).attr('target');
				$.get(insertWeb, function(data) {
					$('.insert-content').html(data);
				})
					
		})
		
// 		$('#analysis_shift').on('click', function() {							
// 				var insertWeb = $(this).attr('target');
// 				$.get(insertWeb, function(data) {
// 					$('.analysis-content').html(data);
// 				})
					
// 		})	
			
// -------------------------------查詢----------------------------------
			$(":button").click(function() {
				if ('getOne'==$(this).attr('name')) {
					$.ajax({
						type : "post",
						url : "getOneShiftre.do",
						data : $(".oneshift").serialize(),
						success : function(data) {
							$(".rul").html(data);
							$("#shi_search").removeAttr("class");
							$("#shi_rel").attr("class","active");
							$("#search").attr("class","tab-pane fade");
							$("#result").attr("class","tab-pane active");
						}
					});
				} else if ('getAll'==$(this).attr('name')) {
					$.ajax({
						type : "post",
						url : "getAllShiftre.do",
						data : {},
						success : function(data) {
							$(".rul").html(data);
							$("#shi_search").removeAttr("class");
							$("#shi_rel").attr("class","active");
							$("#search").attr("class","tab-pane fade");
							$("#result").attr("class","tab-pane active");
						}
					});
				} else if ('getOneByDate'==$(this).attr('name')) {
					$.ajax({
						type : "post",
						url : "getShiftreByDate.do",
						data : $(".shiftbydate").serialize(),
						success : function(data) {
							$(".rul").html(data);
							$("#shi_search").removeAttr("class");
							$("#shi_rel").attr("class","active");
							$("#search").attr("class","tab-pane fade");
							$("#result").attr("class","tab-pane active");
						}
					});			
				}
			})

		$(".print").click(function() {
			window.print();
		})
	})
</script>

</body>
</html>