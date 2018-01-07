<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>searchCpon</title>
<style>
	.col-lg-1 {
	    width: 10%;
	}

	.titlelist {
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #F4A460;
		padding-left: 10px;
		font-size: 23px;
		border-radius: 2px;
	}

	.distance{
		margin: 20px;	
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

				<jsp:useBean id="DiscSvc" scope="page" class="com.discount.model.DiscountService" />

				<div class="titlelist">查詢</div>
				<div class="col-lg-12">

						<p class="distance">
						<form METHOD="post" ACTION="disc.do" id="count" class="form-horizontal style-form">
							<div class="col-lg-4"></div>
							<div class="form-group">
								<label class="col-lg-1 control-label" for="dis_id">選擇折扣身份</label>
								<div class="col-lg-1">
									<select size="1" name="dis_id" id="dis_id" class="form-control">
										<c:forEach var="discVO" items="${DiscSvc.all}">
											<option value="${discVO.dis_id}">${discVO.dis_id}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-lg-4">
									<input type="button" value="以折扣身份查詢" class="btn btn-warning">
								</div>
							</div>
						</form>
						<form METHOD="post" ACTION="GroupByDisc.do" id="pre" class="form-horizontal style-form">
							<div class="col-lg-4"></div>
							<div class="form-group">
								<label class="col-lg-1 control-label" for="dis_price">選擇折扣%數</label>
								<div class="col-lg-1">
									<select size="1" name="dis_price" id="dis_price" class="form-control refresh">
										<c:forEach var="discVO" items="${DiscSvc.groupPrice()}">
											<option value="${discVO.dis_price}">${discVO.dis_price}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-lg-4">
									<input type="button" value="以折扣%數查詢" class="btn btn-warning">
								</div>
							</div>
						</form>
						<form METHOD="post" ACTION="allDisc.do" id="all" class="form-horizontal style-form">
							<div class="col-lg-4"></div>						
							<div class="form-group">
								<label class="col-lg-1 control-label">查詢全部</label>
								<div class="col-lg-1"></div>
								<div class="col-lg-4">
									<input type="button" value="查詢全部" class="btn btn-warning">
								</div>
							</div>
						</form>
					</div>
<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->	
	<script>
	$(document).ready(function() {
		$(":button").click(function() {
			if ("查詢全部" == $(this).val()) {
				var all = $("#all");
				$.ajax({
					"type" : all.attr("method"),
					"url" : all.attr("action"),
					"data" : all.serialize(),
					"success" : function(data) {
						$(".result_content").html(data);
						$("#result").attr("class","active");
						$("#search1").removeAttr("class");
						$("#search_Dic").attr("class","tab-pane fade");
						$("#resolution_Dic").attr("class","tab-pane active");
					},
				});
			} else if ("以折扣%數查詢" == $(this).val()) {
				var pre = $("#pre");
				$.ajax({
					"type" : pre.attr("method"),
					"url" : pre.attr("action"),
					"data" : pre.serialize(),
					"success" : function(data) {
						$(".result_content").html(data)
						$("#result").attr("class","active");
						$("#search1").removeAttr("class");
						$("#search_Dic").attr("class","tab-pane fade");
						$("#resolution_Dic").attr("class","tab-pane active");
					},
				});
			} else if ("以折扣身份查詢" == $(this).val()) {
				var count = $("#count");
				$.ajax({
					"type" : count.attr("method"),
					"url" : count.attr("action"),
					"data" : count.serialize(),
					"success" : function(data) {
						$(".result_content").html(data)
						$("#result").attr("class","active");
						$("#search1").removeAttr("class");
						$("#search_Dic").attr("class","tab-pane fade");
						$("#resolution_Dic").attr("class","tab-pane active");
					},
				});
			} else if ("送出新增" == $(this).val()) {
				var insert = $("#insert");
				$.ajax({
					"type" : insert.attr("method"),
					"url" : insert.attr("action"),
					"data" : insert.serialize(),
					"success" : function(data) {
						$.ajax({
							"type" : "post",
							"url" : "allDisc.do",
							"data" : {},
							"success" : function(data) {
								$(".result_content").html(data);
								$("#result").attr("class","active");
								$("#import").removeAttr("class");
								$("#new_Dic").attr("class","tab-pane fade");
								$("#resolution_Dic").attr("class","tab-pane active");
							},
						});
					},
				});
			}

		})

		
	})
	</script>				
</body>
</html>