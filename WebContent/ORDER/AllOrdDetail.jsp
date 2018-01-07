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

	td{
		text-align: center;
	}
	
	/* 訂單表格標題*/
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background: #4dbcff;
	}
	
	button{
		margin-left: 20px;
		}
</style>

<title>訂單明細</title>

</head>
<body>

		<table class="table table-bordered table-striped table-hover">
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
			</tr>
		</thead>
		
<c:forEach var="list" items="${list}" varStatus="count">
		<form method="post" action="Querydetail_DeleteOrd.do">
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
<!-- 			<td><input type="submit" name="action" value="Delete" ></td> -->
			<input type="hidden" name="ord_id" value="${list.ord_id}">
			
		</tr>
		
</form>
</c:forEach>
	</table>



		<table class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<td>訂單編號 </td>
				<td>商品編號</td>
				<td>商品名稱</td>
				<td>商品數量</td>
				<td>商品價格</td>
			</tr>
		</thead>
		
<c:forEach var="list" items="${detailList}" varStatus="count">
		<form method="post" action="DeleteOrdDetail.do">
		<tr>
			<td>${list.orderVO.ord_id}</td>
			<td>${list.prodVO.prod_id}</td>
			<td>${list.prod_name}</td>
			<td>${list.prod_quantity}</td>
			<td>${list.prod_price}</td>
<!-- 			<td><input type="submit" value="Delete" ></td> -->
			<input type="hidden" name="ord_id" value="${list.orderVO.ord_id}">
			<input type="hidden" name="prod_id" value="${list.prodVO.prod_id}">
			<input type="hidden" name="action" value="DeleteDetail">
			
		</tr>
		
</form>
</c:forEach>
	</table>
		<center>
			<form method="post" action="../ORDER/OrdToShip.do">
<!-- 				<button type="button" name="action" class="btn btn-success" value="返回" onclick="history.back();">← 回訂單</button> -->
				<a href="../ORDER/order.jsp" class="btn btn-info">← 繼續收銀結帳</a>　　
				<a href="../ORDER/ordmain.jsp" class="btn btn-success">回訂單維護</a>
				<button type="submit" class="btn btn-danger">$轉出貨</button>
				<input type="hidden" name="ord_id" value="${ordVO.ord_id}">
				<input type="hidden" name="action" value="toShip">			  
			</form>
			<br>
			<c:if test="${not empty oldOrd_id}">
				<a href="<%=request.getContextPath()%>/INVO/select_page.jsp" class="btn btn-info">作廢發票 ${oldOrd_id} </a>
			</c:if>
		</center>

</body>
</html>