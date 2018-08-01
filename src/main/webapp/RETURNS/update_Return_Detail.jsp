<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.returns.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	    
<%-- 
	RtnDetailVO rtnDetailVO = (RtnDetailVO) request.getAttribute("list");
	
--%>

<%-- <jsp:useBean id="rtnItemsSvc" scope="page" class="com.returns.model.ReturnItemsService" /> --%>
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
<title>�h�f����ӭק�</title>

<style type="text/css">
	
	body{
		background: #ebe1f7;
	}
	
	legend{
		background: #818ebf;
	}
	
	a{
		margin-left: 30px;
	}
	
	span{
		color: red;
		font-family: �L�n������;
	} 
</style>


</head>
<body>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="update_dtl.do" name="form1">
<fieldset>
	<legend>�h�f����Ӹ�ƭק�</legend>
<from class="form-horizontal">
	<div class="form-group">
    	<label class="col-sm-4 control-label">�h�f��s��:</label>
    	<div class="col-sm-5">
      		<input type="text" class="form-control" name="ret_id" value="${list.rtnListVO.ret_id}" disabled="disabled"/>
    	</div>
  	</div>
	<div class="form-group">
    	<label class="col-sm-4 control-label">���~�W��:</label>
    	<div class="col-sm-5">
      		<input type="text" class="form-control" name="prod_name" value="${list.rtnItemsVO.prod_name}"/>
    	</div>
  	</div>
	<div class="form-group">
    	<label class="col-sm-4 control-label">�h�f�ƶq:</label>
    	<div class="col-sm-5">
      		<input id="quantity" type="text" class="form-control" name="prod_quantity" value="${list.prod_quantity}"/>
      		<span class="MsgError"></span>
    	</div>
  	</div>
	<div class="form-group">
    	<label class="col-sm-4 control-label">�h�f��]:</label>
    	<div class="col-sm-5">
      		<input type="text" class="form-control" name="ret_reason" value="${list.ret_reason}"/>
    	</div>
  	</div>
  	<div class="form-group">
    <div class="col-sm-offset-6">
    �@�@�@�@<input type="hidden" name="action" value="update">
       <input type="hidden" name="ret_id" value="${list.rtnListVO.ret_id}">
      <button type="submit" class="btn btn-default">�e�@�X</button>
    </div>
  </div>   	
</from>
</fieldset>
</FORM>

	<a id="back" href="../RETURNS/Return_Detail.jsp"><i class="glyphicon glyphicon-th-list"></i>�@�h�f�����</a></br>
	<a id="back" href="javascript:" onclick="history.back(); "><i class="glyphicon glyphicon-arrow-left"></i>�@�^�W��</a>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script>
		!window.jQuery
				&& document
						.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
	</script>
	<script src="<c:url value="../resources/js/bootstrap.min.js" />"></script>
		<script type="text/javascript">
	
	$(document).ready(function(){

            var rule = /^\d{1,3}$/;
            $('#quantity').blur(function(){
            	if(rule.test($(this).val())){
                    $("span").text('')
                    $(this).css("border-color","green")
                }else{
                    $("span").html('�榡���~,�Э��s��J')
                    $(this).css("border-color","red")
                }
            })
        })
	</script>
</body>
</html>