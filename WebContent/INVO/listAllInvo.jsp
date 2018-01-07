<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.invo.model.*"%>

<%
	InvoService InvoSvc = new InvoService();
	List<InvoVO> list = InvoSvc.getAll();
	pageContext.setAttribute("list",list);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	body{
		background: white;
	}
	 
	a{
		margin-left: 30px;
	}
	
	.titledetail {
		/*     	margin-top:auto; */
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #ff8d3a;
		font-size: 23px;
		border-radius: 2px;
		text-align: center;
	}
	
	/* 	表格標題 */
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background: #ff6c00;
		font-weight:bold;
		color: white;
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

</style>
	
<title>所有發票作廢資料</title>
</head>
<body>
<div class="titledetail">發票作廢</div>
	<table class="table table-bordered table-striped table-hover">
	<thead>
		<tr>
			<td align='center'>發票編號</td>
			<td align='center'>訂單編號</td>
			<td align='center'>新發票編號</td>
			<td align='center'>新訂單編號</td>
			<td align='center'>修改</td>
			<td align='center'>刪除</td>
		</tr>
		</thead>
		<c:forEach var="invoVO" items="${list}">
			<tr align='center' valign='middle'>
				<td>${invoVO.invoice_id}</td>
				<td>${invoVO.ord_id}</td>
				<td>${invoVO.new_invoice_number}</td>
				<td>${invoVO.new_ord_id}</td>

				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/INVO/allForUpdate.do">
			     		<button type="submit" class="btn btn-success"><i class="fa fa-pencil"></i></button>
			     		<input type="hidden" name="invoice_id" value="${invoVO.invoice_id}">
			     		<input type="hidden" name="action"	value="getOne_For_Update">
			  		</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="deleteInvo.do">
			     		<button type="submit" class="btn btn-danger"><i class="fa fa-pencil"></i></button>
			     		<input type="hidden" name="invoice_id" value="${invoVO.invoice_id}">
			     		<input type="hidden" name="action"	value="getOne_For_Update">
			  		</FORM>
				</td>
			</tr>
		</c:forEach>

	</table>

	<a href="select_page.jsp"><i class="glyphicon glyphicon-tags"></i>　發票作廢</a></br>
	<a href="javascript:" onclick="history.back(); "><i class="glyphicon glyphicon-arrow-left"></i>　回上頁</a>
</body>
</html>

