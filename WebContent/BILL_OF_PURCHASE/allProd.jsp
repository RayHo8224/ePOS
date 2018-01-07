<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List,java.util.Collections"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>全部詢價單資料</title>

<link href="<c:url value="../resources/css/bootstrap.css" />"
	rel="stylesheet">


</head>
<style>

</style>
<body>
	<section id="container"> <section class="wrapper">
	<div class="row mt">

		<nav class="nav navbar-default">
		<div class="container-fluid"
			style="float: left;">
			<ul class="nav navbar-nav"
				style="float: left;">
				<li><a id="bop1" target="selectOfY.do" href="javascript: void(0)">新增進貨單</a></li>
				<li><a id="bop2" target="SelectBOP0.jsp" href="javascript: void(0)">單筆查詢</a></li>
				<li><a id="bop3" target="getAllBop.do" href="javascript: void(0)">全部查詢</a></li>
				<li><a id="bop4" target="SelectbyDate.jsp" href="javascript: void(0)">依日期查詢</a></li>
				<li><a id="bop5" target="selectOfN.do" href="javascript: void(0)">審核</a></li>
				<li style="background-color: rgba(221, 15, 15, 0.8);"><a
					id="bop6" target="selectOfY2.do" style="color: white;" href="javascript: void(0)">到貨確認</a></li>
				<li><a id="bop7" target="analyze.do">單月進貨金額</a>
			</ul>
		</div>
		</nav>
		<div
			style="background-color: rgba(66, 134, 244, 0.3); position: relativve; height: 750px; overflow: auto;">
			<div align=center>
			<hr>
				<table border="0" id="table1" class="table table-bordered table-striped table-hover">
					<tr>
						<td>商品編號</td>
						<td>商品名稱</td>
						<td>商品類別</td>
						<td>商品成本</td>
						<td>商品售價</td>
						<td>商品規格</td>
						<td>商品庫存</td>
						<td>安全庫存</td>
						<td>廠商編號</td>
						<td>備註</td>

					</tr>
					<%
						List list = (List) request.getAttribute("list");
						if (list == null) {
							list = (List) session.getAttribute("list");
						}
						// 					List list2 = list.subList(0,list.size());
						Collections.reverse(list);
					%>

					<c:forEach var="list" items="<%=list%>" varStatus="count">
						<form method="post">
							<tr>
								<td>${list.prod_id}</td>
								<td>${list.prod_name}</td>
								<td>${list.prod_group}</td>
								<td>${list.prod_cost}</td>
								<td>${list.prod_mkprice}</td>
								<td>${list.prod_spec}</td>
								<td>${list.prod_stock}</td>
								<td>${list.prod_q_safty}</td>
								<td>${list.com_id}</td>
								<td>${list.remark}</td>
								<!-- 							<td><input type="submit" class="detail" -->
								<%-- 								target="${list.quo_id}" value="Detail"></td> --%>

								<%-- 							<input type="hidden" name="req_id" value="${list.req_id}"> --%>

							</tr>

						</form>
					</c:forEach>
				</table>

			</div>
		</div>
		<script>
			$(function() {

				$('#bop1').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
						$('#main-content').html(data);
					})
				})

				$('#bop2').on('click', function() {
					var t1 = $(this).attr('target');
					$.get(t1, function(data) {
						$('#main-content').html(data);
					})
				})

				$('#bop3').on('click', function() {
					var t1 = $(this).attr('target');
					$.ajax({
						type : "post",
						url : t1,
						success : function(data) {
							$('#main-content').html(data);
						}
					})
				})
				$('#bop4').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
						$('#main-content').html(data);
					})
				})
				$('#bop5').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
						$('#main-content').html(data);
					})
				})
				$('#bop6').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
						$('#main-content').html(data);
					})
				})
				$('#bop7').on('click', function() {
						var t1 = $(this).attr('target');
						$.post(t1, function(data) {
							$('#main-content').html(data);
						})
					})
			})
// 			$("#table1").dataTable();
		</script>
</body>
</html>