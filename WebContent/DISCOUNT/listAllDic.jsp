<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.discount.model.*"%>
<%
	List<DiscountVO> list = (List<DiscountVO>) request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.css" />
<style>

		/* 	表格標題 */
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background: #CD853F;
		font-weight:bold;
		text-align: center;
	}
	
	/* 	表格內容偶數 */
	.table-bordered > thead > tr > th, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > tbody > tr > td, .table-bordered > tfoot > tr > td{
		background:	#FFE4B5;
		border:1px solid #DEB887;
		
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
		background: #F4A460;
		padding-left: 10px;
		font-size: 23px;
		border-radius: 2px;
	}
	
	.formlist {
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #F4A460;
		text-align: center;
		font-size: 23px;
		border-radius: 2px;
	}
	
	.distance{
		margin: 30px;	
	}

	.my-valid-class{
		color:#3a51e8;
	}
	
	.my-error-class{
		color:red;
	}

</style>

<title>折扣清單</title>
</head>
<body>
	<section class="wrapper">
		<div class="titlelist">查詢結果</div>
			<div class="col-lg-12">
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
				<p  class="distance">
				<div class="formlist">折扣清單</div>
					<table id="dic" border='1' bordercolor='#CCCCFF' class="table table-bordered table-striped  table-hover" style="width:100%">
						<thead>
							<tr>
								<td class="numeric">折扣身分</td>
								<td class="numeric">折扣%數</td>
								<td class="numeric">修改/確認</td>
								<td class="numeric">刪除</td>
							</tr>
						</thead>
						<c:forEach var="discVO" items="${list}">
							<tr align='center' valign='middle'>
								<td class="numeric">${discVO.dis_id}</td>
								<td class="numeric">
									<label>${discVO.dis_price}</label> 
									<input type="text" name="dis_price" size="5" maxlength="4"class="chg_price">
								</td>
								<td class="numeric">
									<button type="button" class="btn btn-success" onclick="editEvent(this)">
										<i class="fa fa-pencil"></i>
									</button>
									<button type="button" class="btn btn-primary" onclick="confirmEvent(this)">
										<i class="fa fa-check"></i>
									</button>
								</td>
								<td class="numeric">
									<button type="button" class="btn btn-danger"  target="${discVO.dis_id}">
										<i class="fa fa-trash-o"></i>
									</button>
								</td>
							</tr>
						</c:forEach>
					</table>
			</div></section>
<!-- --------------------------------------------------------------程式開始處---------------------------------------------------------- -->	
<script type="text/JavaScript">

	$(document).ready(function() {
//初始hide		
		$('.btn-primary').hide();
		$('.chg_price').hide();
		
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
		
		$("#dic").dataTable({
			"bPaginate" : true, //翻頁功能
			"bLengthChange" : false, //改變每頁顯示數據數量
			"bFilter" : false, //過濾功能
			"bSort" : true, //排序功能
			"bInfo" : true,//頁腳信息
			"bAutoWidth" : true,//自動寬度
			"oLanguage" : oLanguage,
			"bDestroy" : false,
			"bProcessing" : false,
			"bServerSide" : false}
		);
		
	})

//刪除		
		$('.btn-danger').click(function() {
			var id = $(this).attr("target");
			$.ajax({
				"type" : "post",
				"url" : "deleteDisc.do",
				"data" : {
					dis_id : id
				},
				"success" : function(data) {
							$(".result_content").html(data);
				},
			});
		})
//修改	
	function editEvent(event) {
// 		console.log($(event).html());  //現在位置
// 		console.log($(event).parent().parent().find("td:eq(1) > label").html());  //要更改的位置
		var $label = $(event).parent().parent().find("td:eq(1) > label");
		var value = $label.html();
		var input = $(event).parent().parent().find("td:eq(1) input");
		input.val(value);
		$label.hide();
		input.show();		
		$(event).hide();
		$(event).parent().find("button:eq(1)").show();
	}
	
//確認修改
	function confirmEvent(event) {
		var id = $(event).parent().parent().find("td:eq(0)").html(); //id
		var inp = $(event).parent().parent().find(":text");
		var value =inp.val();
		var $label = $(event).parent().parent().find("td:eq(1) > label");
		if(parseFloat(value)<=1){			
			$.ajax({
				"type" : "post",
				"url" : "updateDisc.do",
				"data" : {
					dis_id : id,
					dis_price : value
				},
				"success" : {}
	// 				function() {
					//ajax
	// 				$.ajax({
	// 					"type" : "post",
	// 					"url" : "alljson.do",
	// 					"data" : {},
	// 					"success" : function(data) {
	// 						var sel = $('select[name="dis_id"]:eq(1)');
	// 						sel.empty();
	// 						$.each($.parseJSON(data), function() {
	// 							var n = this.dis_id;
	// 							var vp = this.dis_price;
	// 							var opt = $("<option>");
	// 							opt.append(vp);
	// 							opt.val(n);
	// 							sel.append(opt);
	// 						})						
							
	// 					}
	// 				});
	// 			}
			});
			inp.hide();
			$label.html(value);
			$label.show();
			$(event).hide();
			$(event).parent().find("button:eq(0)").show();
		}else{
// 			inp.after("必須是數字且介於0.01~1之間");
			alert("必須是數字且介於0.01~1之間");
		}
				
		
	}
	

</script>

</body>


</html>