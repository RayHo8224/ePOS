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
<title>�s�W�h�f�����</title>
</head>
<body>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<Form METHOD="post" ACTION="insert_dtl.do" name="form1">
<h2>�s�W�h�f�����</h2>
<table border="0">
		<tr>
			<td>�h�f��s��:</td>
			<td><input type="TEXT" name="ret_id" size="45" 
			value="RE2016" /></td>
		</tr>
		<tr>
			<td>�ӫ~�W��:</td>
			<td><input type="TEXT" name="prod_name" size="45" 
			value="iphone 6 16G �¦�" /></td>
		</tr>
		<tr>
			<td>�h�f�ƶq:</td>
			<td><input type="TEXT" name="prod_quantity" size="45" 
			value="5" /></td>
		</tr>
		<tr>
			<td>�h�f��]:</td>
			<td><input type="TEXT" name="ret_reason" size="45" 
			value="����Ӭ�" /></td>
		</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

	<a href="../index.jsp">�^����</a>
	<a href="javascript:" onclick="history.back(); ">�^�W��</a> 

</body>
</html>