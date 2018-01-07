package com.springMVC.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.returns.model.ReturnDetailService;
import com.returns.model.ReturnItemsService;
import com.returns.model.ReturnListService;
import com.returns.model.RtnDetailVO;
import com.returns.model.RtnItemsVO;
import com.returns.model.RtnListVO;

@Controller
public class RtnDetail_Controller extends HttpServlet {

	private final static ReturnDetailService rtnDetailSvc = new ReturnDetailService();
	private final static ReturnItemsService rtnItemsSvc = new ReturnItemsService();
	private final static ReturnListService rtnListSvc = new ReturnListService();

	@RequestMapping(method = RequestMethod.POST, value = { "/getName.do", "/RETURNS/getName.do" })
	public String getName(@RequestParam("prod_name") String prod_name, ModelMap model) {

		List<String> errorMsgs = new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);

		try {
			// String prod_name=req.getParameter("prod_name");
			if (prod_name == null || (prod_name.trim()).length() == 0) {
				errorMsgs.add("請輸入產品名稱");
			}

			if (!errorMsgs.isEmpty()) {
				// RequestDispatcher failureView =
				// req.getRequestDispatcher("/returns/Return_Detail.jsp");
				// failureView.forward(req, res);
				return "/RETURNS/searchdetail";
			}

			// String prod_name=null;
			try {
				prod_name = new String(prod_name);
			} catch (Exception e) {
				errorMsgs.add("產品名稱格式錯誤");
			}

			if (!errorMsgs.isEmpty()) {
				// RequestDispatcher failureView =
				// req.getRequestDispatcher("/returns/Return_Detail.jsp");
				// failureView.forward(req, res);
				return "/RETURNS/searchdetail";
			}

			/*************************** 2.開始查詢資料 *****************************************/

			// ReturnDetailService rtnDetailSvc = new ReturnDetailService();
			List<RtnDetailVO> detailList = rtnDetailSvc.getFindByName(prod_name);
			model.addAttribute("detailList", detailList);

			// ReturnItemsService rtnItemsSvc = new ReturnItemsService();
			RtnItemsVO rtnItemsVO = rtnItemsSvc.byName(prod_name);
			LinkedList<RtnItemsVO> list = new LinkedList<RtnItemsVO>();
			list.add(rtnItemsVO);

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			model.addAttribute("list", list);
			// String url = "/returns/listOneDetail.jsp";
			// RequestDispatcher successView = req.getRequestDispatcher(url);
			// successView.forward(req, res);
			return "/RETURNS/listOneDetail";
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/returns/Return_Detail.jsp");
			// failureView.forward(req, res);
			return "/RETURNS/searchdetail";
		}
		// return prod_name;
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/getId.do", "/RETURNS/getId.do" })
	public String getId(@RequestParam("ret_id") String str, ModelMap model) {

		List<String> errorMsgs = new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);

		try {
			// String str=req.getParameter("ret_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入退貨單編號");
			}

			if (!errorMsgs.isEmpty()) {
				// RequestDispatcher failureView =
				// req.getRequestDispatcher("/returns/Return_Detail.jsp");
				// failureView.forward(req, res);
				return "/RETURNS/searchdetail";
			}

			String ret_id = null;
			try {
				ret_id = new String(str);
			} catch (Exception e) {
				errorMsgs.add("退貨單編號格式錯誤");
			}

			if (!errorMsgs.isEmpty()) {
				// RequestDispatcher failureView =
				// req.getRequestDispatcher("/returns/Return_Detail.jsp");
				// failureView.forward(req, res);
				return "/RETURNS/searchdetail";
			}

			/*************************** 2.開始查詢資料 *****************************************/

			// ReturnDetailService rtnDetailSvc = new ReturnDetailService();
			List<RtnDetailVO> detailList = rtnDetailSvc.getFindById(ret_id);
			model.addAttribute("detailList", detailList);

