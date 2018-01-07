<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
	<style type="text/css">
		#myform label.error{
			color: #e4007f;
			display: inline-block;
			padding: 3px 3px;
			font-size: 12px;
			line-height: 1;
			width: auto;
		}
	</style>
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="" id="myform">
		<div class="form-group">
			<label>姓名<span>（必須要大於 3 個字，且小於 8 個字，必填）</span></label>
			<input type="text" name="user_name" class="required" minlength="3" maxlength="8" >
		</div>
		<div class="form-group">
			<label>Email<span>（規則：Email 格式，必填）</span></label>
			<input type="email" name="user_mail" class="required" >
		</div>
		<div class="form-group">
			<label>最喜歡的網站<span>（規則：URL格式）</span></label>
			<input type="url" name="website">
		</div>
		<div class="form-group">
			<label>出生年份</label>
			<input type="tel" class="digits" name="user_birthy">
		</div>
		<div class="form-group">
			<label>密碼</label>
			<input type="password" name="user_pwd" id="user-pwd-input" >
		</div>
		<div class="form-group">
			<label>一樣的密碼<span>（規則：要跟上一格寫一樣）</span></label>
			<input type="password" name="user_pwd_again" >
		</div>
		<br/>
		<h5 class="text-error">## 以下將示範一些特別的 Rule</h5>
		<div class="form-group">
			<label>複雜密碼<span>（規則：6～20個字元的英文字母、數字混合，但不含空白鍵及標點符號。）</span></label>
			<input type="password" class="checkpwdhard" name="pwdhard" >
		</div>
		<div class="form-group">
			<label>有規則的姓名<span>（規則：姓名可為中文（最少兩字）或英文（最少三字），英文請勿使用除了空白、底線、DASH以外的符號）</span></label>
			<input type="text" class="checkname" name="namehard" >
		</div>
		<div class="form-group">
			<label>台灣手機號碼<span>（規則：僅允許09開頭的10碼數字，且會自動將使用者寫的 8869 改成 09）</span></label>
			<input type="tel" class="mobileTaiwan" name="mobileTaiwanInput" >
		</div>
		<div class="form-group">
			<label>一般市話<span>（規則：僅接受數字、#-()等符號）</span></label>
			<input type="tel" class="phoneStyle" name="phoneInput" >
		</div>
		<div class="form-group">
			<label>身分證字號<span>（規則：一個大寫英文字開頭，且接續一個 1 或 2 數字的10個字字串）</span></label>
			<input type="text" class="chkPid" name="user_pid" maxlength="10">
		</div>
		<div class="form-group">
			<label>民國出生年月日<span>（規則：民國0~104年之間）</span></label>
			<input type="tel" class="taiwanBirth" name="user_birth" minlength="6" maxlength="7">
		</div>
		<button type="submit" >送出</button>
	</form>
	
	<script type="text/javascript">
	$(document).ready(function(){
		//自己這個站的 js
		$("#myform").validate({
			submitHandler: function(form) {
				form.submit();
			},
			errorPlacement: function(error, element) {
				element.closest('.form-group').append(error);
			},
			rules: {
				user_pwd_again: {
					equalTo : '#user-pwd-input'
				}
			}
		});
	});
	

</script>

</body>
</html>