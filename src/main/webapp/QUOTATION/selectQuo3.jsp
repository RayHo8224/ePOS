<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List,java.util.Collections"%>
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
	font-size: 13px;
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
				<li><a id="quo1" target="addQuo0.do" href="javascript: void(0)">新增詢價單</a></li>
				<li><a id="quo2" target="SelectQuo.jsp" href="javascript: void(0)">單筆查詢</a></li>
				<li><a id="quo3" target="getAllQuo.do" href="javascript: void(0)">全部查詢</a></li>
				<li><a id="quo4" target="SelectbyDate.jsp" href="javascript: void(0)">依日期查詢</a></li>
				<li><a id="quo5" target="selectOfN.do" href="javascript: void(0)">審核</a></li>
				<li style="background-color: rgba(221, 15, 15, 0.8);"><a
					style="color: white;">新增商品目錄</a></li>
			</ul>
		</div>
		</nav>
		<div
			style="background-color: rgba(66, 134, 244, 0.3); position: relativve; height: 750px; overflow: auto;">
			<!-- 	<br> -->
			<!-- 		<h1>詢價單</h1> -->
			<!-- 		<hr> -->
			<h1>新增商品目錄</h1>
			<hr>
			<form method="post" action="REQUISITION/insertReq.do" id="form1">

				<table border="0" id="table1"
					class="table table-bordered table-striped table-hover">
					<tr>
						<td>&nbsp;&nbsp;詢價單號：<input type="text" name="quo_id"
							value="${quoVO.quo_id }" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;請購單號：<input type="text" name="req_id"
							id="req_id" value="${quoVO.req_id}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;建檔人員：<input type="text" name="key_id"
							id="key_id" value="${quoVO.key_id}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;建檔日期：<input type="date" name="key_date"
							id="key_date" value="${quoVO.key_date}" style="width: 199px;" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;備&nbsp;&nbsp;&nbsp;註&nbsp;&nbsp;：<input
							type="text" name="remark" value="${quoVO.remark }"
							 /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;狀&nbsp;&nbsp;&nbsp;態&nbsp;&nbsp;：<input
							type="text" id="status" name="status" value="${quoVO.status }"
							readonly="readonly" /><span style="font-size: 10px; color: gray">(N:未審核
								Y:已審核 D:註銷 S:成功)</span></td>
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
						<td>商品類別<span style="font-size:11px;color:red;">*必填</span></td>
						<td>商品成本</td>
						<td>商品售價<span style="font-size:11px;color:red;">*必填</span></td>
						<td>商品規格</td>
						<td>商品庫存</td>
						<td>安全庫存</td>
						<td>廠商編號</td>
						<td>備註</td>
					</tr>
					<c:forEach var="detailVO" items="${quoDetailVO}"
						varStatus="status">
						<tr>
							<td>${status.count}</td>

							<td><input type="text" name="prod_name${status.count }"
								value="${detailVO.prod_name }" readonly="readonly" /></td>

							<td><input type="text" name="prod_group${status.count }"
								required /></td>

							<td><input type="text" name="prod_cost${status.count }"
								value="${detailVO.prod_cost }" readonly="readonly" /></td>

							<td><input type="text" name="prod_mkprice${status.count }"
								required /></td>

							<td><input type="text" name="prod_spec${status.count }"
								value="" /></td>

							<td><input type="text" name="prod_stock${status.count }"
								value="0" required readonly="readonly"/></td>

							<td><input type="text" name="prod_q_safty${status.count }"
								value="5" required/></td>

							<td><input type="text" name="com_id${status.count }"
								value="${com_id}" readonly="readonly" /><span>
									(${com_name })</span></td>

							<td><input type="text" name="remark${status.count }"
								value="" /></td>




						</tr>

					</c:forEach>

				</table>
				<div style="position: absolute; bottom: 30px; right: 40%;">

					<input type="button" id="insert" target="${quoVO.quo_id }"
						value="確認新增" /> <input type="button" id="return" value="返回" />
					<!-- 					<input type="hidden" name="action" value="insert"> -->

				</div>
			</form>
		</div>
		<script>
			$(function() {
				// 			if ($('#status').val() == 'Y') {
				// 				$('#alter').hide();
				// 			}
				// 			if ($('#status').val() == 'D') {
				// 				$('#alter').hide();
				// 			}
				$('#form1').validate({

					errorClass : "my-error-class",
					validClass : "my-valid-class",

					rules : {
						key_date : "required",
						end_date : "required"
					}
				})

				$('#insert').on('click', function() {
					var $form = $('#form1');
					var quo_id = $(this).attr('target');
					var url = "insertProd.do";
					if ($form.valid()) {
						$.ajax({
							type : "POST",
							url : url,
							data : $('#form1').serialize(),
							success : function(data) {
								$("#main-content").html(data);
							}
						})
					}
				})

				$('#return').on('click', function() {
					var url = "selectOfY.do";
					$.ajax({
						type : "post",
						url : url,
						success : function(data) {
							$('#main-content').html(data);
						}
					})
				})
				$('#quo1').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
						$('#main-content').html(data);
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

			})
			$("input[readonly]").css("background-color","lightgray");
// 			$("#table1").dataTable();
		</script>
</body>
</html>