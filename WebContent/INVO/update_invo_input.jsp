<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.invo.model.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/bootstrap-theme.min.css" rel="stylesheet">
<style type="text/css">
	
	body { 
 		background: #ffe8d8; 
 	}
 	
 	a{
		margin-left: 30px;
	}
	
	legend{
		background: #ff8d3a;
	} 


</style>
<title>發票作廢資料修改</title>

</head>
<body>
<FORM METHOD="post" ACTION="updateInvo.do" name="form1">
		<fieldset style="height: 50%">
        <legend>修改發票作廢資料</legend>
<from class="form-horizontal">
	<div class="form-group">
    	<label class="col-sm-4 control-label">發票編號:</label>
    	<div class="col-sm-5">
      		<input type="text" class="form-control" name="invoice_id" value="${invoVO.invoice_id}" disabled="disabled"/>
    	</div>
  	</div>
  	<div class="form-group">
    	<label class="col-sm-4 control-label">訂單編號:</label>
    	<div class="col-sm-5">
      		<input type="text" class="form-control" name="ord_id" value="${invoVO.ord_id}"/>
    	</div>
  	</div>
  	<div class="form-group">
    	<label class="col-sm-4 control-label">新發票編號:</label>
    	<div class="col-sm-5">
      		<input type="text" class="form-control" name="new_invoice_number" value="${invoVO.new_invoice_number}"/>
    	</div>
  	</div>
  	<div class="form-group">
    	<label class="col-sm-4 control-label">新訂單編號:</label>
    	<div class="col-sm-5">
      		<input type="text" class="form-control" name="new_ord_id" value="${invoVO.new_ord_id}"/>
    	</div>
  	</div>
<!--   	送出   -->
	<div class="form-group">
    	<div class="col-sm-offset-6">
    　　　　		<input type="hidden" name="action" value="update">
    		<input type="hidden" name="invoice_id" value="${invoVO.invoice_id}">
      		<button type="submit" class="btn btn-default">送　出</button>
    	</div>
  	</div>
		</from>
		</fieldset>
	</FORM>
	
	<a href="select_page.jsp"><i class="glyphicon glyphicon-tags"></i>　發票作廢</a></br>
	<a href="javascript:" onclick="history.back(); "><i class="glyphicon glyphicon-arrow-left"></i>　回上頁</a>

</body>
</html>