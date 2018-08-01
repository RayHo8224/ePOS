<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon.model.*"%>
<%
	List<CouponVO> list =(List<CouponVO>) request.getAttribute("list");	//除了錯誤訊息是用VO或List送來外,其餘的查詢結果都是用List送來
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.css" />
<style>

		/* 	表格標題 */
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background: #66CDAA;
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
		background-color:FFE4C4;
	}
	
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
	
	.formlist {
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: mediumseagreen;
		text-align: center;
		font-size: 23px;
		border-radius: 2px;
	}
	
	.distance {
		margin: 30px;
	}
	
</style>
<title>折價券清單</title>
</head>
<body>

<div class="titlelist">查詢結果</div>
<div class="col-lg-12">

	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	
 <p class="distance">
	<div class="formlist">折價券清單</div>
		<table id="allcpon" border="2" bordercolor='#CCCCFF' class="table table-bordered table-striped  table-hover">
		<thead><tr>
			<th>折價券編號</th>
			<th>折價券名稱</th>
			<th>發行日期</th>
			<th>使用期限</th>
			<th>面額</th>
			<th>狀態(Y已出貨，N庫存)</th>
			<th>修改</th>
			<th>刪除</th>
		</tr></thead>
	
		<c:forEach var="copVO" items="${list}">
			<tr align='center' valign='middle'>
				<td>${copVO.cpon_id}</td>
				<td>${copVO.cpon_name}</td>
				<td>${copVO.release_date}</td>
				<td>${copVO.cpon_period}</td>
				<td>${copVO.cpon_dollar}</td>
				<td>${copVO.status}</td>
				<td>
					<button type="button" class="btn btn-success" name="update" target="${copVO.cpon_id}">
							<i class="fa fa-pencil"></i>
					</button>
				</td>
				<td>
					<button type="button" class="btn btn-danger" name="delete" target="${copVO.cpon_id}">
						<i class="fa fa-trash-o"></i>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->
	<script>
			
			$('button').on('click', function() {		
				if($(this).attr('name')=="delete"){
					$.ajax({
						type : "POST",
						url : "deleteCpon.do",
						data : {
							cpon_id:$(this).attr('target')
						},
						success : function(data) {
							$(".result_content").html(data);
						}
					})
				}if($(this).attr('name')=="update"){
					$.ajax({
						type : "POST",
						url : "allForUpdateCpon.do",
						data : {
							cpon_id:$(this).attr('target')
						},
						success : function(data) {
							$(".result_content").html(data);
						}
					})
				}	
			})

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
			
			$("#allcpon").dataTable({
				"bPaginate" : true, //翻頁功能
				"bLengthChange" : false, //改變每頁顯示數據數量
				"bFilter" : true, //過濾功能
				"bSort" : true, //排序功能
				"bInfo" : true,//頁腳信息
				"bAutoWidth" : true,//自動寬度
				"oLanguage" : oLanguage,
				"bDestroy" : false,
				"bProcessing" : false,
				"bServerSide" : false,
				"order" : [[ 0, "desc" ]]
				
			});		
// 			$("#allcpon").DefaultView.Sort = "折價券編號  DESC";
	</script>
</body>
</html>