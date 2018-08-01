<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%		
 		Date nowDate =new java.sql.Date(System.currentTimeMillis());
 		pageContext.setAttribute("nowDate",nowDate);
 		
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改請購單</title>

<link href="<c:url value="../resources/css/bootstrap.css" />"
	rel="stylesheet">

</head>
<style>


.my-error-class {
	color: #1dc489;
}

.my-valid-class {
	color: #3a51e8;
}
</style>
<body>
	<section id="container"> <section class="wrapper">
	<div class="row mt">

		<nav class="nav navbar-default">
		<div class="container-fluid"
			style="float: left;">
			<ul class="nav navbar-nav"
				style="float: left;">
				<li style="background-color: rgba(221, 15, 15, 0.8);"><a
					data-toggle="tab" style="color: white;">新增詢價單</a></li>
				<li><a id="quo2" target="SelectQuo.jsp" href="javascript: void(0)">單筆查詢</a></li>
				<li><a id="quo3" target="getAllQuo.do" href="javascript: void(0)">全部查詢</a></li>
				<li><a id="quo4" target="SelectbyDate.jsp" href="javascript: void(0)">依日期查詢</a></li>
				<li><a id="quo5" target="selectOfN.do" href="javascript: void(0)">審核</a></li>
				<li><a id="quo6" target="selectOfY.do" href="javascript: void(0)">新增商品目錄</a></li>
			</ul>
		</div>
		</nav>
		<div
			style="background-color: rgba(66, 134, 244, 0.3); position: relativve; height: 750px; overflow: auto;">
			<!-- 	<br> -->
			<h1>請購轉詢價單</h1>
			<hr>

			<form method="post" action="REQUISITION/insertReq.do" id="form1">

				<table border="0" id="table1"
					class="table table-bordered table-striped table-hover">
					<tr>
						<td>&nbsp;&nbsp;詢價單號：<input type="text" name="quo_id"
							value="系統產生" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;請購單號：<input type="text" name="req_id"
							id="req_id" value="${reqVO.req_id}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;建檔人員：<input type="text" name="key_id"
							value="${sessionScope.LoginOK.emp_id }" readonly="readonly"
							id="key_id"></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;建檔日期：<input type="text" name="key_date"
							value="${nowDate}" id="theDate" style="width: 199px;" readonly="readonly"></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;廠商名稱：<select name="com_name"
							style="width: 199px; height: 30px;">
								<c:forEach var="comVO" items="${list }" varStatus="status">
									<option>${comVO.com_name }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;備&nbsp;&nbsp;&nbsp;註&nbsp;&nbsp;：<input
							type="text" name="remark" value="" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;狀&nbsp;&nbsp;&nbsp;態&nbsp;&nbsp;：<input
							type="text" id="status" name="status" value="N"
							readonly="readonly" /><span style="font-size: 10px; color: gray">(N:未審核
								Y:已審核 D:註銷)</span></td>
					</tr>
				</table>
				<hr>
				<!-- 			<hr> -->
				<table border=0
					class="table table-bordered table-striped table-hover"
					id="detailtable">
					<tr>
						<td>#</td>
						<td>商品名稱</td>
						<td>商品數量</td>
						<td>商品成本</td>
						<td>備註</td>
					</tr>
					<c:forEach var="detailVO" items="${reqDetailVO}"
						varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>&nbsp;&nbsp;商品名稱：<input type="text"
								name="prod_name${status.count }" value="${detailVO.prod_name }"
								readonly="readonly" /></td>
							<td>&nbsp;&nbsp;商品數量：<input type="text"
								name="prod_quantity${status.count }"
								value="${detailVO.prod_quantity }" readonly="readonly" /></td>
							<td>&nbsp;&nbsp;商品成本：<input type="text"
								name="prod_cost${status.count }" required/></td>
							<td>&nbsp;&nbsp;備註：<input type="text"
								name="remark${status.count }" value="" /></td>
						</tr>

					</c:forEach>

				</table>
				<div style="position: absolute; bottom: 30px; right: 40%;">

					<input type="button" id="sbt" value="新增詢價單" /> <input
						type="button" id="return" value="返回" />
					<!-- 					<input type="hidden" name="action" value="insert"> -->

				</div>
			</form>
		</div>
		<script>
			$(function() {
				$('#form1').validate({

					errorClass : "my-error-class",
					validClass : "my-valid-class"

					
				})

				$('#sbt').on('click', function() {
					var $form = $('#form1');
					var url = "insertQuo.do";
					if ($form.valid()) {
						$.ajax({
							type : "POST",
							url : url,
							data : {
								"status2" : "S"
							} + '&' + $('#form1').serialize(),
							success : function(data) {
								$("#main-content").html(data);
							}
						})
					}
				})

				$('#return').on('click', function() {
					var url = "addQuo0.do";
					$.ajax({
						type : "post",
						url : url,
						success : function(data) {
							$('#main-content').html(data);
						}
					})
				})
				$('#quo2').on('click', function() {
					var t1 = $(this).attr('target');
					$.get(t1, function(data) {
						$('#main-content').html(data);
					})
				})

				$('#quo3').on('click', function() {
					var t1 = $(this).attr('target');
					$.ajax({
						type : "post",
						url : t1,
						success : function(data) {
							$('#main-content').html(data);
						}
					})
				})
				$('#quo4').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
						$('#main-content').html(data);
					})
				})
				$('#quo5').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
						$('#main-content').html(data);
					})
				})
				$('#quo6').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
						$('#main-content').html(data);
					})
				})
				$("input[readonly]").css("background-color","lightgray");
// 				$("input[readonly]").css("border",1);
			})
// 			$("#table1").dataTable();
		</script>
</body>
</html>