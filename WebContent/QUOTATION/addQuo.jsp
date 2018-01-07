<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增詢價單</title>
</head>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty param.message}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${param.message}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<h1>詢價單</h1>

<form method="post" action="insertQuo.do" id="form1">

<table border="0">
	<tr>
		<td>詢價單編號 <input type="text" name="quo_id" value="由資料庫產生" readonly="readonly" /></td></tr>
		<tr><td>請購單編號<input type="text" name="req_id" value="R2016092900005"></td></tr>
		<tr><td>備註<input type="text" name="remark" value="test"></td></tr>
		<tr><td>狀態<input type="text" name="status" value="Y"></td></tr>
</table>
<hr><hr>
<input type="button" value="新增明細" id="addNewDetail">
<input type="submit" value="送出"/>
<!-- <input type="hidden" name="action" value="insert"> -->
<table border="0">
	<tr>
		<td>詢價單編號 <input type="text" name="quo_id1" value="由資料庫產生" readonly="readonly" /></td></tr>
		<tr><td>商品名稱<input type="text" name="prod_name1" value="iphone 7 128G 黑色"></td></tr>
		<tr><td>商品數量<input type="text" name="prod_quantity1" value="1"></td></tr>
		<tr><td>廠商名稱<input type="text" name="com_name1" value="德儀數位"></td></tr>
		<tr><td>商品成本<input type="text" name="prod_cost1" value="27000"></td></tr>
		<tr><td>交貨日期<input type="date" name="delivery_date1" value="2016-10-14"></td></tr>
		<tr><td>備註<input type="text" name="remark1" value="test"></td></tr>
		<tr><td>修改人員<input type="text" name="key_id1" value="E00001"></td></tr>
		<tr><td>修改日期<input type="date" name="key_date1" value="2016-10-10"></td></tr>
		<tr><td><input type='button' value='刪除' class='btn btn-danger'></input></td></tr>
</table>

</form>

<!-- <script src="../js/jquery-3.1.1.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

	<script>
	$("#form1").on('click', '.btn-danger', function() {
		$(this).parents("table").remove();
	})
		$(function() {   
			var a=2;
			$("#addNewDetail").click(function() {$("#form1").append(
					"</br><table border='0'>"
					+"<tr><td>詢價單編號 <input type='text' name='quo_id"+a+"' value='由資料庫產生' readonly='readonly'/></td></tr>"
					+"<tr><td>商品名稱<input type='text' name='prod_name"+a+"'/ ></td></tr>"
					+"<tr><td>商品數量<input type='text' name='prod_quantity"+a+"'/></td></tr>"
					+"<tr><td>廠商名稱<input type='text' name='com_name"+a+"' /></td></tr>"
					+"<tr><td>商品成本<input type='text' name='prod_cost"+a+"' /></td></tr>"
					+"<tr><td>交貨日期<input type='date' name='delivery_date"+a+"' /></td></tr>"
					+"<tr><td>備註<input type='text' name='remark"+a+"' /></td></tr>"
					+"<tr><td>修改人員<input type='text' name='key_id"+a+"' /></td></tr>"
					+"<tr><td>修改日期<input type='date' name='key_date"+a+"' /></td></tr>"
					+"<tr><td><input type='button' value='刪除' class='btn btn-danger'></input></td></tr>"
					+"</table>")
					a=a+1;
			})

		})
		
	</script>

	<a href="../index.jsp">回首頁</a>
	<a href="javascript:" onclick="history.back(); ">回上頁</a> 

</body>
</html>