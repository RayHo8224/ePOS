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
					style="color: white;">新增採購單</a></li>
				<li><a id="pur2" target="SelectPur.jsp" href="javascript: void(0)">單筆查詢</a></li>
				<li><a id="pur3" target="getAllPur.do" href="javascript: void(0)">全部查詢</a></li>
				<li><a id="pur4" target="SelectbyDate.jsp" href="javascript: void(0)">依日期查詢</a></li>
				<li><a id="pur5" target="selectOfN.do" href="javascript: void(0)">審核</a></li>
			</ul>
		</div>
		</nav>
		<div
			style="background-color: rgba(66, 134, 244, 0.3); position: relativve; height: 750px; overflow: auto;">

			<div align=center>
				<br> <br> <br>
				<h3>請選擇廠商名稱</h3>
				<br>
				<form method="post" id="form1" action="getByReq_id.do">
					<table border="0">
						<tr>
							<td><select id="com_name" name="com_name"
								style="width: 199px; height: 30px;">
									<c:forEach var='comVO' items='${list}'>
										<option>${comVO.com_name}</option>
									</c:forEach>
							</select></td>
							<td><input type="button" id="sbt" value="確認"></td>
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
			$('#sbt').on('click', function() {

				var url = "insertPur0.do";
				var com_name = $('#com_name').val();
				$.ajax({
					type : "POST",
					url : url,
					data : {"com_name":com_name},
					success : function(data) {
						$("#main-content").html(data);
					}
				})
			})
			$('#pur2').on('click', function() {
				var t1 = $(this).attr('target');
				$.get(t1, function(data) {
					$('#main-content').html(data);
				})
			})

			$('#pur3').on('click', function() {
				var t1 = $(this).attr('target');
				$.ajax({
					type : "post",
					url : t1,
					success : function(data) {
						$('#main-content').html(data);
					}
				})
			})
			$('#pur4').on('click', function() {
				var t1 = $(this).attr('target');
				$.post(t1, function(data) {
					$('#main-content').html(data);
				})
			})
			$('#pur5').on('click', function() {
				var t1 = $(this).attr('target');
				$.post(t1, function(data) {
					$('#main-content').html(data);
				})
			})
		})
	</script>
</body>
</html>