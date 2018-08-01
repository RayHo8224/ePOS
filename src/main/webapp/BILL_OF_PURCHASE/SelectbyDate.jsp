<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查詢請購單</title>

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
				<li><a id="bop1" target="selectOfY.do" href="javascript: void(0)">新增進貨單</a></li>
				<li><a id="bop2" target="SelectBOP0.jsp" href="javascript: void(0)">單筆查詢</a></li>
				<li><a id="bop3" target="getAllBop.do" href="javascript: void(0)">全部查詢</a></li>
				<li style="background-color: rgba(221, 15, 15, 0.8);"><a
					style="color: white;">依日期查詢</a></li>
				<li><a id="bop5" target="selectOfN.do" href="javascript: void(0)">審核</a></li>
				<li><a id="bop6" target="selectOfY2.do" href="javascript: void(0)">到貨確認</a></li>
				<li><a id="bop7" target="analyze.do" href="javascript: void(0)">單月進貨金額</a>
			</ul>
		</div>
		</nav>
		<div
			style="background-color: rgba(66, 134, 244, 0.3); position: relativve; height: 750px; overflow: auto;">

			<div align=center>
				<br> <br> <br>
				<h3>請輸入查詢日期範圍</h3>
				<br>
				<form method="post" id="form1" action="getByReq_id.do">
					<table border="0">
						<tr>
							<td>起&nbsp;&nbsp;</td>
							<td><input type="date" name="begin_date"></td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>訖&nbsp;&nbsp;</td>
							<td><input type="date" name="end_date"></td>
							<td><input type="button" id="sbt" value="查詢"></td>
						</tr>
					</table>
					<!-- 				<input type="hidden" name="action" value="getByReq_id"> -->
				</form>
			</div>
		</div>
	</div>
	</section></section>
	<script>
		$(function() {
			$('#form1').validate({

				errorClass : "my-error-class",
				validClass : "my-valid-class",

				rules : {
					begin_date : "required",
					end_date : "required"
				},
				messages : {
					begin_date : {
						required : "【請輸入起始日期】"
					},
					end_date : {
						required : "【請輸入結束日期】"
					}
				}
			})
			$('#sbt').on('click', function() {
				var $form = $('#form1');
				var url = "findByDate.do";
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
	</script>
</body>
</html>