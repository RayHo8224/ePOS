package com.springMVC.controller;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.model.ComService;
import com.company.model.ComVO;
import com.returns.model.ReturnDetailService;
import com.returns.model.ReturnItemsService;
import com.returns.model.ReturnListService;
import com.returns.model.RtnDetailVO;
import com.returns.model.RtnItemsVO;
import com.returns.model.RtnListVO;

@Controller
public class RtnList_Controller extends HttpServlet {
	
	private final static ReturnListService rtnListSvc = new ReturnListService();
	private final static ReturnDetailService rtnDetailSvc = new ReturnDetailService();
	
	//查詢全部	
//	@RequestMapping(method = RequestMethod.POST, value = "/RtnListVO/allList.do")
//	public String getAllList(ModelMap model) {	
//
//			List<RtnListVO> list = rtnListSvc.getAll();
//			model.addAttribute("list", list);	
//			return "/RETURNS/AllList";
//		
//		}
	
	
	@RequestMapping(method = RequestMethod.POST, value = {"/getOne.do","/RETURNS/getOne.do"})
	public String getOne(@RequestParam("ret_id") String ret_id, ModelMap model) {
		
		List<String> errorMsgs=new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
		
		try{
			//String ret_id=req.getParameter("ret_id");
			if(ret_id ==null || (ret_id.trim()).length()==0){
				errorMsgs.add("請輸入退貨單編號");
			}
			if(!errorMsgs.isEmpty()){
//				RequestDispatcher failureView = req.getRequestDispatcher("/returns/ReturnList.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/searchList";
			}
			
			
			try{
				ret_id=new String(ret_id);
			}catch(Exception e){
				errorMsgs.add("退貨單編號格式錯誤");
			}
			
			if(!errorMsgs.isEmpty()){
//				RequestDispatcher failureView = req.getRequestDispatcher("/returns/ReturnList.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/searchList";
			}		
			
			/***************************2.開始查詢資料*****************************************/
			
			//ReturnListService rtnListSvc = new ReturnListService();
			List<RtnListVO> list=rtnListSvc.getfindById(ret_id);
			if(list.size()==0){
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/returns/ReturnList.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/searchList";
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			model.addAttribute("list", list);
//			String url = "/returns/listOneList.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);	
			return "/RETURNS/listOneList";
		}catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/ReturnList.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/searchList";
		}
		
		//return ret_id;

	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/getDate.do","/RETURNS/getDate.do"})
	public String getDate(@RequestParam("ret_date") String ret_date_str, ModelMap model) {
		
		List<String> errorMsgs=new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
		
		try{
			Date ret_date = null;
			if (ret_date_str.length() == 0) {
				errorMsgs.add("請輸入查詢日期");
			} else {
				ret_date = Date.valueOf(ret_date_str);
			}

			if(!errorMsgs.isEmpty()){
//				RequestDispatcher failureView = req.getRequestDispatcher("/returns/ReturnList.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/searchList";
			}	
			
			/***************************2.開始查詢資料*****************************************/
			
			//ReturnListService rtnListSvc = new ReturnListService();

			List<RtnListVO>	list = rtnListSvc.findByDate(ret_date);

			if(list.size()==0){
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/returns/ReturnList.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/searchList";
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			model.addAttribute("list", list);
//			String url = "/returns/listOneList.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
			return "/RETURNS/listOneList";
		}catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/ReturnList.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/searchList";
		}

//		return ret_date;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/getComName.do","/RETURNS/getComName.do"})
	public String getComName(@RequestParam("com_name") String com_name, ModelMap model) {
		
		List<String> errorMsgs=new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
		
		try{
			//String com_name=req.getParameter("com_name");
			if(com_name ==null || (com_name.trim()).length()==0){
				errorMsgs.add("請輸入廠商名稱");
			}
			if(!errorMsgs.isEmpty()){
//				RequestDispatcher failureView = req.getRequestDispatcher("/returns/ReturnList.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/searchList";
			}		
			
			/***************************2.開始查詢資料*****************************************/
			
			ReturnListService rtnListSvc = new ReturnListService();
			List<RtnListVO> list=rtnListSvc.findByCom(com_name);
			if(list.size()==0){
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/returns/ReturnList.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/searchList";
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			model.addAttribute("list", list);
//			String url = "/returns/listOneList.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
			return "/RETURNS/listOneList";
		}catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/ReturnList.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/searchList";
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/getDetail.do","/RETURNS/getDetail.do"})
	public String getDetail(@RequestParam("ret_id") String ret_id, ModelMap model) {
		
		List<String> errorMsgs=new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
//		
//		try{
			//String ret_id=req.getParameter("ret_id");

			/***************************2.開始查詢資料*****************************************/
//			ReturnDetailService rtnDetailSvc = new ReturnDetailService();				
			List<RtnDetailVO> detailList=rtnDetailSvc.getFindById(ret_id);
			model.addAttribute("detailList", detailList);
			
			ReturnListService rtnListSvc = new ReturnListService();
			RtnListVO rtnListVO = rtnListSvc.findById(ret_id);
			LinkedList<RtnListVO> list = new LinkedList<RtnListVO>();
			list.add(rtnListVO);
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			model.addAttribute("list", list);
//			String url = "/returns/listOneDetail.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
			return "/RETURNS/listOneDetail";
//		}catch (Exception e) {
//			errorMsgs.add("無法取得資料:" + e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/ReturnList.jsp");
//			failureView.forward(req, res);
//			return "/returns/ReturnList";
//		}

//		return ret_id;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/getOne_For_Update.do","/RETURNS/getOne_For_Update.do"})
	public String getOne_For_Update(@RequestParam("ret_id") String ret_id, ModelMap model) {
		
		List<String> errorMsgs = new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
		
		try{
//			String ret_id = new String(req.getParameter("ret_id"));
			
			/***************************2.開始查詢資料****************************************/
//			ReturnListService rtnListSvc = new ReturnListService();
			RtnListVO rtnListVO = rtnListSvc.getOneRtnList(ret_id);
			
			model.addAttribute("RtnListVO", rtnListVO);
//			String url ="/returns/update_Return_List.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			return "/RETURNS/update_Return_List";
		}catch(Exception e){
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/ReturnList.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/ReturnList";
		}

//		return ret_id;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/update.do","/RETURNS/update.do"})
	public String update(@RequestParam("ret_id") String ret_id, ModelMap model,HttpServletRequest req) {
		
		List<String> errorMsgs = new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
		
		try{
//			String ret_id = new String(req.getParameter("ret_id").trim());
							
			Date ret_date = null;
			try {
				ret_date = java.sql.Date.valueOf(req.getParameter("ret_date").trim());
			} catch (IllegalArgumentException e) {
				ret_date=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			
			String com_id = req.getParameter("com_id");
			if (com_id == null || com_id.trim().length() == 0) {
				errorMsgs.add("廠商代號請勿空白");
			}
			String comidCK = "^[C][0-9]{5}$";
			if(!com_id.trim().matches(comidCK) ) { 
				errorMsgs.add("廠商代號格式:C00001");
            }
			
			String com_name = req.getParameter("com_name");
			if (com_name == null || com_name.trim().length() == 0) {
				errorMsgs.add("廠商名稱請勿空白");
			}
			
			String key_id = req.getParameter("key_id");
			if (key_id == null || key_id.trim().length() == 0) {
				errorMsgs.add("修改人員請勿空白");
			}
			String keyidCK = "^[E][0-9]{5}$";
			if(!key_id.trim().matches(keyidCK) ) { 
				errorMsgs.add("修改人員格式:E00001");
            }
			
			
			Date key_date = null;
			try {
				key_date = java.sql.Date.valueOf(req.getParameter("key_date").trim());
			} catch (IllegalArgumentException e) {
				key_date=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			
			String remark = req.getParameter("remark");
			
			String status = req.getParameter("status");
			if (status == null || status.trim().length() == 0) {
				errorMsgs.add("狀態請勿空白");
			}
			String statusCK = "^[N,Y]{1}$";
			if(!status.trim().matches(statusCK) ) { 
				errorMsgs.add("狀態格式:N or Y");
            }

			RtnListVO rtnListVO = new RtnListVO();
			rtnListVO.setRet_id(ret_id);
			rtnListVO.setRet_date(ret_date);
			rtnListVO.setCom_id(com_id);
			rtnListVO.setCom_name(com_name);
			rtnListVO.setKey_id(key_id);;
			rtnListVO.setKey_date(key_date);
			rtnListVO.setRemark(remark);
			rtnListVO.setStatus(status);
			
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("RtnListVO", rtnListVO); 
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/returns/update_Return_List.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/update_Return_List"; 
			}
			/***************************2.開始修改資料*****************************************/
//			ReturnListService rtnListSvc = new ReturnListService();
			rtnListVO = rtnListSvc.updateRtnList(ret_id, ret_date, com_id, com_name, key_id, key_date, remark, status);
			List<RtnListVO> list = new LinkedList<RtnListVO>();
			list.add(rtnListVO);
			/***************************3.修改完成,準備轉交(Send the Success view)***********/
			req.setAttribute("list", list);
//			String url = "/returns/listOneList.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
			return "/RETURNS/listOneList";
		}catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/update_Return_List.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/update_Return_List";
		}
		
		
//		return ret_id;
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/insert.do","/RETURNS/insert.do"})
	public String insert(ModelMap model,HttpServletRequest req) {
System.out.println("-2");			
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String ret_id = req.getParameter("ret_id");
			if (ret_id == null || ret_id.trim().length() == 0) {
				errorMsgs.add("退貨單編號: 請勿空白");
			}

			Date ret_date = null;
			try {
				ret_date = java.sql.Date.valueOf(req.getParameter("ret_date").trim());
			} catch (IllegalArgumentException e) {
				ret_date=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			
		
			
			String com_id = req.getParameter("com_id");
			if (com_id == null || com_id.trim().length() == 0) {
				errorMsgs.add("廠商代號請勿空白");
			}
			String comidCK = "^[C][0-9]{5}$";
			if(!com_id.trim().matches(comidCK) ) { 
				errorMsgs.add("廠商代號格式:C00001");
            }
			
			String com_name = req.getParameter("com_name");
			if (com_name == null || com_name.trim().length() == 0) {
				errorMsgs.add("廠商名稱請勿空白");
			}
			
			String key_id = req.getParameter("key_id");
			if (key_id == null || key_id.trim().length() == 0) {
				errorMsgs.add("修改人員請勿空白");
			}
			String keyidCK = "^[E][0-9]{5}$";
			if(!key_id.trim().matches(keyidCK) ) { 
				errorMsgs.add("修改人員格式:E00001");
            }
			
			
			Date key_date = null;
			try {
				key_date = java.sql.Date.valueOf(req.getParameter("key_date").trim());
			} catch (IllegalArgumentException e) {
				key_date=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			
			String remark = req.getParameter("remark");
			
			String status = req.getParameter("status");
			if (status == null || status.trim().length() == 0) {
				errorMsgs.add("狀態請勿空白");
			}
			String statusCK = "^[N,Y]{1}$";
			if(!status.trim().matches(statusCK) ) { 
				errorMsgs.add("狀態格式:N or Y");
            }
System.out.println("-1");
			RtnListVO rtnListVO = new RtnListVO();
			//rtnListVO.setRet_id(ret_id);
			rtnListVO.setRet_date(ret_date);
			rtnListVO.setCom_id(com_id);
			rtnListVO.setCom_name(com_name);
			rtnListVO.setKey_id(key_id);;
			rtnListVO.setKey_date(key_date);
			rtnListVO.setRemark(remark);
			rtnListVO.setStatus(status);
			
System.out.println("0");			
			List<RtnDetailVO> list =new LinkedList<RtnDetailVO>();
			Set<RtnDetailVO> setDetail =new LinkedHashSet<RtnDetailVO>();
			Integer i=1;
			
			while(true){

				String x=i.toString();
				try{
				RtnItemsVO rtnItemsVO =new RtnItemsVO();
				RtnDetailVO rtnDetailVO=new RtnDetailVO();
//				rtnDetailVO.setRet_id(req.getParameter("ret_id"+x));
//				rtnDetailVO.setProd_name(req.getParameter("prod_name"+x));
				rtnDetailVO.setRtnListVO(rtnListVO);
				rtnItemsVO.setProd_name(req.getParameter("prod_name"+x));
				rtnDetailVO.setRtnItemsVO(rtnItemsVO);
				rtnDetailVO.setProd_quantity(Integer.parseInt(req.getParameter("prod_quantity"+x)));
				rtnDetailVO.setRet_reason(req.getParameter("ret_reason"+x));
				rtnDetailVO.setRtnListVO(rtnListVO);
				
				setDetail.add(rtnDetailVO);
				i++;
System.out.println("1");				
				}catch(Exception e){
					if(i<100){
						i++;
						continue;
						}else
					break;
				}
			}
//			
			

			/***************************2.開始新增資料***************************************/
			rtnListVO.setRtnDetail(setDetail);
			//ReturnListService rtnListSvc = new ReturnListService();
			System.out.println("-----insert begain-----");
			rtnListSvc.addRtnList(rtnListVO, list);
			System.out.println("-----insert end & select begain -----");
//			List<RtnListVO> all = rtnListSvc.getAll();
			System.out.println("-----select end-----");

System.out.println("2");		
//			req.getSession().setAttribute("list", all);
//			req.setAttribute("list", list);
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
//			String url = "/returns/ReturnList.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);	
System.out.println("3");
			return "redirect:/RETURNS/AllList.jsp";
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/addList.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/addList";
		}
		
//		return ret_id;				
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/delete.do","/RETURNS/delete.do"})
	public String delete(@RequestParam("ret_id") String ret_id, ModelMap model,HttpServletRequest req) {
		
		List<String> errorMsgs = new LinkedList<String>();
		
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數**************************************/
			//String ret_id = new String(req.getParameter("ret_id"));
			
			/****************************2.開始刪除資料******************************************/
			//ReturnListService rtnListSvc = new ReturnListService();
			rtnListSvc.delete(ret_id);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//			String url = "/returns/ReturnList.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			return "/RETURNS/AllList";
			/**************************其他可能的錯誤處理***************************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/ReturnList.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/ReturnList";
		}
		//return ret_id;
	}
		
	@RequestMapping(method = RequestMethod.GET, value = { "/getProd_DDL_rtn.do","/RETURNS/getProd_DDL_rtn.do" })
	public void getProd_DDL_rtn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF8");
		PrintWriter out = response.getWriter();
		/************************
		 * 1.接收請求參數 - 輸入格式的錯誤處理
		 *************************/
//		String prod_id = request.getParameter("prod_id");
		/*************************** 2.開始查詢資料 *****************************************/
		// OrderService ordSvc = new OrderService();
		List<RtnItemsVO> list = null;
		List<Map> prodlist = new LinkedList();
		try {
			

			ReturnItemsService rtnItmSvc = new ReturnItemsService();
			

			list = rtnItmSvc.getAll();
			
			for(RtnItemsVO rtnVO : list){
				Map map = new HashMap();
				map.put("SelectValue",rtnVO.getProd_name()+"^"+rtnVO.getRe_quantity()+"^"+rtnVO.getRemark());
				map.put("SelectText", rtnVO.getProd_name());
				prodlist.add(map);
			}

			
			String jsonString = JSONValue.toJSONString(prodlist);
			out.println(jsonString);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/getCom_DDL_rtn.do","/RETURNS/getCom_DDL_rtn.do" })
	public void getCom_DDL_rtn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF8");
		PrintWriter out = response.getWriter();
		/************************
		 * 1.接收請求參數 - 輸入格式的錯誤處理
		 *************************/
//		String prod_id = request.getParameter("prod_id");
		/*************************** 2.開始查詢資料 *****************************************/
		List<ComVO> list = null;
		List<Map> prodlist = new LinkedList();
		try {
			
			ComService comSvc = new ComService();
			
			list = comSvc.getAll();
			
			for(ComVO comVO : list){
				Map map = new HashMap();
				map.put("SelectValue",comVO.getCom_id()+"^" + comVO.getCom_name());
				map.put("SelectText", comVO.getCom_name());
				prodlist.add(map);
			}
			
			String jsonString = JSONValue.toJSONString(prodlist);
			out.println(jsonString);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
