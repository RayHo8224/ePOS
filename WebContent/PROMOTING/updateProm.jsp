<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.promoting.model.*"%>
<%
	PromotingVO promVO = (PromotingVO) request.getAttribute("promVO");//若輸入錯誤可以傳回包含錯誤的VO,有些對的就不用重打了
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>修改促銷商品</title>
<style>
	.navbar-default .navbar-nav > li > a{
		color:#255957;
	}
	
	.navbar-default .navbar-nav > .active > a, .navbar-default .navbar-nav > .active > a:hover, .navbar-default .navbar-nav > .active > a:focus{
 		background: #95BF8F;
 	}

	.navbar-default {
		background: #CCFFCC;
		border-color: #CCFF99;
		border-radius: 8px;
	}

 	.main{ 
  		height: 800px;  
 		border-radius: 8px; 
 		background:	#FFE4E1; 
 	}
 	
 	.titlelist {
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #F7C548;
		padding-left: 10px;
		font-size: 23px;
		border-radius: 2px;
	}

	.distance{
		margin: 20px;	
	}
	
	.btn-success {
    color: #fff;
    background-color: #e8c68a;
    border-color: #f3f3f3;
	} 
	
	.my-valid-class{
		color:#3a51e8;
	}
	
	.my-error-class{
		color:red;
	}

	.update_text{
		font-family: 微軟正黑體; 
		color:blue;
	} 
</style>
</head>
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<div class="titlelist">修改</div>
	<div class="col-lg-12">
		<FORM METHOD="post" ACTION="updateProm.do" name="upProForm" class="form-horizontal" role="form" id="pord_upd_form">
			<p class="distance">
			<div class="form-group">

				<label class="col-lg-1 col-lg-offset-2 control-label">促銷商品編號:</label>
				<div class="col-lg-2">
					<input type="text" name="pro_prod_id" size="20" value="${promVO.pro_prod_id}" readonly="readonly"
						 class="form-control" />
				</div>
				<label class="col-lg-1 control-label update_text">促銷商品名稱:</label>
				<div class="col-lg-2">
					<input type="text" name="pro_prod_name" size="20" maxlength="20"
						value="${promVO.pro_prod_name}" />
				</div>
				<label class="col-lg-1 control-label">促銷商品起始日期:</label>
				<div class="col-lg-2">
					<input type="date" name="pro_begin" size="20" value="${promVO.pro_begin}" readonly="readonly"
						class="form-control" /><div class="col-lg-1"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-1 col-lg-offset-2 control-label update_text">促銷商品截止日:</label>
				<div class="col-lg-2">
					<input type="date" name="pro_end" size="20" value="${promVO.pro_end}" />
				</div>
				<label class="col-lg-1 control-label update_text">備註:</label>
				<div class="col-lg-6">
					<textarea name="pro_neirong" rows='5' cols="50" value="${promVO.pro_neirong}"></textarea>
				</div>

			</div>

			<p class="distance">
			<div class="form-group">
				<div class="col-lg-1 col-lg-offset-5">
					<input type="button" value="送出修改" name="c_promoting"
						class="btn btn-success">
				</div>
				<div class="col-lg-6">
					<input type="reset" value="清除" class="btn btn-success">
				</div>
			</div>
	</FORM>
	</div>


<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->
<script>
	//----------------------------------------	驗證----------------------------------------	
	$("#pord_upd_form").validate({
		errorClass:"my-error-class",
		validClass:"my-valid-class",
		
		rules:{
			pro_prod_name: {required:true},
			pro_end:{compareDate:$("input[name='pro_begin']"),required:true},
			pro_neirong:{maxlength:70}
		},
		messages:{
			pro_prod_name:{
				required:"【請輸入促銷商品名稱】"
			},
			pro_end:{
				required:"【請輸入商品截止日】",
				compareDate:"【使用期限必须大於發行日期】"
			},
			pro_neirong:{
				maxlength:"【範圍必須小於70字之間】"
			}
			
		}
	})	

	$(function() {
		$(":button").on('click', function() {
			var update = $("#pord_upd_form");
			if(update.valid()){
				$.ajax({
					type : "POST",
					url : "updateProm.do",
					data : $("form[name='upProForm']").serialize(),
					success : function(data) {
						$.ajax({
							"type" : "post",
							"url" : "allProm.do",
							"data" : {},
							"success" : function(data) {
								$(".result_content").html(data);
							},
						});
					},
				})
			}	
		})
		
	})
</script>	
	

</body>
</html>