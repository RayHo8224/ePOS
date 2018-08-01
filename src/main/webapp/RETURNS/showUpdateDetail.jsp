<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.returns.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	List<RtnDetailVO> list = (List<RtnDetailVO>)request.getAttribute("list");
--%>
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
<title>退貨單明細</title>

<style type="text/css">

	html{
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
</style>
</head>
<body>
<div class="titledetail">退貨單明細</div>
<table class="table table-bordered table-striped table-hover">
<thead>
<tr>
	<td align='center'>退貨單明細</td><td align='center'>商品名稱</td><td align='center'>退貨原因</td><td td align='center'>退貨數量</td><td align='center'>修改</td><td align='center'>刪除</td>
</tr>
</thead>
<c:forEach var="list" items="${list}">
		<tr align='center' valign='middle'>
			<td>${list.rtnListVO.ret_id}</td>
			<td>${list.rtnItemsVO.prod_name}</td>
			<td>${list.ret_reason}</td>
			<td>${list.prod_quantity}</td>

		<td>
			  <FORM METHOD="post" ACTION="getOne_For_Update_dtl.do">
			     <button type="submit" class="btn btn-success"><i class="fa fa-pencil"></i></button>
			     <input type="hidden" name="ret_id" value="${list.rtnListVO.ret_id}">
			     <input type="hidden" name="prod_name" value="${list.rtnItemsVO.prod_name}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
		</td>
		<td>
			  <FORM METHOD="post" ACTION="delete_dtl.do">
			    <button type="submit" class="btn btn-danger"><i class="fa fa-trash-o "></i></button>
			    <input type="hidden" name="ret_id" value="${list.rtnListVO.ret_id}">
			    <input type="hidden" name="prod_name" value="${list.rtnItemsVO.prod_name}">
			    <input type="hidden" name="action"value="delete">
			  </FORM>
		</td>
		</tr>
		
</c:forEach>
</table>

	<a href="../RETURNS/ReturnList.jsp"><i class="glyphicon glyphicon-th-list"></i>　退貨單</a></br>
	<a href="../RETURNS/Return_Detail.jsp"><i class="glyphicon glyphicon-list-alt"></i>　退貨單明細</a></br>
	<a href="javascript:" onclick="history.back(); "><i class="glyphicon glyphicon-arrow-left"></i>　回上頁</a> 

</body>
</html>