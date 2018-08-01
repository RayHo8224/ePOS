<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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

	body {
		background: white;
}
	
	#divship{
		background: #3bafaf;
		height: 35px;
		text-align: center;
		font-size: 20px;
		color: white;
 		font-weight: bold; 
	}
	
	/* 表格標題 */
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background: #179090;
	}
	
	/* 表格單數欄位*/
	.table-striped > tbody > tr:nth-child(odd) > td, .table-striped > tbody > tr:nth-child(odd) > th{
		background: white;
	}
	
	/* 表格雙數欄位*/
	.table-bordered > thead > tr > th, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > tbody > tr > td, .table-bordered > tfoot > tr > td{
		background: #eaffff;
		border: 1px solid #1fb9b9;
	}
	
	/* 表格滑過*/
	.table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th{
		background: #57dcdc;
	}
	
	/* 表格框線*/
	table.dataTable{
		border: 1px solid #1fb9b9;
	}
	
	/* 表格頁數*/
	.pagination > .active > a, .pagination > .active > span, .pagination > .active > a:hover, .pagination > .active > span:hover, .pagination > .active > a:focus, .pagination > .active > span:focus{
		background: #179090;
	}
	
	td{
		text-align: center;
	}
	
	/*回上一頁*/
	a{
		margin-top:10px;
		margin-left: 30px;
	}


</style>

<title>出貨單資料</title>


</head>
<body>
		<div id="divship">出貨單</div>
		<table id="ship" class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<td>出貨單編號 </td>
				<td>訂單編號</td>
				<td>出貨日期</td>
				<td>收件人地址</td>
				<td>收件人姓名</td>
				<td>修改人</td>
				<td>修改時間</td>
				<td>備註</td>
				<td>明細</td>
			</tr>
		</thead>
		
<c:forEach var="list" items="${list}" varStatus="count">
		<tr>
			<td>${list.ship_id}</td>
			<td>${list.ord_id}</td>
			<td>${list.ship_date}</td>
			<td>${list.rec_addr}</td>
			<td>${list.rec_name}</td>
			<td>${list.key_id}</td>
			<td>${list.key_date}</td>
			<td>${list.remark}</td>
			<td>
			<form method="post" action="detailDeleteShip.do">
				<button type="submit" name="action" class="btn btn-warning" value="Detail"><i class="glyphicon glyphicon-search"></i></button>
				<input type="hidden" name="ship_id" value="${list.ship_id}">
			</form>
			</td>	
		</tr>	
</c:forEach>
	</table>
	
<!-- 	<a href="../index.jsp">回首頁</a> -->
<!-- 	<a href="javascript:" onclick="history.back(); ">回上頁</a>  -->
	<a href="javascript:" onclick="history.back(); "><i class="glyphicon glyphicon-arrow-left"></i>　回上頁</a>

<script type="text/javascript"
		src="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.js"></script>
		
		<script type="text/javascript">
			
			$("#ship").dataTable();
		
		</script>

</body>
</html>