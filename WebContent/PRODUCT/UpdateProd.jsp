<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>修改商品資料</title>
<style>
	.titlelist {
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #99CCCC;
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
</style>
</head>
<body>
	<jsp:useBean id="ComSvc" scope="page" class="com.company.model.ComService" />
	<div class="titlelist">修改商品資料</div>
		<div class="col-lg-12">
	<p class="distance">
		<c:if test="${not empty errorMsgs}">
			<font color='red'>請修正以下錯誤:
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
		
		<form method="post" action="updateProd.do" enctype="multipart/form-data" class="upd_prod form-horizontal" role="form" id="upd_prod_valid">
				<div class="form-group">
					<label class="col-lg-1 col-lg-offset-5 control-label">商品編號:</label>
					<div class="col-lg-6">
					<input type="text" name="prod_id" value="${prodVO.prod_id}" readonly="readonly" style="color: gray">
					</div>
				</div>	
				<div class="form-group">	
					<label class="col-lg-1 col-lg-offset-5 control-label">商品名稱:</label>
					<div class="col-lg-6">
						<input type="text" name="prod_name" value="${prodVO.prod_name}">
					</div>	
				</div>	
				<div class="form-group">	
					<label class="col-lg-1 col-lg-offset-5 control-label">廠商名稱:</label>
					<div class="col-lg-1">
						<select size="1" name="com_id" id="com_id" class="form-control">
							<c:forEach var="ComVO" items="${ComSvc.all}">
								<option value="${ComVO.com_id}">${ComVO.com_name}</option>
							</c:forEach>
						</select>
					</div><div class="col-lg-5"></div>	
				</div>	
				<div class="form-group">	
					<label class="col-lg-1 col-lg-offset-5 control-label">分類:</label>
					<div class="col-lg-6">	
						<input type="text" name="prod_group" value="${prodVO.prod_group}">
					</div>
				</div>	
				<div class="form-group">					
					<label class="col-lg-1 col-lg-offset-5 control-label">定價:</label>
					<div class="col-lg-6">
						<input type="text" name="prod_mkprice" value="${prodVO.prod_mkprice}">
					 </div>
				</div>	
				<div class="form-group">	 
					<label class="col-lg-1 col-lg-offset-5 control-label">成本:</label>
					<div class="col-lg-6">
						<input type="text" name="prod_cost" value="${prodVO.prod_cost}">
					</div>
				</div>	
				<div class="form-group">	
					<label class="col-lg-1 col-lg-offset-5 control-label">庫存:</label>
					<div class="col-lg-6">
						<input type="text" name="prod_stock" value="${prodVO.prod_stock}">
					</div>
				</div>	
				<div class="form-group">	
					<label class="col-lg-1 col-lg-offset-5 control-label">安全庫存:</label>
					<div class="col-lg-6">
						<input type="text" name="prod_q_safty" value="${prodVO.prod_q_safty}">
					</div>
				</div>	
				<div class="form-group">	
					<label class="col-lg-1 col-lg-offset-5 control-label">規格:</label>
					<div class="col-lg-6">
						<input type="text" name="prod_spec" value="${prodVO.prod_spec}">
					</div>
				</div>	
				<div class="form-group">	
					<label class="col-lg-1 col-lg-offset-5 control-label">商品照片:</label>
					<div class="col-lg-6">
						<img alt="尚無名片" src="data:image/gif;base64,${prodVO.picture}"> 
					</div>	
				</div>	
				<div class="form-group">
					<label class="col-lg-1 col-lg-offset-5 control-label">上傳:</label>	
					<div class="col-lg-6">
						<input type="file" name="newPicture">
					</div>
				</div>	
				<div class="form-group">	
					<label class="col-lg-1 col-lg-offset-5 control-label">備註:</label>
					<div class="col-lg-6">
						<input type="text" name="remark" value="${prodVO.remark}">
					</div>
				</div>	
				<div class="form-group">	
					<label class="col-lg-1 col-lg-offset-5 control-label">是否上架:</label>
					<div class="col-lg-6">
						<input type="text" name="status" value="${prodVO.status}">
					</div>
				</div>
				<p class="distance">	
				<div class="form-group">
					<div class="col-lg-1 col-lg-offset-5 control-label">
						<input type="submit" value="修改" class="btn btn-warning">
					</div>
					<div class="col-lg-6">
						<input type="reset" value="清除" class="btn btn-warning">
					</div>
				</div>

		</form>
	</div>


	<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->
	<script type="text/JavaScript">
	//----------------------------------------	驗證----------------------------------------	
	$("#upd_prod_valid").validate({
		errorClass:"my-error-class",
		validClass:"my-valid-class",
		
		rules:{
			prod_name: {required:true},
			prod_group:{required:true},
			prod_mkprice:{digits:true ,required:true,min:1},
			prod_cost:{digits:true,required:true,min:1},
			prod_q_safty:{min:1,required:true},
			prod_spec:{maxlength:70},
			remark:{maxlength:70},
			status:{required:true}
		},
		messages:{
			prod_name:{
				required:"【請輸入商品名稱】"
			},
			prod_group:{
				required:"【請輸入商品分類】"
			},
			prod_mkprice:{
				required:"【請輸入商品定價】",
				digits:"【必須是數字】",
				min:"【數字至少大於1且不可為負】"
			},
			prod_cost:{
				required:"【請輸入商品成本】",
				digits:"【必須是數字】",
				min:"【數字至少大於1且不可為負】"
			},prod_q_safty:{
				min:"【安全庫存量至少大於1且不可為負】",
				required:"【請輸入安全庫存量】"		
			},prod_spec:{
				maxlength:"【範圍必須小於70字之間】"
			},remark:{
				maxlength:"【範圍必須小於70字之間】"
			},status:{
				required:"【請輸入商品狀態】"
			}
			
		}
	})	
	//----------------------------------------	更新----------------------------------------	
		$("#upd_prod_valid").on('submit', function(e) {
			e.preventDefault();
			var serializeData = $(this).serialize();
			if($("#upd_prod_valid").valid()){
				$(this).ajaxSubmit({
					type : "post",
					url : "updateProd.do",
					data : serializeData,
					contentType : false,
					cache : false,
					processData : false,
					success : function(data) {
						$.ajax({
							"type" : "post",
							"url" : "getAllProd.do",
							"data" : {},
							"success" : function(data) {
								$(".result_content").html(data);
							},
						});
					}
				});
			}
		});
	</script>
</body>
</html>