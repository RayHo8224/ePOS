<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.valuation.model.ValuationVO"%>
<%@ page import="com.valuation_detail.model.Valuation_DetailVO"%>
<%@ page import="com.product.model.ProdVO"%>

<%
	session.getAttribute("LoginOK");
	List detailList = (List)request.getAttribute("detailList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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

	/* 訂單div*/
	#orddiv{
		background: #4dbcff;
		height: 30px;
		text-align: center;
		color: white;
		font-size: 20px;
		font-weight: bold;
	}
	
	#ordmain{	
		height: 150px;
		background: #e1f4ff;
		border-bottom: double #4dbcff 4px;
		border-left: double #4dbcff 4px;
		border-right: double #4dbcff 4px;
	
	}
	
	.form-group{
		margin-left: 20px;
		margin-top: 20px;
	}
	
	#group{
		margin-left: 50px;
	}
	
	/* 訂單明細div*/
	#detaildiv{
		background: #ffc36e;
		height: 30px;
		text-align: center;
		color: white;
		font-size: 20px;
 		margin-top: 10px;
 		font-weight: bold; 
	}
	
	td{
		text-align: center;
	}
	
	/* 表格雙數欄位*/
	.table-bordered > thead > tr > th, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > tbody > tr > td, .table-bordered > tfoot > tr > td{
		background: #ffe8c8;
		border:3px double #ffc572;
	}
	
	/* 表格標題*/
	.table > caption + thead > tr:first-child > th, .table > colgroup + thead > tr:first-child > th, .table > thead:first-child > tr:first-child > th, .table > caption + thead > tr:first-child > td, .table > colgroup + thead > tr:first-child > td, .table > thead:first-child > tr:first-child > td{
		background: #f0ad4e;
	}
	
	/* 滑鼠移過*/
	.table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th{
		background: #ffd497;	
	}
	
	button{
		margin-left: 20px;
	}
	

	
	
</style>
<title>報價單轉訂單</title>

</head>
<body>

	<div id="orddiv">訂單</div>
	<form method="post" action="../ORDER/addOrder.do" class="form-inline">
	<div id="ordmain">
		<table id="ord">
			<c:forEach var="list" items="${list}" varStatus="count">
				<tr>
					<div class="form-group">
						<label for="exampleInputName2">報價單號：</label>
						<input type="text" class="form-control" name="vlt_id" value="${list.vlt_id}" readonly/>	
					</div>
					<div id="group" class="form-group">
						<label for="exampleInputName2">實際金額：</label>
						<input type="text" class="form-control" value="${list.total_price}" readonly/>	
						<input type="hidden" name="total_price_temp" value="${list.total_price}" />
						<input type="hidden" name="cash" value="${list.total_price}" />
					</div>
					<div id="group" class="form-group">
						<label for="exampleInputName2">修改人員：</label>
						<input type="text" name="key_id" class="form-control" value="${LoginOK.emp_id}"/>
<!-- 						<input type="hidden" name="key_id" class="form-control" value=""/>	 -->
					</div>
					<div id="group" class="form-group">
						<label for="exampleInputName2">建檔日期：</label>
						<input type="text" name="key_date" class="form-control" value="${list.key_date}"/>	
					</div>
					<div id="group" class="form-group">
						<label for="exampleInputName2">狀態：</label>
						<input type="text" class="form-control" value="${list.status}" readonly/>	
					</div>
					<div id="group" class="form-group">
						<label for="exampleInputName2">備註：</label>
						<input type="text" name="remark" class="form-control" value="${list.remark}" readonly/>	
					</div>
					<div id="group" class="form-group">
						<label for="exampleInputName2">班別：</label>
						<input type="text" name="shift" class="form-control" value="${SHIFT}"/>	
					</div>
					<div id="group" class="form-group">
						<input type="hidden" name="ord_um" value="" />
						<input type="hidden" name="cpon_id" value="" />
						<input type="hidden" name="cpon_dollar" value="" />		
						<input type="hidden" name="emp_id" value="" />		
					</div>
				</tr>
			</c:forEach>
		</table>
	</div>
		<div id="detaildiv">訂單明細</div>
		<table id="del" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<td>商品編號</td>
					<td>商品名稱</td>
					<td>商品數量</td>
					<td>商品價格</td>
				</tr>
			</thead>
		
			<%for(int i=0;i<detailList.size();i++){ 
				Valuation_DetailVO valuation_DetailVO = (Valuation_DetailVO)detailList.get(i);
				ProdVO prodVO = (ProdVO)valuation_DetailVO.getProdVO();
				
			%>
<%-- 			<c:forEach var="list" items="${detailList}" varStatus="count"> --%>			
				<tr>
					<td><input type="text" name="prod_id<%=i+1%>" value="<%=prodVO.getProd_id()%>"
						readonly /></td>
					<td><input type="text" name="prod_name<%=i+1%>" value="<%=valuation_DetailVO.getProd_name()%>" readonly /></td>
					<td><input type="text" name="prod_quantity<%=i+1%>" value="<%=valuation_DetailVO.getProd_quantity()%>"
						readonly /></td>
					<td><input type="text" name="prod_price<%=i+1%>" value="<%=valuation_DetailVO.getProd_price()%>"
						readonly /></td>

				</tr>
<%-- 			</c:forEach> --%>
			<%} %>
		</table>
					


		<center>
			<table>
				<tr>
					<td><button type="button" name="action" class="btn btn-success" value="返回"
						onclick="history.back();">← 返回</button></td>
					<td><button type="submit" value="結帳" class="btn btn-danger">$ 結帳</button></td>
				</tr>
			</table>
		</center>

	</form>
</body>
</html>