package com.springMVC.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.promoting.model.PromotingService;
import com.promoting.model.PromotingVO;

@Controller
public class Prom_Controller  {
	private static final PromotingService PromSvc = new PromotingService();



//新增資料																		
			@RequestMapping(method = RequestMethod.POST,value = "/PROMOTING/insertProm.do")
			public String insertCpon(ModelMap model,HttpServletRequest req) throws Exception, Exception {
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
	
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String pro_prod_id = req.getParameter("pro_prod_id").trim();
			
			String pro_prod_name = req.getParameter("pro_prod_name").trim();
			if (pro_prod_name == null || pro_prod_name.length() == 0) {
				errorMsgs.add("促銷商品名稱: 請勿空白");
				pro_prod_name = "請填入促銷商品名稱";
			}
			Date pro_begin = null;
			if(req.getParameter("pro_begin").length()==0){
				errorMsgs.add("促銷起始日: 請勿空白");
			}else{
				pro_begin = Date.valueOf(req.getParameter("pro_begin"));
			}

			Date pro_end = null;
			if(req.getParameter("pro_end").length()==0){
				errorMsgs.add("促銷結束日: 請勿空白");
			}else{
				pro_end = Date.valueOf(req.getParameter("pro_end"));
			}
			
			String pro_neirong = req.getParameter("pro_neirong").trim();	
			
			PromotingVO promVO = new PromotingVO();
			promVO.setPro_prod_id(pro_prod_id);
			promVO.setPro_prod_name(pro_prod_name);				
			promVO.setPro_begin(pro_begin);
			promVO.setPro_end(pro_end);				
			promVO.setPro_neirong(pro_neirong);					
			
			if (!errorMsgs.isEmpty()) {
				
				model.addAttribute("promVO", promVO);
				return "redirect:/PROMOTING/addProm.jsp";
				
			}
			/*************************** 2.永續層存取 ***************************************/
			
				promVO = PromSvc.addPro(pro_prod_id, pro_prod_name,pro_begin,pro_end,pro_neirong);
				List<PromotingVO> list = PromSvc.getAll();
				/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

				req.setAttribute("list", list);
				return "/PROMOTING/listAllProm";
		}
//按下修改 轉交到修改頁面
			@RequestMapping(method = RequestMethod.POST,value = "/PROMOTING/allForUpdateProm.do")
			public String updatecpon(ModelMap model,HttpServletRequest req){
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
				String pro_prod_id = new String(req.getParameter("pro_prod_id"));
				Date pro_begin = Date.valueOf(req.getParameter("pro_begin"));
			/*************************** 2.永續層存取 ***************************************/
				
				PromotingVO promVO = PromSvc.getOnePro(pro_prod_id, pro_begin);
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

				model.addAttribute("promVO",promVO);
				return "/PROMOTING/updateProm";
			}

		
//選擇促銷編號範圍
			@RequestMapping(method = RequestMethod.POST,value = "/PROMOTING/idsProm.do")
			public String getIds(ModelMap model,HttpServletRequest req) throws Exception, Exception {
				/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
			
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

					String pro_prod_id1 = req.getParameter("pro_prod_id1");
					String pro_prod_id2 = req.getParameter("pro_prod_id2");

					List<PromotingVO > list = PromSvc.getIds(pro_prod_id1, pro_prod_id2);
					req.setAttribute("list", list);

					if (list == null) {
						errorMsgs.add("資料不存在");
					}
				
					return "/PROMOTING/listAllProm";
		}		
				
//查詢日期
			@RequestMapping(method = RequestMethod.POST,value = "/PROMOTING/datesProm.do")
			public String getDates(ModelMap model,HttpServletRequest req) throws Exception, Exception {
				
				/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

					Date pro_begin = null;
					if (req.getParameter("pro_begin").length() == 0) {
						errorMsgs.add("起始日期: 請勿空白");
					} else {
						pro_begin = Date.valueOf(req.getParameter("pro_begin"));
					}
							
					Date pro_end = null;
					if (req.getParameter("pro_end").length() == 0) {
						errorMsgs.add("結束日期: 請勿空白");
					} else {
						pro_end = Date.valueOf(req.getParameter("pro_end"));
					}
							
//					if (!errorMsgs.isEmpty()) {
//						RequestDispatcher failureView = req.getRequestDispatcher("/promoting/promoting.jsp");
//						failureView.forward(req, res);
//						return;
					

					List<PromotingVO > list = PromSvc.getDates(pro_begin, pro_end);
					req.setAttribute("list", list);

					if (list == null) {
						errorMsgs.add("資料不存在");
					}
					return "/PROMOTING/listAllProm";
		}
		

