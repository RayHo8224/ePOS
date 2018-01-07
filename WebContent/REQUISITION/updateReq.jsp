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
				<li><a id="req1" target="addReq.jsp">新增請購單</a></li>
				<li style="background-color: rgba(221, 15, 15, 0.8);"><a
					data-toggle="tab" style="color: white;">單筆查詢</a></li>
				<li><a id="req3" target="getAllReq.do">全部查詢</a></li>
				<li><a id="req4" target="SelectbyDate.jsp">依日期查詢</a></li>
				<li><a id="req5" target="selectOfN.do">審核</a></li>
			</ul>
		</div>
		</nav>
		<div
			style="background-color: rgba(66, 134, 244, 0.3); position: relativve; height: 750px; overflow: auto;">
			<!-- 	<br> -->
			<h1>請購單修改</h1>
			<hr>

			<form method="post" action="insertReq.do" id="form1">

				<table border="0" id="table1"
					class="table table-bordered table-striped table-hover">
					<tr>
						<td>&nbsp;&nbsp;請購單號：<input type="text" name="req_id"
							id="req_id" value="${reqVO.req_id}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;建檔人員：<input type="text" name="key_id"
							value="${reqVO.key_id}" id="key_id" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;建檔日期：<input type="date" name="key_date"
							value="${reqVO.key_date}" id="theDate" style="width: 199px;"></td>
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
				<table border=0
					class="table table-bordered table-striped table-hover"
					id="detailtable">
					<tr>
						<td>#</td>
						<td>商品名稱</td>
						<td>商品數量</td>
					</tr>
					<c:forEach var="detailVO" items="${reqDetailVO}"
						varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td><input type="text" name="prod_name${status.count }"
								value="${detailVO.prod_name }" readonly="readonly" /></td>
							<td><input type="text" name="prod_quantity${status.count }"
								value="${detailVO.prod_quantity }" required/></td>
						</tr>

					</c:forEach>

				</table>
				<div style="position: absolute; bottom: 30px; right: 40%;">

					<input type="button" id="sbt" target="updateReq5.do" value="修改" />
					<input type="button" value="註銷" id="writeoff" target="updateReq.do">
					<input type="button" id="return" target="${reqVO.req_id}"
						value="返回" /> <input type="hidden" name="action" value="insert">

				</div>
			</form>
		</div>
	</div>
	</section></section>
	<script>
		$(function() {
			if ($('#status').val() == 'Y') {
				$('#writeoff').hide();
				$('#sbt').hide();
				$('#key_id').attr('readonly', 'readonly');
				$('#theDate').attr('readonly', 'readonly');
			}
			if ($('#status').val() == 'D') {
				$('#writeoff').hide();
				$('#sbt').hide();
				$('#key_id').attr('readonly', 'readonly');
				$('#theDate').attr('readonly', 'readonly');
			}
			if ($('#status').val() == 'S') {
				$('#writeoff').hide();
				$('#sbt').hide();
				$('#key_id').attr('readonly', 'readonly');
				$('#theDate').attr('readonly', 'readonly');
			}

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

			$('#form1').validate({

				errorClass : "my-error-class",
				validClass : "my-valid-class",

				rules : {
					key_date : "required"
				},
				messages : {
					key_date : {required:"請輸入請購日期"}
				}
			})

			$('#sbt').on('click', function() {
				var $form = $('#form1');
				var url = $(this).attr('target');
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
				var url = "getByReq_id.do";

				var req_id = $(this).attr('target');
				$.ajax({
					type : "POST",
					url : url,
					data : {
						"req_id" : req_id
					},
					success : function(data) {
						$("#main-content").html(data);
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
		$("input[readonly]").css("background-color","lightgray");
// 		$("#table1").dataTable();
	</script>
</body>
</html>