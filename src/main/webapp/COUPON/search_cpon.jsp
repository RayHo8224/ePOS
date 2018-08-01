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
	.titlelist {
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: mediumseagreen;
		padding-left: 10px;
		font-size: 23px;
		border-radius: 2px;
	}
	
	.distance {
		margin: 30px;
	}
	
	.form-horizontal .control-label {
		text-align: right;
	}

	.my-valid-class{
		color:#3a51e8;
	}
	
	.my-error-class{
		color:red;
	}
	.move{
		margin-left:20px;
	}
	
	.col-lg-offset-1 {
    	margin-left: 100px;
    }
</style>
</head>
<body>
<!-- 錯誤表列 -->
<%-- 				<c:if test="${not empty errorMsgs}"> --%>
<!-- 					<font color='red'>請修正以下錯誤 -->
<!-- 						<ul> -->
<%-- 							<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 								<li>${message}</li> --%>
<%-- 							</c:forEach> --%>
<!-- 						</ul> -->
<!-- 					</font> -->
<%-- 				</c:if> --%>

	<div class="titlelist">查詢</div>
		<div class="col-lg-12">
		<p class="distance">

<!-- 			<FORM METHOD="post" ACTION="coupon.do" class="search_1 form-horizontal" role="form""> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label class="col-lg-1 col-lg-offset-4 control-label">輸入折價券編號 (如CPa00001):</label> -->
<!-- 					<div class="col-lg-3"> -->
<!-- 						<input type="text" name="cpon_id" size="10" class="form-control"> -->
<!-- 					</div>	 -->
<!-- 					<div class="col-lg-4">	  -->
<!-- 						<input type="button" value="送出" id="search_1"  class="btn btn-theme03"> -->
<!-- 					</div>	 -->
<!-- 					<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 				</div>	 -->
<!-- 			</FORM> -->

					
		<jsp:useBean id="copSvc" scope="page" class="com.coupon.model.CouponService" />
		

			<FORM METHOD="post" ACTION="coupon.do" class="search_2 form-horizontal" role="form">
				<div class="form-group">
					<label class="col-lg-1 col-lg-offset-4 control-label">選擇折價券編號:</label>
					<div class="col-lg-3">
						<select size="1" name="cpon_id" class="form-control">
							<c:forEach var="copVO" items="${copSvc.all}">
								<option value="${copVO.cpon_id}">${copVO.cpon_id}
							</c:forEach>
						</select>
					</div>
					 <div class="col-lg-4">
						<input type="button" value="送出" id="search_2" class="btn btn-theme03">
					</div>
						<input type="hidden" name="action" value="getOne_For_Display">
				</div>	
			</FORM>
		
<%--查詢多筆 --%>
		<p class="distance">
		
			<FORM METHOD="post" ACTION="namesCpon.do" class="search_3 form-horizontal" role="form">
				<div class="form-group">
					<label class="col-lg-1 col-lg-offset-4 control-label">選擇折價券名稱:</label> 
					<div class="col-lg-3">
						<select size="1" name="cpon_name" class="form-control">
							<c:forEach var="copVO" items="${copSvc.groupNam}">
								<option value="${copVO.cpon_name}">${copVO.cpon_name}
							</c:forEach>
						</select> 
					</div>
					<div class="col-lg-4">
						<input type="button" value="送出" id="search_3" class="btn btn-theme03">
					</div>
						<input type="hidden" name="action" value="getNames_For_Display">
				</div>	
			</FORM>
	
		<p class="distance">
				
			<FORM METHOD="post" ACTION="dollarCpon.do" class="search_4 form-horizontal" role="form">
				<div class="form-group">
					<label class="col-lg-1 col-lg-offset-4 control-label">選擇折價券金額:</label> 
					<div class="col-lg-3">
						<select size="1" name="cpon_dollar" class="form-control">
							<c:forEach var="copVO" items="${copSvc.groupDol}">
								<option value="${copVO.cpon_dollar}">${copVO.cpon_dollar}
							</c:forEach>
						</select> 
					</div>
					<div class="col-lg-4">
						<input type="button" value="送出" id="search_4" class="btn btn-theme03">
					</div>
					<input type="hidden" name="action" value="getDollar_For_Display">
				</div>
			</FORM>
	
		<p class="distance">
		
			<FORM METHOD="post" ACTION="datesCpon.do" class="search_5 form-horizontal" role="form" id="sel_cou_valid">
				<div class="form-group">
					<label class="col-lg-1 col-lg-offset-4 control-label">選擇折價券日期區間:</label>
					<div class="col-lg-1">
						<input type="date" name="release_date">
					</div>
					<div class="col-lg-1 move">
						<input class="move" type="date" name="cpon_period"> 
					</div>
					<div class="col-lg-4 col-lg-offset-1">
						<input type="button" value="送出" id="search_5" class="btn btn-theme03">
					</div>
					<input type="hidden" name="action" value="getDates_For_Display">
				</div>
			</FORM>

