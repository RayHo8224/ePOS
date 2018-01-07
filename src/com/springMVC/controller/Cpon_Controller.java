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

import com.coupon.model.CouponService;
import com.coupon.model.CouponVO;

@Controller
public class Cpon_Controller  {
	private static final CouponService copSvc = new CouponService();

//查詢全部		
	@RequestMapping(method = RequestMethod.POST, value = "/COUPON/allCpon.do")
	public String getAllCom(ModelMap model) {	
	/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		List<CouponVO> list = copSvc.getAll();
			
	/*************************** 2.永續層存取 ***************************************/	
				model.addAttribute("list", list);	
				
	/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				return "/COUPON/listAllCpon";			
		}
		

//查詢日期
			@RequestMapping(method = RequestMethod.POST,value = "/COUPON/datesCpon.do")
			public String getDates(ModelMap model,HttpServletRequest req) throws Exception, Exception {
				
				/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/


			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				Date release_date = null;
				if (req.getParameter("release_date").length() == 0) {
					errorMsgs.add("折價券發放日期: 請勿空白");
				} else {
					release_date = Date.valueOf(req.getParameter("release_date"));
				}

				Date cpon_period = null;
				if (req.getParameter("cpon_period").length() == 0) {
					errorMsgs.add("折價券使用期限: 請勿空白");
				} else {
					cpon_period = Date.valueOf(req.getParameter("cpon_period"));
				}

				/*************************** 2.永續層存取 ***************************************/
					
				List<CouponVO> list = copSvc.getDates(release_date,cpon_period);
				
				model.addAttribute("list", list);

				if (list.size()==0) {
					errorMsgs.add("資料不存在");
				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/coupon/coupon.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				return "/COUPON/listAllCpon";
			}
// 查詢金額
	
			@RequestMapping(method = RequestMethod.POST, value = "/COUPON/dollarCpon.do")
			public String getDollar(ModelMap model,@RequestParam("cpon_dollar")int cpon_dollar) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/							
			

			List<String> errorMsgs = new LinkedList<String>();
			model.addAttribute("errorMsgs", errorMsgs);

			/*************************** 2.永續層存取 ***************************************/
			
				List<CouponVO> list = copSvc.getDollars(cpon_dollar);
				model.addAttribute("list", list);

				if (list == null) {
					errorMsgs.add("資料不存在");
				}

				if (!errorMsgs.isEmpty()) {
					model.addAttribute("message", errorMsgs);
					return "redirect:coupon.jsp";	
				}
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				return "/COUPON/listAllCpon";
			}
	

// 選擇折價券名稱
			@RequestMapping(method = RequestMethod.POST, value = "/COUPON/namesCpon.do")
			public String getNames(ModelMap model,@RequestParam("cpon_name")String cpon_name) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/							
			List<String> errorMsgs = new LinkedList<String>();
					
		/*************************** 2.永續層存取 ***************************************/
			
				List<CouponVO> list = copSvc.getNames(cpon_name);

				if (list == null) {
					errorMsgs.add("資料不存在");
				}
				if (!errorMsgs.isEmpty()) {
					model.addAttribute("message", errorMsgs);
					return "redirect:coupon.jsp";
				}
				model.addAttribute("list", list);
		
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				return "/COUPON/listAllCpon";
			}
// 輸入折價券編號
			
			
	@RequestMapping(method = RequestMethod.POST, value = "/COUPON/coupon.do")
	public String getOneCpon(@RequestParam("cpon_id") String cpon_id, ModelMap model) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			List<String> errorMsgs = new LinkedList<String>();
			
				if (cpon_id == null || (cpon_id.trim()).length() == 0) {
					errorMsgs.add("折價券編號: 請勿空白");
				}

				if (!errorMsgs.isEmpty()) {
					model.addAttribute("message", errorMsgs);			
					return "redirect:coupon.jsp";
				}
	/*************************** 2.永續層存取 ***************************************/
				
				CouponVO copVO = copSvc.getOneCpon(cpon_id);
				List<CouponVO> list = new ArrayList<CouponVO>();
				list.add(copVO);
				
				if (copVO == null) {
					errorMsgs.add("資料不存在");
					return "redirect:coupon.jsp";
				}

				if (!errorMsgs.isEmpty()) {
					model.addAttribute("message", errorMsgs);
					return "redirect:coupon.jsp";
				}

	/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				
				model.addAttribute("list", list);				
				return "/COUPON/listAllCpon";				
		}