			// ReturnListService rtnListSvc = new ReturnListService();
			RtnListVO rtnListVO = rtnListSvc.findById(ret_id);
			LinkedList<RtnListVO> list = new LinkedList<RtnListVO>();
			list.add(rtnListVO);

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			model.addAttribute("list", list);
			// String url = "/returns/listOneDetail.jsp";
			// RequestDispatcher successView = req.getRequestDispatcher(url);
			// successView.forward(req, res);
			return "/RETURNS/listOneDetail";
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/returns/Return_Detail.jsp");
			// failureView.forward(req, res);
			return "/RETURNS/searchdetail";
		}

		// return getId;
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/getOne_For_Update_dtl.do","/RETURNS/getOne_For_Update_dtl.do" })
	public String getOne_For_Update_dtl(ModelMap model,
			@RequestParam("ret_id") String ret_id,
			@RequestParam("prod_name") String prod_name) {

		System.out.println("===============================================");
		List<String> errorMsgs = new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);

		try {
			// ReturnDetailService rtnDtlSvc = new ReturnDetailService();
			RtnDetailVO rtnDetailVO = rtnDetailSvc.getOneRtnDetail(ret_id, prod_name);

			System.out.println("------------------" + rtnDetailVO.getRet_reason());

			model.addAttribute("list", rtnDetailVO);
			// String url ="/returns/update_Return_Detail.jsp";
			// RequestDispatcher successView = req.getRequestDispatcher(url);
			// successView.forward(req, res);
			return "/RETURNS/update_Return_Detail";
		} catch (Exception e) {

			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/returns/Return_Detail.jsp");
			// failureView.forward(req, res);
			return "/RETURNS/Return_Detail";
		}

		// return ret_id;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = { "/update_dtl.do", "/RETURNS/update_dtl.do" })
	public String update_dtl(ModelMap model,HttpServletRequest req) {
		
		List<String> errorMsgs = new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
		
		try{
			String ret_id = req.getParameter("ret_id");
			if (ret_id == null || ret_id.trim().length() == 0) {
				errorMsgs.add("退貨單編號請勿空白 & 請勿更改退貨單編號");
			}
			System.out.println("ret_id = " + ret_id);
			String prod_name = new String(req.getParameter("prod_name").trim());
			System.out.println("prod_name = " + prod_name);
			Integer prod_quantity = null;
			try{
				prod_quantity = new Integer(req.getParameter("prod_quantity").trim());
			}catch(NumberFormatException e){
				prod_quantity = 0;
				errorMsgs.add("請填入退貨品數量");
			}
			System.out.println("prod_quantity = " + prod_quantity);
			String ret_reason =new String(req.getParameter("ret_reason").trim());
			System.out.println("ret_reason = " + ret_reason);
			RtnDetailVO rtnDetailVO = new RtnDetailVO();
			RtnItemsVO rtnItemsVO =new RtnItemsVO();
			RtnListVO rtnListVO = new RtnListVO();
			
			rtnListVO.setRet_id(ret_id);
			rtnItemsVO.setProd_name(prod_name);
			rtnDetailVO.setProd_quantity(prod_quantity);
			rtnDetailVO.setRet_reason(ret_reason);
			
			
			if (!errorMsgs.isEmpty()) {
				model.addAttribute("RtnDetailVO", rtnDetailVO); 
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/returns/update_Return_Detail.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/update_Return_Detail"; 
			}
			/***************************2.開始修改資料*****************************************/
//			ReturnDetailService rtnDtlSvc = new ReturnDetailService();
			rtnDetailVO = rtnDetailSvc.updateDetailVO(ret_id, prod_name, ret_reason, prod_quantity);
			List<RtnDetailVO> list = new LinkedList<RtnDetailVO>();
			list.add(rtnDetailVO);
			/***************************3.修改完成,準備轉交(Send the Success view)***********/
			model.addAttribute("list", list);
//			String url = "/returns/showUpdateDetail.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
			return "/RETURNS/showUpdateDetail";
		}catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/update_Return_Detail.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/update_Return_Detail";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/insert_dtl.do", "/RETURNS/insert_dtl.do" })
	public String insert_dtl(ModelMap model,HttpServletRequest req) {
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String ret_id = req.getParameter("ret_id");
			if (ret_id == null || ret_id.trim().length() == 0) {
				errorMsgs.add("退貨單編號請勿空白");
			}
			
			String prod_name = new String(req.getParameter("prod_name").trim());
			
			Integer prod_quantity = null;
			try{
				prod_quantity = new Integer(req.getParameter("prod_quantity").trim());
			}catch(NumberFormatException e){
				prod_quantity = 0;
				errorMsgs.add("請填入退貨品數量");
			}
//			
			String ret_reason = req.getParameter("ret_reason");
			
			RtnDetailVO rtnDetailVO = new RtnDetailVO();
			RtnItemsVO rtnItemsVO =new RtnItemsVO();
			RtnListVO rtnListVO = new RtnListVO();
			
			rtnListVO.setRet_id(ret_id);
			rtnItemsVO.setProd_name(prod_name);
			rtnDetailVO.setProd_quantity(prod_quantity);
			rtnDetailVO.setRet_reason(ret_reason);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("RtnDetailVO", rtnDetailVO); 
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/returns/addDetail.jsp");
//				failureView.forward(req, res);
				return "/RETURNS/addDetail";
			}
			
			/***************************2.開始新增資料***************************************/
//			ReturnDetailService rtnDtlSvc = new ReturnDetailService();
			rtnDetailVO = rtnDetailSvc.addDetail(ret_id, prod_name, ret_reason, prod_quantity);
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
//			String url = "/returns/Return_Detail.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);				
			return "/RETURNS/Return_Detail";
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/addDetail.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/addDetail";
		}	
	}
	
	@RequestMapping(method = RequestMethod.POST, value = { "/delete_dtl.do","/RETURNS/delete_dtl.do" })
	public String delete_dtl(ModelMap model,HttpServletRequest req,
			@RequestParam("ret_id") String ret_id,
			@RequestParam("prod_name") String prod_name) {
				
		List<String> errorMsgs = new LinkedList<String>();
		
		model.addAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數**************************************/
//			String ret_id = new String(req.getParameter("ret_id"));
//			String prod_name = new String(req.getParameter("prod_name"));
			
			/****************************2.開始刪除資料******************************************/
//			ReturnDetailService rtnDtlSvc = new ReturnDetailService();
			rtnDetailSvc.delete(ret_id,prod_name);
			List<RtnDetailVO> detailList = rtnDetailSvc.getFindById(ret_id);
			
//			ReturnListService rtnListSvc = new ReturnListService();
			RtnListVO rtnListVO = rtnListSvc.findById(ret_id);
			List<RtnListVO> list = new LinkedList<RtnListVO>();
			list.add(rtnListVO);
			
			HttpSession session = req.getSession();
			session.setAttribute("detailList", detailList);
			session.setAttribute("list", list);
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//			String url = "/returns/Return_Detail.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			return "/RETURNS/Return_Detail";
			/**************************其他可能的錯誤處理***************************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/returns/Return_Detail.jsp");
//			failureView.forward(req, res);
			return "/RETURNS/Return_Detai";
		}
	}
	
}
