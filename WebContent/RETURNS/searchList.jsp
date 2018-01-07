<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.returns.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/bootstrap-theme.min.css" rel="stylesheet">
<title>Insert title here</title>
<style>
	.divser{
		font-size: 20px;
		background: #ff7d7d;
		font-family: 微軟正黑體;
		font-weight:bold;
		text-align: center;
		color:white;
	}
	
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background-color:#fbbbbb;
	
	}

</style>

</head>
<body>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- <font color='red'>請修正以下錯誤: -->
<!-- <ul> -->
<%-- 	<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 		<li>${message}</li> --%>
<%-- 	</c:forEach> --%>
<!-- </ul> -->
<!-- </font> -->
<%-- </c:if> --%>
	<div class="divser">查詢</div>
	<div style="height: 20px"></div>
	
		<form action="getOne.do" method="post" class="form-inline">
		<div class="form-group">
	 		<label style="font-family: 微軟正黑體; font-size: 20px;" for="exampleInputName2">退貨單編號：</label>
	 		<input style="width: 50%" type='text' name='ret_id' class="form-control"/>
	 	</div>
		 	<button type="submit" class="btn btn-danger">查詢</button>
		 	<input type="hidden" name="action" value="getOne">
		</form>
	 	<div style="height: 20px;"></div>
	 <form action="getDate.do" method="post" class="form-inline">
	 	<div class="form-group">
	 		<label style="font-family: 微軟正黑體; font-size: 20px;" for="exampleInputName2">退貨日期：</label>　　
	 		<input style="width: 50%" type='date' name='ret_date' class="form-control"/>
	 	</div>　 
	 	<input type="submit" value="查詢" class="btn btn-danger">
	 	<input type="hidden" name="action" value="getDate">
	 </form>
	 <div style="height: 20px;"></div>
	 <form action="getComName.do" method="post" class="form-inline">
	 	 <div class="form-group">	 
	 		<label style="font-family: 微軟正黑體; font-size: 20px;" for="exampleInputName2">廠商名稱：</label>　 
	 		<input style="width: 50%" type='text' name='com_name' class="form-control"/>
	 	</div>
	 		<input type="submit" value="查詢" class="btn btn-danger"> 
	 		<input type="hidden" name="action" value="getComName">
	 </form>
	 	<c:if test="${not empty errorMsgs}"><font color='red'>請修正錯誤:<c:forEach var="message" items="${errorMsgs}"><span>${message}</span></c:forEach></c:if></font>
	

</body>
</html>