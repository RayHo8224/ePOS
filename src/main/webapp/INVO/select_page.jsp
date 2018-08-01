<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.invo.model.*"%>

<%
	InvoService invoSvc = new InvoService();
	List<InvoVO> list = invoSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap core CSS -->
<!-- <link href="../resources/css/bootstrap.min.css" rel="stylesheet"> -->
<!-- <link href="../resources/css/bootstrap-theme.min.css" rel="stylesheet"> -->
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
<title>作廢發票</title>

<style>
	.titleinvo {
		/*     	margin-top:auto; */
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #ff8d3a;
		font-size: 23px;
		border-radius: 2px;
		}
	
	a{
		color:#f7781b;
	}
	
	.main{
		height: 250px;
		border-radius: 8px;
		background: #fff1e7;
	}
	
	/* 	表格標題 */
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background: #ff6c00;
		font-weight:bold;
	}
	
	/* 	表格內容偶數 */
	.table-bordered > thead > tr > th, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > tbody > tr > td, .table-bordered > tfoot > tr > td{
		background:#ffe1ca;
		border:1px solid #ffa15c;
		
	}
	/* 	表格內容單數 */
	.table-striped > tbody > tr:nth-child(odd) > td, .table-striped > tbody > tr:nth-child(odd) > th{
		background:white;
	}
	
	/* 	表格偶數滑鼠指向 */
	.table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th{
		background-color:#ffb784;
	}
	
	/* 表格頁數*/
	.pagination > .active > a, .pagination > .active > span, .pagination > .active > a:hover, .pagination > .active > span:hover, .pagination > .active > a:focus, .pagination > .active > span:focus{
		background: #ff6c00;
		border-color:#ff8d3a;
	}
	
	.alert-info{
		background: #ffcca7;
		border-color:#ffa15c;
	}
	
