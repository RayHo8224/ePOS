<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.returns.model.*"%>

<%--
	RtnDetailVO rtnDetailVO = (RtnDetailVO) request.getAttribute("ret_id");
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增退貨單明細</title>
</head>
<body>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<Form METHOD="post" ACTION="insert_dtl.do" name="form1">
<h2>新增退貨單明細</h2>
<table border="0">
		<tr>
			<td>退貨單編號:</td>
			<td><input type="TEXT" name="ret_id" size="45" 
			value="RE2016" /></td>
		</tr>
		<tr>
			<td>商品名稱:</td>
			<td><input type="TEXT" name="prod_name" size="45" 
			value="iphone 6 16G 黑色" /></td>
		</tr>
		<tr>
			<td>退貨數量:</td>
			<td><input type="TEXT" name="prod_quantity" size="45" 
			value="5" /></td>
		</tr>
		<tr>
			<td>退貨原因:</td>
			<td><input type="TEXT" name="ret_reason" size="45" 
			value="不能照相" /></td>
		</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

	<a href="../index.jsp">回首頁</a>
	<a href="javascript:" onclick="history.back(); ">回上頁</a> 

</body>
</html>