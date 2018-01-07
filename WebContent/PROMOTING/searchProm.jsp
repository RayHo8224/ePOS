<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.promoting.model.*"%>
<%
	PromotingVO promVO = (PromotingVO) request.getAttribute("promVO");	//若輸入錯誤可以傳回包含錯誤的VO,有些對的就不用重打了
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>search</title>
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
</style>
</head>
<body>
	<div class="titlelist">查詢</div>
	<div class="col-lg-12">
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

		<p class="distance">
		<FORM METHOD="post" ACTION="namesProm.do" name="r_prom1" class="form-horizontal" role="form" id="r_prom1">
			<div class="form-group">
				<label class="col-lg-2 col-lg-offset-3 control-label">輸入促銷商品編號(如P00001):</label>
				<div class="col-lg-2">
					<input type="text" name="pro_prod_name" size="20" maxlength="6">
				</div>
				<div class="col-lg-3  col-lg-offset-2">
					<input type="button" value="送出" class="r_prom1 btn btn-success">
				</div>
			</div>
		</FORM>


		<jsp:useBean id="PromSvc" scope="page"
			class="com.promoting.model.PromotingService" />

		<FORM METHOD="post" ACTION="datesProm.do" name="r_prom2" class="form-horizontal" role="form" id="r_prom2">
			<div class="form-group">
				<label class="col-lg-2 col-lg-offset-3 control-label">選擇促銷日期區間:</label>
				<div class="col-lg-2">
					<input type="date" name="pro_begin">
				</div>
				<div class="col-lg-2">
					<input type="date" name="pro_end">
				</div>
				<div class="col-lg-3">
					<input type="button" value="送出" class="r_prom2 btn btn-success">
				</div>

				<input type="hidden" name="action" value="getDates_For_Display">
			</div>
		</FORM>

		<FORM METHOD="post" ACTION="idsProm.do" name="r_prom3" class="form-horizontal" role="form" id="r_prom3">
			<div class="form-group">
				<label class="col-lg-2 col-lg-offset-3 control-label">選擇促銷編號範圍:</label>
				<div class="col-lg-2">
					<select size="1" name="pro_prod_id1" class="form-control">
						<c:forEach var="promVO" items="${PromSvc.GroupByIDs()}">
							<option value="${promVO.pro_prod_id}">${promVO.pro_prod_id}
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-2">
					<select size="1" name="pro_prod_id2" class="form-control">
						<c:forEach var="promVO" items="${PromSvc.GroupByIDs()}">
							<option value="${promVO.pro_prod_id}">${promVO.pro_prod_id}
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-3">
					<input type="button" value="送出" class="r_prom3 btn btn-success">
				</div>
					<input type="hidden" name="action" value="getIds_For_Display">
			</div>
		</FORM>

		<%-- 查詢全部 --%>
		<div class="form-group">
		<label class="col-lg-2 col-lg-offset-3 control-label">查詢全部:</label>
			<div class="col-lg-3 col-lg-offset-4">
				<input type="button" value="送出" class="r_prom4 btn btn-success">
			</div>
		</div>	
	</div>


	<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->			
<script type="text/JavaScript">
//----------------------------------------	驗證----------------------------------------	
	$("#r_prom1").validate({
		errorClass:"my-error-class",
		validClass:"my-valid-class",
		
		rules:{
			pro_prod_name:{required:true}
		},
		messages:{
			pro_prod_name:{required:"【請輸入促銷商品關鍵字】"}
		}
	})	

	$("#r_prom2").validate({
		errorClass:"my-error-class",
		validClass:"my-valid-class",
		
		rules:{
			pro_begin:{required:true},
			pro_end:{compareDate:$("input[name='pro_begin']"),required:true},
		},
		messages:{
			pro_begin:{
				required:"【請輸入商品起始日期】"
			},
			pro_end:{
				required:"【請輸入商品截止日】",
				compareDate:"【使用期限必须大於發行日期】"
			}	
		}
	})		
	
	$(document).ready(function() {
		// 查詢						
		$(":button").click(function() {
			if ($(this).attr("class") == 'r_prom1 btn btn-success') {
				var r_prom1 = $("form[name='r_prom1']");
				if(r_prom1.valid()){
					$.ajax({
						"type" : r_prom1.attr("method"),
						"url" : r_prom1.attr("action"),
						"data" : r_prom1.serialize(),
						"success" : function(data) {
							$(".result_content").html(data);
							$("#result_prom").attr("class", "active");
							$("#search_prom").removeAttr("class");
							$("#search_Pro").attr("class", "tab-pane fade");
							$("#result_Pro").attr("class", "tab-pane active");
						},
					});
				}	
			} else if ($(this).attr("class") == 'r_prom2 btn btn-success') {
				var r_prom2 = $("form[name='r_prom2']");
				if(r_prom2.valid()){
					$.ajax({
						"type" : r_prom2.attr("method"),
						"url" : r_prom2.attr("action"),
						"data" : r_prom2.serialize(),
						"success" : function(data) {
							$(".result_content").html(data);
							$("#result_prom").attr("class", "active");
							$("#search_prom").removeAttr("class");
							$("#search_Pro").attr("class", "tab-pane fade");
							$("#result_Pro").attr("class", "tab-pane active");
						},
					});
				}	
			} else if ($(this).attr("class") == 'r_prom3 btn btn-success') {
				var r_prom3 = $("form[name='r_prom3']");
// 				if(r_prom3.valid()){
					$.ajax({
						"type" : r_prom3.attr("method"),
						"url" : r_prom3.attr("action"),
						"data" : r_prom3.serialize(),
						"success" : function(data) {
							$(".result_content").html(data);
							$("#result_prom").attr("class", "active");
							$("#search_prom").removeAttr("class");
							$("#search_Pro").attr("class", "tab-pane fade");
							$("#result_Pro").attr("class", "tab-pane active");
						},
					});
// 				}	
			} else if ($(this).attr("class") == 'r_prom4 btn btn-success') {
				$.ajax({
					"type" : "post",
					"url" : "allProm.do",
					"data" : {},
					"success" : function(data) {
						$(".result_content").html(data);
						$("#result_prom").attr("class", "active");
						$("#search_prom").removeAttr("class");
						$("#search_Pro").attr("class", "tab-pane fade");
						$("#result_Pro").attr("class", "tab-pane active");
					},
				});
			}
		})

	})
</script>				
</body>
</html>