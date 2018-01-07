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
<link href="<c:url value="../resources/css/bootstrap.css" />"
	rel="stylesheet">
<!--external css-->
<link
	href="<c:url value="../resources/font-awesome/css/font-awesome.css" />"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<c:url value="../resources/lineicons/style.css" />">
<!-- Custom styles for this template -->
<link href="<c:url value="../resources/css/style.css" />"
	rel="stylesheet">
<link href="<c:url value="../resources/css/style-responsive.css" />"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" 
 href="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.css" />	
<style>

	.navbar-default .navbar-nav > li > a{
		color:#542344;
	}

 	.navbar-default .navbar-nav > .active > a, .navbar-default .navbar-nav > .active > a:hover, .navbar-default .navbar-nav > .active > a:focus{
 		background: #BFD1E5;
 	}

	.navbar-default {
		background: #CCFFCC;
		border-color: #CCFF99;
		border-radius: 8px;
	}
	
	.main {
		height: 750px;
		border-radius: 8px;
		background: #FFFFCC;
	}
	
	.titlelist {
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #99CCCC;
		padding-left: 10px;
		font-size: 23px;
		border-radius: 2px;
	}

	.my-valid-class{
		color:#3a51e8;
	}
	
	.my-error-class{
		color:red;
	}
</style>
<title>商品管理</title>
</head>
<body>
	<!--header start-->
	<header class="header black-bg">
	<div class="sidebar-toggle-box">
		<div class="fa fa-bars tooltips" data-placement="right"
			data-original-title="Toggle Navigation"></div>
	</div>
	<!--logo start--> <a href="<%=request.getContextPath()%>/index.jsp"
		class="logo"><b>ePOS</b></a> <!--logo end-->

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
			<li class="sub-menu"><a href="javascript:;" class="active"> <i
					class="fa fa-book"></i> <span>存貨作業</span>
			</a>
				<ul class="sub">
					<li class="active"><a
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
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-usd"></i> <span>金流管理</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/SHIFTREPORT/shiftreport.jsp">班別報表維護</a></li>
					<li><a href="<%=request.getContextPath()%>/COUPON/coupon.jsp">折價券</a></li>
					<li><a
						href="<%=request.getContextPath()%>/DISCOUNT/discount.jsp">折扣管理</a></li>
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

	<section id="main-content"> <section id="container">
	<section class="wrapper">
	<div class="row mt">
		<div class="col-lg-12">
			<div class="nav navbar-default">
				<div class="container-fluid">
					<ul class="nav navbar-nav">
						<li id="chg_search"><a data-toggle="tab" href="#search_Prod"><span class="glyphicon glyphicon-search"></span>搜尋</a></li>
						<li id="chg_new"><a id="c_prod" target="InsertProd.jsp" data-toggle="tab" href="#new_Prod"><span class="glyphicon glyphicon-file"></span>新增</a></li>
						<li id="chg_result"><a data-toggle="tab" href="#result_Prod"><span class="glyphicon glyphicon-list-alt"></span>查詢結果</a></li>
						<li><a data-toggle="tab" href="#print" class="print"><span class="glyphicon glyphicon-print"></span>列印</a></li>
					</ul>
				</div>
			</div>

			<div class="tab-content">
				<div id="search_Prod" class="tab-pane fade">
					<%-- 錯誤表列 --%>
<%-- 					<c:if test="${not empty param.message}"> --%>
<!-- 						<font color='red'>請修正以下錯誤: -->
<!-- 							<ul> -->
<%-- 								<c:forEach var="message" items="${param.message}"> --%>
<%-- 									<li>${message}</li> --%>
<%-- 								</c:forEach> --%>
<!-- 							</ul> -->
<!-- 						</font> -->
<%-- 					</c:if> --%>

	<jsp:useBean id="ProdSvc" scope="page" class="com.product.model.ProdService" />
					<div class="titlelist">查詢</div>
					<div class="col-lg-12  main">
						<p>
						<FORM class="form-horizontal style-form" role="form">
							<div class="form-group">
								<div class="col-lg-3"></div>
								<label class="col-lg-2 control-label" for="prod_id">商品編號搜尋</label>
								<div class="col-lg-2">
									<select size="1" name="prod_id" id="prod_id" class="form-control">
										<c:forEach var="prodVO" items="${ProdSvc.all}">
											<option value="${prodVO.prod_id}">${prodVO.prod_id}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-lg-2">
									<input type="button" value="商品編號搜尋" class="btn btn-warning">
								</div>
							</div>
						</FORM>	
						<FORM class="form-horizontal style-form" role="form">
							<p>
							<div class="form-group">
								<div class="col-lg-3"></div>
								<label class="col-lg-2 control-label" for="prod_id">查詢全部商品</label>
								<div class="col-lg-2"></div>
								<div class="col-lg-2">
									<input type="button" value="查詢全部商品" class="btn btn-warning">
								</div>
							</div>

							<p>
						</FORM>
						<FORM class="form-horizontal style-form" role="form" name="prod_keyword" id="prod_keyword">	
							<div class="form-group">
								<div class="col-lg-3"></div>
								<label class="col-lg-2 control-label" for="prod_name">商品名稱關鍵字查詢</label>
								<div class="col-lg-2">
									<input type="text" class="form-control" name="prod_name" id="prod_name">
								</div>
								<div class="col-lg-2">
									<input type="button" value="商品名稱關鍵字查詢" class="btn btn-warning">
								</div>
							</div>
						</FORM>

							<p>
						<FORM class="form-horizontal style-form" role="form">	
							<div class="form-group">
								<div class="col-lg-3"></div>
								<label class="col-lg-2 control-label" for="prod_group">商品分類查詢</label>
								<div class="col-lg-2">
