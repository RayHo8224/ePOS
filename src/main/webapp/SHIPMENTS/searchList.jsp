<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.css" />
<style>
	.divser{
		font-size: 20px;
		background: #3bafaf;
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
<title>查詢出貨單</title>
</head>
<body>

	<div class="divser">出貨單查詢</div>
	<div style="height: 20px;"></div>
	

<form method="post" action="getShipByShipId.do" class="form-inline">

	<div class="form-group">
		<label for="exampleInputName2">出貨單編號：</label>
		<input type="text" name="ship_id" class="form-control">　
		<button type="submit"class="btn btn-primary"><i class="glyphicon glyphicon-search"></i></button>				 
	</div> 　
</form>

<div style="height: 30px;"></div>
<form method="post" action="getShipByOrdId.do" class="form-inline">
	
	<div class="form-group">
		<label for="exampleInputName2">訂單編號：</label>　
		<input type="text" name="ord_id" class="form-control">　
		<button type="submit"class="btn btn-primary"><i class="glyphicon glyphicon-search"></i></button>				 
	</div> 　

</form>


<div style="height: 30px;"></div>
<form method="post" action="getShipByDate.do">

<table>
		<tr>
			<th>日期範圍(起)</th>
			<th>日期範圍(迄)</th>
		</tr>
		<tr>
		    <td><input type="date" name="dateBegin" value="2016-09-16" class="form-control"></td>
			<td><input type="date" name="dateEnd" value="2016-10-16" class="form-control"></td>
			<td>　</td>
			<td>
				<button type="submit"class="btn btn-primary"><i class="glyphicon glyphicon-search"></i></button>			
			</td>
		</tr>
</table>
</form>
<!-- 	<a href="../index.jsp">回首頁</a> -->
<!-- 	<a href="javascript:" onclick="history.back(); ">回上頁</a>  -->

</body>
</html>