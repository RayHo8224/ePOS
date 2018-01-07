<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
				<li><a id="req1" target="addReq.jsp" href="javascript: void(0)">新增請購單</a></li>
				<li style="background-color: rgba(221, 15, 15, 0.8);"><a
					style="color: white;" href="javascript: void(0)">單筆查詢</a></li>
				<li><a id="req3" target="getAllReq.do" href="javascript: void(0)">全部查詢</a></li>
				<li><a id="req4" target="SelectbyDate.jsp" href="javascript: void(0)">依日期查詢</a></li>
				<li><a id="req5" target="selectOfN.do" href="javascript: void(0)">審核</a></li>
			</ul>
		</div>
		</nav>
		<div
			style="background-color: rgba(66, 134, 244, 0.3); position: relativve; height: 750px; overflow: auto;">
			<div>
				<h1>請購單查詢</h1>
				<hr>
				<table border="0" id="table1" class="table table-bordered table-striped table-hover">
					<c:forEach var="list" items="${list}" varStatus="count">
						<tr>
							<td>&nbsp;&nbsp;請購單號：<input type="text" name="req_id"
								id="req_id" value="${list.req_id}" readonly="readonly" /></td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;建檔人員：<input type="text" name="key_id"
								value="${list.key_id}" id="key_id" readonly="readonly"></td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;建檔日期：<input type="date" name="key_date"
								value="${list.key_date}" id="theDate" style="width: 199px;"
								readonly="readonly"></td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;狀&nbsp;&nbsp;&nbsp;態&nbsp;&nbsp;：<input
								type="text" id="status" name="status" value="${list.status }"
								readonly="readonly" /><span
								style="font-size: 10px; color: gray">(N:未審核 Y:已審核 D:註銷 S:成功)</span></td>
						</tr>
					</c:forEach>
				</table>
				<hr>
				<table border=0 class="table table-bordered table-striped table-hover">
<%-- 					<c:forEach var="list" items="${list}" varStatus="count"> --%>
						<tr>
							<td>#</td>
							<td>商品名稱</td>
							<td>商品數量</td>
						</tr>
						<c:forEach var="detailVO" items="${reqDetaolList}" varStatus="status">
							<tr>
								<td>${status.count}</td>
							<td><input type="text" name="prod_name${status.count }"
							value="${detailVO.prod_name }" readonly="readonly" /></td>
							<td><input type="text" name="prod_quantity${status.count }"
							value="${detailVO.prod_quantity }" readonly="readonly" /></td>
							</tr>

						</c:forEach>
<%-- 					</c:forEach> --%>
				</table>
			</div>
		</div>
		<div style="position: absolute; bottom: 30px; right: 40%;">

			<input type="button" value="修改" id="alter"
				target="DetailDeleteReq.do" /> <input type="button" value="確認"
				id="confirm" target="getAllReq.do">
			<!-- 		<input type="button" value="註銷" id="writeoff"> -->
		</div>
	</div>
	</section></section>
	<script>
		$(function() {
			if ($('#status').val() == 'Y') {
				$('#alter').hide();
			}
			if ($('#status').val() == 'D') {
				$('#alter').hide();
				}
			if ($('#status').val() == 'S') {
				$('#alter').hide();
				}
			
			$('#alter').on('click', function() {
				var url = $(this).attr('target');
				var req_id = $('#req_id').val();
				$.ajax({
					type : "POST",
					url : url,
					data : {
						"req_id" : req_id,
						"action" : "update"
					},
					success : function(data) {
						$("#main-content").html(data);
					}
				})
			})

			$('#confirm').on('click', function() {
				var t1 = $(this).attr('target');
				$.ajax({
					type : "post",
					url : t1,
					success : function(data) {
						$('#main-content').html(data);
					}
				})
			})

			$('#req1').on('click', function() {
				var t1 = $(this).attr('target');
				$.get(t1, function(data) {
					$('#main-content').html(data);
				})
			})
			$('#req3').on('click', function() {
				var t1 = $(this).attr('target');
				$.ajax({
					type : "post",
					url : t1,
					success : function(data) {
						$('#main-content').html(data);
					}
				})
			})
			$('#req4').on('click', function() {
				var t1 = $(this).attr('target');
				$.get(t1, function(data) {
					$('#main-content').html(data);
				})
			})
			$('#req5').on('click', function() {
				var t1 = $(this).attr('target');
				$.post(t1, function(data) {
					$('#main-content').html(data);
				})
			})
		})
// 		$("#table1").dataTable();
	</script>
</body>
</html>