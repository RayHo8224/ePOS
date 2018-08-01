package com.springMVC.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
import com.product.model.ProdDAO;
import com.product.model.ProdService;
import com.product.model.ProdVO;
import com.returns.model.ReturnItemsService;
import com.returns.model.RtnItemsVO;

@Controller
public class RtnItems_Controller extends HttpServlet {
	
	private final static ReturnItemsService rtnItemSvc = new ReturnItemsService();
	
	@RequestMapping(method = RequestMethod.POST, value = {"/getName_Item.do","/RETURNS/getName_Item.do"})
	public String getName_Item(@RequestParam("prod_name") String str, ModelMap model) {
		
		List<String> errorMsgs=new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
		
		try{
//			String str=req.getParameter("prod_name");
			if(str ==null || (str.trim()).length()==0){
				errorMsgs.add("請輸入產品名稱");
			}
			
			if(!errorMsgs.isEmpty()){
//				RequestDispatcher failureView = req.getRequestDispatcher("/returns/Return_Items.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/searchItem";
			}
			
			String prod_name=null;
			try{
				prod_name=new String(str);
			}catch(Exception e){
				errorMsgs.add("產品名稱格式錯誤");
			}
			
			if(!errorMsgs.isEmpty()){
//				RequestDispatcher failureView = req.getRequestDispatcher("/returns/Return_Items.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/searchItem";
			}		
			
			/***************************2.開始查詢資料*****************************************/
			
//			ReturnItemsService rtnItmSvc = new ReturnItemsService();
			List<RtnItemsVO> list = rtnItemSvc.findByName(prod_name);
			if(list.size()==0){
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/returns/Return_Items.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/searchItem";
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			model.addAttribute("list", list);
//			String url = "/returns/listOneItem.jsp";//returns/listOneItem.jsp
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
			return "/RETURNS/listOneItem";
		}catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/Return_Items.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/searchItem";
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/getComId.do","/RETURNS/getComId.do"})
	public String getComId(@RequestParam("com_id") String str, ModelMap model) {
		
		List<String> errorMsgs=new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
		
		try{
//			String str=req.getParameter("com_id");
			if(str ==null || (str.trim()).length()==0){
				errorMsgs.add("請輸入廠商代號");
			}
			
			if(!errorMsgs.isEmpty()){
//				RequestDispatcher failureView = req.getRequestDispatcher("/returns/Return_Items.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/searchItem";
			}
			
			String com_id=null;
			try{
				com_id=new String(str);
			}catch(Exception e){
				errorMsgs.add("廠商代號格式錯誤");
			}
			
			if(!errorMsgs.isEmpty()){
//				RequestDispatcher failureView = req.getRequestDispatcher("/returns/Return_Items.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/Return_Items";
			}		
			
			/***************************2.開始查詢資料*****************************************/
			
//			ReturnItemsService rtnItmSvc = new ReturnItemsService();
			List<RtnItemsVO> list = rtnItemSvc.findById(com_id);
			if(list.size()==0){
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/returns/Return_Items.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/Return_Items";
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			model.addAttribute("list", list);
//			String url = "/returns/listOneItem.jsp";//returns/listOneItem.jsp
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
			return "/RETURNS/listOneItem";
		}catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/Return_Items.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/searchItem";
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/getOne_For_Update_Item.do","/RETURNS/getOne_For_Update_Item.do"})
	public String getOne_For_Update_Item(@RequestParam("prod_name") String prod_name, ModelMap model) {
		
		List<String> errorMsgs = new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
		
		try{
//			String prod_name = new String(req.getParameter("prod_name"));
			
			/***************************2.開始查詢資料****************************************/
//			ReturnItemsService rtnItmSvc = new ReturnItemsService();
			RtnItemsVO rtnItemsVO = rtnItemSvc.getOneRtnItem(prod_name);
			
			model.addAttribute("RtnItemsVO", rtnItemsVO);
//			String url ="/returns/update_Return_Items.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			return "/RETURNS/update_Return_Items";
		}catch(Exception e){
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/Return_Items.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/Return_Items";
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/update_Item.do","/RETURNS/update_Item.do"})
	public String update_Item(ModelMap model,HttpServletRequest req) {
		
		List<String> errorMsgs = new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
		
		try{
			String prod_name = new String(req.getParameter("prod_name").trim());
			System.out.println("prod_name=" + prod_name);
			String com_id = req.getParameter("com_id");
			if (com_id == null || com_id.trim().length() == 0) {
				errorMsgs.add("廠商代號請勿空白");
			}
			System.out.println("com_id=" + com_id);
			String comidCK = "^[C][0-9]{5}$";
			if(!com_id.trim().matches(comidCK) ) { 
				errorMsgs.add("廠商代號格式:C00001");
            }
			
			Integer re_quantity = null;
			try{
				re_quantity = new Integer(req.getParameter("re_quantity").trim());
			}catch(NumberFormatException e){
				re_quantity = 0;
				errorMsgs.add("請填入退貨品數量");
			}
			
			String remark = req.getParameter("remark");
			
			RtnItemsVO rtnItemsVO = new RtnItemsVO();
			rtnItemsVO.setProd_name(prod_name);
			rtnItemsVO.setCom_id(com_id);
			rtnItemsVO.setRe_quantity(re_quantity);
			rtnItemsVO.setRemark(remark);
			
			
			if (!errorMsgs.isEmpty()) {
				model.addAttribute("RtnItemsVO", rtnItemsVO); 
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/returns/update_Return_Items.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/update_Return_Items"; 
			}
			/***************************2.開始修改資料*****************************************/
//			ReturnItemsService rtnItemSvc = new ReturnItemsService();
			rtnItemsVO = rtnItemSvc.updateRtnItem(prod_name, com_id, re_quantity,remark);
			List<RtnItemsVO> list = new LinkedList<RtnItemsVO>();
			list.add(rtnItemsVO);
			/***************************3.修改完成,準備轉交(Send the Success view)***********/
			model.addAttribute("list", list);
//			String url = "/returns/listOneItem.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
			return "/RETURNS/listOneItem";
		}catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/update_Return_Items.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/update_Return_Items";
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/insert_Item.do","/RETURNS/insert_Item.do"})
	public String insert_Item(ModelMap model,HttpServletRequest req) {
		
		List<String> errorMsgs = new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);

		try {
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String prod_name = req.getParameter("prod_name");
			if (prod_name == null || prod_name.trim().length() == 0) {
				errorMsgs.add("產品名稱: 請勿空白");
			}
			
			String com_id = req.getParameter("com_id");
			if (com_id == null || com_id.trim().length() == 0) {
				errorMsgs.add("廠商代號請勿空白");
			}
			String comidCK = "^[C][0-9]{5}$";
			if(!com_id.trim().matches(comidCK) ) { 
				errorMsgs.add("廠商代號格式:C00001");
            }
			
			Integer re_quantity = null;
			try{
				re_quantity  = new Integer(req.getParameter("re_quantity").trim());
			}catch(NumberFormatException e){
				re_quantity = 0;
				errorMsgs.add("請填入退貨品數量");
			}
			
			String remark = req.getParameter("remark");

			RtnItemsVO rtnItemsVO = new RtnItemsVO();
			rtnItemsVO.setProd_name(prod_name);
			rtnItemsVO.setCom_id(com_id);
			rtnItemsVO.setRe_quantity(re_quantity);
			rtnItemsVO.setRemark(remark);

			if (!errorMsgs.isEmpty()) {
				model.addAttribute("RtnItemsVO", rtnItemsVO); 
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/returns/addItem.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/addItem";
			}
			
			ProdService prodsiv = new ProdService();

			ProdVO ProdVO = prodsiv.getByName(prod_name).get(0);
			String P_name = ProdVO.getProd_name();
			
			System.out.println("prod_name="+prod_name);
			System.out.println("P_name="+P_name);
			if(prod_name.equals(P_name)&&re_quantity>=0){
				int P_quantity = ProdVO.getProd_stock();
				ProdVO.setProd_stock(P_quantity+Integer.parseInt((req.getParameter("re_quantity"))));
				prodsiv.update(ProdVO);
			}
			
			
			/***************************2.開始新增資料***************************************/
//			ReturnItemsService rtnItemSvc = new ReturnItemsService();
			rtnItemSvc.addRtnItem(rtnItemsVO);
//			List list = rtnItemSvc.getAll();
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
//			String url = "/returns/Return_Items.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);			s	
			return "/RETURNS/Return_Items";
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/addItem.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/addItem";
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/delete_Item.do","/RETURNS/delete_Item.do"})
	public String delete_Item(@RequestParam("prod_name") String prod_name, ModelMap model) {
		
		List<String> errorMsgs = new LinkedList<String>();
		
		model.addAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數**************************************/
//			String prod_name = new String(req.getParameter("prod_name"));
			
			/****************************2.開始刪除資料******************************************/
//			ReturnItemsService rtnItemSvc = new ReturnItemsService();
			rtnItemSvc.delete(prod_name);

			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//			String url = "/returns/Return_Items.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			return "/RETURNS/Return_Items";
			/**************************其他可能的錯誤處理***************************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/Return_Items.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/Return_Items";
		}
	
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/getProd_DDL_itm.do","/RETURNS/getProd_DDL_itm.do" })
	public void getProd_DDL_itm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF8");
		PrintWriter out = response.getWriter();
		/************************
		 * 1.接收請求參數 - 輸入格式的錯誤處理
		 *************************/
		String prod_id = request.getParameter("prod_id");
		/*************************** 2.開始查詢資料 *****************************************/
		// OrderService ordSvc = new OrderService();
		List<ProdVO> list = null;
		List<Map> prodlist = new LinkedList();
		try {
			
			ProdService prodSvc = new ProdService();
			list = prodSvc.getAll();
			
			for(ProdVO prodVO : list){
				Map map = new HashMap();
				map.put("SelectValue",prodVO.getProd_name());
				map.put("SelectText", prodVO.getProd_name());
				prodlist.add(map);
			}
			
			String jsonString = JSONValue.toJSONString(prodlist);
			out.println(jsonString);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/getCom_DDL_com.do","/RETURNS/getCom_DDL_com.do" })
	public void getCom_DDL_com(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF8");
		PrintWriter out = response.getWriter();
		/************************
		 * 1.接收請求參數 - 輸入格式的錯誤處理
		 *************************/
//		String prod_id = request.getParameter("prod_id");
		/*************************** 2.開始查詢資料 *****************************************/
		// OrderService ordSvc = new OrderService();
		List<ComVO> list = null;
		List<Map> prodlist = new LinkedList();
		try {
			
//			ProdService prodSvc = new  ProdService();
			ComService comSvc = new ComService();
			
//			list = prodSvc.getAll();
			list = comSvc.getAll();
			
			for(ComVO comVO : list){
				Map map = new HashMap();
//				map.put("SelectValue",comVO.getProd_name()+"^"+comVO.getRe_quantity());
				map.put("SelectValue",comVO.getCom_id());
				map.put("SelectText", comVO.getCom_id());
				prodlist.add(map);
			}
			
			String jsonString = JSONValue.toJSONString(prodlist);
			out.println(jsonString);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
