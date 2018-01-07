<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.order_detail.model.*"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%
	OrderVO orderVO = (OrderVO) request.getAttribute("ord_id");
	Order_DetailVO ord_detail = (Order_DetailVO) request.getAttribute("ord_id");

	OrderService ordSvc = new OrderService();
	double dayPrice = ordSvc.GetDayTotalPrice();
	pageContext.setAttribute("dayPrice", dayPrice);

	long dayPeople = ordSvc.GetDayTotalPeople();
	pageContext.setAttribute("dayPeople", dayPeople);
	
	session.getAttribute("LoginOK");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script>
	//計算商品明細總金額
	function count_total_prod_price(prod_quantityObj, prod_priceObj,
			total_prod_priceObj) {

		//先刪除原本的商品明細總金額=單價*數量
		document.getElementById("total_price_temp").value = parseInt(document
				.getElementById("total_price_temp").value)
				- total_prod_priceObj.value
		//計算目前的商品明細總金額=單價*數量
		total_prod_priceObj.value = prod_quantityObj.value
				* prod_priceObj.value;
		//計算實際金額
		count_total_price(total_prod_priceObj.value)
	}
	//計算實際金額
	function count_total_price(total_prod_price) {
		if (total_prod_price == undefined) {
			total_prod_price = 0;
		}

		document.getElementById("total_price_temp").value = Math.floor(parseInt(document
				.getElementById("total_price_temp").value)
				+ parseInt(total_prod_price));
		document.getElementById("total_price").value = Math.floor((document
				.getElementById("total_price_temp").value * document
				.getElementById("dis_price").value)
				- document.getElementById("cpon_dollar").value);
	}

	//計算應付金額(現金)
	function countCash() {
		var cpon_dollar = document.getElementById("cpon_dollar").value;

		if (cpon_dollar == "")//給初始值，不能拿空值來計算
			cpon_dollar = 0;

		var total_price = document.getElementById("total_price").value;
		document.getElementById("cash").value = total_price - cpon_dollar;
	}
	
	function check(value) {  
// 	    var f = document.forms[0];  
	    var re = /^\d+$/;  
	    if (!re.test(value)) {  
// 	       alert("欄位不能空白且只允許輸入數字");
		   ordmain.cash_temp.value = '';
	       ordmain.cash_temp.focus();  
	       return false;  
	    }  
	    return true;  
	} 
	
</script>

<head>
<SCRIPT LANGUAGE="JavaScript">


function varitext(text){
text=document
print(text)
}

</script>



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
<title>訂單</title>

<style>

	.titledetail {
		/*     	margin-top:auto; */
		font-family: '微軟正黑體';
		font-weight: bold;
		color: white;
		height: 35px;
		background: #428bca;
		font-size: 23px;
		border-radius: 2px;
	}
	
	a{
		color:#ab2222;
	}
	
	.table-bordered{
		border: 1px solid #428bca;
	}
	
	.table > thead:first-child > tr:first-child > td {
		height: 20px;
  		background:#007bb7;
  		color:white;
  		border-top: 0;
  		font-family: 微軟正黑體;
}

	/* 	表格內容單數 */
	.table-striped > tbody > tr:nth-child(odd) > td, .table-striped > tbody > tr:nth-child(odd) > th{
		background-color:white;
	}
	/* 	表格內容偶數 */
	.table-bordered > thead > tr > th, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > tbody > tr > td, .table-bordered > tfoot > tr > td{
		border:1px solid #3393c1;
		background:#dcf3ff;
		height: 30px;
		font-weight: bold;
	}
	/* 	表格偶數滑鼠指向 */
	.table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th{
		background-color:#dcf3ff;
	}
	
	.alert-info{
		background: #f7a2a2;
		border-color:#e86262;
	}
	
	.addNewDetail{
		
	}
	
	.deltitle {
	/*     	margin-top:auto; */
	font-family: '微軟正黑體';
	font-weight: bold;
	color: white;
	height: 35px;
	background: #0594da;
	font-size: 23px;
	border-radius: 2px;
}
	
	
	.btn-primary{
		margin-top:60px;
	}
	
	
	.main{
		height: 150px;
		background: #ddf4ff;
	}
	
/* 	.btn-success{ */
/* 		height: 100px; */
/* 		width: 100px; */
/* 	} */
	
