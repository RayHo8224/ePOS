<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<!--external css-->
<link
	href="<c:url value="/resources/font-awesome/css/font-awesome.css" />"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style-responsive.css" />"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.css" />
<title>顧客關係維護</title>
<style>
.titlelist {
	font-family: '微軟正黑體';
	font-weight: bold;
	color: white;
	height: 35px;
	background: salmon;
	padding-left: 10px;
	font-size: 23px;
	border-radius: 2px;
}

th {
	text-align: center;
}

input[type="date"], input[type="time"], input[type="datetime-local"],
	input[type="month"] {
	line-height: normal;
}

.my-valid-class {
	color: #3a51e8;
}

.my-error-class {
	color: red;
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
	<!--logo start--> <a href="<%=request.getContextPath()%>/index.jsp" class="logo"><b>ePOS</b></a> <!--logo end-->

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
				<a href="<%=request.getContextPath()%>/ORDER/order.jsp"><img
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
			<li class="sub-menu"><a href="javascript:;" class="active"> <i class="fa fa-users"></i><span>顧客關係</span>
			</a>
				<ul class="sub">
					<li class="active"><a href="<%=request.getContextPath()%>/MAIL/Mail.jsp">寄送系統</a></li>
				</ul></li>
		</ul>
		<!-- sidebar menu end-->
	</div>
	</aside> <!--sidebar end--> <section id="main-content"> <section
		class="wrapper">
	<div class="row mt">
		<div class="col-lg-12">
			<div class="titlelist">顧客關係維護</div>
			<div class="col-lg-12">
				<p>
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">

						<font color='red'>請修正以下錯誤:
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li>${message}</li>
								</c:forEach>
							</ul>
						</font>
					</c:if>
				<form method="post" action="email.do" name="form2"
					class="form-horizontal style-form">
					<div class="col-lg-12">
						<div class="form-group">
							<label class="col-lg-1 col-lg-offset-4 control-label">收件者信箱:</label>
							<div class="col-lg-6">
								<input type="text" name="addres" id="addres"><span
									id="span1"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-1 col-lg-offset-4 control-label">標題:</label>
							<div class="col-lg-6">
								<input type="text" name="from" value="ePos">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-1 col-lg-offset-4 control-label">主旨:</label>
							<div class="col-lg-6">
								<input type="text" name="subject" value="聖誕節優惠">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-1 col-lg-offset-4 control-label">內容:</label><br>
							<div class="col-lg-6">
								<textarea cols="50" rows="9" name="text"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-offset-4">
								<input type="submit" value="寄送單一會員" name="howMany"
									class="btn  btn-theme01" id="one"> 
								<input type="submit"
									value="寄送全部會員" name="howMany" class="btn  btn-theme01">
								<input type="submit" value="寄送優惠訊息" name="howMany"
									class="btn  btn-theme01">
							</div>
						</div>
						<p>
					</div>
					<hr>
					<div class="col-lg-6">
						<input type="date" name="s_ord_date" value="2016-09-14"> <input
							type="date" name="e_ord_date" value="2016-10-25"> <input
							type="submit" value="查詢未下訂單會員" name="howMany"
							class="btn  btn-theme01">
						<table border='1' width='500'>
							<tr align='center' valign='middle'>
								<th>會員編號</th>
								<th>信箱</th>
							</tr>
						<c:if test="${not empty list1}">	
							<c:forEach var="list" items="${list1}">
								<tr align='center' valign='middle'>
									<td>${list.key}</td>
									<td>${list.value.mem_mail}</td>
								</tr>
							</c:forEach>
							</c:if>
						</table>
						<a
							href="<c:out value='${pageContext.request.contextPath}' />/MAIL/Mail.jsp">回上頁</a>
					</div>
				</form>
				<div class="col-lg-6" id="allMem">
					<!-----------------------寄送全部會員--------------- -->
					<form method="post" action="allMail.do">
						<input type="submit" value="查詢全部會員" class="btn  btn-theme01" >
						<table border='1' width='500' id="table1">
							<tr>
								<th>會員編號</th>
								<th>信箱</th>
							</tr>
							<c:if test="${not empty listMail}">
							<c:forEach var="list" items="${listMail}">
								<tr align='center' valign='middle'>
									<td>${list.mem_id}</td>
									<td>${list.mem_mail}</td>
								</tr>
							</c:forEach>
							</c:if>
						</table>
						<a
							href="<c:out value='${pageContext.request.contextPath}' />/MAIL/Mail.jsp">回上頁</a>
					</form>
				</div>

			</div>
		</div>
	</div>
	</section> </section></section>
	<input type="hidden" name="shift" value="${SHIFT}">
	<input type="hidden" name="emp_id" value="${LoginOK.emp_id}">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script>
		!window.jQuery
				&& document
						.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
	</script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.js"></script>
	<script src="<c:url value='../resources/js/jquery.validate.min.js' />"></script>
	<script class="include" type="text/javascript"
		src="<c:url value="/resources/js/jquery.dcjqaccordion.2.7.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.scrollTo.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.nicescroll.js" />"
		type="text/javascript"></script>

	<!--common script for all pages-->
	<script src="<c:url value="/resources/js/common-scripts.js" />"></script>
	<script src="<c:url value="../resources/js/gen_validatorv4.js" />"
		type="text/javascript"></script>
		
<!-- 	<script>
		$(document).ready(function() {
			var name = $('#addres');
			name.blur(function() {
				$.get('email.do', {
					name : name.val()
				}, function(data) {
					if (data == "") {
						$('#span1').append("Hello");
					}
				})
			})
		});
	</script> -->
</body>
</html>