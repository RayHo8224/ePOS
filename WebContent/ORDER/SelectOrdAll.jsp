<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.*"%>

<%
 	OrderService ordSvc = new OrderService();
 	List<OrderVO> list = ordSvc.getAll();
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

	.table > thead:first-child > tr:first-child > td {
		height: 20px;
  		background:#007bb7;
  		color:white;
  		border-top: 0;
  		font-family: 微軟正黑體;
  		font-size: 16px;
	}

	/* 	表格內容單數 */
	.table-striped > tbody > tr:nth-child(odd) > td, .table-striped > tbody > tr:nth-child(odd) > th{
		background-color:white;
	}
	/* 	表格內容偶數 */
	.table-bordered > thead > tr > th, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > tbody > tr > td, .table-bordered > tfoot > tr > td{
		border:1px solid #3393c1;
		background:#dcf3ff;
		height: 30px;
		font-weight: bold;
	}
	/* 	表格偶數滑鼠指向 */
	.table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th{
		background-color:#dcf3ff;
	}
	
	td{
		text-align: center;
	}

</style>
<title>所有訂單資料</title>

</head>
<body>
		<table id="table2" class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<td>訂單編號 </td>
				<td>訂單日期</td>
				<td>發票號碼</td>
				<td>統一編號</td>
				<td>身分</td>
				<td>實際金額</td>
				<td>折價券金額</td>
				<td>應付金額(現金)</td>
				<td>修改人員編號</td>
				<td>建檔日期</td>
				<td>班別</td>
				<td>備註</td>
				<td>狀態</td>
				<td>明細</td>
			</tr>
		</thead>
		
<c:forEach var="list" items="${list}" varStatus="count">
		<tr>
			<td>${list.ord_id}</td>
			<td>${list.ord_date}</td>
			<td>${list.invoice_id}</td>
			<td>${list.ord_um}</td>
			<td>${list.discount}</td>
			<td>${list.total_price}</td>
			<td>${list.cpon_dollar}</td>
			<td>${list.cash}</td>
			<td>${list.key_id}</td>
			<td>${list.key_date}</td>
			<td>${list.shift}</td>
			<td>${list.remark}</td>
			<td>${list.status}</td>
			<td>
				<form method="post" action="Querydetail_DeleteOrd.do">
					<button type="submit" name="action" class="btn btn-success" value="Detail"><i class="glyphicon glyphicon-search"></i></button>
					<input type="hidden" name="ord_id" value="${list.ord_id}">
				</form>
			</td>
		</tr>		
</c:forEach>
	</table>

	
	
<!-- <script type="text/javascript" src="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.js"></script> -->

<script type="text/javascript">
	$('#table2').dataTable();
</script>

</body>
</html>