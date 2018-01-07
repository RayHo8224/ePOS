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
				<li><a id="pur1" target="insertPur00.do" href="javascript: void(0)">新增採購單</a></li>
				<li style="background-color: rgba(221, 15, 15, 0.8);"><a
					style="color: white;">單筆查詢</a></li>
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
				<h3>請輸入採購單編號</h3>

				<form method="post" id="form1">
					<table border="0">
						<tr>
							<td><input type="text" name="pur_id"></td>
							<td><input type="button" id="sbt" value="查詢"></td>
						</tr>
					</table>
					<!-- 				<input type="hidden" name="action" value="getByReq_id"> -->
				</form>

			</div>
		</div>
		<script>
			$(function() {
				$.validator.addMethod( "rule1" , function(value,element){
					 
					var re = /^P[0-9]{13}/g;
					 
					return value.match(re);
					 
					});
				$('#form1').validate({

					errorClass : "my-error-class",
					validClass : "my-valid-class",

					rules : {
						pur_id : {required:true,rule1:true}
					},
					messages : {
						pur_id : {
							required:"【請輸入採購單編號】",
							rule1:"【請輸入正確編號格式】"
							}
					},
					errorPlacement: function(error, element) {  
					    error.appendTo(element.parent().next());  
					}
				})
				$('#sbt').on('click', function() {
					var $form = $('#form1');
					var url = "getByPur_id.do";
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
				$('#pur1').on('click', function() {
					var t1 = $(this).attr('target');
					$.post(t1, function(data) {
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