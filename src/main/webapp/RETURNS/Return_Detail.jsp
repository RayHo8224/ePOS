<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.returns.model.*"%>
<%
	ReturnDetailService rtnDtlSvc = new ReturnDetailService();
    List<RtnDetailVO> list = rtnDtlSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap core CSS -->
<!-- <link href="../resources/css/bootstrap.min.css" rel="stylesheet"> -->
<!-- <link href="../resources/css/bootstrap-theme.min.css" rel="stylesheet"> -->
<link href="<c:url value="../resources/css/bootstrap.css" />"
	rel="stylesheet">
<!--external css-->
<link
	href="<c:url value="../resources/font-awesome/css/font-awesome.css" />"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<c:url value="../resources/lineicons/style.css" />">
<!-- Custom styles for this template -->
<link href="<c:url value="../resources/css/style.css" />"
	rel="stylesheet">
<link href="<c:url value="../resources/css/style-responsive.css" />"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.css" />
<title>退貨單明細</title>

<style type="text/css">

	a{
		margin-left: 30px;
	}
	
	.main{
		background: #e4e4ff;
	}
	
	.alert-info{
		color:#a2afde;
		background-color: #bdcbfd;
		border-color: #818ebf;
		
	}
	
	.titledetail {
		/*     	margin-top:auto; */
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #a2afde;
		font-size: 23px;
		border-radius: 2px;
	}

	
	/* 	表格標題 */
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background: #818ebf;
		font-weight:bold;
	}
	
	/* 	表格內容偶數 */
	.table-bordered > thead > tr > th, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > tbody > tr > td, .table-bordered > tfoot > tr > td{
		background:#dfd5ff;
		border:1px solid #a4b0dc;
		
	}
	/* 	表格內容單數 */
	.table-striped > tbody > tr:nth-child(odd) > td, .table-striped > tbody > tr:nth-child(odd) > th{
		background:white;
	}
	
	/* 	表格偶數滑鼠指向 */
	.table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th{
		background-color:#dfd5ff;
	}
	
	/* 表格頁數*/
	.pagination > .active > a, .pagination > .active > span, .pagination > .active > a:hover, .pagination > .active > span:hover, .pagination > .active > a:focus, .pagination > .active > span:focus{
		background: #818ebf;
		border-color: #bdcbfd;
	}
	
</style>


</head>

<body>	
<!-- 	<div class="row mt"> -->
	<div class="col-sm-12">
		<div id="add" class="main">
			<div class="tab-content">
				<nav class="alert alert-info">
				<div>
				<a id="add" href="#"><span class="glyphicon glyphicon-file"></span>新增</a>
		    	<a href="#" onclick="window.open('searchdetail.jsp', 'Yahoo', config='height=500,width=850')"><span class="glyphicon glyphicon-search"></span>查詢</a>
		    	<a href="#" onclick="window.open('AllReturnDetail.jsp', '退貨單明細', config='height=850,width=1600')"><span class="glyphicon glyphicon-inbox"></span>全部查詢</a>
				<a href="ReturnList.jsp"><i class="glyphicon glyphicon-th-list"></i>退貨單</a>
		    	<a id="print" href="javaScript:varitext()"><span class="glyphicon glyphicon-print" ></span>列印</a>
		    	<a id="sub" href="#"><span class="glyphicon glyphicon-ok-sign">送出</span></a>
				</div>

				</nav>
			</div>


			<FORM id="detailform" METHOD="post" ACTION="insert_dtl.do" class="form-inline">
				<div class="form-group">
					<label for="exampleInputName2">　退貨單編號：</label> 
					<input type="text" name="ret_id" class="form-control">
				</div> 　　
				<div class="form-group">
					<label for="exampleInputName2">商品名稱：</label> 
					<input type="text" name="prod_name" class="form-control">
				</div>　　
				<div class="form-group">
					<label for="exampleInputEmail2">退貨數量：</label> 
					<input type="text" name="prod_quantity" class="form-control">
				</div>　　
				<div class="form-group">
					<label for="exampleInputName2">退貨原因：</label> 
					<input type="text" name="ret_reason" class="form-control">
				</div>
			</FORM>

		</div>


<!-- -----------------------------------------------------------表格----------------------------------------------------------- -->
<div style="height: 20px;"></div>
<div class="titledetail">退貨單明細</div>
<table id="detail" class="table table-bordered table-striped table-hover">
<thead>
<tr>
	<td align='center'>退貨單編號</td><td align='center'>商品名稱</td><td align='center'>退貨原因</td><td align='center'>退貨數量</td><td align='center'>修改</td><td align='center'>刪除</td>
</tr>
</thead>

<c:forEach var="list" items="${list}">
	<tr align='center' valign='middle'>
			<td>${list.rtnListVO.ret_id}</td> 
			<td>${list.rtnItemsVO.prod_name}</td>
			<td>${list.ret_reason}</td>
			<td>${list.prod_quantity}</td>
		<td>
			  <FORM METHOD="post" ACTION="getOne_For_Update_dtl.do">
			     <button type="submit" class="btn btn-success"><i class="fa fa-pencil"></i></button>
			     <input type="hidden" name="ret_id" value="${list.rtnListVO.ret_id}">
			     <input type="hidden" name="prod_name" value="${list.rtnItemsVO.prod_name}">
			  </FORM>
		</td>
		<td>
		
			  <FORM METHOD="post" ACTION="delete_dtl.do">
			    <button type="submit" class="btn btn-danger"><i class="fa fa-trash-o "></i></button>
			    <input type="hidden" name="ret_id" value="${list.rtnListVO.ret_id}">
			    <input type="hidden" name="prod_name" value="${list.rtnItemsVO.prod_name}">
			    <input type="hidden" name="action"value="delete">
			  </FORM>
		</td>
		</tr>	
</c:forEach>
</table>
</div>

<!-- 	<a href="../RETURNS/ReturnList.jsp"><i class="glyphicon glyphicon-th-list"></i>　退貨單</a></br> -->
<!-- 	<a href="../RETURNS/Return_Detail.jsp"><i class="glyphicon glyphicon-list-alt"></i>　退貨單明細</a></br> -->
<!-- 	<a href="javascript:" onclick="history.back(); "><i class="glyphicon glyphicon-arrow-left"></i>　回上頁</a> -->
	
<!-----------------------------------------程式     JS---------------------------------------------------- -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script>
		!window.jQuery
				&& document
						.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
	</script>

	<script src="<c:url value="../resources/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.js"></script>
	
<!------------------------------------------程式------------------------------------------------------------- -->

<script type="text/javascript">
	$('#detail').DataTable();
	
	$('#sub').on('click',function(){
	    var url = "insert_dtl.do"; 
	    $.ajax({
	           type: "POST",
	           url: url,
	           data: $("#detailform").serialize(), 
	           success: function(data)
	           {
	        	   location.reload();
	           }
	         });
		})
	
	
	
</script>

	

</body>
</html>