<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap core CSS -->
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
<title>權限控管</title>
<style>
.navbar-default {
	background: #D2E9FF;
}

.titlelist {
	font-family: '微軟正黑體';
	font-weight: bold;
	color: white;
	height: 35px;
	background: #66B3FF;
	padding-left: 10px;
	font-size: 23px;
	border-radius: 2px;
}

.glyphicon {
	top: auto;
}
table {
    font-size: large;
}
/* .pn { */
/* 	height: auto; */
/* 	box-shadow: inherit; */
/* } */
</style>
</head>
<body>
	<section id="container"> <!--header start--> <header
		class="header black-bg">
	<div class="sidebar-toggle-box">
		<div class="fa fa-bars tooltips" data-placement="right"
			data-original-title="Toggle Navigation"></div>
	</div>
	<!--logo start--> <a href="<%=request.getContextPath()%>/index.jsp"
		class="logo"><b>ePOS</b></a> <!--logo end-->

	<div class="top-menu">
		<ul class="nav pull-right top-menu">
			<li><a class="logout"
				href="<%=request.getContextPath()%>/LOGIN/logout.jsp">Logout</a>Hi ,
				${LoginOK.emp_name}</li>
		</ul>
	</div>
	</header> <!--header end--> <!--sidebar start--> <aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu" id="nav-accordion">

			<p class="centered">
				<a href="<%=request.getContextPath()%>/index.jsp"> <img
					src="<c:url value="../resources/img/ui-sam.jpg"/>"
					class="img-circle" width="60"></a>
			</p>
			<h5 class="centered">ePOS</h5>

			<li class="mt"><a href="<%=request.getContextPath()%>/ORDER/order.jsp">
					<i class="fa fa-dashboard"></i> <span>收銀結帳</span>
			</a></li>

			<li class="sub-menu"><a href="javascript:;" class="active">
					<i class="fa fa-desktop"></i> <span>基本資料維護</span>
			</a>
				<ul class="sub">
					<li><a href="<%=request.getContextPath()%>/MEMBER/member.jsp">會員資料維護</a></li>
					<li class="active"><a
						href="<%=request.getContextPath()%>/EMPLOYEE/employee.jsp">員工資料維護</a></li>
					<li><a
						href="<%=request.getContextPath()%>/COMPANY/company.jsp">廠商資料維護</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-edit"></i> <span>進貨作業</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/REQUISITION/requisition.jsp">請購單維護</a></li>
					<li><a
						href="<%=request.getContextPath()%>/QUOTATION/quotation.jsp">詢價單維護</a></li>
					<li><a href="<%=request.getContextPath()%>/PURCHASE/pur.jsp">採購單維護</a></li>
					<li><a
						href="<%=request.getContextPath()%>/BILL_OF_PURCHASE/bop.jsp">進貨單維護</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="glyphicon glyphicon-shopping-cart"></i> <span>銷貨系統</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/VALUATION/ValuationList.jsp">報價單維護</a></li>
					<li><a href="<%=request.getContextPath()%>/ORDER/ordmain.jsp">訂單維護</a></li>
					<li><a
						href="<%=request.getContextPath()%>/SHIPMENTS/ShipmentsList.jsp">出貨單維護</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-book"></i> <span>存貨作業</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/PRODUCT/product.jsp">商品管理</a></li>
					<li><a
						href="<%=request.getContextPath()%>/PROMOTING/promoting.jsp">促銷商品管理</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="glyphicon glyphicon-log-out"></i> <span>退貨作業</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/RETURNS/Return_Items.jsp">退貨品管理</a></li>
					<li><a
						href="<%=request.getContextPath()%>/RETURNS/ReturnList.jsp">退貨單管理</a></li>
					<li><a
						href="<%=request.getContextPath()%>/INVO/select_page.jsp">作廢發票管理</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-usd"></i> <span>金流管理</span>
			</a>
				<ul class="sub">
					<li><a
						href="<%=request.getContextPath()%>/SHIFTREPORT/shiftreport.jsp">班別報表維護</a></li>
					<li><a href="<%=request.getContextPath()%>/COUPON/coupon.jsp">折價券</a></li>
					<li><a
						href="<%=request.getContextPath()%>/DISCOUNT/discount.jsp">折扣管理</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i class="fa fa-bar-chart-o"></i> <span>報表分析</span>
					</a>
					<ul class="sub">
						<li><a href="<%=request.getContextPath()%>/ANALYSIS/analysis.jsp">銷售報表分析</a></li>
					</ul></li>
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-users"></i> <span>顧客關係</span>
			</a>
				<ul class="sub">
					<li><a href="<%=request.getContextPath()%>/MAIL/Mail.jsp">寄送系統</a></li>
				</ul></li>
		</ul>
		<!-- sidebar menu end-->
	</div>
	</aside> <!--sidebar end--> <section id="main-content"> <section
		class="wrapper">
	<div class="row mt">
		<div class="col-lg-12">
			<nav class="nav navbar-default">
			<div class="tab-content">
				<ul class="nav navbar-nav ">
					<li><a href="searchEmp.jsp" class="glyphicon glyphicon-search">搜尋</a></li>
					<li><a href="addEmp.jsp" class="glyphicon glyphicon-file">新增</a></li>
					<li><a style="background-color: rgba(172, 214, 255, 0.6);"
						class="glyphicon glyphicon-lock">修改權限</a></li>
					<li><a href="#" class="glyphicon glyphicon-list-alt">查詢結果</a></li>
				</ul>
			</div>
			</nav>
			<div class="tab-content">
				<div>
					<div class="titlelist">員工權限管理</div>
					<div class="col-lg-12  main" id="psDiv">
						<p>
							<jsp:useBean id="empSvc" scope="page"
								class="com.employee.model.EmpService" />
						<center>
							<form method="post" action="setPassCode.do">
								<b>選擇員工姓名:</b> <select size="1" name="emp_id" id="button Toggle">
									<c:forEach var="empVO" items="${empSvc.all}">
										<option value="${empVO.emp_id}">${empVO.emp_name}${empVO.emp_id}
									</c:forEach>
								</select>
								<div class="panel-heading">
										<div class="pull-left"><h3><i class="fa fa-eye"></i>選擇權限</h3></div><br>
									</div>
									<div class="custom-check goleft mt">
										<table id="todo" class="table table-hover custom-check">
											<tbody>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="ALL" checked>通行無阻</span></td>
													<td><span class="check"><input type="checkbox" id="empPass" />一般員工</span></td>
													<td></td>
												</tr>
												<tr><td></td><td></td><td></td><td></td></tr>
											</tbody>
										</table>
									</div>
									<div class="panel-heading">
										<div class="pull-left"><h3><i class="fa fa-desktop"></i> 基本資料維護</h3></div><br>
									</div>
									<div class="custom-check goleft mt">
										<table id="todo" class="table table-hover custom-check">
											<tbody>
												<tr>
													<th>會員資料維護</th>
													<th>員工資料維護</th>
													<th>廠商資料維護</th>
													<th>         </th>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox"
															name="pass_code" value="/addMem.jsp">新增會員</span></td>
													<td><input type="checkbox" name="pass_code"
														value="/searchEmp.jsp">查詢員工<br></td>
													<td><input type="checkbox" name="pass_code"
														value="/searchCom.jsp">搜尋廠商<br></td>
														<td></td>

												</tr>
												<tr>
													<td>
														<span class="check"><input type="checkbox" name="pass_code" value="/searchMem.jsp">搜尋會員</span>
													</td>
													<td>
														<span class="check"><input type="checkbox" name="pass_code" value="/addEmp.jsp">新增員工</span>
													</td>
													<td>
														<span class="check"><input type="checkbox" name="pass_code" value="/addCom.jsp">新增廠商</span>
													</td>
													<td></td>
												</tr>
												<tr>
													<td>
														<span class="check"><input type="checkbox" name="pass_code" value="/allForUpdateMem.do">修改會員</span>
													</td>
													<td>
														<span class="check"><input type="checkbox" name="pass_code" value="/SetPassCode.jsp">權限設定</span>
													</td>
													<td>
														<span class="check"><input type="checkbox" name="pass_code" value="/updateDeleteCom.do">刪除修改廠商</span>
													</td>
													<td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox"
															name="pass_code" value="/deleteMem.do">刪除會員<br></span>
													</td><td></td><td></td><td></td>
												</tr>
												<tr><td></td><td></td><td></td><td></td><td></td></tr>
											</tbody>
										</table>
									</div>
									<div class="panel-heading">
										<div class="pull-left"><h3><i class="fa fa-edit"></i> 進貨系統</h3></div><br>
									</div>
									<div class="custom-check goleft mt">
										<table id="todo" class="table table-hover custom-check">
											<tbody>
												<tr>
													<th>請購單維護</th>
													<th>詢價單料維護</th>
													<th>採購單料維護</th>
													<th>進貨單料維護</th>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/addReq.jsp">新增請購單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/addQuo0.do">新增詢價單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/insertPur00.do">新增採購單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/selectOfY.do">新增進貨單</span></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/SelectReq.jsp">單筆查詢請購單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/SelectQuo.jsp">查詢單筆詢價單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/SelectPur.jsp">單筆查詢採購單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/SelectBOP0.jsp">單筆查詢進貨單</span></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/getAllReq.do">查詢全部請購單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/getAllQuo.do">查詢全部詢價單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/getAllPur.do">查詢全部採購單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/getAllBop.do">查詢全部進貨單</span></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/SelectbyDate.jsp">依請購單日期查詢</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/SelectbyDate.jsp">依詢價單日期查詢</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/SelectbyDate.jsp">依採購單日期搜尋</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/SelectbyDate.jsp">依進貨單日期查詢</span></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/selectOfN.do">審核請購單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/selectOfN.do">審核詢價單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/selectOfN.do">審核採購單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/selectOfN.do">審核進貨單</span></td>
												</tr>
												<tr>
													<td><span class="check"></span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/selectOfY.do">新增商品目錄</span></td>
													<td><span class="check"></span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/selectOfY2.do">到貨現況</span></td>
												</tr>
												<tr><td></td><td></td><td></td><td></td></tr>
											</tbody>
										</table>
									</div>
									<div class="panel-heading">
										<div class="pull-left"><h3><i class="glyphicon glyphicon-shopping-cart"></i> 銷貨系統</h3></div><br>
									</div>
									<div class="custom-check goleft mt">
										<table id="todo" class="table table-hover custom-check">
											<tbody>
												<tr>
													<th>報價單維護</th>
													<th>訂單維護</th>
													<th>出貨單維護</th>
													<th> </th>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/addVltList.do">新增報價單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/OrdsearchList.jsp">查詢訂單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/searchList.jsp">單筆查詢出貨單</span></td>
													<td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/searchList.jsp">單筆查詢報價單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/SelectOrdAll.jsp">查詢全部訂單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/AllShip.jsp">查詢全部出貨單</span></td>
													<td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/SelectVltAll.jsp">全部查詢報價單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/Querydetail_DeleteOrd.do">註銷訂單</span></td>
													<td></td><td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/SelectVltAllForCHK.jsp">審核報價單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/OrdToShip.do">訂單轉出貨單</span></td>
													<td></td><td></td>
												</tr>
												<tr><td></td><td></td><td></td><td></td></tr>
											</tbody>
										</table>
									</div>
									<div class="panel-heading">
										<div class="pull-left"><h3><i class="fa fa-book"></i> 存貨系統</h3></div><br>
									</div>
									<div class="custom-check goleft mt">
										<table id="todo" class="table table-hover custom-check">
											<tbody>
												<tr>
													<th>商品管理</th>
													<th>促銷商品管理</th>
													<th> </th>
													<th> </th>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/InsertProd.jsp">新增商品</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/searchProm.jsp">查詢促銷品</span></td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/getAllProd.do">查詢全部商品</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code"value="/addProm.jsp">新增促銷品</span></td>
													<td><span class="check"></span></td>
													<td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/getOneProd.do">依商品編號搜尋</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/deleteProm.do">刪除促銷品</span></td>
													<td></td><td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/getProdByName.do">依商品名稱搜尋</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="allForUpdateProm.do">修改促銷品</span></td>
													<td></td><td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/getProdByGroup.do">依商品類別搜尋</span></td>
													<td></td>
													<td></td><td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/updateDeleteProd.do">刪除修改商品</span></td>
													<td></td>
													<td></td><td></td>
												</tr>
												<tr><td></td><td></td><td></td><td></td></tr>
											</tbody>
										</table>
									</div>
									<div class="panel-heading">
										<div class="pull-left"><h3><i class="glyphicon glyphicon-log-out"></i> 退貨系統</h3></div><br>
									</div>
									<div class="custom-check goleft mt">
										<table id="todo" class="table table-hover custom-check">
											<tbody>
												<tr>
													<th>退貨品管理</th>
													<th>退貨單管理</th>
													<th>作廢發票管理</th>
													<th> </th>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/insert_Item.do">新增退貨品</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/insert.do">新增退貨單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/insertInvo.do">新增作廢發票</span></td>
													<td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/searchItem.jsp">搜尋退貨品</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/searchList.jsp">單筆搜尋退貨單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/searchinvo.jsp">依發票編號搜尋</span></td>
													<td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/delete_Item.do">刪除退貨品</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/AllList.jsp">搜尋全部退貨單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/listAllInvo.jsp">搜群全部作廢發票</span></td><td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/update_Item.do">修改退貨品</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/Return_Detail.jsp">查看退貨單明細</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/deleteInvo.do">刪除作廢發票</span></td><td></td>
												</tr>
												<tr>
													<td></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/delete.do">刪除退貨單</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/updateInvo.do">修改作廢發票</span></td><td></td>
												</tr>
												<tr>
													<td></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/update.do">修改退貨單</span></td>
													<td></td><td></td>
												</tr>
												<tr><td></td><td></td><td></td><td></td></tr>
											</tbody>
										</table>
									</div>
									<div class="panel-heading">
										<div class="pull-left"><h3><i class="fa fa-usd"></i> 金流管理</h3></div><br>
									</div>
									<div class="custom-check goleft mt">
										<table id="todo" class="table table-hover custom-check">
											<tbody>
												<tr>
													<th>班別報表維護</th>
													<th>折價券</th>
													<th>折扣管理</th>
													<th> </th>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/getOneShiftre.do">依班別報表搜尋</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/coupon.do">依折價券編號搜尋</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/search_disc.jsp">搜尋折扣</span></td>
													<td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/getAllShiftre.do">查詢全部</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/namesCpon.do">依折價券名稱搜尋</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/insertDic.jsp">新增折扣</span></td>
													<td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/getShiftreByDate.do">依日期搜尋</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/dollarCpon.do">依折價券金額搜尋</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/deleteDisc.do">刪除折扣</span></td><td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/insertShiftre.do">新增班別報表</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/datesCpon.do">依折價券日期搜尋</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/updateDisc.do">修改折扣</span></td><td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/updateShiftre.do">修改班別報表</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/allCpon.do">查詢全部</span></td><td></td>
													<td></td>
												</tr>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/updateDeleteShiftre.do">刪除班別報表</span></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/insertCpon.do">新增折價券</span></td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/updateCpon.do">修改折價券</span></td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td></td>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/deleteCpon.do">刪除折價券</span></td>
													<td></td>
													<td></td>
												</tr>
												<tr><td></td><td></td><td></td><td></td></tr>
											</tbody>
										</table>
									</div>
									<div class="panel-heading">
										<div class="pull-left"><h3><i class="fa fa-bar-chart-o"></i> 報表分析</h3></div><br>
									</div>
									<div class="custom-check goleft mt">
										<table id="todo" class="table table-hover custom-check">
											<tbody>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/analysis.jsp">銷售報表分析</span></td>
													<td><span class="check"></span></td>
													<td><span class="check"></span></td>
													<td></td>
												</tr>
												<tr><td></td><td></td><td></td><td></td></tr>
											</tbody>
										</table>
									</div>
									<div class="panel-heading">
										<div class="pull-left"><h3><i class="fa fa-users"></i>顧客關係</h3></div><br>
									</div>
									<div class="custom-check goleft mt">
										<table id="todo" class="table table-hover custom-check">
											<tbody>
												<tr>
													<td><span class="check"><input type="checkbox" name="pass_code" value="/Mail.jsp">寄送系統</span></td>
													<td><span class="check"></span></td>
													<td><span class="check"></span></td>
													<td></td>
												</tr>
												<tr><td></td><td></td><td></td><td></td></tr>
											</tbody>
										</table>
									</div>
									<!-- /table-responsive -->
								<!--/ White-panel -->
								<!-- /table-responsive -->
								<input type="submit" value="修改權限" class="btn btn-theme03">
								<input type="button" value="清除全部" id="Toggle" class="btn btn-theme03">

							</form>
						</center>
					</div>
				</div>
			</div>
	</section> </section> </section>
	<input type="hidden" name="shift" value="${SHIFT}">
	<input type="hidden" name="emp_id" value="${LoginOK.emp_id}">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script>
		!window.jQuery
				&& document
						.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
	</script>

	<script src="<c:url value="../resources/js/bootstrap.min.js" />"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.js"></script>
	<script src="<c:url value='../resources/js/jquery.validate.min.js' />"></script>
	<script class="include" type="text/javascript"
		src="<c:url value="../resources/js/jquery.dcjqaccordion.2.7.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.scrollTo.min.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.nicescroll.js" />"
		type="text/javascript"></script>

	<!--common script for all pages-->
	<script src="<c:url value="../resources/js/common-scripts.js" />"></script>
	<!------------------------------------------------ 程式 --------------------------------------------------------------->
	<script src="<c:url value="../resources/js/gen_validatorv4.js" />"
		type="text/javascript"></script>
	<script>
		$('select').change(
				function() {
					$("input[name='pass_code']").prop("checked", false);
					var myBody = $('#passTable>tbody');
					myBody.empty();
					$.getJSON('getPassCode.do', {
						emp_id : $(this).val()
					}, function(datas) {
						console.log(datas);

						$.each(datas, function(idx, passCode) {
							$.each(passCode, function(key, value) {
								// 					 			console.log( value);
								if (value == "ALL") {
									$("input[value='ALL']").prop("checked",
											true);
								} else if (value != null) {

									var psCodeArray = value.split(",");
									for (i = 0; i < psCodeArray.length; i++) {
										var x = psCodeArray[i];
										$("input[value='" + x + "']").prop(
												"checked", true);
									}
								}
							})
						});
					})
				})
		$('#Toggle').click(function(){
		   $(':checkbox[name="pass_code"]').prop('checked',false);
	   });
		//員工
		$('#empPass').change(function(){
		   var b = $(this).prop('checked');
		   $(':checkbox[value="/addMem.jsp"]').prop('checked',b);//新增會員
		   $(':checkbox[value="/searchMem.jsp"]').prop('checked',b);//搜尋會員
		   $(':checkbox[value="/searchCom.jsp"]').prop('checked',b);//搜尋廠商
		   $(':checkbox[value="/addCom.jsp"]').prop('checked',b);//新增廠商
		   $(':checkbox[value="/addReq.jsp"]').prop('checked',b);//新增請購單
		   $(':checkbox[value="/SelectReq.jsp"]').prop('checked',b);//單筆查詢請購單
		   $(':checkbox[value="/getAllReq.do"]').prop('checked',b);//查詢全部請購單
		   $(':checkbox[value="SelectbyDate.jsp"]').prop('checked',b);//依日期查詢請購單
		   $(':checkbox[value="/addQuo0.do"]').prop('checked',b);//新增詢價單
		   $(':checkbox[value="/SelectQuo.jsp"]').prop('checked',b);//查詢單筆詢價單
		   $(':checkbox[value="/getAllQuo.do"]').prop('checked',b);//查詢全部詢價單
		   $(':checkbox[value="/SelectbyDate.jsp"]').prop('checked',b);//依詢價單日期查詢
		   $(':checkbox[value="/selectOfY.do"]').prop('checked',b);//新增商品目錄
		   $(':checkbox[value="/insertPur00.do"]').prop('checked',b);//新增採購單
		   $(':checkbox[value="/SelectPur.jsp"]').prop('checked',b);//單筆查詢採購單
		   $(':checkbox[value="/getAllPur.do"]').prop('checked',b);//查詢全部採購單
		   $(':checkbox[value="/SelectbyDate.jsp"]').prop('checked',b);//依日期搜尋採購單
		   $(':checkbox[value="/selectOfY.do"]').prop('checked',b);//新增進貨單
		   $(':checkbox[value="/SelectBOP0.jsp"]').prop('checked',b);//單筆查詢進貨單
		   $(':checkbox[value="/getAllBop.do"]').prop('checked',b);//查詢全部進貨單
		   $(':checkbox[value="/SelectbyDate.jsp"]').prop('checked',b);//依日期查詢進貨單
		   $(':checkbox[value="/selectOfY2.do"]').prop('checked',b);//到貨現況
		   $(':checkbox[value="/addVltList.do"]').prop('checked',b);//新增報價單
		   $(':checkbox[value="/searchList.jsp"]').prop('checked',b);//單筆查詢報價單
		   $(':checkbox[value="/SelectVltAll.jsp"]').prop('checked',b);//全部查詢報價單
		   $(':checkbox[value="/OrdsearchList.jsp"]').prop('checked',b);//查詢訂單
		   $(':checkbox[value="/SelectOrdAll.jsp"]').prop('checked',b);//查詢全部訂單
		   $(':checkbox[value="/OrdToShip.do"]').prop('checked',b);//訂單轉出貨單
		   $(':checkbox[value="/searchList.jsp"]').prop('checked',b);//單筆查詢出貨單
		   $(':checkbox[value="/AllShip.jsp"]').prop('checked',b);//查詢全部出貨單
		   $(':checkbox[value="/InsertProd.jsp"]').prop('checked',b);//新增商品
		   $(':checkbox[value="/getAllProd.do"]').prop('checked',b);//查詢全部商品
		   $(':checkbox[value="/getOneProd.do"]').prop('checked',b);//依商品編號搜尋
		   $(':checkbox[value="/getProdByName.do"]').prop('checked',b);//依商品名稱搜尋
		   $(':checkbox[value="/getProdByGroup.do"]').prop('checked',b);//依商品類別搜尋
		   $(':checkbox[value="/searchProm.jsp"]').prop('checked',b);//查詢促銷品
		   $(':checkbox[value="/addProm.jsp"]').prop('checked',b);//新增促銷品
		   $(':checkbox[value="/insert_Item.do"]').prop('checked',b);//新增退貨品
		   $(':checkbox[value="/searchItem.jsp"]').prop('checked',b);//搜尋退貨品
		   $(':checkbox[value="/insert.do"]').prop('checked',b);//新增退貨單
		   $(':checkbox[value="/searchList.jsp"]').prop('checked',b);//單筆搜尋退貨單
		   $(':checkbox[value="/AllList.jsp"]').prop('checked',b);//搜尋全部退貨單
		   $(':checkbox[value="/Return_Detail.jsp"]').prop('checked',b);//查看退貨單明細
		   $(':checkbox[value="/insertInvo.do"]').prop('checked',b);//新增作廢發票
		   $(':checkbox[value="/searchinvo.jsp"]').prop('checked',b);//依發票編號搜尋
		   $(':checkbox[value="/listAllInvo.jsp"]').prop('checked',b);//搜群全部作廢發票
		   $(':checkbox[value="/getOneShiftre.do"]').prop('checked',b);//依班別報表搜尋
		   $(':checkbox[value="/getAllShiftre.do"]').prop('checked',b);//查詢全部
		   $(':checkbox[value="/getShiftreByDate.do"]').prop('checked',b);//依日期搜尋
		   $(':checkbox[value="/insertShiftre.do"]').prop('checked',b);//新增班別報表
		   $(':checkbox[value="/coupon.do"]').prop('checked',b);//依折價券編號搜尋
		   $(':checkbox[value="/namesCpon.do"]').prop('checked',b);//依折價券名稱搜尋
		   $(':checkbox[value="/dollarCpon.do"]').prop('checked',b);//依折價券金額搜尋
		   $(':checkbox[value="/datesCpon.do"]').prop('checked',b);//依折價券日期搜尋
		   $(':checkbox[value="/allCpon.do"]').prop('checked',b);//查詢全部
		   $(':checkbox[value="/insertCpon.do"]').prop('checked',b);//新增折價券
		   $(':checkbox[value="/search_disc.jsp"]').prop('checked',b);//搜尋折扣
		   $(':checkbox[value="/insertDic.jsp"]').prop('checked',b);//新增折扣
		   $(':checkbox[value="/Mail.jsp"]').prop('checked',b);//寄送系統
	   });
	</script>

</body>
</html>