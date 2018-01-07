<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
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
<title>�ק�t�Ӹ��</title>
<style>
.navbar-default {
	background: #D2E9FF;
}

.titlelist {
	font-family: '�L�n������';
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

.my-valid-class {
	color: #3a51e8;
}

.my-error-class {
	color: red;
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
					<i class="fa fa-dashboard"></i> <span>���ȵ��b</span>
			</a></li>

			<li class="sub-menu"><a href="javascript:;" class="active">
					<i class="fa fa-desktop"></i> <span>�򥻸�ƺ��@</span>
			</a>
				<ul class="sub">
					<li><a href="<%=request.getContextPath()%>/MEMBER/member.jsp">�|����ƺ��@</a></li>
					<li><a
						href="<%=request.getContextPath()%>/EMPLOYEE/employee.jsp">���u��ƺ��@</a></li>
					<li class="active"><a
						href="<%=request.getContextPath()%>/COMPANY/company.jsp">�t�Ӹ�ƺ��@</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-edit"></i> <span>�i�f�@�~</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/REQUISITION/requisition.jsp">���ʳ���@</a></li>
					<li><a
						href="<%=request.getContextPath()%>/QUOTATION/quotation.jsp">�߻�����@</a></li>
					<li><a href="<%=request.getContextPath()%>/PURCHASE/pur.jsp">���ʳ���@</a></li>
					<li><a
						href="<%=request.getContextPath()%>/BILL_OF_PURCHASE/bop.jsp">�i�f����@</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="glyphicon glyphicon-shopping-cart"></i> <span>�P�f�t��</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/VALUATION/ValuationList.jsp">��������@</a></li>
					<li><a href="<%=request.getContextPath()%>/ORDER/ordmain.jsp">�q����@</a></li>
					<li><a
						href="<%=request.getContextPath()%>/SHIPMENTS/ShipmentsList.jsp">�X�f����@</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-book"></i> <span>�s�f�@�~</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/PRODUCT/product.jsp">�ӫ~�޲z</a></li>
					<li><a
						href="<%=request.getContextPath()%>/PROMOTING/promoting.jsp">�P�P�ӫ~�޲z</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="glyphicon glyphicon-log-out"></i> <span>�h�f�@�~</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/RETURNS/Return_Items.jsp">�h�f�~�޲z</a></li>
					<li><a
						href="<%=request.getContextPath()%>/RETURNS/ReturnList.jsp">�h�f��޲z</a></li>
					<li><a
						href="<%=request.getContextPath()%>/INVO/select_page.jsp">�@�o�o���޲z</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-usd"></i> <span>���y�޲z</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/SHIFTREPORT/shiftreport.jsp">�Z�O������@</a></li>
					<li><a href="<%=request.getContextPath()%>/COUPON/coupon.jsp">�����</a></li>
					<li><a
						href="<%=request.getContextPath()%>/DISCOUNT/discount.jsp">�馩�޲z</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i class="fa fa-bar-chart-o"></i> <span>������R</span>
					</a>
					<ul class="sub">
						<li><a href="<%=request.getContextPath()%>/ANALYSIS/analysis.jsp">�P�������R</a></li>
					</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-users"></i> <span>�U�����Y</span>
			</a>
				<ul class="sub">
					<li><a href="<%=request.getContextPath()%>/MAIL/Mail.jsp">�H�e�t��</a></li>
				</ul></li>
		</ul>
		<!-- sidebar menu end-->
	</div>
	</aside> <!--sidebar end--> <section id="main-content"> <section
		class="wrapper">

	<div class="row mt">
		<div class="col-lg-12">
			<nav class="nav navbar-default">
			<div class="tab-content">
				<ul class="nav navbar-nav">
					<li><a href="searchCom.jsp" class="glyphicon glyphicon-search">�j�M</a></li>
					<li><a href="addCom.jsp" class="glyphicon glyphicon-file">�s�W</a></li>
					<li><a style="background-color: rgba(172, 214, 255, 0.6);"
						class="glyphicon glyphicon-list-alt">�d�ߵ��G</a></li>
				</ul>
			</div>
			</nav>
			<div class="tab-content">
				<div>
					<div class="titlelist">�ק�t�Ӹ��</div>
					<div class="col-lg-12  main">
						<p>
							<c:if test="${not empty errorMsgs}">
								<font color='red'>�Эץ��H�U���~:
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li>${message}</li>
										</c:forEach>
									</ul>
								</font>
							</c:if>
						<form method="post" action="updateCom.do" id="create_com"
							enctype="multipart/form-data" class="form-horizontal style-form">
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�t�ӽs��:</label>
								<div class="col-lg-6">
									<input type="text" name="com_id" value="${comVO.com_id}"
										readonly="readonly" style="color: gray;">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�t�ӦW��:</label>
								<div class="col-lg-6">
									<input type="text" name="com_name" value="${comVO.com_name}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�Τ@�s��:</label>
								<div class="col-lg-6">
									<input type="text" name="com_um" value="${comVO.com_um}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�a�}:</label>
								<div class="col-lg-6">
									<input type="text" name="com_addr" value="${comVO.com_addr}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�q�l�H�c:</label>
								<div class="col-lg-6">
									<input type="text" name="com_mail" value="${comVO.com_mail}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�q��:</label>
								<div class="col-lg-6">
									<input type="text" name="com_phone" value="${comVO.com_phone}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�Ӥ�:</label>
								<img alt="�|�L�W��" src="data:image/gif;base64,${comVO.picture}"
									class="col-lg-1"> <input type="hidden" name="picture"
									value="${comVO.picture}" class="col-lg-1"> <input
									type="file" name="newPicture">
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�ק�H:</label>
								<div class="col-lg-6">
									<input type="text" name="key_id" value="${comVO.key_id}"
										style="color: gray;" readonly="readonly">
								</div>
							</div>
							<!-- <input type="hidden" name="action" value="updateToDB"> -->
							<div class="form-group">
								<div class="col-lg-1  col-lg-offset-5">
									<div class="col-lg-6">
										<input type="submit" value="�ק�" class="btn  btn-theme03">
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
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
	<script src="<c:url value='../resources/js/jquery.validate.min.js' />"></script>
	<script class="include" type="text/javascript"
		src="<c:url value="../resources/js/jquery.dcjqaccordion.2.7.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.scrollTo.min.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.nicescroll.js" />" ��
		type="text/javascript"></script>
	<!--common script for all pages-->
	<script src="<c:url value="../resources/js/common-scripts.js" />"></script>
	<script src="<c:url value="../resources/js/gen_validatorv4.js" />"
		type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			$("#create_com").validate({
				success: function(label) {
					label.text("�i���T�j"),
					label.css('color','#1dc489')
					},
				errorClass : "my-error-class",
				validClass : "my-valid-class",

				rules : {
					com_name : {
						required : true
					},
					com_um : {
						digits : true,
						rangelength : [ 8, 8 ],
					},
					com_addr : {
						required : true
					},
					com_mail : {
						email : true
					},
					com_phone : {
						digits : true,
						required : true,
						rangelength : [ 10, 10 ]
					}
				},
				messages : {
					com_name : {
						required : "�i�п�J�t�ӦW�١j"
					},
					com_um : {
						digits : "�i�����O�Ʀr�j",
						rangelength : "�i�νs�榡�����T�j"
					},
					com_addr : {
						required : "�i�п�J�t�Ӧa�}�j"

					},
					com_mail : {
						email : "�iemail�榡�����T�j"
					},
					com_phone : {
						digits : "�i�����O�Ʀr�j",
						required : "�i�п�J�t�ӹq�ܡj",
						rangelength : "�i�q�ܿ�J�榡�����T�j"
					}
				}
			})
		})
		$("input[readonly]").css("background-color","#eee");
	</script>
</body>
</html>