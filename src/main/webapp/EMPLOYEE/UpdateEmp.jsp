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
<title>�ק���u���</title>
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
					<li class="active"><a
						href="<%=request.getContextPath()%>/EMPLOYEE/employee.jsp">���u��ƺ��@</a></li>
					<li><a
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
					<li><a href="searchEmp.jsp" class="glyphicon glyphicon-search">�j�M</a></li>
					<li><a href="addEmp.jsp" class="glyphicon glyphicon-file">�s�W</a></li>
					<li><a href="SetPassCode.jsp" class="glyphicon glyphicon-lock">�ק��v��</a></li>
					<li><a style="background-color: rgba(172, 214, 255, 0.6);"
						class="glyphicon glyphicon-list-alt">�d�ߵ��G</a></li>
				</ul>
			</div>
			</nav>
			<div class="tab-content">
				<div>
					<c:if test="${not empty errorMsgs}">
						<font color='red'>�Эץ��H�U���~:
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li>${message}</li>
								</c:forEach>
							</ul>
						</font>
					</c:if>
					<div class="titlelist">�ק���u���</div>
					<div class="col-lg-12  main">
						<p>
						<form method="post" action="updateEmp.do" id="create_emp"
							enctype="multipart/form-data" class="form-horizontal style-form">
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">���u�s��:</label>
								<div class="col-lg-6">
									<input type="text" name="emp_id" value="${empVO.emp_id}"
										readonly="readonly" style="color: gray;">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�K�X:</label>
								<div class="col-lg-6">
									<input type="password" style="background-color: white;" name="emp_pwd" value="${empVO.emp_pwd}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�m�W:</label>
								<div class="col-lg-6">
									<input type="text" name="emp_name" value="${empVO.emp_name}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�ʧO:</label>
								<div class="col-lg-6">
									<input type="text" name="emp_sex" value="${empVO.emp_sex}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">������:</label>
								<div class="col-lg-6">
									<input type="text" name="emp_idnum" value="${empVO.emp_idnum}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�a�}:</label>
								<div class="col-lg-6">
									<input type="text" name="emp_addr" value="${empVO.emp_addr}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�q�l�H�c:</label>
								<div class="col-lg-6">
									<input type="text" name="emp_mail" value="${empVO.emp_mail}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�q��:</label>
								<div class="col-lg-6">
									<input type="text" name="emp_phone" value="${empVO.emp_phone}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�ͤ�:</label>
								<div class="col-lg-6">
									<input type="date" name="emp_bday" value="${empVO.emp_bday}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">��¾��:</label>
								<div class="col-lg-6">
									<input type="date" name="emp_reg" value="${empVO.emp_reg}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">��¾��:</label>
								<div class="col-lg-6">
									<input type="date" name="emp_due" value="${empVO.emp_due}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�Ӥ�:</label>

									<img alt="�|�L�j�Y��" src="data:image/gif;base64,${empVO.picture}" class="col-lg-1">
									<input type="hidden" name="picture" value="${empVO.picture}">
									<input type="file" name="newPicture">

							</div>
							<div class="form-group">
								<label class="col-lg-1 col-lg-offset-4 control-label">�ק�H:</label>
								<div class="col-lg-6">
									<input type="text" name="key_id"
										value="${sessionScope.LoginOK.emp_id }" readonly="readonly"
										style="color: gray;">
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-1  col-lg-offset-5">
									<div class="col-lg-6">
										<input type="submit" value="�ק�" class="btn btn-theme03">
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section> </section> 
		<input type="hidden" name="shift" value="${SHIFT}">
	<input type="hidden" name="emp_id" value="${LoginOK.emp_id}">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script>
		!window.jQuery
				&& document
						.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
	</script> <script src="<c:url value="../resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value='../resources/js/jquery.validate.min.js' />"></script>
	<script class="include" type="text/javascript"
		src="<c:url value="../resources/js/jquery.dcjqaccordion.2.7.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.scrollTo.min.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.nicescroll.js" />" ��
		type="text/javascript"></script> <!--common script for all pages--> <script
		src="<c:url value="../resources/js/common-scripts.js" />"></script> <script
		src="<c:url value="../resources/js/gen_validatorv4.js" />"
		type="text/javascript"></script> <script>
			$(document).ready(function() {
				// 		------------------------------------------�[�J���������Ҥ�k----------------------
				$.validator.addMethod("twId", function(value, element) {
					var regExpID = /^[a-z](1|2)\d{8}$/i, // �����ҥ��W��
					re = new RegExp(regExpID);
					return this.optional(element) || re.test(value);
				});
				// -----------------------------------����-------------------------------------------
				$("#create_emp").validate({
					success: function(label) {
						label.text("�i���T�j"),
						label.css('color','#1dc489')
						},
					errorClass : "my-error-class",
					validClass : "my-valid-class",

					rules : {
						emp_pwd : {
							required : true,
							minlength : 4
						},
						emp_name : {
							required : true
						},
						emp_idnum : {
							required : true,
							rangelength : [ 10, 10 ],
							twId : true
						},
						emp_addr : {
							required : true
						},
						emp_mail : {
							email : true
						},
						emp_phone : {
							digits : true,
							required : true,
							rangelength : [ 10, 10 ]
						},
						emp_reg : {
							required : true
						}
					},
					messages : {
						emp_pwd : {
							required : "�i�п�J�K�X�j",
							minlength : "�i�K�X���ץ����j��4��ơj"
						},
						emp_name : {
							required : "�i�п�J���u�m�W�j"
						},
						emp_idnum : {
							required : "�i�п�J�����Ҹ��j",
							rangelength : "�i���ץ�����10��ơj",
							twId : "�i�п�J���Ī������Ҧr���j"
						},
						emp_addr : {
							required : "�i�п�J�a�}�j"

						},
						emp_mail : {
							email : "�iemail�榡�����T�j"
						},
						emp_phone : {
							digits : "�i�����O�Ʀr�j",
							required : "�i�п�J�q�ܡj",
							rangelength : "�i�q�ܿ�J�榡�����T�j"
						},
						emp_reg : {
							required : "�i�п�J��¾��j"
						}
					}
				})
			})
			$("input[readonly]").css("background-color","#eee");
		</script>
</body>
</html>