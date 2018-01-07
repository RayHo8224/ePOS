<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>insertDic</title>
<style>
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
		margin: 30px;	
	}
	
	.btn-theme02{
		float:center			
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
		<div class="titlelist">新增</div>
				<div class="col-lg-12">
						<!--錯誤表列 -->
<%-- 						<c:if test="${not empty errorMsgs}"> --%>
<!-- 							<font color='red'>請修正以下錯誤: -->
<!-- 								<ul> -->
<%-- 									<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 										<li>${message}</li> --%>
<%-- 									</c:forEach> --%>
<!-- 								</ul> -->
<!-- 							</font> -->
<%-- 						</c:if> --%>
					<form class="form-inline" METHOD="post" ACTION="insertDisc.do" id="insert">
							<center>
								<p  class="distance">
								<div class="form-group">
									<label for="dis_id">　折扣身分：</label>
									<input type=text class="form-control" name="dis_id" size="10" id="dis_id"  maxlength="10">
								</div>
								<p  class="distance">
								<div class="form-group">
									<label for="dis_price">　折扣%數：</label>
									<input type="text" class="form-control" name="dis_price" placeholder="1.0" maxlength="4" size="10" id="dis_price">
								</div>
								<p  class="distance">
								<div class="veri_id"></div><div class="veri_price"></div>
								<div class="form-group">
									<div class="col-lg-6">
										<input type="button" value="送出新增" class="btn btn-warning">
									</div>	
									<div class="col-lg-5 col-lg-offset-1">
										<input type="reset" value="清除" class="btn btn-warning">
									</div>
								</div>
							</center>
					</form>
				</div>
<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->			
<script type="text/JavaScript">
$(document).ready(function() {

// ----------------------------------------	驗證----------------------------------------	

	$("#insert").validate({
		errorClass:"my-error-class",
		validClass:"my-valid-class",
		
		rules:{
			dis_id: {required:true,maxlength:10},
			dis_price:{required:true,number:true,range:[0.01,1]}
		},
		messages:{
			dis_id:{
				required:"【請輸入折扣身分】",
				maxlength:"【輸入長度不可大於10】"
			},
			dis_price:{
				required:"【請輸入折扣%數】",
				number:"【請輸入數字】",
				range:"【範圍必須介於0.01~1之間】"
			}
		}
	})
// ----------------------------------------	新增----------------------------------------			
	$(":button[value='送出新增']").click(function() {

		var insert = $("#insert");
		if(insert.valid()){
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