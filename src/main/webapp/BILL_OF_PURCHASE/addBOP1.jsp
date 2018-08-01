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
				<li style="background-color: rgba(221, 15, 15, 0.8);"><a
					data-toggle="tab" style="color: white;">新增進貨單</a></li>
				<li><a id="bop2" target="SelectBOP0.jsp">單筆查詢</a></li>
				<li><a id="bop3" target="getAllBop.do">全部查詢</a></li>
				<li><a id="bop4" target="SelectbyDate.jsp">依日期查詢</a></li>
				<li><a id="bop5" target="selectOfN.do">審核</a></li>
				<li><a id="bop6" target="selectOfY2.do">到貨確認</a></li>
				<li><a id="bop7" target="analyze.do">單月進貨金額</a>
			</ul>
		</div>
		</nav>
		<div
			style="background-color: rgba(66, 134, 244, 0.3); position: relativve; height: 750px; overflow: auto;">
			<!-- 	<br> -->
			<h1>採購轉進貨單</h1>
			<hr>

			<form method="post" action="REQUISITION/insertReq.do" id="form1">

				<table border="0" id="table1"
					class="table table-bordered table-striped table-hover">
					<tr>
						<td>&nbsp;&nbsp;進貨單號：<input type="text" name="bop_id"
							value="系統產生" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;採購單號：<input type="text" name="pur_id"
							value="${purVO.pur_id }" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;採購日期：<input type="text" name="pur_date"
							id="pur_date" style="width: 199px;" value="${purVO.pur_date}"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;廠商編號：<input type="text" name="com_id"
							value="${purVO.com_id }" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;建檔人員：<input type="text" name="key_id"
							value="${purVO.key_id }" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;建檔日期：<input type="text" name="key_date"
							id="key_date" style="width: 199px;" value="${purVO.key_date}"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;送貨日期：<input type="date" name="bop_date"
							id="delivery_date" style="width: 199px;"
							value="${purVO.delivery_date}" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;金&nbsp;&nbsp;&nbsp;額&nbsp;&nbsp;：<input
							type="text" name="remark" value="${purVO.remark }"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;狀&nbsp;&nbsp;&nbsp;態&nbsp;&nbsp;：<input
							type="text" id="status" name="status" value="${purVO.status }"
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
						<td>商品編號</td>
						<td>商品名稱</td>
						<td>商品數量</td>
						<td>商品單價</td>
						<td>金額小計</td>

					</tr>
					<c:forEach var="detailVO" items="${purDetail}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td><input type="text" name="prod_id${status.count }"
								value="${detailVO.prodVO.prod_id }" readonly="readonly" /></td>
							<td><input type="text" name="prod_name${status.count }"
								value="${detailVO.prod_name }" readonly="readonly" /></td>
							<td><input type="text" name="prod_quantity${status.count }"
								value="${detailVO.prod_quantity }" readonly="readonly" /></td>
							<td><input type="text" name="prod_cost${status.count }"
								value="${detailVO.prod_price }" readonly="readonly" /></td>
							<td><input type="text" name="prod_lsum${status.count }"
								value="${detailVO.prod_lsum }" readonly="readonly" /></td>

						</tr>

					</c:forEach>

				</table>
				<div style="position: absolute; bottom: 30px; right: 40%;">

					<input type="button" id="sbt" value="新增進貨單" /> <input
						type="button" id="return" value="返回" />
					<!-- 					<input type="hidden" name="action" value="insert"> -->

				</div>
			</form>
		</div>
		<script>
			$(function() {
				// 			if ($('#status').val() == 'Y') {
				// 				$('#writeoff').hide();
				// 				$('#sbt').hide();
				// 				$('#key_id').attr('readonly', 'readonly');
				// 				$('#theDate').attr('readonly', 'readonly');
				// 			}
				// 			if ($('#status').val() == 'D') {
				// 				$('#writeoff').hide();
				// 				$('#sbt').hide();
				// 				$('#key_id').attr('readonly', 'readonly');
				// 				$('#theDate').attr('readonly', 'readonly');
				// 			}

				$('#form1').validate({

					errorClass : "my-error-class",
					validClass : "my-valid-class",

					rules : {
						bop_date : "required"
					},
					messages : {
						bop_date : {
							required : "請輸入送貨日期"
						}
					}
				})

				$('#sbt').on('click', function() {
					var $form = $('#form1');
					var url = "insertBOP.do";
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
			$("input[readonly]").css("background-color","lightgray");
// 			$("#table1").dataTable();
		</script>
</body>
</html>