//新增資料		
			@RequestMapping(method = RequestMethod.POST,value = "/COUPON/insertCpon.do")
			public String insertCpon(ModelMap model,HttpServletRequest req) throws Exception, Exception {
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
	

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String cpon_name = req.getParameter("cpon_name").trim();
			if (cpon_name == null || cpon_name.length() == 0) {
				errorMsgs.add("折價券名稱: 請勿空白");
			}
			String cpon_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
			if (!cpon_name.matches(cpon_nameReg)) {
				errorMsgs.add("折價券名稱:只能是中、英文字母、數字和_ , 且長度必須在1到20之間");
			}

			Date release_date = null;
			if (req.getParameter("release_date").length() == 0) {
				errorMsgs.add("折價券發放日期: 請勿空白");
			} else {
				release_date = Date.valueOf(req.getParameter("release_date"));
			}

			Date cpon_period = null;
			if (req.getParameter("cpon_period").length() == 0) {
				errorMsgs.add("折價券使用期限: 請勿空白");
			} else {
				cpon_period = Date.valueOf(req.getParameter("cpon_period"));
			}

			Integer dollar = null;
			try {
				dollar = new Integer(req.getParameter("cpon_dollar").trim());
			} catch (NumberFormatException e) {
				dollar = 100;
				errorMsgs.add("折價券金額請填數字");
			}

			if (!(dollar > 0 && dollar <= 10000)) {
				errorMsgs.add("折價券金額須為正整數且範圍必須在1~10000之間");
				dollar = 100;
			}
			int count =  new Integer(req.getParameter("cpon_count"));

			String status = req.getParameter("status");

			CouponVO copVO = new CouponVO();
			
			copVO.setCpon_name(cpon_name);
			copVO.setRelease_date(release_date);
			copVO.setCpon_period(cpon_period);
			copVO.setCpon_dollar(dollar);
			copVO.setStatus(status);

			if (!errorMsgs.isEmpty()) {
				model.addAttribute("copVO", copVO);
				return "redirect:addCpon.jsp";
			}
		
			/*************************** 2.永續層存取 ***************************************/
				for(int i=0;i<count;i++){
					copVO = copSvc.addCpon(cpon_name, release_date, cpon_period, dollar, status);					
				}	
				List<CouponVO> list = copSvc.getAll();
				req.getSession().setAttribute("list", list);
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

				return "redirect:/COUPON/listAllCpon.jsp";


		}
			
//按下修改 轉交到修改頁面
			@RequestMapping(method = RequestMethod.POST,value = "/COUPON/allForUpdateCpon.do")
			public String updatecpon(ModelMap model,HttpServletRequest req){
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
				String cpon_id = new String(req.getParameter("cpon_id"));
			/*************************** 2.永續層存取 ***************************************/
				
				CouponVO copVO = copSvc.getOneCpon(cpon_id);
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

				model.addAttribute("copVO",copVO);
				return "/COUPON/updateCpon";
			}

//送出修改
			@RequestMapping(method = RequestMethod.POST,value = "/COUPON/updateCpon.do")
			public String update(ModelMap model,HttpServletRequest req) throws Exception, Exception {
				/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);


				String cpon_id = req.getParameter("cpon_id").trim();
				String cpon_name = req.getParameter("cpon_name").trim();
				
				
				
				if (cpon_name == null || cpon_name.length() == 0) {
					errorMsgs.add("折價券名稱: 請勿空白");
				}
				String cpon_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (!cpon_name.matches(cpon_nameReg)) {
					errorMsgs.add("折價券名稱:只能是中、英文字母、數字和_ , 且長度必須在1到20之間");
				}

				Date release_date = null;
				if (req.getParameter("release_date").length() == 0) {
					errorMsgs.add("折價券發放日期: 請勿空白");
				} else {
					release_date = Date.valueOf(req.getParameter("release_date"));
				}

				Date cpon_period = null;
				if (req.getParameter("cpon_period").length() == 0) {
					errorMsgs.add("折價券使用期限: 請勿空白");
				} else {
					cpon_period = Date.valueOf(req.getParameter("cpon_period"));
				}

				Integer dollar = null;
				try {
					dollar = new Integer(req.getParameter("cpon_dollar").trim());
				} catch (NumberFormatException e) {
					dollar = 100;
					errorMsgs.add("折價券金額請填數字");
				}

				if (!(dollar > 0 && dollar <= 10000)) {
					errorMsgs.add("折價券金額須為正整數且範圍必須在1~10000之間");
					dollar = 100;
				}

				String status = req.getParameter("status");

				CouponVO copVO = new CouponVO();
				copVO.setCpon_id(cpon_id);
				copVO.setCpon_name(cpon_name);
				copVO.setRelease_date(release_date);
				copVO.setCpon_period(cpon_period);
				copVO.setCpon_dollar(dollar);
				copVO.setStatus(status);
				
				if (!errorMsgs.isEmpty()) {
					model.addAttribute("copVO", copVO);
					return "redirect:/COUPON/listAllCpon.jsp";
				}

				
				/*************************** 2.永續層存取 ***************************************/
					
				copVO = copSvc.updCpon(cpon_id, cpon_name, release_date, cpon_period, dollar, status);
				List<CouponVO> list = new ArrayList<CouponVO>();
				list.add(copVO);
				
				/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				
				req.getSession().setAttribute("list", list);
				return "redirect:/COUPON/listAllCpon.jsp";
		
		}


//刪除	
			@RequestMapping(method = RequestMethod.POST, value = "/COUPON/deleteCpon.do")
			public String delete(@RequestParam("cpon_id") String cpon_id, ModelMap model,HttpServletRequest req) throws Exception, Exception {
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				
			/*************************** 2.永續層存取 ***************************************/
				
				copSvc.delCpon(cpon_id);
				List<CouponVO> list = copSvc.getAll();
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				
				req.getSession().setAttribute("list", list);
				return "redirect:/COUPON/listAllCpon.jsp";			
		}


}
