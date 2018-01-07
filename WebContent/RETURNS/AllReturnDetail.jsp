<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<%@ page import="com.returns.model.*"%>

<%
	ReturnDetailService rtnDetailSvc = new ReturnDetailService();
	List<RtnDetailVO> list = rtnDetailSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<html>
 
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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


<style type="text/css">
	html{
		background: white;
	}
	
	.titledetail {
		/*     	margin-top:auto; */
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #a2afde;
		font-size: 23px;
		border-radius: 2px;
		text-align: center;
	}
	
	body{
		background: white;
	}
	
	/* 	表格標題 */
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background: #818ebf;
		font-weight:bold;
	}
	
	/* 	表格內容偶數 */
	.table-bordered > thead > tr > th, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > tbody > tr > td, .table-bordered > tfoot > tr > td{
		background:#dfd5ff;
		border:1px solid #a4b0dc;
		
	}
	/* 	表格內容單數 */
	.table-striped > tbody > tr:nth-child(odd) > td, .table-striped > tbody > tr:nth-child(odd) > th{
		background:white;
	}
	
	/* 	表格偶數滑鼠指向 */
	.table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th{
		background-color:#dfd5ff;
	}
	
	/* 表格頁數*/
	.pagination > .active > a, .pagination > .active > span, .pagination > .active > a:hover, .pagination > .active > span:hover, .pagination > .active > a:focus, .pagination > .active > span:focus{
		background: #818ebf;
		border-color: #bdcbfd;
	}
	
	a{
		margin-top:20px;
		
		margin-left: 30px;
	}

</style>
<title>退貨單</title>

</head>

<body>
	<div>
			<div class="titledetail">退貨單</div>
			<table id="table1" class="table table-bordered table-striped table-hover">
				<thead id="theadlist">
					<tr>
						<td align='center'>退貨單編號</td>
						<td align='center'>商品名稱</td>
						<td align='center'>退貨數量</td>
						<td align='center'>退貨原因</td>
						<td align='center'>修改</td>
						<td align='center'>刪除</td>
					</tr>
				</thead>
				<c:forEach var="list" items="${list}">
					<tr class="table2" align='center' valign='middle'>
						<td>${list.rtnListVO.ret_id}</td>
			<td>${list.rtnItemsVO.prod_name}</td>
			<td>
				<label>${list.prod_quantity}</label>
				<input type="text" name="prod_quantity" class="conftext">
			</td>
			<td>
				<label>${list.ret_reason}</label>
				<input type="text" name="ret_reason" class="conftext">
			</td>

			<td>
<!-- 			  <FORM METHOD="post" ACTION="getOne_For_Update_dtl.do"> -->
			     <button type="submit" class="btn btn-success" onclick="editbtn(this)" target="${list.rtnListVO.ret_id}"><i class="fa fa-pencil"></i></button>
			     <button type="button" class="btn btn-primary" onclick="confirmbtn(this)" ><i class="glyphicon glyphicon-ok"></i></button>
			     <input type="hidden" name="prod_name" value="${list.rtnItemsVO.prod_name}">

			</td>
			<td>
<!-- 			  <FORM METHOD="post" ACTION="delete_dtl.do"> -->
			    <button type="submit" class="btn btn-danger" target="${list.rtnListVO.ret_id}"><i class="fa fa-trash-o "></i></button>

			</td>
		</tr>
			</c:forEach>
			</table>
		</div>
	
	<a href="../RETURNS/ReturnList.jsp"><i class="glyphicon glyphicon-th-list"></i>　退貨單</a></br>
	<a href="../RETURNS/Return_Detail.jsp"><i class="glyphicon glyphicon-list-alt"></i>　退貨單明細</a></br>
	<a href="javascript:" onclick="history.back(); "><i class="glyphicon glyphicon-arrow-left"></i>　回上頁</a>		
		
			<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script>
		!window.jQuery
				&& document
						.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
	</script>

	<script src="<c:url value="../resources/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".conftext").hide();
		$(".btn-primary").hide();
	})
	
	function editbtn(event){
			
			var quantity_label = $(event).parent().parent().find("td:eq(2) > label");
			var quantity_input = $(event).parent().parent().find("td:eq(2) > input");
			var reason_label = $(event).parent().parent().find("td:eq(3) > label");
			var reason_input = $(event).parent().parent().find("td:eq(3) > input");
			var confbtn = $(event).parent().parent().find("td:eq(4) > button:eq(1)");
			var q_label = quantity_label.html();
			var r_label = reason_label.html();
			
			
			quantity_label.hide();
			quantity_input.val(q_label);
			quantity_input.show();
			reason_label.hide();
			reason_input.val(r_label);
			reason_input.show();
			$(event).hide();
			confbtn.show();
			
		};
		
		
		function confirmbtn(event){
			
			var quantity_label = $(event).parent().parent().find("td:eq(2) > label");
			var quantity_input = $(event).parent().parent().find("td:eq(2) > input");
			var quantity_val = quantity_input.val();
			var reason_label = $(event).parent().parent().find("td:eq(3) > label");
			var reason_input = $(event).parent().parent().find("td:eq(3) > input");
			var reason_val = reason_input.val();
			var editbtn = $(event).parent().parent().find("td:eq(4) > button:eq(0)");
			
			var ret_id = $(event).parent().parent().find("td:eq(0)").html();
			var prod_name = $(event).parent().parent().find("td:eq(1)").html();
			
			var url = "update_dtl.do";
			
			$.ajax({
				type:"POST",
				url:url,
				data:{
					ret_id:ret_id,
					prod_name:prod_name,
					prod_quantity:quantity_val,
					ret_reason:reason_val,
				},
				success:{}
			})
			
			quantity_input.hide();
			reason_input.hide();
			quantity_label.html(quantity_val);
			reason_label.html(reason_val);
			quantity_label.show();
			reason_label.show();
			$(event).hide();
			editbtn.show();
			
		};
		
//----------------------------刪除-------------------------------------------
	
		$('.btn-danger').on('click',function(){	
			var delbtn = $(this).parent().parent();
			var id = $(this).attr('target');
			var prod_name = $(this).parent().parent().find("td:eq(4) > input").val();
	    	var url = "delete_dtl.do"; 
	    $.ajax({
	           type: "POST",
	           url: url,
	           data: {
	        	   ret_id:id,
	        	   prod_name:prod_name,
	           },
	           success: function(data)
	           {
	        	   delbtn.remove();
	           }
	         });
		})
		
		
		$('#table1').dataTable();
		

	
	</script>
	
</body>
</html>
