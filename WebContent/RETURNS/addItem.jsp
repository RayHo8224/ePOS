<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.returns.model.*"%>

<%
   RtnItemsVO rtnItemsVO = (RtnItemsVO) request.getAttribute("prod_name");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>�s�W�h�f�~</title>
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
<Form METHOD="post" ACTION="insert_Item.do" name="form1">
<h2>�s�W�h�f�~</h2>
<table border="0">
		<tr>
			<td>�ӫ~�W��</td>
			<td><input type="TEXT" name="prod_name" size="45" 
			value="<%= (rtnItemsVO==null)? "Note 5 128G ����" : rtnItemsVO.getProd_name()%>" /></td>
		</tr>
		<tr>
			<td>�t�ӥN��</td>
			<td><input type="TEXT" name="com_id" size="45" 
			value="<%= (rtnItemsVO==null)? "C00002" : rtnItemsVO.getCom_id()%>" /></td>
		</tr>
		<tr>
			<td>�h�f�ƶq</td>
			<td><input type="TEXT" name="re_quantity" size="45" 
			value="<%= (rtnItemsVO==null)? "10" : rtnItemsVO.getRe_quantity()%>" /></td>
		</tr>
		<tr>
			<td>�Ƶ�</td>
			<td><input type="TEXT" name="remark" size="45" 
			value="<%= (rtnItemsVO==null)? " " : rtnItemsVO.getRemark()%>" /></td>
		</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

	<a href="../index.jsp">�^����</a>
	<a href="javascript:" onclick="history.back(); ">�^�W��</a> 

</body>
</html>