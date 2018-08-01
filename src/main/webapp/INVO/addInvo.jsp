<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.invo.model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>發票作廢資料新增 </title>
</head>
</head>
<body bgcolor='white'>
<!-- 	<a href="select_page.jsp">回首頁</a> -->
	<FORM METHOD="post" ACTION="insertInvo.do" name="form1">
		<table border="0">
			<tr>
				<td>發票編號:</td>
				<td><input type="TEXT" name="invoice_id" size="45"
					value="${invoVO.invoice_id}" /></td>
			</tr>
			<tr>
				<td>訂單編號:</td>
				<td><input type="TEXT" name="ord_id" size="45"
					value="${invoVO.ord_id}" /></td>
			</tr>
			<tr>
				<td>新發票編號:</td>
				<td><input type="TEXT" name="new_invoice_number" size="45"
					value="${invoVO.new_invoice_number}" /></td>
			</tr>
			<tr>
				<td>新訂單編號:</td>
				<td><input type="TEXT" name="new_ord_id" size="45"
					value="${invoVO.new_ord_id}" /></td>
			</tr>
		</table>
		<br> 
<!-- 		<input type="hidden" name="action" value="insert">  -->
		<input type="submit" value="送出新增">
	</FORM>

</body>

</html>