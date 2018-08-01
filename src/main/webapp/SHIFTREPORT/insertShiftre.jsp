<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>insertShiftre</title>
<style>
/* nav */
	.navbar-default{
		background: #E6F9AF;
		border-color:#E6F9AF;
		border-radius: 8px;
	}
/* background */
 	.main{ 
  		height: 800px;  
 		border-radius: 8px; 
 		background:	#A0DBB9; 
 	}
/*  title	 */
 	.titlelist {
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #384E77;
		padding-left: 10px;
		font-size: 23px;
		border-radius: 2px;
	}
		
	.distance{
		margin: 30px;	
	}
	
	 .form-horizontal .control-label { 
	     text-align: right; 
	 }
	 
	 .btn-theme02 {
    color: #fff;
    background-color: #229abd;
    border-color: #31535d;
} 
</style>
</head>
<body>
		<div class="titlelist">新增</div>
	<div class="col-lg-12">

		<form method="post" action="insertShiftre.do" class="insertShift form-horizontal" role="form">

			<p class="distance">
			<div class="form-group">
				<label class="col-lg-1 col-lg-offset-5 control-label">日期:</label>
				<div class="col-lg-6">
					<input type="Date" name="Date">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-1 col-lg-offset-5 control-label">班別:</label>
				<div class="col-lg-1">
<%-- 					<input type="text" name="shift" value="${SHIFT}"> --%>
									<select size="1" name="shift" id="shift" class="form-control refresh">
											<option value="A">A</option>
											<option value="B">B</option>
									</select>
				</div><div class="col-lg-5"></div>
			</div>

			<div class="form-group">
				<label class="col-lg-1 col-lg-offset-5 control-label">員工編號:</label>
				<div class="col-lg-6">
					<input type="text" name="emp_id" value="${LoginOK.emp_id}">
				</div>
			</div>

<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-lg-1 col-lg-offset-5 control-label">現金:</label> -->
<!-- 				<div class="col-lg-6"> -->
<!-- 					<input type="text" name="cash" value="2000"> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-lg-1 col-lg-offset-5 control-label">禮卷:</label> -->
<!-- 				<div class="col-lg-6"> -->
<!-- 					<input type="text" name="coupon" value="0"> -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-lg-1 col-lg-offset-5 control-label">折讓:</label> -->
<!-- 				<div class="col-lg-6"> -->
<!-- 					<input type="text" name="discount" value="0"> -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-lg-1 col-lg-offset-5 control-label">零用金:</label> -->
<!-- 				<div class="col-lg-6"> -->
<!-- 					<input type="text" name="coins" value="1500"> -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-lg-1 col-lg-offset-5 control-label">交易額:</label> -->
<!-- 				<div class="col-lg-6"> -->
<!-- 					<input type="text" name="deal_sum" value="2000"> -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-lg-1 col-lg-offset-5 control-label">交易成本:</label> -->
<!-- 				<div class="col-lg-6"> -->
<!-- 					<input type="text" name="deal_cost" value="1000"> -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-lg-1 col-lg-offset-5 control-label">交易淨利:</label> -->
<!-- 				<div class="col-lg-6"> -->
<!-- 					<input type="text" name="deal_profit" value="1000"> -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-lg-1 col-lg-offset-5 control-label">交易次數:</label> -->
<!-- 				<div class="col-lg-6"> -->
<!-- 					<input type="text" name="deal_num" value="3"> -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-lg-1 col-lg-offset-5 control-label">班別小計:</label> -->
<!-- 				<div class="col-lg-6"> -->
<!-- 					<input type="text" name="shift_sum" value="2000"> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<p class="distance">
			<div class="form-group">
				<div class="col-lg-1 col-lg-offset-5">
					<input type="button" value="新增" name="insert_shift" class="btn btn-theme02">
				</div>
				<div class="col-lg-5">
					<input type="reset" value="清除" class="btn btn-theme02">
				</div>
			</div>

	</form>
	</div>
<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->
<script>
	$(":button").click(function() {
		if("新增"==$(this).val()){
			$.ajax({
				type : "post",
				url : "insertShiftre.do",
				data : $(".insertShift").serialize(),
				success : function(data) {
					$(".rul").html(data);
					$("#shi_new").removeAttr("class");
					$("#shi_rel").attr("class", "active");
					$("#new").attr("class", "tab-pane fade");
					$("#result").attr("class", "tab-pane active");
				}
			});
		}
	});
</script>				
</body>
</html>