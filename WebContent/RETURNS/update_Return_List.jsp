<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.returns.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	    
<% 
	RtnListVO rtnListVO = (RtnListVO) request.getAttribute("RtnListVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="../resources/css/bootstrap.css" />"
	rel="stylesheet">
<!--external css-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/bootstrap-theme.min.css" rel="stylesheet">
<title>退貨單資料修改</title>


<style type="text/css">
	
	legend{
		background: #d47f9b;
	}
	
	a{
		margin-left: 30px;
	}
	
	span{
		color: red;
		font-family: 微軟正黑體;
	}


</style>
</head>
<body>

<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="update.do" name="form1">
<fieldset>
	<legend>退貨單資料修改</legend>
<from class="form-horizontal">
	<div class="form-group">
    	<label class="col-sm-4 control-label">退貨單編號:</label>
    	<div class="col-sm-5">
      		<input type="text" class="form-control" name="ret_id" value="<%=rtnListVO.getRet_id()%>" disabled="disabled"/>
    	</div>
  	</div>
  	<div class="form-group">
    	<label class="col-sm-4 control-label">退貨日期:</label>
    	<div class="col-sm-5">
      		<input id="date" type="date" class="form-control" name="ret_date" value="<%=rtnListVO.getRet_date()%>"/>
      		<span id="Msgdate"></span>
    	</div>
  	</div>
  	<div class="form-group">
    	<label class="col-sm-4 control-label">廠商編號:</label>
    	<div class="col-sm-5">
      		<input type="text" class="form-control" name="com_id" value="<%=rtnListVO.getCom_id()%>" disabled="disabled"/>
    	</div>
  	</div>
  	<div class="form-group">
    	<label class="col-sm-4 control-label">廠商名稱:</label>
    	<div class="col-sm-5">
      	<input type="text" class="form-control" name="com_name" value="<%=rtnListVO.getCom_name()%>" disabled="disabled"/>
    	</div>
  	</div>
  	<div class="form-group">
    	<label class="col-sm-4 control-label">修改人員:</label>
    	<div class="col-sm-5">
      		<input id="emp" type="text" class="form-control" name="key_id" value="<%=rtnListVO.getKey_id()%>"/>
      		<span id="Msgemp"></span>
   		 </div>
  	</div>
  	<div class="form-group">
    	<label class="col-sm-4 control-label">修改日期:</label>
    	<div class="col-sm-5">
      		<input id="date1" type="date" class="form-control" name="key_date" value="<%=rtnListVO.getKey_date()%>"/>
      		<span id="Msgdate1"></span>
    	</div>
  	</div>
  	<div class="form-group">
    	<label class="col-sm-4 control-label">狀　　態:</label>
    	<div class="col-sm-5">
      		<input id=status type="text" class="form-control" name="status" value="<%=rtnListVO.getStatus()%>"/>
      		<span id="Msgstatus"></span>
    	</div>
  	</div>
  	<div class="form-group">
   		<label class="col-sm-4 control-label">備　　註:</label>
    	<div class="col-sm-5">
      		<input type="text" class="form-control" name="remark" value="<%=rtnListVO.getRemark()%>"/>
    	</div>
  	</div>
  	<div class="form-group">
    <div class="col-sm-offset-6">
    　　　　<input type="hidden" name="action" value="update">
      <input type="hidden" name="ret_id" value="<%=rtnListVO.getRet_id()%>">
      <input type="hidden" name="com_id" value="<%=rtnListVO.getCom_id()%>">
      <input type="hidden" name="com_name" value="<%=rtnListVO.getCom_name()%>">
      <button type="submit" class="btn btn-default">送　出</button>
    </div>
  </div>
</from>
</fieldset>
  </FORM>

	<a href="../RETURNS/ReturnList.jsp"><i class="glyphicon glyphicon-th-list"></i>　退貨單</a></br>
	<a href="javascript:" onclick="history.back(); "><i class="glyphicon glyphicon-arrow-left"></i>　回上頁</a>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script>
		!window.jQuery
				&& document
						.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
	</script>	
	
	
	<script type="text/javascript">
	
	$(document).ready(function(){

            var rule = /^((19|20)?[0-9]{2}[- /.](0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01]))*$/;
            $('#date').blur(function(){
            	if(rule.test($(this).val())){
                    $("#Msgdate").text('')
                    $(this).css("border-color","green")
                }else{
                    $("#Msgdate").html('格式錯誤,YYYY/MM/DD')
                    $(this).css("border-color","red")
                }
            })
            
            $('#date1').blur(function(){
            	if(rule.test($(this).val())){
                    $("#Msgdate1").text('')
                    $(this).css("border-color","green")
                }else{
                    $("#Msgdate1").html('格式錯誤,YYYY/MM/DD')
                    $(this).css("border-color","red")
                }
            })
            
            var ruleEmp = /^[E][0-9]{5}$/;
            $('#emp').blur(function(){
            	if(ruleEmp.test($(this).val())){
                    $("#Msgemp").text('')
                    $(this).css("border-color","green")
                }else{
                    $("#Msgemp").html('格式錯誤,如:E00001')
                    $(this).css("border-color","red")
                }
            })
            
            var ruleStatus = /^[N,Y]{1}$/;
            $('#status').blur(function(){
            	if(ruleStatus.test($(this).val())){
                    $("#Msgstatus").text('')
                    $(this).css("border-color","green")
                }else{
                    $("#Msgstatus").html('格式錯誤,如:N,Y')
                    $(this).css("border-color","red")
                }
            })
        })
	</script>

</body>
</html>