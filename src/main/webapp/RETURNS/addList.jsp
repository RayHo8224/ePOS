<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.returns.model.*"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	     --%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%--
	RtnListVO rtnListVO = (RtnListVO) request.getAttribute("Ret_id");
	RtnDetailVO rtnDetailVO = (RtnDetailVO) request.getAttribute("ret_id");
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增退貨單</title>
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

<FORM METHOD="post" ACTION="insert.do" name="form1">
<h2>新增退貨單</h2><input type="submit" value="送出新增">
<table border="0">
	<tr>
		<td>退貨單編號：</td>
<%-- 		<td><input type="TEXT" name="ret_id" size="40" value="<%= (rtnListVO==null)? "RE2016010100001" : rtnListVO.getRet_id()%>" disabled/></td> --%>
		<td><input type="TEXT" name="ret_id" size="40" disabled/></td>
		<td>退貨日期：</td>
		<td><input type="date" name="ret_date" size="40" value="2016-10-14" /></td>
		<td>廠商編號：</td>
		<td><input type="TEXT" name="com_id" size="40" value="C00001" /></td>
		<td>廠商名稱：</td>
		<td><input type="TEXT" name="com_name" size="40" value="德儀數位" /></td>
	</tr>
	<tr>
		<td>修改人員：</td>
		<td><input type="TEXT" name="key_id" size="40" value="E00001" /></td>
		<td>修改日期：</td>
		<td><input type="Date" name="key_date" size="40" value="2016-10-14" /></td>
		<td>備註：</td>
		<td><input type="TEXT" name="remark" size="40" value="test" /></td>
		<td>狀態：</td>
		<td><input type="TEXT" name="status" size="40" value="Y"/></td>
	</tr>
</table>
<h2>新增退貨單明細</h2><input type="button" value="新增明細" id="addNewDetail">
<table border="0" id="form1">
	<tr>
<!-- 		<td>退貨單編號：</td> -->
<!-- 		<td><input type="TEXT" name="ret_id1" size="40" value="RE2016101500001"/></td> -->
		<td>商品名稱：</td>
		<td><input type="TEXT" name="prod_name1" size="40" value="iphone 6 16G 黑色" /></td>
		<td>退貨數量：</td>
		<td><input type="TEXT" name="prod_quantity1" size="25" value="100" /></td>
		<td>退貨原因：</td>
		<td><input type="TEXT" name="ret_reason1" size="40" value="不能照相"/></td>
		<td><input type='button' value='刪除' class='btn btn-danger'></input></td>
		</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
</FORM>
<!-- <script src="../js/jquery-3.1.1.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script> 
!window.jQuery && document.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
 </script>

<!-- "<tr><td>退貨單編號：</td><td><input type='TEXT' name='ret_id"+a+"' size='40' /></td>" -->
<script>

	$("#form1").on('click', '.btn-danger', function() {
		$(this).parents("tr").remove();
	})
	
	$(function() {
		var a = 2;
		$("#addNewDetail")
				.click(
						function() {
							$("#form1")
									.append(
											"<tr><td>商品名稱：</td><td><input type='TEXT' name='prod_name"+a+"' size='40' /></td>"
													+ "<td>退貨數量：</td><td><input type='TEXT' name='prod_quantity"+a+"' size='25'/></td>"
													+ "<td>原因：</td><td><input type='TEXT' name='ret_reason"+a+"' size='40'/></td>"
													+ "<td><input type='button' value='刪除' class='btn btn-danger'></input></td></tr>"
													)
							a = a + 1;
						})
	})
</script>

	<a href="../index.jsp">回首頁</a>
	<a href="javascript:" onclick="history.back(); ">回上頁</a> 

</body>
</html>