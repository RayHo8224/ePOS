<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>進貨單+明細</title>

</head>
<body>

		<table border="2">
		<tr>
			<th>進貨單編號 </th>
			<th>採購單編號</th>
			<th>進貨日期</th>
			<th>廠商名稱</th>
			<th>修改人員</th>
			<th>修改日期</th>
			<th>備註</th>
			<th>狀態</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		
<c:forEach var="list" items="${list}" varStatus="count">
		<form method="post" action="DetailUpdateDeleteBOP.do">
		<tr>
			<td>${list.bop_id}</td>
			<td>${list.pur_id}</td>
			<td>${list.bop_date}</td>
			<td>${list.com_id}</td>
			<td>${list.key_id}</td>
			<td>${list.key_date}</td>
			<td>${list.remark}</td>
			<td>${list.status}</td>
			<td><input type="submit" name="action" value="update" ></td>
			<td><input type="submit" name="action" value="delete" ></td>
			<input type="hidden" name="bop_id" value="${list.bop_id}">
			
		</tr>
		
</form>
</c:forEach>
	</table>

<body>

		<table border="2">
		<tr>
			<th>商品編號 </th>
			<th>商品名稱</th>
			<th>商品數量</th>
			<th>商品價格</th>
			<th>刪除明細</th>
		</tr>
		
<c:forEach var="list" items="${detailList}" varStatus="count">
		<form method="post" action="deleteDetailBOP.do">
		<tr>
			<td>${list.prod_id}</td>
			<td>${list.prod_name}</td>
			<td>${list.prod_quantity}</td>
			<td>${list.prod_price}</td>
			<td><input type="submit" value="刪除明細" ></td>
			<input type="hidden" name="bop_id" value="${list.bopVO.bop_id}">
			<input type="hidden" name="prod_id" value="${list.prod_id}">
			
		</tr>
		
</form>
</c:forEach>
	</table>
		
	<a href="../index.jsp">回首頁</a>
	<a href="javascript:" onclick="history.back(); ">回上頁</a> 

</body>
</html>