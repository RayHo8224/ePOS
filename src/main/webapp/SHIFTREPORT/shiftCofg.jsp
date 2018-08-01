<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ConfigureShiftre</title>
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
	     text-align: left;
	 }
	 
	 .btn-theme02 {
    color: #fff;
    background-color: #229abd;
    border-color: #31535d;
	}
	
	.text{
	font-size: 18px; 
	} 
</style>
</head>
<body>
<div class="titlelist">設定</div>
	<div class="col-lg-12">
			<p class="distance">
		<form method="post" action="insertShiftre.do" class="insertShift form-horizontal" role="form">

			<div class="form-group">
				<label class="col-lg-2 col-lg-offset-2 control-label text">自動新增時間:</label>
				<div class="col-lg-1">
					<select size="1" name="hours" id="hours" class="form-control refresh">
					</select>
				</div>
				<label class="col-lg-1 control-label text">時</label>
				<div class="col-lg-1">	
					<select size="1" name="minutes" id="minutes" class="form-control refresh">
					</select>
				</div>	
				<label class="col-lg-1 control-label text">分</label>
				<div class="col-lg-1">
					<select size="1" name="seconds" id="seconds" class="form-control refresh">
					</select>
				</div>
				<label class="col-lg-1 control-label text">秒</label>
				<div class="col-lg-2"></div>
			</div>

			<p class="distance">
			<div class="form-group">
				<div class="col-lg-1 col-lg-offset-6">
					<input type="button" value="確定" name="change_config" class="btn btn-theme02">
				</div>

			</div>

	</form>
	</div>
<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->
<script>
	$(document).ready(function() {
		var s;
		var m;
		var h;
		var sels = $('select[name="seconds"]');
		var selm = $('select[name="minutes"]');
		var selh = $('select[name="hours"]');
		sels.empty();
		selm.empty();
		selh.empty();
			for(s=0;s<=60;s++){
				var opt=$("<option>");
				opt.append(s);
				opt.val(s);
				sels.append(opt);
			}
			
			for(m=0;m<=60;m++){
				var opt=$("<option>");
				opt.append(m);
				opt.val(m);
				selm.append(opt);
			}
			
			for(h=0;h<=23;h++){
				var opt=$("<option>");
				opt.append(h);
				opt.val(h);
				selh.append(opt);
			}
	})

	$(":button").click(function() {
		if("確定"==$(this).val()){
			var setHour = $('select[name="hours"]').val();
			var setMinute = $('select[name="minutes"]').val();
			var setSecond = $('select[name="seconds"]').val();
			hh  = setHour;
			mm = setMinute;
			ss = setSecond;
		}

			setInterval(function(){

				insertTime = new Date();
				var hour = insertTime.getHours();
				var minutes = insertTime.getMinutes();
				var seconds = insertTime.getSeconds();
				if((hour==hh||hour==15)&&(minutes==mm)&&(seconds==ss)){
					alert("班別報表自動新增完成");
					$.ajax({
						type : "post",
						url : "../SHIFTREPORT/insertShiftre.do",
						data : {"shift":$("input[name='shift']").val(),
								"emp_id":$("input[name='emp_id']").val()
						}					
					});
				}else{
		
				}
			},1000);


	});
</script>				
</body>
</html>