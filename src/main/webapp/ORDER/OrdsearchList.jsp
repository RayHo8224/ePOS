<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	
	th{
		text-align: center;
	}
	
	#form1{
		margin-top: 50px;
	}
	
	#form2{
		margin-top: 50px;
	}
</style>
<title>查詢訂單</title>
</head>
<body>
	
	<form id="form1" method="post" action="getOrderDate.do">

		<table>
			<tr>
				<th>日期範圍(起)</th>
				<th>日期範圍(迄)</th>
				<th>　</th>
			</tr>
			<tr>
				<td><input type="date" name="dateBegin" value="2016-09-14" class="form-control"></td>
				<td><input type="date" name="dateEnd" value="2016-10-16" class="form-control"></td>
				<td></td>
				<td>
					 <button type="submit"class="btn btn-info"><i class="glyphicon glyphicon-search"></i></button>
					 <input type="hidden"name="action" value="SelectByDate">						
				</td>
			</tr>
		</table>
	</form>
	
	<form id="form2" method="post" action="getByOrd_id.do" class="form-inline">
		<table>
			<tr>
				<th>訂單編號：</th>
				<th>　</th>
			</tr>
			<tr>
				<td><input type="text" name="ord_id" class="form-control"></td>
				<td></td>
				<td><button type="submit"class="btn btn-info"><i class="glyphicon glyphicon-search"></i></button></td>
			</tr>
		</table>
		<input type="hidden"name="action" value="getByOrd_id">
	</form>



</body>
</html>