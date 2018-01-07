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
<title>查詢退貨單明細</title>
<style>
	.divser{
		font-size: 20px;
		background: #818ebf;
		font-family: 微軟正黑體;
		font-weight:bold;
		text-align: center;
		color:white;
	}
	
/* 	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{ */
/* 		background-color:#818ebf; */
	
	}

</style>

</head>
<body>
	<div class="divser">查詢</div>
	<div style="height: 20px"></div>
	
	<form action="getId.do" method="post" class="form-inline">
  		<div class="form-group">
    		<label class="sr-only">退貨單編號</label>
    		<p class="form-control-static"><font size="4" face="微軟正黑體" color="blue">退貨單編號：</font></p>
  		</div>
  		<div class="form-group">
    		<label for="inputPassword2" class="sr-only"></label>
    		<input type="text" name='ret_id' class="form-control" id="inputPassword2" >
  		</div>
  		<button type="submit" class="btn btn-default">查詢</button>
	</form>
	<form action="getName.do" method="post" class="form-inline">
  		<div class="form-group">
    		<label class="sr-only">產品名稱</label>
    		<p class="form-control-static"><font size="4" face="微軟正黑體" color="blue">產品名稱：</font></p>
  		</div>
  		<div class="form-group">
    		<label for="inputPassword2" class="sr-only"></label>
    		<input type="text" name='prod_name' class="form-control" id="inputPassword2" >
  		</div>
  		<button type="submit" class="btn btn-default">查詢</button>
	</form>	
	 	<c:if test="${not empty errorMsgs}"><font color='red'>請修正錯誤:<c:forEach var="message" items="${errorMsgs}"><span>${message}</span></c:forEach></c:if></font>
	

</body>
</html>