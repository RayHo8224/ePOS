<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>�ק���u���</title>
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
		font-family: '�L�n������';
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
	
	.update_text{
		font-family: �L�n������; 
		color:blue;
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
		<div class="titlelist">�ק�</div>
	<div class="col-lg-12">
		<c:if test="${not empty errorMsgs}">
			<font color='red'>�Эץ��H�U���~:
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
		
		<form method="post" action="updateShiftre.do" class="updateShi form-horizontal" role="form" id="upd_shiftreport" name="upd_shiftreport">
		<p class="distance">
			
			<div class="form-group">
				<label class="col-lg-1 col-lg-offset-2 control-label">���:</label>
				<div class="col-lg-1">
					<input type="date" name="Date" value="${shiftreVO.date}" readonly="readonly" size="20">
				</div>
				<label class="col-lg-1 control-label">�Z�O:</label>
				<div class="col-lg-1">
					<input type="text" name="shift" value="${shiftreVO.shift}" readonly="readonly" class="form-control">
				</div>
				<label class="col-lg-1 control-label">���u�s��:</label>
				<div class="col-lg-1">			
					<input type="text" name="emp_id" value="${shiftreVO.emp_id}" readonly="readonly" class="form-control">
				</div>
				<label class="col-lg-1 control-label">�{��:</label>			
				<div class="col-lg-1">			
					<input type="text" name="cash" value="${shiftreVO.cash}" readonly="readonly" class="form-control">
				</div><div class="col-lg-2"></div>	
			</div>
			
			<div class="form-group">
				<label class="col-lg-1 col-lg-offset-2 control-label update_text">�ꦬ�{��:</label>
				<div class="col-lg-1">			
					<input type="text" name="real_cash" value="${shiftreVO.real_cash}" class="form-control">
				</div>
				<label class="col-lg-1 control-label">§��:</label>
				<div class="col-lg-1">			
					<input type="text" name="coupon" value="${shiftreVO.coupon}" readonly="readonly" class="form-control">
				</div>
				<label class="col-lg-1 control-label update_text">�ꦬ§��:</label>
				<div class="col-lg-1">		
					<input type="text" name="real_coupon" value="${shiftreVO.real_coupon}" class="form-control">
				</div>
				<label class="col-lg-1 control-label">����:</label>
				<div class="col-lg-1">		
					<input type="text" name="discount" value="${shiftreVO.discount}" readonly="readonly" class="form-control">
				</div><div class="col-lg-2"></div>
			</div>
			
			<div class="form-group">
				<label class="col-lg-1 col-lg-offset-2 control-label">�s�Ϊ�:</label>
				<div class="col-lg-1">			
					<input type="text" name="coins" value="${shiftreVO.coins}" readonly="readonly" class="form-control">
				</div>
				<label class="col-lg-1 control-label">����B:</label>
				<div class="col-lg-1">			
					<input type="text" name="deal_sum" value="${shiftreVO.deal_sum}" readonly="readonly" class="form-control">
				</div>
				<label class="col-lg-1 control-label">�ӫȼ�:</label>
				<div class="col-lg-1">			
					<input type="text" name="deal_num" value="${shiftreVO.deal_num}" readonly="readonly" class="form-control">
				</div>	
				<label class="col-lg-1 control-label update_text">�Ƶ�:</label>
				<div class="col-lg-1">			
					<input type="text" name="shift_remark" value="${shiftreVO.remark}" class="form-control">
				</div>
				<div class="col-lg-2"></div>
			</div>

			<p class="distance">			
			<div class="form-group">
				<div class="col-lg-1 col-lg-offset-5">
					<input type="button" value="�e�X�ק�" class="btn btn-theme02">
				</div>
				<div class="col-lg-5">
					<input type="reset" value="�M��" class="btn btn-theme02">
				</div>
			</div>

		</form>
</div>
<!-- --------------------------------------------------------------�{���}�l�B---------------------------------------------------------- -->
<script type="text/JavaScript">
//----------------------------------------	����----------------------------------------	
$("#upd_shiftreport").validate({
	errorClass:"my-error-class",
	validClass:"my-valid-class",
	
	rules:{
		real_cash:{required:true,digits:true},		
		real_coupon:{required:true,digits:true},
		shift_sum:{required:true,digits:true}
	},
	messages:{
		real_cash:{
			required:"�i�п�J���B�j",
			digits:	"�i�п�J�Ʀr�j"
		},
		real_coupon:{
			required:"�i�п�J���B�j",
			digits:	"�i�п�J�Ʀr�j"
			},
		shift_sum:{
			required:"�i�п�J���B�j",
			digits:	"�i�п�J�Ʀr�j"
			}
	}
})
	
		$(document).ready(function() {
			$(":button").on('click', function() {
				var upd_shiftreport = $("form[name='upd_shiftreport']");
				if(upd_shiftreport.valid()){
					$.ajax({
						type : "post",
						url : "updateShiftre.do",
						data : $(".updateShi").serialize(),
						success : function(data) {
							$(".rul").html(data);
	
						}
					});
				}
			});
		});
	</script>

</body>
</html>