/* 	.btn-warning{ */
/* 		height: 100px; */
/* 		width: 100px; */
/* 	} */
	
	.btndiv{
		border-style:double;
		border-color:#007bb7;
		border-width:thick;
		height: auto;
	}
	
	.ordbtm{
		margin-top: 20px;
	}
	
	/* 下方明細主黨 */
	.form-inline .form-group{
		margin-top: 10px;
		margin-left: 20px;
	}
	
	label{
		color:black;
	}
	
 	.btn-success{ 
 		font-family:微軟正黑體;
 		margin-left:120px;
 		margin-right:100px; 
 	}
 	
 	.btn-warning{
 		font-family:微軟正黑體;
 	} 

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
	
<li><a class="logout" href="<%=request.getContextPath()%>/LOGIN/logout.jsp">Logout</a>Hi , ${LoginOK.emp_name}</li>		</ul>
	</div>
	</header> <!--header end--> <!--sidebar start--><aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu" id="nav-accordion">

			<p class="centered">
				<a href="<%=request.getContextPath()%>/index.jsp"><img
					src="<c:url value="../resources/img/ui-sam.jpg"/>"
					class="img-circle" width="60"></a>
			</p>
			<h5 class="centered">ePOS</h5>

			<li class="mt"><a
				href="<%=request.getContextPath()%>/ORDER/order.jsp" class="active"> <i
					class="fa fa-dashboard"></i> <span>收銀結帳</span>
			</a></li>

			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-desktop"></i> <span>基本資料維護</span>
			</a>
				<ul class="sub">
					<li><a href="<%=request.getContextPath()%>/MEMBER/member.jsp">會員資料維護</a></li>
					<li><a
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
					<li class="active"><a href="<%=request.getContextPath()%>/ORDER/ordmain.jsp">訂單維護</a></li>
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
					<li class="sub-menu"><a href="javascript:;"> <i class="fa fa-users"></i> <span>顧客關係</span>
					</a>
						<ul class="sub">
							<li><a href="<%=request.getContextPath()%>/MAIL/Mail.jsp">寄送系統</a></li>
						</ul></li>

		</ul>
		<!-- sidebar menu end-->
	</div>
	</aside> <!--sidebar end--> <section id="main-content"> <section class="wrapper">
	 <!-- -----------------------------------------------------------查詢----------------------------------------------------------- -->
	
	<div class="row mt">
	<div class="col-sm-12">
		<div id="add" class="main">
			<div style="height: 15px;"></div>
			<Form METHOD="post" action="addOrder.do" name="ordmain" class="form-inline">
					<jsp:useBean id="weather" class="analysis.LoadWeatherRss" scope="page"/>
					<table border="1">
						<tr>
						<div class="form-group">
							<label for="exampleInputName2">　收銀員編號：</label> 
							<input type="text" value="${LoginOK.emp_id}" name="key_id_txt" class="form-control" disabled="disabled">
							<input type="hidden" name="key_id" value="${LoginOK.emp_id}">
