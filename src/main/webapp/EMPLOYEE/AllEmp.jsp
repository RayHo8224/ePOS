<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

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
<title>員工資料清單</title>
<style>

/* 	表格標題 */
.table>caption+thead>tr:first-child>th, .table>colgroup+thead>tr:first-child>th,
	.table>thead:first-child>tr:first-child>th, .table>caption+thead>tr:first-child>td,
	.table>colgroup+thead>tr:first-child>td, .table>thead:first-child>tr:first-child>td
	{
	font-weight: bold;
	text-align: center;
	background: steelblue;
}

/* 	表格內容偶數 */
.table-bordered>thead>tr>th, .table-bordered>tbody>tr>th,
	.table-bordered>tfoot>tr>th, .table-bordered>thead>tr>td,
	.table-bordered>tbody>tr>td, .table-bordered>tfoot>tr>td {
 	background: lightgray; 
}
/* 	表格內容單數 */
.table-striped>tbody>tr:nth-child(odd)>td, .table-striped>tbody>tr:nth-child(odd)>th
	{
	background: lightgray;
}

/* 	表格偶數滑鼠指向 */
.table-hover>tbody>tr:hover>td, .table-hover>tbody>tr:hover>th {
	background-color: lightsteelblue;
}

.titlelist {
	font-family: '微軟正黑體';
	font-weight: bold;
	color: white;
	height: 35px;
	background: #66B3FF;
	padding-left: 10px;
	font-size: 23px;
	border-radius: 2px;
}
.glyphicon {
	top: auto;
}
.navbar-default {
	background: #D2E9FF;
}
table {
    font-size: large;
}
body {
    font-size: 16px;
}
label {
    font-weight: 900;
}
</style>
</head>
<body>
	<section id="container"> <!--header start--> <header
		class="header black-bg">
	<div class="sidebar-toggle-box">
		<div class="fa fa-bars tooltips" data-placement="right"
			data-original-title="Toggle Navigation"></div>
	</div>
	<!--logo start--> <a href="<%=request.getContextPath()%>/index.jsp"
		class="logo"><b>ePOS</b></a> <!--logo end-->

	<div class="top-menu">
		<ul class="nav pull-right top-menu">
			<li><a class="logout"
				href="<%=request.getContextPath()%>/LOGIN/logout.jsp">Logout</a>Hi , ${LoginOK.emp_name}</li>
		</ul>
	</div>
	</header> <!--header end--> <!--sidebar start--> <aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu" id="nav-accordion">

			<p class="centered">
				<a href="<%=request.getContextPath()%>/index.jsp"><img
					src="<c:url value="../resources/img/ui-sam.jpg"/>"
					class="img-circle" width="60"></a>
			</p>
			<h5 class="centered">ePOS</h5>

			<li class="mt"><a href="<%=request.getContextPath()%>/ORDER/order.jsp">
					<i class="fa fa-dashboard"></i> <span>收銀結帳</span>
			</a></li>

			<li class="sub-menu"><a href="javascript:;" class="active">
					<i class="fa fa-desktop"></i> <span>基本資料維護</span>
			</a>
				<ul class="sub">
					<li><a href="<%=request.getContextPath()%>/MEMBER/member.jsp">會員資料維護</a></li>
					<li class="active"><a
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
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-book"></i> <span>存貨作業</span>
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
<li class="sub-menu"><a href="javascript:;"> <i
     class="fa fa-users"></i> <span>顧客關係</span>
   </a>
    <ul class="sub">
     <li><a href="<%=request.getContextPath()%>/MAIL/Mail.jsp">寄送系統</a></li>
    </ul></li>
		</ul>
		<!-- sidebar menu end-->
	</div>
	</aside> <!--sidebar end--> <section id="main-content"> <section
		class="wrapper">
	<div class="row mt"><div class="col-lg-12">
		<nav class="nav navbar-default">
		<div class="tab-content">
			<ul class="nav navbar-nav">
				<li><a href="searchEmp.jsp" class="glyphicon glyphicon-search">搜尋</a></li>
				<li><a href="addEmp.jsp" class="glyphicon glyphicon-file">新增</a></li>
				<li><a href="SetPassCode.jsp" class="glyphicon glyphicon-lock">修改權限</a></li>
				<li><a style="background-color: rgba(172, 214, 255, 0.6);"
					class="glyphicon glyphicon-list-alt">查詢結果</a></li>
			</ul>
		</div>
		</nav>
		<div class="titlelist">員工資料清單</div>
		<center>
			<table border="1" bordercolor='#CCCCFF' id="table1"
				class="table table-bordered table-striped  table-hover">
				<thead>
					<tr>
						<th>員編</th>
						<th>密碼</th>
						<th>姓名</th>
						<th>性別</th>
						<th>身分證字號</th>
						<th>地址</th>
						<th>電子信箱</th>
						<th>電話</th>
						<th>生日</th>
						<th>到職日</th>
						<th>離職日</th>
						<th>照片</th>
						<th>修改人</th>
						<th>修改時間</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
				</thead>
				<c:forEach var="list" items="${list}" varStatus="count">
					<form method="post" action="updateDeleteEmp.do">
						<tr>
							<td>${list.emp_id}</td>
<%-- 							<td>${list.emp_pwd}</td> --%>
							<td><input type="password" style="width:4em;background-color:#d3d3d3;" value="${list.emp_pwd}" readonly="readonly"></td>
							<td>${list.emp_name}</td>
							<td>${list.emp_sex}</td>
							<td>${list.emp_idnum}</td>
							<td>${list.emp_addr}</td>
							<td>${list.emp_mail}</td>
							<td>${list.emp_phone}</td>
							<td>${list.emp_bday}</td>
							<td>${list.emp_reg}</td>
							<td>${list.emp_due}</td>
							<td><img alt="尚無大頭照"
								src="data:image/gif;base64,${list.picture}" width="50" height="50"></td>
							<td>${list.key_id}</td>
							<td>${list.key_date}</td>
							<td><input type="submit" name="action"
								class="btn btn btn-xs" value="update"></td>
							<td><input type="submit" name="action"
								class="btn btn btn-xs" value="delete"></td>
							<input type="hidden" name="emp_id" value="${list.emp_id}">
						</tr>
					</form>
				</c:forEach>
			</table>
		</center>
	</div></div>
	</section> </section> </section>
		<input type="hidden" name="shift" value="${SHIFT}">
	<input type="hidden" name="emp_id" value="${LoginOK.emp_id}">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script>
		!window.jQuery
				&& document
						.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
	</script>
	<script src="<c:url value="../resources/js/bootstrap.min.js" />"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.js"></script>
	<script class="include" type="text/javascript"
		src="<c:url value="../resources/js/jquery.dcjqaccordion.2.7.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.scrollTo.min.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.nicescroll.js" />"
		type="text/javascript"></script>

	<!--common script for all pages-->
	<script src="<c:url value="../resources/js/common-scripts.js" />"></script>
	<!------------------------------------------------ 程式 --------------------------------------------------------------->
	<script src="<c:url value="../resources/js/gen_validatorv4.js" />"type="text/javascript"></script>
<!-- <script>
	$(function () {
$('#table1').DataTable();
	})
</script> -->
</body>
</html>