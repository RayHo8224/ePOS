<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>查詢庫存量不足之商品</h3>
	<c:if test="${not empty lessProdlist}">
		<c:forEach items="${lessProdlist}" var="lessProdlist">
				
				編號= ${lessProdlist.prod_id}  品名= ${lessProdlist.prod_name} <BR>

		</c:forEach>
	</c:if>


	<form action="getLowStockProd" method="POST">
		<input type="submit" value="go">
	</form>


</body>
</html>