<%-- 							<input type="hidden" name="emp_id" value="${LoginOK.emp_id}"> --%>
						</div>
						<div class="form-group">
							<label for="exampleInputName2">　收銀員姓名：</label> 
							<input type="text" value="${LoginOK.emp_name}" class="form-control" disabled="disabled">
						</div>
						
						<div class="form-group">
							<label for="exampleInputName2">　班別：</label> 
							<input type="text" value="${SHIFT}" name="shift_txt" class="form-control" disabled="disabled">
							<input type="hidden" value="${SHIFT}" name="shift">
						</div>
						<div class="form-group">
							<label for="exampleInputName2">　購買會員：</label> 
							<input type="text" name="mem_name" class="form-control" disabled>
						</div>
						<div class="form-group">
							<label for="exampleInputName2">　購買員工：</label> 
							<input type="text" name="emp_name" class="form-control" disabled>
						</div>
						<div style="height: 20px;"></div>
						<div class="form-group">
							<label for="exampleInputName2">　今 日 天 氣：</label> 
							<input type="text" value="${weather.nowWeather}" name="txtnowWeather" class="form-control" disabled>
							<input type="hidden" value="${weather.nowWeather}" name="nowWeather">
						</div>
							
						</tr>
					</table>
					<!------------------------------------------------------------ 明細 -------------------------------------------------------------->
					<div style="height: 20px;"></div>
					<div class="deltitle">訂單明細</div>
				
					<!-- 		<input type="button" value="新增明細" id="addNewDetail"> -->
						<table border="1" id="table1" class="table table-bordered table-striped table-hover">
							<!-- 由$("#addNewDetail").click產出明細table -->
							<thead id="title">
								<tr>
									<td align='center'>商品編號</td>
									<td align='center'>商品名稱</td>
									<td align='center'>商品數量</td>
									<td align='center'>商品價格</td>
									<td align='center'>刪除</td>
								</tr>
							</thead>
						</table>
					
					<!------------------------------------------------------------ 輸入區 -------------------------------------------------------------->
					<div style="height: 10px;"></div>
					<div class="btndiv">
					<form class="form-inline">
					<table class="ordbtm" border="3" bordercolor="#007bb7">
						<tr> <!--  valign="top"  -->
 							<div class="form-group">
									<label>輸入商品ID：</label>
									<input type="text" id="prod_id" name="prod_id" size="60" class="form-control" />
							</div>　　
							<div class="form-group">
									<label>筆　　數：</label>
									<input type="text" id="count" name="count" value="0" class="form-control" readonly/>
							</div>　　
							<div class="form-group">
									<label>現　　金：</label>
									<input type="text" id="cash_temp" name="cash_temp" value="" class="form-control" onkeyup="check(this.value)"/>　
									<input type="hidden" id="cash" name="cash" value="" />
							</div>
							<div class="form-group">
									<label>找　　零：</label>
									<input type="text" id="charge" name="charge" value="" class="form-control" readonly/>
							</div>　
						</tr>
						<div style="height: 5px;"></div>
						<tr>
							<div class="form-group">
									<label for="exampleInputName2">會員編號：　</label>
									<input type="text" id="mem_id" name="mem_id"  class="form-control" />
							</div>　
							<div class="form-group">
									<label for="exampleInputName2">統一編號：</label>
									<input type="text" id="ord_um" name="ord_um"  class="form-control" />
							</div>　　
							<div class="form-group">
									<label>禮　　卷：</label>
									<input type="text" id="cpon_dollar" name="cpon_dollar" value="0" class="form-control" readonly/>	
							</div>
						</tr>
						<div style="height: 5px;"></div>
						<tr>
							<div class="form-group">
									<label for="exampleInputName2">員工編號：　</label>
									<input type="text" id="emp_id" name="emp_id"  class="form-control" />
							</div>　
							<div class="form-group">
									<label for="exampleInputName2">日營業額：</label>
									<input type="text" value="${dayPrice}"  class="form-control" readonly/>
							</div>　　
							<div class="form-group">
									<label>折　　扣：</label>
									<input type="text" id="dis_price" name="dis_price" value="1" class="form-control" readonly/>	
							</div>
						</tr>
						<div style="height: 5px;"></div>
						<tr>
							<div class="form-group">
									<label for="exampleInputName2">折價卷號：　</label>
									<input type="text" id="cpon_id" name="cpon_id" class="form-control"/>
							</div>　
							<div class="form-group">
									<label for="exampleInputName2">來客數量：</label>
									<input type="text" value="${dayPeople}"  class="form-control" readonly/> 人次
							</div>
							<div class="form-group">
									<label>總計金額：</label>
									<input type="text" id="total_price" name="total_price" value="0" class="form-control" readonly/>
									<input type="hidden" id="total_price_temp" name="total_price_temp" value="0" />
							</div>
									<input type="button" id="addNewDetail"  value="　　　輸入　　　" class="btn btn-success btn-lg" />
									<input type="submit" value="　　　結帳　　　" class="btn btn-warning btn-lg" />	
						</tr>
					</table>
					</form>
					</div>
					<br>
					<input type="hidden" name="action" value="insert"> 
				</Form>
		</div>
	</div>  	<!-- "col-sm-12" -->
	<div> 		<!-- "row mt" -->
	</div>
	</section> </section> </section>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script>
		!window.jQuery
				&& document
						.write("<script src='<c:url value='../resources/js/jquery-3.1.1.min.js'/>'><\/script>")
	</script>
	<script src="<c:url value="../resources/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/u/bs/jq-2.2.3,dt-1.10.12/datatables.min.js"></script>
	<script class="include" type="text/javascript" src="<c:url value="../resources/js/jquery.dcjqaccordion.2.7.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.scrollTo.min.js" />"></script>
	<script src="<c:url value="../resources/js/jquery.nicescroll.js" />"
		type="text/javascript"></script>

	<!--common script for all pages-->
	<script src="<c:url value="../resources/js/common-scripts.js" />"></script>
	<!------------------------------------------------ 程式 --------------------------------------------------------------->
	<script src="<c:url value="../resources/js/gen_validatorv4.js" />"
		type="text/javascript"></script>
	<script type="text/JavaScript">
	

				var i=1;
				for(var a=0;a<=12;a++){
					$("#table1").each(function(tr){
						$("#table1").append("<tr align='center'><td id="+i+"></td><td id="+i+"></td><td id="+i+"></td><td id="+i+"></td><td id="+i+"></td></tr>")
						i=i+1;
					})
				}
