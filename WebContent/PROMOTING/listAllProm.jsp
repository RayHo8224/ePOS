<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ page import="java.util.*"%>
<%@ page import="com.promoting.model.*"%>
<%
	List<PromotingVO> list = (List<PromotingVO>)request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>

		/* 	表格標題 */
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background: #278829;
		font-weight:bold;
		text-align: center;
	}
	
	/* 	表格內容偶數 */
	.table-bordered > thead > tr > th, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > tbody > tr > td, .table-bordered > tfoot > tr > td{
		background:		#F0FFF0;
		border:1px solid #556B2F;
		
	}
	/* 	表格內容單數 */
	.table-striped > tbody > tr:nth-child(odd) > td, .table-striped > tbody > tr:nth-child(odd) > th{
		background:white;
	}
	
	/* 	表格偶數滑鼠指向 */
	.table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th{
		background-color:#BFD1E5;
	}
	
	.titlelist {
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #F7C548;
		padding-left: 10px;
		font-size: 23px;
		border-radius: 2px;
	}
	
	.distance{
		margin: 30px;
	
	}
	
		.btn-success {
    color: #fff;
    background-color: #e8c68a;
    border-color: #f3f3f3;
} 
	
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>促銷商品清單</title>
</head>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>


	<div class="titlelist">查詢結果</div>
	<div class="col-lg-12">
		<p class="distance">
		<div class="titlelist">折扣清單</div>
		<table id="prom" border='1' bordercolor='#CCCCFF'
			class="table table-bordered table-striped  table-hover">
			<thead>
				<tr>
					<th>促銷品號</th>
					<th>促銷品名</th>
					<th>活動起始日</th>
					<th>活動截止日</th>
					<th>備註</th>
					<th>修改</th>
					<th>查詢</th>
				</tr>
			</thead>

			<c:forEach var="PromVO" items="${list}">
				<tr align='center' valign='middle'>
					<td>${PromVO.pro_prod_id}</td>
					<td>${PromVO.pro_prod_name}</td>
					<td>${PromVO.pro_begin}</td>
					<td>${PromVO.pro_end}</td>
					<td>${PromVO.pro_neirong}</td>

					<td>
						<button type="button" class="btn btn-success" target1="${PromVO.pro_prod_id}" target2="${PromVO.pro_begin}" value="修改">
							<i class="fa fa-pencil"></i>
						</button>
					</td>
					<td>
						<button type="button" class="btn btn-danger" target1="${PromVO.pro_prod_id}" target2="${PromVO.pro_begin}" value="刪除">
							<i class="fa fa-trash-o"></i>
						</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>


	<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->
	<script type="text/JavaScript">
	$(document).ready(function() {
		
		$("button").on('click',function(){
			if($(this).val()=="刪除"){
				$.ajax({
					type : "post",
					url : "deleteProm.do",
					data : {
						pro_prod_id:$(this).attr('target1'),
						pro_begin:$(this).attr('target2')
					},
					success :function(data) {
						$(".result_content").html(data);
					}	
				});
			}else if($(this).val()=="修改"){
				$.ajax({
					type : "post",
					url : "allForUpdateProm.do",
					data : {
						pro_prod_id:$(this).attr('target1'),
						pro_begin:$(this).attr('target2')
					},
					success :function(data) {
						$(".result_content").html(data);
					}
				});
			}
		});

		//datatable設定
		var oLanguage	= {
		//"sUrl": "cn.txt",//從配置文件中讀取語言包
		"sLengthMenu": "每頁顯示 _MENU_ 筆記錄",
		"sZeroRecords": "抱歉， 沒有找到",
		"sInfo": "目前記錄：_START_ 至 _END_, 總筆數：_TOTAL_",
		"sInfoEmpty": "沒有數據資料",
		//"sInfoFiltered": "(從 _MAX_ 項結果過濾)",
		"oPaginate": {
			"sFirst": "首頁",
			"sPrevious": "上頁",
			"sNext": "下頁",
			"sLast": "尾頁"
			},
		"sZeroRecords": "無符合資料"
		};
		
		$("#prom").dataTable({
			"bPaginate" : true, //翻頁功能
			"bLengthChange" : false, //改變每頁顯示數據數量
			"bFilter" : true, //過濾功能
			"bSort" : true, //排序功能
			"bInfo" : true,//頁腳信息
			"bAutoWidth" : true,//自動寬度
			"oLanguage" : oLanguage,
			"bDestroy" : false,
			"bProcessing" : false,
			"bServerSide" : false}
		);
	});

	</script>	
</body>
</html>