<!-- 									<input type="text" class="form-control" name="prod_group" id="prod_group"> -->
									<select size="1" name="prod_group" id="prod_group" class="form-control">
										<c:forEach var="prodVO" items="${ProdSvc.selgroup()}">
											<option value="${prodVO.prod_group}">${prodVO.prod_group}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-lg-2">
									<input type="button" value="商品分類關鍵字查詢" class="btn btn-warning">
								</div>
							</div>
						</FORM>
					</div>
				</div>
				<div id="new_Prod" class="tab-pane fade">
					<div class="insert_content main"></div>
				</div>
				<div id="result_Prod" class="tab-pane fade">
					<div class="result_content main"></div>
				</div>
			</div>
		</div>
	</div>
	</section> </section></section>
<input type="hidden" name="shift" value="${SHIFT}"><input type="hidden" name="emp_id" value="${LoginOK.emp_id}">


	<!-- ------------------------------------------------------------程式--------------------------------------------------- -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script>
		!window.jQuery&& document.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
	</script>
	<script src="<c:url value="../resources/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.js"></script>
	<script src="<c:url value="../resources/js/jquery.validate.min.js" />"></script>
	<script class="include" type="text/javascript"
		src="<c:url value="../resources/js/jquery.dcjqaccordion.2.7.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.scrollTo.min.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.nicescroll.js" />"
		type="text/javascript"></script>

	<!--common script for all pages-->
	<script src="<c:url value="../resources/js/common-scripts.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.form.js" />"></script>
	<script type="text/JavaScript">
	$("#prod_keyword").validate({
		errorClass:"my-error-class",
		validClass:"my-valid-class",
		
		rules:{
			prod_name:{required:true}
		},
		messages:{
			prod_name:{required:"【請輸入商品關鍵字】"}
		}
	})	
	
		$(document).ready(function() {
// 載入新增網頁

			$('#c_prod').on('click', function() {
				var insertWeb = $(this).attr('target');
				$.get(insertWeb, function(data) {
					$('.insert_content').html(data);
				})
			})
//查詢
			$(function() {

				$(":button").click(function() {
					if ("查詢全部商品" == $(this).val()) {
						$.ajax({
							type : "post",
							url : "getAllProd.do",
							data : {
								action : "getAllProd.do"
							},
							success : function(data) {
								$(".result_content").html(data);
								$("#chg_search").removeAttr("class");
								$("#chg_result").attr("class", "active");
								$("#search_Prod").attr("class", "tab-pane fade");
								$("#result_Prod").attr("class", "tab-pane active");
							}
						});
					} else if ("商品編號搜尋" == $(this).val()) {
						$.ajax({
							type : "post",
							url : "getOneProd.do",
							data : {
								action : "getOneProd.do",
								prod_id : $("#prod_id").val()
							},
							success : function(data) {
								$(".result_content").html(data);
								$("#chg_search").removeAttr("class");
								$("#chg_result").attr("class", "active");
								$("#search_Prod").attr("class", "tab-pane fade");
								$("#result_Prod").attr("class", "tab-pane active");
							}
						});
					} else if ("商品名稱關鍵字查詢" == $(this).val()) {
						var prod_keyword = $("form[name='prod_keyword']");
						if(prod_keyword.valid()){
							$.ajax({
								type : "post",
								url : "getProdByName.do",
								data : {
									action : "getProdByName.do",
									prod_name : $("#prod_name").val()
								},
								success : function(data) {
									$(".result_content").html(data);
									$("#chg_search").removeAttr("class");
									$("#chg_result").attr("class", "active");
									$("#search_Prod").attr("class", "tab-pane fade");
									$("#result_Prod").attr("class", "tab-pane active");
								}
							});
						}
					} else if ("商品分類關鍵字查詢" == $(this).val()) {
						$.ajax({
							type : "post",
							url : "getProdByGroup.do",
							data : {
								action : "getProdByGroup.do",
								prod_group : $("#prod_group").val()
							},
							success : function(data) {
								$(".result_content").html(data);
								$("#chg_search").removeAttr("class");
								$("#chg_result").attr("class", "active");
								$("#search_Prod").attr("class", "tab-pane fade");
								$("#result_Prod").attr("class", "tab-pane active");
							}
						});
					}
				})
			})

			$(".print").click(function() {
				window.print();
			})
		})
	</script>
</body>
</html>