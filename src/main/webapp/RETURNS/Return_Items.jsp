<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<%@ page import="com.returns.model.*"%>

<%
	ReturnItemsService rtnItemSvc = new ReturnItemsService();
	List<RtnItemsVO> list = rtnItemSvc.getAll();
	pageContext.setAttribute("list", list);
	
	session.getAttribute("LoginOK");
%>
<html>
<head>
<SCRIPT LANGUAGE="JavaScript">

function varitext(text){
text=document
print(text)
}

</script>



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
<title>退貨品</title>

<style>

	.titledetail {
	/*     	margin-top:auto; */
	font-family: '微軟正黑體';
	font-weight: bold;
	color: white;
	height: 35px;
	background: #2B74C6;
	font-size: 23px;
	border-radius: 2px;
}

	.main {
	    height: 230px;
	    border-radius: 8px;
	    background: #ECFFFF;
	}
	
	#a{
		margin-left: 30px;
	}
	
	#sub{
		margin-left: 30px;
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
			<li class="sub-menu"><a href="javascript:;" class="active"> <i
					class="glyphicon glyphicon-log-out"></i> <span>退貨作業</span>
			</a>
				<ul class="sub">
					<li class="active"><a
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
		</ul>
		<!-- sidebar menu end-->
	</div>
	</aside> <!--sidebar end--> <section id="main-content"> <section
		class="wrapper"> <!-- -----------------------------------------------------------查詢----------------------------------------------------------- -->
	
	<div class="row mt">
	<div class="col-sm-12">
		<div id="add" class="main">
			<div class="tab-content">
				<nav class="alert alert-info">
				<div>
				<a id="add" href="#"><span class="glyphicon glyphicon-file"></span>新增</a>
		    	<a id="a" href="#" onclick="window.open('searchItem.jsp', 'Yahoo', config='height=500,width=850')"><span class="glyphicon glyphicon-search"></span>查詢</a>
		    	<a id="a" id="print" href="javaScript:varitext()"><span class="glyphicon glyphicon-print" ></span>列印</a>
		    	<a id="sub" href="#"><span class="glyphicon glyphicon-ok-sign">送出</span></a>
				</div>

				</nav>
			</div>


			<FORM id="itemsform" METHOD="post" ACTION="insert_Item.do" class="form-inline">
				<div class="form-group">
					<label for="exampleInputName2">　商品名稱：</label>
				    <select type="TEXT" name="prod_name_select1" id="prod_name_select1" class="form-control">
				    <option value="">請選擇商品</option></select>
				    <input type="hidden" name="prod_name" id="prod_name">
				</div> 　　
				<div class="form-group">
					<label for="exampleInputName2">廠商代號：</label>
					<select type="TEXT" id="com_id_select" name="com_id_select" class="form-control">
					<option value="">請選擇廠商代號</option></select>
					<input type="hidden" name="com_id" id="com_id" class="form-control">
				</div>　　
				<div class="form-group">
					<label for="exampleInputEmail2">退貨數量：</label> <input type="text"
						name="re_quantity" class="form-control">
				</div>　　
				<div class="form-group">
					<label for="exampleInputName2">備註：</label> <input type="text"
						name="remark" class="form-control">
				</div>
				<div>
					<input type="hidden" name="shift" value="${SHIFT}" >
					<input type="hidden" name="emp_id" value="${LoginOK.emp_id}">
				</div>
			</FORM>

		</div>
		<!-- -----------------------------------------------------------表格----------------------------------------------------------- -->
		<div>
			<div class="titledetail">退貨品</div>
			<table id="table1"
				class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<td align='center'>商品名稱</td>
						<td align='center'>廠商代號</td>
						<td align='center'>退貨數量</td>
						<td align='center'>備註</td>
						<td align='center'>修改</td>
						<td align='center'>刪除</td>
					</tr>
				</thead>
				<c:forEach var="RtnItemsVO" items="${list}">
					<tr class="table2" align='center' valign='middle'>
						<td>${RtnItemsVO.prod_name}</td>
						<td>${RtnItemsVO.com_id}</td>
						<td>
							<label>${RtnItemsVO.re_quantity}</label>
							<input type="text" name="re_quantity" class="conftext">
						</td>
						<td>
							<label>${RtnItemsVO.remark}</label>
							<input type="text" name="remark" class="conftext">
						</td>
						<td>
								<!-- getOne_For_Update_Item.do -->
							<button type="button" class="btn btn-success" onclick="editbtn(this)" target="${RtnItemsVO.prod_name}"><i class="fa fa-pencil"></i></button>
							<button type="button" class="btn btn-primary" onclick="confirmbtn(this)" ><i class="glyphicon glyphicon-ok"></i></button>
						</td>
						<td>