/* 	/* 	表格標題 */ */
/* 	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{ */
/* 		background: #f56700; */
/* 	} */
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
			<li><a class="logout" href="<%=request.getContextPath()%>/LOGIN/logout.jsp">Logout</a>Hi , ${LoginOK.emp_name}</li>
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
					<li><a href="<%=request.getContextPath()%>/ORDER/order.jsp">訂單維護</a></li>
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
			<li class="sub-menu"><a href="javascript:;" class="active"> <i
					class="glyphicon glyphicon-log-out"></i> <span>退貨作業</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/RETURNS/Return_Items.jsp">退貨品管理</a></li>
					<li><a
						href="<%=request.getContextPath()%>/RETURNS/ReturnList.jsp">退貨單管理</a></li>
					<li class="active"><a
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
			<li class="sub-menu"><a href="javascript:;"> <i
					class=" fa fa-bar-chart-o"></i> <span>報表分析</span>
			</a>
				<ul class="sub">
					<li><a href="<%=request.getContextPath()%>/ORDER/report.jsp">月營收</a></li>
					<li><a href="<%=request.getContextPath()%>/ORDER/weatherCharts.jsp">商品排行榜</a></li>
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
	</aside> <!--sidebar end--> <section id="main-content"> <section class="wrapper">

	<div class="row mt">
	<div class="col-sm-12">
		<div id="add" class="main">
			<div class="tab-content">
				<nav class="alert alert-info">
				<div>
				<a id="add" href="#"><span class="glyphicon glyphicon-file"></span>新增</a>　　　
		    	<a href="#" onclick="window.open('searchinvo.jsp', 'Yahoo', config='height=500,width=850')"><span class="glyphicon glyphicon-search"></span>查詢</a>　　　
		    	<a href="#" onclick="window.open('listAllInvo.jsp', 'RetrunList', config='height=800,width=1680')"><span class="glyphicon glyphicon-inbox"></span>全部查詢</a>　　
		    	<a id="print" href="javaScript:varitext()"><span class="glyphicon glyphicon-print" ></span>列印</a>　　　
		    	<a id="sub" href="#"><span class="glyphicon glyphicon-ok-sign">送出</span></a>　
				</div>
				</nav>
			</div>


			<FORM id="invoform" METHOD="post" ACTION="insertInvo.do" class="form-inline">
				<div class="form-group">
					<label for="exampleInputName2">　作廢發票編號:</label> <input type="text"
						name="invoice_id" class="form-control" value="${oldInvoice_id}" readonly>
				</div> 　　
				<div class="form-group">
					<label for="exampleInputName2">註銷訂單編號:</label> <input type="text"
						name="ord_id" class="form-control" value="${oldOrd_id}" readonly>
				</div>　　
				<div class="form-group">
					<label for="exampleInputEmail2">新發票編號：</label> <input type="text"
						name="new_invoice_number" class="form-control" value="${newInvoice_id}" readonly>
				</div>　　
				<div class="form-group">
					<label for="exampleInputName2">新訂單編號：</label> <input type="text"
						name="new_ord_id" class="form-control" value="${newOrd_id}" readonly>
				</div>
				<div>
					<input type="hidden" name="shift" value="${SHIFT}" >
					<input type="hidden" name="emp_id" value="${LoginOK.emp_id}">
				</div>
			</FORM>

		</div>
		
		
		<!-- -----------------------------------------------------------表格----------------------------------------------------------- -->
		<div>
			<div class="titleinvo">發票作廢</div>
			<table id="table1" class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<td align='center'>作廢發票編號</td>
						<td align='center'>註銷訂單編號</td>
						<td align='center'>新發票號碼</td>
						<td align='center'>新訂單編號</td>
						<td align='center'>修改</td>
						<td align='center'>刪除</td>
					</tr>
				</thead>
				<c:forEach var="invoVO" items="${list}">
					<tr class="table2" align='center' valign='middle'>
						<td>${invoVO.invoice_id}</td>
						<td>${invoVO.ord_id}</td>
						<td>${invoVO.new_invoice_number}</td>
						<td>
							<label>${invoVO.new_ord_id}</label>
							<input type="text" name="new_invoice_number" class="edittext">
						</td>
						<td>
							<button type="button" class="btn btn-success" onclick="editdata(this)" target="${invoVO.invoice_id}"><i class="fa fa-pencil"></i></button>
			     			<button type="button" class="btn btn-primary" onclick="confirm(this)" ><i class="glyphicon glyphicon-ok"></i></button>
						</td>
						<td>
			    			<button type="submit" target="${invoVO.invoice_id}" class="btn btn-danger"><i class="fa fa-trash-o "></i></button>
						</td>
					</tr>

				</c:forEach>

			</table>
		</div>
	</div>
	
	</div>
	</section> </section> </section>

	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script>
		!window.jQuery
				&& document
						.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
	</script>

	<script src="<c:url value="../resources/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.js"></script>
	<script class="include" type="text/javascript" src="<c:url value="../resources/js/jquery.dcjqaccordion.2.7.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.scrollTo.min.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.nicescroll.js" />"
		type="text/javascript"></script>

	<!--common script for all pages-->
	<script src="<c:url value="../resources/js/common-scripts.js" />"></script>
	<!------------------------------------------------ 程式 --------------------------------------------------------------->
	<script src="<c:url value="../resources/js/gen_validatorv4.js" />"
		type="text/javascript"></script>
	<script type="text/JavaScript">
	
	$(document).ready(function(){
		$(".btn-primary").hide();
		$(".edittext").hide();
	})	
	
		 $(function () {

// <!----------------------------------------  新增         ------------------------------------>

				$('#sub').on('click',function(){
			    var url = "insertInvo.do"; 
			    $.ajax({
			           type: "POST",
			           url: url,
			           data: $("#invoform").serialize(), 
			           success: function(data)
			           {
			        	   location.reload();
			           }
			         });
				})
//<!----------------------------------------刪除------------------------------------>
				$('.btn-danger').on('click',function(){	
					var id = $(this).attr('target');
					console.log(id);
			    	var url = "deleteInvo.do";
			    $.ajax({
			           type: "POST",
			           url: url,
			           data: {invoice_id:id},
			           success: function(data)
			           {
			        	   location.reload(); 
			           }
			         });
				})
//<!----------------------------------------  表格         ------------------------------------>
		 		$('#table1').DataTable();

		 	})
		 	
//<!----------------------------------------  修改         ------------------------------------>	
	
	function editdata(event) {
		var label = $(event).parent().parent().find("td:eq(3) > label");
		var oldval = label.html();
		console.log("oldval= "+oldval)
		var edittext = $(event).parent().parent().find("td:eq(3) > input");
		var confbtn = $(event).parent().parent().find("td:eq(4) > button:eq(1)");
		edittext.val(oldval);
		label.hide();
		edittext.show();
		$(event).hide();
		confbtn.show();
	}
	
	function confirm(event){
		
		var id = $(event).parent().parent().find("td:eq(0)").html();
		var ord_id = $(event).parent().parent().find("td:eq(1)").html();
		var new_invoice_number = $(event).parent().parent().find("td:eq(2)").html();
		
		var label = $(event).parent().parent().find("td:eq(3) > label");
		var edittext = $(event).parent().parent().find("td:eq(3) > input").val();
		var editbtn = $(event).parent().parent().find("td:eq(4) > button:eq(0)"); 
		console.log("edittext="+edittext);
		var url = "updateInvo.do";
		
		$.ajax({
			type:"POST",
			url:url,
			data:{
				invoice_id:id,
				ord_id:ord_id,
				new_invoice_number:new_invoice_number,
				new_ord_id:edittext,
				},
			sucess:{}
		})
		
		$(".edittext").hide();
		label.html(edittext);
		console.log("label="+label.html());
		label.show();
		$(event).hide();
		editbtn.show();
		
	}
	
	</script>
</body>
</html>