			@RequestMapping(method = RequestMethod.POST, value = "/PROMOTING/namesProm.do")
			public String getNames(ModelMap model,@RequestParam("pro_prod_name")String pro_prod_name) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/							
			
				List<String> errorMsgs = new LinkedList<String>();
//				model.addAttribute("errorMsgs", errorMsgs);
//								
//				
//					String pro_prod_nameReg = "^[(a-zA-Z0-9)]{1,10}$";
//					if (!pro_prod_name.matches(pro_prod_nameReg)) {
//						errorMsgs.add("編號:只能是英文字母、數字 , 且長度必須在1到10之間");
//					}
									
					
				/*************************** 2.永續層存取 ***************************************/
						
					List<PromotingVO > list = PromSvc.getNames(pro_prod_name);
				/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
					
					model.addAttribute("list", list);
					return "/PROMOTING/listAllProm";
		}		
//查詢全部		
			@RequestMapping(method = RequestMethod.POST, value = "/PROMOTING/allProm.do")
			public String getAllProm(ModelMap model) {	
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				List<PromotingVO> list = PromSvc.getAll();
					
			/*************************** 2.永續層存取 ***************************************/	
						model.addAttribute("list", list);	
						
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
						return "/PROMOTING/listAllProm";			
				}		

			@RequestMapping(method = RequestMethod.POST,value = "/PROMOTING/updateProm.do")
			public String update(ModelMap model,HttpServletRequest req) throws Exception, Exception {
				/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			
				String pro_prod_id = req.getParameter("pro_prod_id").trim();	
				
				String pro_prod_name = req.getParameter("pro_prod_name").trim();
				if (pro_prod_name == null || pro_prod_name.length() == 0) {
					errorMsgs.add("促銷商品名稱: 請勿空白");
					pro_prod_name = "請填入促銷商品名稱";
				}
				
				Date pro_begin = Date.valueOf(req.getParameter("pro_begin"));
				
				Date pro_end = null;
				if(req.getParameter("pro_end").length()==0){
					errorMsgs.add("促銷結束日: 請勿空白");
				}else{
					pro_end = Date.valueOf(req.getParameter("pro_end"));
				}
				
				String pro_neirong = req.getParameter("pro_neirong").trim();				
				
				PromotingVO promVO = new PromotingVO();
				promVO.setPro_prod_id(pro_prod_id);
				promVO.setPro_prod_name(pro_prod_name);				
				promVO.setPro_begin(pro_begin);
				promVO.setPro_end(pro_end);				
				promVO.setPro_neirong(pro_neirong);					
				
				if (!errorMsgs.isEmpty()) {
					model.addAttribute("promVO", promVO);
					return "redirect:/PROMOTING/listAllProm.jsp";
					
				}
			/*************************** 2.永續層存取 ***************************************/
				
				promVO = PromSvc.updPro(pro_prod_id, pro_prod_name,pro_begin,pro_end,pro_neirong);
				List<PromotingVO> list = new ArrayList<PromotingVO>();
				list.add(promVO);
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
					
				req.getSession().setAttribute("list", list);
				return "redirect:/PROMOTING/listAllProm.jsp";
			
		}
//刪除	
			@RequestMapping(method = RequestMethod.POST, value = "/PROMOTING/deleteProm.do")
			public String delete(@RequestParam("pro_prod_id") String pro_prod_id,@RequestParam("pro_begin") Date pro_begin, ModelMap model,HttpServletRequest req) throws Exception, Exception {
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				
			/*************************** 2.永續層存取 ***************************************/
				
				PromSvc.delPro(pro_prod_id, pro_begin);
				List<PromotingVO> list = PromSvc.getAll();
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				
				req.getSession().setAttribute("list", list);
				return "redirect:/PROMOTING/listAllProm.jsp";			
		}


}