<!-- 							<FORM id="del" METHOD="post" ACTION="delete_Item.do"> -->
			    <button type="submit" target="${RtnItemsVO.prod_name}" class="btn btn-danger"><i class="fa fa-trash-o "></i></button>
<%-- 			    <input type="hidden" name="prod_name" value="${RtnItemsVO.prod_name}"> --%>
<!-- 			    <input type="hidden" name="action"value="delete"> -->
<!-- 			  </FORM> -->
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
		!window.jQuery&& document.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
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
				$(".conftext").hide();
				$(".btn-primary").hide();
			})
	
	
		 	$(function () {
		 		
		 		$.getJSON("getProd_DDL_itm.do",{},function(data){
					$.each(data,function(){
						var SelectValue = this.SelectValue;
						var SelectText = this.SelectText;
						var opt=$("<option></option>").val(SelectValue).text(SelectText);
						$('#prod_name_select1').append(opt);
					})
		 		
						$('#prod_name_select1').change(function(){
					 		var values = $('#prod_name_select1').val();
					 		document.getElementById('prod_name').value=values;
						})
						
						
				$.getJSON("getCom_DDL_com.do",{},function(data){
					$.each(data,function(){
						var SelectValue = this.SelectValue;
						var SelectText = this.SelectText;
						var opt=$("<option></option>").val(SelectValue).text(SelectText);
						$('#com_id_select').append(opt);
					})
		 		
						$('#com_id_select').change(function(){
					 		var values = $('#com_id_select').val();
					 		document.getElementById('com_id').value=values;
						})
// <!----------------------------------------  新增         ------------------------------------>
// 				$('#add').on('click',function(){
// 				var url = "Return_Items.jsp"; 
// 				    $.ajax({
// 				           type: "GET",
// 				           url: url,
// 				           success: function(data)
// 				           {
// 				               location.reload(); 
// 				           }
// 				         });
// 					})	

				$('#sub').on('click',function(){
			    var url = "insert_Item.do"; 
			    $.ajax({
			           type: "POST",
			           url: url,
			           data: $("#itemsform").serialize(), 
			           success: function(data)
			           {
			        	   location.reload();
			           }
			         });
				})
			
//<!----------------------------------------刪除------------------------------------>
				$('.btn-danger').on('click',function(){	
					var id = $(this).attr('target');
			    	var url = "delete_Item.do"; 
			    $.ajax({
			           type: "POST",
			           url: url,
			           data: {prod_name:id},
			           success: function(data)
			           {
			        	   location.reload(); 
			           }
			         });
				})
//<!----------------------------------------  表格         ------------------------------------>
		 		$('#table1').DataTable();

		 	})
		 })	 	
	})		
//<!----------------------------------------  修改        ------------------------------------>
				function editbtn(event){
					
					var quantity_label = $(event).parent().parent().find("td:eq(2) > label");
					var quantity_input = $(event).parent().parent().find("td:eq(2) > input");
					var remark_label = $(event).parent().parent().find("td:eq(3) > label");
					var remark_input = $(event).parent().parent().find("td:eq(3) > input");
					var confbtn = $(event).parent().parent().find("td:eq(4) > button:eq(1)");
					var q_label = quantity_label.html();
					var r_label = remark_label.html();
					
					
					quantity_label.hide();
					quantity_input.val(q_label);
					quantity_input.show();
					remark_label.hide();
					remark_input.val(r_label);
					remark_input.show();
					$(event).hide();
					confbtn.show();
					
				};
				
				
				function confirmbtn(event){
					
					var quantity_label = $(event).parent().parent().find("td:eq(2) > label");
					var quantity_input = $(event).parent().parent().find("td:eq(2) > input");
					var quantity_val = quantity_input.val();
					var remark_label = $(event).parent().parent().find("td:eq(3) > label");
					var remark_input = $(event).parent().parent().find("td:eq(3) > input");
					var remark_val = remark_input.val();
					var editbtn = $(event).parent().parent().find("td:eq(4) > button:eq(0)");
					
					var prod_name = $(event).parent().parent().find("td:eq(0)").html();
					var com_id = $(event).parent().parent().find("td:eq(1)").html();
					
					var url = "update_Item.do";
					
					$.ajax({
						type:"POST",
						url:url,
						data:{
							prod_name:prod_name,
							com_id:com_id,
							re_quantity:quantity_val,
							remark:remark_val,
						},
						success:{}
					})
					
					quantity_input.hide();
					remark_input.hide();
					quantity_label.html(quantity_val);
					remark_label.html(remark_val);
					quantity_label.show();
					remark_label.show();
					$(event).hide();
					editbtn.show();
					
				};		 	
	</script>
</body>
</html>
