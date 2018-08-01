<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改請購單</title>

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
				<li><a id="req1" target="addReq.jsp">新增請購單</a></li>
				<li><a id="req2" target="SelectReq.jsp">單筆查詢</a></li>
				<li><a id="req3" target="getAllReq.do">全部查詢</a></li>
				<li><a id="req4" target="SelectbyDate.jsp">依日期查詢</a></li>
				<li style="background-color: rgba(221, 15, 15, 0.8);"><a
					style="color: white;">審核</a></li>
			</ul>
		</div>
		</nav>
		<div
			style="background-color: rgba(66, 134, 244, 0.3); position: relativve; height: 750px; overflow: auto;">
			<!-- 	<br> -->
			<h1>修改請購單</h1>
			<hr>

			<form method="post" action="insertReq.do" id="form1">

				<table border="0" id="table1" class="table table-bordered table-striped table-hover">
					<tr>
						<td>&nbsp;&nbsp;請購單號：<input type="text" name="req_id"
							id="req_id" value="${reqVO.req_id}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;建檔人員：<input type="text" name="key_id"
							value="${reqVO.key_id}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;建檔日期：<input type="date" name="key_date"
							value="${reqVO.key_date}" id="theDate" style="width: 199px;"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;狀&nbsp;&nbsp;&nbsp;態&nbsp;&nbsp;：<input
							type="text" id="status" name="status" value="${reqVO.status }"
							readonly="readonly" /><span style="font-size: 10px; color: gray">(N:未審核
								Y:已審核 D:註銷 S:成功)</span></td>
					</tr>
				</table>
				<hr>
				<!-- 			<hr> -->
				<table border=0 class="table table-bordered table-striped table-hover" id="detailtable">
					<tr>
						<td>#</td>
						<td>商品名稱</td>
						<td>商品數量</td>
					</tr>
					<c:forEach var="detailVO" items="${reqDetailVO}"
						varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${detailVO.prod_name }</td>
							<td>${detailVO.prod_quantity }</td>
						</tr>

					</c:forEach>

				</table>
				<div style="position: absolute; bottom: 30px; right: 40%;">

					<input type="button" value="核准" id="authorize"
						target="updateReq2.do"> <input type="button"
						id="writeoff" value="註銷" target="updateReq2.do" /> <input
						type="button" id="return" value="返回"
						target="selectOfN.do" />
					<!-- 					<input type="hidden" name="action" value="insert"> -->

				</div>
			</form>
		</div>
	</div>
	</section></section>

	<script>
		$(function() {

			$('#authorize').on('click', function() {
				var url = $(this).attr('target');
				var req_id = $('#req_id').val();
				$.ajax({
					type : "POST",
					url : url,
					data : {
						"req_id" : req_id,
						"status" : "Y"
					},
					success : function(data) {
						$("#main-content").html(data);
					}
				})
			})

			$('#writeoff').on('click', function() {
				var url = $(this).attr('target');
				var req_id = $('#req_id').val();
				$.ajax({
					type : "POST",
					url : url,
					data : {
						"req_id" : req_id,
						"status" : "D"
					},
					success : function(data) {
						$("#main-content").html(data);
					}
				})
			})

			$('#return').on('click', function() {
				var url = $(this).attr('target');
				$.ajax({
					type : "post",
					url : url,
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

			$('#req2').on('click', function() {
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

		})
// 		$("#table1").dataTable();
	</script>
</body>
</html>