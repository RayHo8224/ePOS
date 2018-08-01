<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%		
 		Date nowDate =new java.sql.Date(System.currentTimeMillis());
 		pageContext.setAttribute("nowDate",nowDate);
 		
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增採購單</title>

<link href="<c:url value="../resources/css/bootstrap.css" />"
	rel="stylesheet">

</head>
<style type="text/css">


table {
	margin: 10px 2px;
	line-height: 20px;
}

input {
	margin: 5px;
	text-align: left;
	/* 		background-color:rgba(234,225,225,1); */
}

.dbt {
	height: 24px;
	color: white;
	background-color: rgba(221, 15, 15, 0.8);
	border: 0px;
}

.detailtable td {
	padding: 2px 10px;
}

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
		<%-- 錯誤表列 --%>
		<%-- <c:if test="${not empty param.message}"> --%>
		<!-- 	<font color='red'>請修正以下錯誤: -->
		<!-- 	<ul> -->
		<%-- 		<c:forEach var="message" items="${param.message}"> --%>
		<%-- 			<li>${message}</li> --%>
		<%-- 		</c:forEach> --%>
		<!-- 	</ul> -->
		<!-- 	</font> -->
		<%-- </c:if> --%>
		<nav class="nav navbar-default">
		<div class="container-fluid"
			style="float: left;">
			<ul class="nav navbar-nav"
				style="float: left;">
				<li style="background-color: rgba(221, 15, 15, 0.8);"><a
					id="pur1" target="insertPur00.do" style="color: white;">新增採購單</a></li>
				<li><a id="pur2" target="SelectPur.jsp" href="javascript: void(0)">單筆查詢</a></li>
				<li><a id="pur3" target="getAllPur.do" href="javascript: void(0)">全部查詢</a></li>
				<li><a id="pur4" target="SelectbyDate.jsp" href="javascript: void(0)">依日期查詢</a></li>
				<li><a id="pur5" target="selectOfN.do" href="javascript: void(0)">審核</a></li>
			</ul>
		</div>
		</nav>

		<div
			style="background-color: rgba(66, 134, 244, 0.3); position: relativve; height: 750px; overflow: auto; overflow-x: hidden;">
			<!-- 	<br> -->
			<h1>新增採購單</h1>
			<hr>

			<form method="post" action="insertReq.do" id="form1">

				<table border="0" id="table1"
					class="table table-bordered table-striped table-hover">
					<tr>
						<td>&nbsp;&nbsp;採購單號：<input type="text" name="pur_id"
							value="系統產生" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;採購日期：<input type="date" name="pur_date"
							value=${nowDate } id="theDate" style="width: 200px;"></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;廠商編號：<input type="text" name="com_id"
							value="${ComVO.com_id }" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;建檔人員：<input type="text" name="key_id"
							value="${sessionScope.LoginOK.emp_id }" readonly="readonly"></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;建檔日期：<input type="date" name="key_date"
							value=${nowDate } id="theDate2" style="width: 200px;"></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;送貨日期：<input type="date" name="delivery_date"
							id="theDate3" style="width: 200px;"></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;金&nbsp;&nbsp;&nbsp;額&nbsp;&nbsp;：<input
							type="text" id="sum" name="remark" value="" readonly="readonly"></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;狀&nbsp;&nbsp;&nbsp;態&nbsp;&nbsp;：<input
							type="text" name="status" value="N" readonly="readonly" /><span
							style="font-size: 10px; color: gray">(N:未審核 Y:已審核 D:註銷
								S:成功)</span></td>
					</tr>
				</table>
				<hr>
				<!-- 			<hr> -->
				<table border=0
					class="table table-bordered table-striped table-hover"
					id="detailtable">

				</table>
				<div style="position: absolute; bottom: 30px; right: 40%;">

					<input type="button" value="新增明細" id="addNewDetail"> <input
						type="button" id="sbt" value="提交" /> <input type="hidden"
						name="action" value="insert">

				</div>
			</form>
		</div>
	</div>
	</section></section>
	<script>
		$(function() {
			var a = 1;
			var sum = 0;
			$("#addNewDetail")
					.click(
							function() {
								$("#detailtable")
										.append(
// 												"<tr><td>商品編號：&nbsp;<select id='w"+a+"' class='item' name='prod_id"+a+"'style='width: 199px; height: 30px;'><c:forEach var='prodVO' items='${list2 }' varStatus='status'><option>${prodVO.prod_id}</option></c:forEach></select></td>"
														"<tr><td>商品編號：<input type='text' class='id' id='x"+a+"' name='prod_id"+a+"'  /></td>"
// 														+ "<td>商品名稱：<input type='text' id='x"+a+"' name='prod_name"+a+"' readonly='readonly' /></td>"
														+ "<td>商品名稱：&nbsp;<select id='w"+a+"' class='item' name='prod_name"+a+"'style='width: 199px; height: 30px;'><c:forEach var='prodVO' items='${list2 }' varStatus='status'><option>${prodVO.prod_name}</option></c:forEach></select></td>"
														+ "<td>商品數量：<input type='text' id='y"+a+"' name='prod_quantity"+a+"'/></td>"
														+ "<td>商品單價：<input type='text' id='z"+a+"' name='prod_price"+a+"' readonly='readonly' /></td>"
														+ "<td>金額小計：<input type='text' id='r"+a+"' class='lsum' name='prod_lsum"+a+"' readonly='readonly' /></td>"
														+ "<td><input type='button' value='刪除' class='dbt'/></td></tr>")

								$('#w' + a).val("");

								$('.dbt').on('click', function() {
									$(this).parents('tr').remove();
									sum = 0;

									$('.lsum').each(function() {

										sum = sum + Number($(this).val());
									})

									$('#sum').val(sum);
								})

								$('#w' + a)
										.change(
												function() {
													var prod_name = $(this).val();
													var element = this;
													var url = "getOneProd1.do";
													$
															.ajax({
																type : 'POST',
																url : url,
																data : {
																	"prod_name" : prod_name
																},
																success : function(
																		data) {

																	$(element).parent().parent()
																			.children()
																			.children(".id")
																			.val(
																					data)                
// 																	alert(data);
																

																}

															})
													url = "getOneProd2.do";
													$
															.ajax({
																type : 'POST',
																url : url,
																data : {
																	"prod_name" : prod_name
																},
																success : function(
																		data) {

																	$(element)
																			.parent()
																			.next()
																			.next()
																			.children()
																			.val(
																					data);
// 																	 											alert(data);

																}
															})

// 													$(this).parent().next()
// 															.next().children()
// 															.val("");

													var y1 = $(this).parent()
															.next()
															.children().val();
													var y2 = $(this).parent()
															.next()
															.next().children()
															.val();

													var ysum = y1 * y2;
													$(this).parent().next()
															.next()
															.next().children()
															.val(ysum);

													sum = 0;

													$('.lsum')
															.each(
																	function() {

																		sum = sum
																				+ Number($(
																						this)
																						.val());
																	})

													$('#sum').val(sum);

												})

								$('#y' + a).blur(
										function() {
											var y1 = $(this).val();
											var y2 = $(this).parent().next()
													.children().val();
											var ysum = y1 * y2;
											$(this).parent().next().next()
													.children().val(ysum);

										})

								$('#y' + a).blur(function() {

									sum = 0;

									$('.lsum').each(function() {

										sum = sum + Number($(this).val());
									})

									$('#sum').val(sum);

								})

								// 								$('#w'+a).change(function(){
								// 									var prod_id = $(this).val();
								// 									var element = this;
								// 									var url = "getOneProd2.do";
								// 									$.ajax({
								// 										type:'POST',
								// 										url:url,
								// 										data:{"prod_id":prod_id},
								// 										success:function(data){

								//  												$(element).parent().next().next().next().children().val(data);

								// 										}
								// 									})
								// 								})

								$('#w' + a)
										.blur(
												function() {
													var element = this;
													var origin = $(this).val();
													var i = 0;
													$('.item')
															.each(
																	function() {
																		if ($(
																				this)
																				.val() != null) {
																			if ($(
																					this)
																					.val() == origin) {
																				i++;
																				if (i >= 2) {
																					var t = $(
																							this)
																							.val();
																					alert("商品 "
																							+ t
																							+ " 已存在，請重新輸入");
																					$(
																							element)
																							.val(
																									"");
																					$(
																							element)
																							.parent()
																							.next()
																							.children()
																							.val(
																									"");
																					$(
																							element)
																							.parent()
																							.next()
																							.next()
																							.next()
																							.children()
																							.val(
																									"");
																					$(
																							element)
																							.parent()
																							.next()
																							.next()
																							.next()
																							.next()
																							.children()
																							.val(
																									"");
																				}
																			}
																		}
																	})
												})

								a = a + 1;
							})

			$('#form1').validate({

				errorClass : "my-error-class",
				validClass : "my-valid-class",

				rules : {
					pur_date : "required",
					key_date : "required",
					delivery_date : "required"
				},
				messages : {
					pur_date : {
						required : "【請輸入採購日期】"
					},
					key_date : {
						required : "【請輸入建檔日期】"
					},
					delivery_date : {
						required : "【請輸入送貨日期】"
					}
				}
			})

			$('#sbt').on('click', function() {
				var $form = $('#form1');
				var url = "insertPur.do";
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
		$("input[readonly]").css("background-color","lightgray");
// 		$("#table1").dataTable();
	</script>


</body>
</html>