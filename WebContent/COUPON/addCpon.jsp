<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.coupon.model.*"%>
<%
CouponVO copVO = (CouponVO) request.getAttribute("copVO");	//若輸入錯誤可以傳回包含錯誤的VO,有些對的就不用重打了
%>
      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新增折價券</title>
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
</style>
</head>
<body>

	<div class="titlelist">新增</div>
		<div class="col-lg-12">
<!-- 錯誤表列 -->
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
							
	<FORM METHOD="post" ACTION="insertCpon.do" name="form1" class="form-horizontal" role="form" id="insert">
		<p class="distance">
			<div class="form-group">
				<label class="col-lg-1 col-lg-offset-5 control-label">折價券名稱:</label>
				<div class="col-lg-6">
					<input type="text" name="cpon_name" size="20" value="<%=(copVO == null) ? "新年" : copVO.getCpon_name()%>" maxlength="20"/>
				</div>
			</div>
			<div class="form-group">	
				<label class="col-lg-1 col-lg-offset-5 control-label">發行日期:</label>
				<div class="col-lg-6">
					<input type="date" name="release_date" size="20" value="<%= (copVO==null)? "" : copVO.getRelease_date()%>" id="release_date"/>
				</div>
			</div>
			<div class="form-group">	
				<label class="col-lg-1 col-lg-offset-5 control-label">使用期限:</label>
				<div class="col-lg-6">
					<input type="date" name="cpon_period" size="20" value="<%= (copVO==null)? "" : copVO.getCpon_period()%>" />
				</div>
			</div>
			<div class="form-group">		
				<label class="col-lg-1 col-lg-offset-5 control-label">面額:</label>
				<div class="col-lg-6">	
					<input type="text" name="cpon_dollar" size="20" value="<%= (copVO==null)? "100" : copVO.getCpon_dollar()%>" />
				</div>
			</div>
			<div class="form-group">		
				<label class="col-lg-1 col-lg-offset-5 control-label">數量:</label>
				<div class="col-lg-1">	
					<select size="1" name="cpon_count" class="form-control">
					</select>
				</div>
				<div class="col-lg-5"></div>
			</div>
			<div class="form-group">		
				<label class="col-lg-1 col-lg-offset-5 control-label">狀態:</label>
				<div class="col-lg-6">
					已出貨<input type="radio" name="status"  value="Y" />
					庫存<input type="radio" name="status"  value="N"/>
				</div>
			</div>
				<p class="distance">
				<div class="form-group">
					<div class="col-lg-offset-5 col-lg-1">
						<input type="button" value="送出新增" name="c_promoting" class="btn btn-theme03">
					</div>
					<div class="col-lg-5">
						<input type="reset" value="清除" class="btn btn-theme03">
					</div>
				</div>
	</FORM>
</div>				
<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->
<script>
//驗證
		$("#insert").validate({
		errorClass:"my-error-class",
		validClass:"my-valid-class",
		
		rules:{
			cpon_name: {required:true},
			release_date:{required:true},
			cpon_period:{required:true,compareDate:$("input[name='release_date']")},
			cpon_dollar:{required:true,number:true,range:[1,10000]},
			status:{required:true}
		},
		messages:{
			cpon_name:{
				required:"【請輸入折價券名稱】"
			},
			release_date:{
				required:"【請輸入折價券起始日】"
			},
			cpon_period:{
				required:"【請輸入折價券到期日】",
				compareDate:"【使用期限必须大於發行日期】"
			},
			cpon_dollar:{
				required:"【請輸入折價券金額】",
				number:"【請輸入數字】",
				range:"【範圍必須介於1~10000之間】"
			},
			status:{
				required:"【請選擇折價券狀態】"
			}
		}
	})	    
	$(function() {
//迴圈產生1~100			
		var sel =$('select[name=cpon_count]');
	    for (i = 1; i <= 100; i++) {
	    	var opt = $("<option>");
	        opt.val(i);
	        opt.append(i);
	        sel.append(opt);
	    }
	    
//新增			
		$(':button').on('click', function() {
			if("送出新增"==$(this).val()){				
				var form1 = $("#insert");
				if(form1.valid()){
					$.ajax({
						type : "POST",
						url : form1.attr('action'),
						data : form1.serialize(),
						success : function(data) {
							$(".result_content").html(data);
							$("#rel_cou").attr("class","active");
							$("#ins_cou").removeAttr("class");
							$("#new_Cou").attr("class","tab-pane fade");
							$("#resolution_Cou").attr("class","tab-pane active");
						}
					})
				}	
			}
		})

	})
</script>	
</body>
</html>