<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/bootstrap-theme.min.css" rel="stylesheet">

<style>
	.title{
		font-size: 20px;
		background: #ff8d3a;
		font-family: 微軟正黑體;
		font-weight:bold;
		text-align: center;
		color:white;
	}

</style>
<title>查詢作廢發票</title>

</head>
<body>

	<div class="title">查詢</div>
	<div style="height: 20px"></div>
	
	<form action="getOneinvo.do" method="post" class="form-inline">
  		<div class="form-group">
    		<label class="sr-only">發票編號</label>
    		<p class="form-control-static"><font size="4" face="微軟正黑體" color="black">發票編號：</font></p>
  		</div>
  		<div class="form-group">
    		<label for="inputPassword2" class="sr-only"></label>
    		<input type="text" name='invoice_id' class="form-control" id="inputPassword2" >
  		</div>
  		<button type="submit" class="btn btn-default">查詢</button>
	</form>

</body>
</html>