package com.springMVC.controller;


import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.invo.model.InvoService;
import com.invo.model.InvoVO;


@Controller
public class Invo_Controller {
	private static final InvoService invoSvc = new InvoService();

	

//查詢單筆																	
			@RequestMapping(method = RequestMethod.POST, value = "/INVO/getOneinvo.do")
			public String getOneInvo(@RequestParam("invoice_id") String invoice_id, ModelMap model,HttpServletRequest req) {
				/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/		
				
			List<String> errorMsgs = new LinkedList<String>();
			model.addAttribute("errorMsgs", errorMsgs);
			
			if (invoice_id == null || invoice_id.length() == 0) {
				errorMsgs.add("發票編號: 請勿空白");
			}
			/*************************** 2.永續層存取 ***************************************/	
			
			InvoVO invoVO = invoSvc.getOneInvo(invoice_id);
			if (invoVO == null) {
				errorMsgs.add("資料不存在");
			return "/INVO/searchinvo";
			}
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			
			model.addAttribute("invoVO", invoVO);
			return "/INVO/listOneInvo";			

		}
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
//查詢全部		
			@RequestMapping(method = RequestMethod.POST, value = "/INVO/listAllInvo.do")
			public String getAllInvo(ModelMap model) {	
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			
			/*************************** 2.永續層存取 ***************************************/	
				
			List list = invoSvc.getAll();
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			
			model.addAttribute("list", list);
			return "/INVO/listAllInvo";	
			

		}
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
//按下修改 轉交到修改頁面				
			@RequestMapping(method = RequestMethod.POST,value = "/INVO/allForUpdate.do")
			public String invoUpdate(ModelMap model,HttpServletRequest req){
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		
			String invoice_id = new String(req.getParameter("invoice_id"));
			/*************************** 2.永續層存取 ***************************************/
			
			InvoVO invoVO = invoSvc.getOneInvo(invoice_id);

			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

			model.addAttribute("invoVO",invoVO);
			return "/INVO/update_invo_input";
			}

		
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
//送出修改
			@RequestMapping(method = RequestMethod.POST,value = "/INVO/updateInvo.do")
			public String updateInvo(ModelMap model,HttpServletRequest req) throws Exception, Exception {
				/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String invoice_id = new String(req.getParameter("invoice_id").trim());
			if (invoice_id == null || invoice_id.length() == 0) {
				errorMsgs.add("發票編號不可為空值");
			}	
			
			String ord_id = req.getParameter("ord_id");
			if (ord_id == null || ord_id.length() == 0) {
				errorMsgs.add("訂單編號不可為空值");
			}
			System.out.println("new_ord_id = " + ord_id);
			String new_invoice_number = req.getParameter("new_invoice_number");
			if (new_invoice_number == null || new_invoice_number.length() == 0) {
				errorMsgs.add("新發票編號不可為空值");
			}
			System.out.println("new_ord_id = " + new_invoice_number);
			String new_ord_id = req.getParameter("new_ord_id");
			if (new_ord_id == null || new_ord_id.length() == 0) {
				errorMsgs.add("新訂單編號不可為空值");
			}
			System.out.println("new_ord_id = " + new_ord_id);
			InvoVO invoVO = new InvoVO();
			invoVO.setInvoice_id(invoice_id);
			invoVO.setOrd_id(ord_id);
			invoVO.setNew_invoice_number(new_invoice_number);
			invoVO.setNew_ord_id(new_ord_id);
			
			if (!errorMsgs.isEmpty()) {
				model.addAttribute("invoVO", invoVO);
				return "redirect:/INVO/update_invo_input.jsp";
				
			}
		/*************************** 2.永續層存取 ***************************************/
			
			invoVO = invoSvc.updateInvo(invoice_id, ord_id, new_invoice_number, new_ord_id);
			
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

			model.addAttribute("invoVO", invoVO);
			return "/INVO/listOneInvo";
			
		}
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
//按下新增 到新增頁面
			@RequestMapping(method = RequestMethod.POST, value = "/INVO/addInvo.do")
			public String addInvo(ModelMap model) {
				return "/INVO/addInvo";
			}
			
		

		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
//新增資料																  
			@RequestMapping(method = RequestMethod.POST,value = "/INVO/insertInvo.do")
			public String insertCpon(ModelMap model,HttpServletRequest req) throws Exception, Exception {
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String invoice_id = req.getParameter("invoice_id");
			if (invoice_id == null || invoice_id.length() == 0) {
				errorMsgs.add("發票編號不可為空值");
			}
			String ord_id = req.getParameter("ord_id");
			if (ord_id == null || ord_id.length() == 0) {
				errorMsgs.add("訂單編號不可為空值");
			}
			String new_invoice_number = req.getParameter("new_invoice_number");
			if (new_invoice_number == null || new_invoice_number.length() == 0) {
				errorMsgs.add("新發票編號不可為空值");
			}
			String new_ord_id = req.getParameter("new_ord_id");
			if (new_ord_id == null || new_ord_id.length() == 0) {
				errorMsgs.add("新訂單編號不可為空值");
			}
		/*************************** 2.永續層存取 ***************************************/
				HttpSession session = req.getSession();
				session.removeAttribute("oldInvoice_id");
				session.removeAttribute("oldOrd_id");
				session.removeAttribute("newInvoice_id");
				session.removeAttribute("newOrd_id");
		
			InvoVO invoVO = invoSvc.addInvo(invoice_id, ord_id, new_invoice_number, new_ord_id);
			List<InvoVO> list = invoSvc.getAll();
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			
			session.setAttribute("list", list);
			return "redirect:/INVO/listAllInvo.jsp";

		}
	
		
		
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
//刪除			
			@RequestMapping(method = RequestMethod.POST, value = "/INVO/deleteInvo.do")
			public String delete(@RequestParam("invoice_id") String invoice_id, ModelMap model,HttpServletRequest req) throws Exception, Exception {
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		
			/*************************** 2.永續層存取 ***************************************/
				invoSvc.deleteInvo(invoice_id);				
				List<InvoVO> list=invoSvc.getAll();
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			
				req.getSession().setAttribute("list", list);
				return "redirect:/INVO/listAllInvo.jsp";
		
		}
			
}