<!--查詢全部 -->
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/COUPON/allCpon.do" class="form-horizontal" role="form">
				<div class="form-group">
					<label class="col-lg-1 col-lg-offset-4 control-label">查詢全部折價券:</label>
					<div class="col-lg-4 col-lg-offset-3">
						<input type="button" value="查詢全部" id="search_6" class="btn btn-theme03">
					</div>
					<input type="hidden" name="action" value="getAll_For_Display">
				</div>
			</FORM>
		</div>		
<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->				
	<script>
		//驗證
			$("#sel_cou_valid").validate({
			errorClass:"my-error-class",
			validClass:"my-valid-class",
			
			rules:{
				release_date:{required:true},
				cpon_period:{required:true,compareDate:$("input[name='release_date']")},
			},
			messages:{
				release_date:{
					required:"【請輸入折價券起始日】"
				},
				cpon_period:{
					required:"【請輸入折價券到期日】",
					compareDate:"【使用期限必须大於發行日期】"
				}
			}				
		})	    				
		$(function() {
			$(':button').on('click', function() {
				
// 				if($(this).attr('id')=="search_1"){
// 					var search_1 = $(".search_1");
// 					$.ajax({
// 						type : "POST",
// 						url : search_1.attr('action'),
// 						data : search_1.serialize(),
// 						success : function(data) {
// 							$(".result_content").html(data);
// 							$("#rel_cou").attr("class","active");
// 							$("#sea_cou").removeAttr("class");
// 							$("#search_Cou").attr("class","tab-pane fade");
// 							$("#resolution_Cou").attr("class","tab-pane active");
// 						}
// 					})
// 				}else 
					if($(this).attr('id')=="search_2"){
					var search_2 = $(".search_2");
	 				$.ajax({
	 					type : "POST",
	 					url : search_2.attr('action'),
	 					data : search_2.serialize(),
	 					success : function(data) {
	 						$(".result_content").html(data);
							$("#rel_cou").attr("class","active");
							$("#sea_cou").removeAttr("class");
							$("#search_Cou").attr("class","tab-pane fade");
							$("#resolution_Cou").attr("class","tab-pane active");
	 					}
	 				})	
				}else if($(this).attr('id')=="search_3"){
					var search_3 = $(".search_3");
	 				$.ajax({
	 					type : "POST",
	 					url : search_3.attr('action'),
	 					data : search_3.serialize(),
	 					success : function(data) {
	 						$(".result_content").html(data);
							$("#rel_cou").attr("class","active");
							$("#sea_cou").removeAttr("class");
							$("#search_Cou").attr("class","tab-pane fade");
							$("#resolution_Cou").attr("class","tab-pane active");
	 					}
	 				})	
				}else if($(this).attr('id')=="search_4"){
					var search_4 = $(".search_4");
	 				$.ajax({
	 					type : "POST",
	 					url : search_4.attr('action'),
	 					data : search_4.serialize(),
	 					success : function(data) {
	 						$(".result_content").html(data);
							$("#rel_cou").attr("class","active");
							$("#sea_cou").removeAttr("class");
							$("#search_Cou").attr("class","tab-pane fade");
							$("#resolution_Cou").attr("class","tab-pane active");
	 					}
	 				})	
				}else if($(this).attr('id')=="search_5"){
					var search_5 = $(".search_5");
					if(search_5.valid()){
		 				$.ajax({
		 					type : "POST",
		 					url : search_5.attr('action'),
		 					data : search_5.serialize(),
		 					success : function(data) {
		 						$(".result_content").html(data);
								$("#rel_cou").attr("class","active");
								$("#sea_cou").removeAttr("class");
								$("#search_Cou").attr("class","tab-pane fade");
								$("#resolution_Cou").attr("class","tab-pane active");
		 					}
		 				})
					}	
				}else if($(this).attr('id')=="search_6"){
					var search_6 = $(".search_6");
	 				$.ajax({
	 					type : "POST",
	 					url : "allCpon.do",
	 					data : {},
	 					success : function(data) {
	 						$(".result_content").html(data);
							$("#rel_cou").attr("class","active");
							$("#sea_cou").removeAttr("class");
							$("#search_Cou").attr("class","tab-pane fade");
							$("#resolution_Cou").attr("class","tab-pane active");
	 					}
	 				})	
				}	
			});
						
		})
	</script>				
</body>
</html>