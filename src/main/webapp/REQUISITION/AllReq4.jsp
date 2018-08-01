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
				<li><a id="req1" target="addReq.jsp" href="javascript: void(0)">新增請購單</a></li>
				<li><a id="req2" target="SelectReq.jsp" href="javascript: void(0)">單筆查詢</a></li>
				<li><a id="req3" target="getAllReq.do" href="javascript: void(0)">全部查詢</a></li>
				<li><a id="req4" target="SelectbyDate.jsp" href="javascript: void(0)">依日期查詢</a></li>
				<li style="background-color: rgba(221, 15, 15, 0.8);"><a
					style="color: white;">審核</a></li>
			</ul>
		</div>
		</nav>
		<div
			style="background-color: rgba(66, 134, 244, 0.3); position: relativve; height: 750px; overflow: auto;">
			<div align=center>
			<hr>
				<table border="0" id="table1" class="table table-bordered table-striped table-hover">
					<tr>
						<th>請購單編號</th>
						<th>修改人員</th>
						<th>修改日期</th>
						<th>狀態</th>
<!-- 						<th>明細</th> -->

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
						<form method="post" action="DetailDeleteReq.do">
							<tr>
								<td><a class="detail" target="${list.req_id}">${list.req_id}</a></td>
								<td>${list.key_id}</td>
								<td>${list.key_date}</td>
								<td>${list.status}</td>
<!-- 								<td><input type="submit" class="detail" -->
<%-- 									target="${list.req_id}" value="Detail"></td> --%>
								<%--<input type="hidden" name="req_id" value="${list.req_id}"> --%>
							</tr>

						</form>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	</section></section>
	<script>
		$(function() {
			$('.detail').on('click', function() {
				var url = "getByReq_id2.do";
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