// <!----------------------------------------  一頁6筆         ------------------------------------>				
				$("#table1").dataTable({
// 					"pageLength": 6,
					"lengthMenu": [ 6 , 10, 15],
				});
				

	$(function() {
				
				
// <!----------------------------------------  新增明細         ------------------------------------>
				var a = 1;
				var prod_name;
				var prod_price;
				var id=0;
				var n=1;
				var q=2;
				var p=3;
				var del=4;
				$("#addNewDetail").click(function() {
									
									$.getJSON('getByProd_id.do',{prod_id:$('#prod_id').val()},function(data){
										//取得JSON資料
										prod_name = data.prod_name;
										prod_price = data.prod_price;

										//先將總計金額加上本次新增商品金額
										ordmain.total_price_temp.value = parseInt(ordmain.total_price_temp.value) + parseInt(prod_price);
										count_total_price();
										
										var count=0;
										var z1=0;
										while(true){
											if($("#table1 input:eq("+z1+")").val()!=null){
												
											count++;
											}else{
												break;
												}
											z1=z1+6;
										}
										console.log(count)
										count=count*5;
										n = count + 1;
										q = count + 2;
										p = count + 3;
										del = count + 4;
// 										$("#table1 >tbody >tr >td:eq("+id+")").append("<input type='text' name='prod_id"+a+"' value='"+$('#prod_id').val()+"' />")
										$("#table1 >tbody >tr >td:eq("+count+")").append("<input type='text' name='prod_id"+a+"' value='"+$('#prod_id').val()+"' readonly />")
										$("#table1 >tbody >tr >td:eq("+n+")").append("<input type='text' name='prod_name"+a+"' value='"+prod_name+"' readonly/>")
										$("#table1 >tbody >tr >td:eq("+q+")").append("<input type='text' name='prod_quantity"+a+"' value='1' onblur='count_total_prod_price(ordmain.prod_quantity"+a+",ordmain.prod_price"+a+",ordmain.total_prod_price"+a+")'/>")
									//	$("#table1 >tbody >tr >td:eq("+p+")").append("<input type='text' name='prod_price"+a+"' value='"+prod_price+"' />")
										//$("#table1 >tbody >tr >td:eq("+p+")").append("<input type='hidden' id='total_prod_price"+a+"' name='total_prod_price"+a+"' value='"+prod_price+"'/>")
										$("#table1 >tbody >tr >td:eq("+p+")").append("<input type='text' name='prod_price"+a+"' value='"+prod_price+"' readonly/><input type='hidden' name='total_prod_price"+a+"' name='total_prod_price"+a+"' value='"+prod_price+"' />")
									
										$("#table1 >tbody >tr >td:eq("+del+")").append("<input type='button' value='刪除' class='btn btn-danger'></input>")
										a = a + 1;

									
// 										id = id + 5;
// 										n = n + 5;
// 										q = q + 5;
// 										p = p + 5;
// 										del = del + 5;
// 										console.log("id="+id);
// 										console.log("n="+n);
// 										console.log("q="+q);
// 										console.log("p="+p);
// 										console.log("del="+del);
										
										if(id==30){
											console.log("id="+id);
											 id=0;
											 n=1;
											 q=2;
											 p=3;
											 del=4;
										}
										                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
										//筆數+1
										ordmain.count.value=parseInt(ordmain.count.value)+1;
										document.getElementById("prod_id").value="";
									})
									
									
								})

				
				$("#mem_id").blur(function() {
									if($('#mem_id').val() != ""){
										ordmain.emp_id.value='';
										$.getJSON('getByMem_id.do',{mem_id:$('#mem_id').val()},function(data){
											
											//取得JSON資料
											//不是會員
											if(data.error=="查無此帳號"){//查無此帳號時,預設查一般的折讓數
												alert(data.error);
												$.getJSON('getByDis_id.do',{dis_id:'一般'},function(data){
													//取得JSON資料
													ordmain.dis_price.value = data.dis_price;
													count_total_price();
													
												})
											}else{//是會員,找%數
												ordmain.emp_name.value ='';
												ordmain.mem_name.value = data.mem_name;
												$.getJSON('getByDis_id.do',{dis_id:'會員'},function(data){
													//取得JSON資料
													ordmain.dis_price.value = data.dis_price;
													count_total_price();
												})
											}
										})
									}else{//沒有輸入資料時,預設查一般的折讓數
										$.getJSON('getByDis_id.do',{dis_id:'一般'},function(data){
											//取得JSON資料
											ordmain.dis_price.value = data.dis_price;
											count_total_price();
										})
									}
									
								})
						
				$("#emp_id").blur(function() {
									if($('#emp_id').val() != ""){
										ordmain.mem_id.value='';
										$.getJSON('getByEmp_id.do',{emp_id:$('#emp_id').val()},function(data){
											//取得JSON資料
											//不是員工
											if(data.error=="查無此員工"){//查無此帳號時,預設查一般的折讓數
												alert(data.error);
												$.getJSON('getByDis_id.do',{dis_id:'一般'},function(data){
													//取得JSON資料
													ordmain.dis_price.value = data.dis_price;
													count_total_price();
												})
											}else{//是員工,找%數
												ordmain.mem_name.value ='';
												ordmain.emp_name.value = data.emp_name;
												$.getJSON('getByDis_id.do',{dis_id:'員工'},function(data){
													//取得JSON資料
													ordmain.dis_price.value = data.dis_price;
													count_total_price();	
												})
											}
										})
									}else{//沒有輸入資料時,預設查一般的折讓數
										$.getJSON('getByDis_id.do',{dis_id:'一般'},function(data){
											//取得JSON資料
											ordmain.dis_price.value = data.dis_price;
											count_total_price();
										})
									}
								})
								
								
								
				$("#cpon_id").blur(function() {
					if($('#cpon_id').val() != ""){
						
						$.getJSON('getByCpon_id.do',{cpon_id:$('#cpon_id').val()},function(data){
							//取得JSON資料
							if(data.error=="查無此禮券"){//查無此禮券時,預設查一般的折讓數
								alert(data.error);
										
								ordmain.cpon_dollar.value = 0;
								count_total_price();	
							}else{
								ordmain.cpon_dollar.value = data.cpon_dollar;
								count_total_price();
							}
						})
					}else{//沒有輸入資料時,預設查一般的折讓數
						
						ordmain.cpon_dollar.value = 0;
						count_total_price();
					}
				})
				
				
				$("#cash_temp").blur(function() {
				
					if($(this).val() != ""){	
						
						ordmain.charge.value = ordmain.cash.value - ordmain.total_price.value;
						ordmain.charge.value = ordmain.cash_temp.value - ordmain.total_price.value;
						ordmain.cash.value = ordmain.cash_temp.value - ordmain.charge.value;
						
						if(parseInt(document.getElementById("charge").value) <= 0){	
							
							var Balance = ordmain.total_price.value - parseInt(document.getElementById("cash_temp").value);
							ordmain.charge.value="請補足餘額："+Balance;
						}
					}else{
						document.getElementById("charge").value="0";
					}
				})				
	//<!----------------------------------------  刪除        ------------------------------------>	
	var table2 = $('#table1').DataTable();
	$("#table1").on('click', '.btn-danger', function() {
		//總計金額先扣除該筆商品的金額
		ordmain.total_price_temp.value = parseInt(ordmain.total_price_temp.value) - $(this).parents("tr").children("td:eq(3)").children("input").eq(1).val();
		count_total_price();

		
		table2.row( $(this).parents('tr') ).remove().draw();


		
	
				

		
		//筆數再-1
		ordmain.count.value=parseInt(ordmain.count.value)-1;
	});
//  	$('#table1').click();

})
	

//<!----------------------------------------  送出全部查詢        ------------------------------------>
		$('#allsearch').on('click',function(){
			var url="SelectOrdAll.jsp";
		
			$.ajax({
				type:"POST",
				url:url,
				success:function()
				{
					window.open("SelectOrdAll.jsp");
				}
			})
		})
		


	</script>
</body>
</html>
