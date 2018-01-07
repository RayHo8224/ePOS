<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.invo.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

<style type="text/css">
	body{
		background: white;
	}
	 
	a{
		margin-left: 30px;
	}
	
	.titledetail {
		/*     	margin-top:auto; */
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #ff8d3a;
		font-size: 23px;
		border-radius: 2px;
		text-align: center;
	}
	
	/* 	表格標題 */
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background: #ff6c00;
		font-weight:bold;
		color: white;
	}
	
	/* 	表格內容偶數 */
	.table-bordered > thead > tr > th, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > tbody > tr > td, .table-bordered > tfoot > tr > td{
		background:#ffe1ca;
		border:1px solid #ffa15c;
		
	}
	/* 	表格內容單數 */
	.table-striped > tbody > tr:nth-child(odd) > td, .table-striped > tbody > tr:nth-child(odd) > th{
		background:white;
	}
	
	/* 	表格偶數滑鼠指向 */
	.table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th{
		background-color:#ffb784;
	}

</style>
<title>發票作廢資料</title>
</head>
<body>
<div class="titledetail">發票作廢查詢結果</div>
<table class="table table-bordered table-striped table-hover">
	<thead>
		<tr>
			<td align='center'>發票編號</td>
			<td align='center'>訂單編號</td>
			<td align='center'>新發票編號</td>
			<td align='center'>新訂單編號</td>
			<td align='center'>修改</td>
			<td align='center'>刪除</td>
		</tr>
	</thead>
		<tr align='center' valign='middle'>
			<td>${invoVO.invoice_id}</td>
			<td>${invoVO.ord_id}</td>
			<td>${invoVO.new_invoice_number}</td>
			<td>
			<label>${invoVO.new_ord_id}</label>
			<input type="text" name="new_invoice_number" class="edittext">
			</td>
			<td>
			     <button type="button" class="btn btn-success" onclick="editdata(this)" target="${invoVO.invoice_id}"><i class="fa fa-pencil"></i></button>
			     <button type="button" class="btn btn-primary" onclick="confirm(this)" ><i class="glyphicon glyphicon-ok"></i></button>
			</td>
			<td>
			     <button type="button" target="${invoVO.invoice_id}" class="btn btn-danger"><i class="fa fa-pencil"></i></button>
			</td>
		</tr>

</table>

	<a href="select_page.jsp"><i class="glyphicon glyphicon-tags"></i>　發票作廢</a></br>
	<a href="javascript:" onclick="history.back(); "><i class="glyphicon glyphicon-arrow-left"></i>　回上頁</a>
	
<!-------------------------------------	JS ------------------------------------------->
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
		$(".btn-primary").hide();
		$(".edittext").hide();
	})
	
	$(function(){
		
	//<!----------------------------------------刪除------------------------------------>
		$('.btn-danger').on('click',function(){
			var delbtn = $(this).parent().parent();
			var id = $(this).attr('target');
			console.log(id);
	    	var url = "deleteInvo.do";
	    $.ajax({
	           type: "POST",
	           url: url,
	           data: {invoice_id:id},
	           success: function(data)
	           {
	        	   delbtn.remove(); 
	           }
	         });
		})
	})
	
	//<!----------------------------------------修改------------------------------------>
	
	function editdata(event) {
		var label = $(event).parent().parent().find("td:eq(3) > label");
		var oldval = label.html();
		console.log("oldval= "+oldval)
		$(".edittext").val(oldval);
		label.hide();
		$(".edittext").show();
		$(".btn-success").hide();
		$(".btn-primary").show();
	}
	
	function confirm(event){
		var label = $(event).parent().parent().find("td:eq(3) > label");
		var id = $(event).parent().parent().find("td:eq(0)").html();
		var ord_id = $(event).parent().parent().find("td:eq(1)").html();
		var new_invoice_number = $(event).parent().parent().find("td:eq(2)").html();
		console.log("id="+id);
		var edittext = $(".edittext").val();
		console.log("edittext="+edittext);
		var url = "updateInvo.do";
		
		$.ajax({
			type:"POST",
			url:url,
			data:{
				invoice_id:id,
				ord_id:ord_id,
				new_invoice_number:new_invoice_number,
				new_ord_id:edittext,
				},
			sucess:{}
		})
		
		$(".edittext").hide();
		label.html(edittext);
		console.log("label="+label.html());
		label.show();
		$(".btn-primary").hide();
		$(".btn-success").show();
		
	}
	
	
	
	</script>

</body>
</html>
