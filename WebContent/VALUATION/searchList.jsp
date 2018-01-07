<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.css" />
<style>
	.divser{
		font-size: 20px;
		background: #ffb400;
		font-family: 微軟正黑體;
		text-align: center;
		color:white;
	}
	
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background-color:#fbbbbb;
	
	}
	
	th{
		text-align: center;
	}


</style>
<title>查詢報價單</title>
</head>
<body>
	<div class="divser">報價單查詢</div>
	<div style="height: 20px;"></div>
	<form method="post" action="getVltDate.do" >

		<table>
			<tr>
				<th>日期範圍(起)</th>
				<th>日期範圍(迄)</th>
				<th>　</th>
<!-- 				<th>查詢</th> -->
			</tr>
			<tr>
				<td><input type="date" name="dateBegin" value="2016-09-14" class="form-control"></td>
				<td><input type="date" name="dateEnd" value="2016-10-16" class="form-control"></td>
				<td></td>
				<td>
					 <button type="submit"class="btn btn-success"><i class="glyphicon glyphicon-search"></i></button>
					 <input type="hidden"name="action" value="SelectByDate">					
				</td>
			</tr>
		</table>
		<br>

	</form>

	<form method="post" action="getByVlt_id.do">
		<table>
			<tr>
				<th>報價單編號</th>
				<th>　</th>
<!-- 				<th>查詢</th> -->
			</tr>
			<tr>
				<td><input type="text" name="vlt_id" class="form-control"></td>
				<td></td>
				<td><button type="submit"class="btn btn-info"><i class="glyphicon glyphicon-search"></i></button></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="getByVlt_id">
	</form>

	<!-- 	<a href="javascript:" onclick="history.back(); ">回上頁</a>  -->
</body>
</html>