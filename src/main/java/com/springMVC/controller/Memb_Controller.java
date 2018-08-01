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
import com.coupon.model.CouponVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@Controller
public class Memb_Controller {
	private static final MemberService MemSvc = new MemberService();

//查詢單筆
		@RequestMapping(method = RequestMethod.POST, value = "/MEMBER/Memb.do")
		public String getOneMem(@RequestParam("mem_id") String mem_id, ModelMap model,HttpServletRequest req) {
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/		
			List<String> errorMsgs = new LinkedList<String>();
			model.addAttribute("errorMsgs", errorMsgs);
			

				
				if (mem_id == null || mem_id.length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				}
		
	/*************************** 2.永續層存取 ***************************************/
				
				MemberVO memVO = MemSvc.getOneMem(mem_id);
				List<MemberVO> list = new ArrayList<MemberVO>();
				list.add(memVO);
				
				if (memVO == null) {
					errorMsgs.add("資料不存在");
				return "/MEMBER/listAllMem";
				}
		
	/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				
				model.addAttribute("list", list);				
				return "/MEMBER/listAllMem";			
		}
		
//新增資料
			@RequestMapping(method = RequestMethod.POST,value = "/MEMBER/insertMemb.do")
			public String insertCpon(ModelMap model,HttpServletRequest req) throws Exception, Exception {
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
	
		
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);


			String mem_pwd = req.getParameter("mem_pwd").trim();
			if (mem_pwd == null || mem_pwd.length() == 0) {
				errorMsgs.add("密碼不可為空值");
			}
			
			String mem_pwdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9!@#$%^)]{1,20}$";
			if (!mem_pwd.matches(mem_pwdReg)) {
				errorMsgs.add("密碼:只能是中、英文字母、數字和!@#$%^ , 且長度必須在1到20之間");
			}
			
			String mem_name = req.getParameter("mem_name").trim();
			if (mem_name == null || mem_name.length() == 0) {
				errorMsgs.add("姓名不可為空值");
			}
			
			String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\s)]{1,20}$";
			if (!mem_name.matches(mem_nameReg)) {
				errorMsgs.add("姓名:只能是中、英文字母、數字和_ , 且長度必須在1到20之間");
			}
			
			String mem_idnum = req.getParameter("mem_idnum").trim();
			if (mem_idnum == null || mem_idnum.length() == 0) {
				errorMsgs.add("身分證ID不可為空值");
			}
			
			String mem_idnumReg = "^[(a-zA-Z0-9)]{1,12}$";		//簡易的判斷,之後再做修改
			if (!mem_idnum.matches(mem_idnumReg)) {
				errorMsgs.add("身分證ID:只能是英文字母、數字 , 且長度必須在1到12之間");
			}
			
			Date mem_bday = null;
			if (req.getParameter("mem_bday").length() == 0) {

			} else {
			mem_bday = Date.valueOf(req.getParameter("mem_bday"));
			}

			String mem_phone = req.getParameter("mem_phone").trim();
			if (mem_phone == null || mem_phone.length() == 0) {
				errorMsgs.add("聯絡方式不可為空值");
			}
			String mem_phoneReg = "^[(0-9)]{1,10}$";		//簡易的判斷,之後再做修改
			if (!mem_phone.matches(mem_phoneReg)) {
				errorMsgs.add("聯絡方式:只能是數字 , 且長度必須在1到10之間");
			}
			
			String mem_addr = req.getParameter("mem_addr").trim();
			String mem_mail = req.getParameter("mem_mail").trim();
			
			Date mem_due = null;
			if (req.getParameter("mem_due").length() == 0) {
				
			} else {
			mem_due = Date.valueOf(req.getParameter("mem_due"));
			}

			String key_id = req.getParameter("key_id");
			if (key_id == null || key_id.length() == 0) {
				errorMsgs.add("建檔ID不可為空值");
			}
			Long now = new java.util.Date().getTime();
			Date key_date = new Date(now);
			String mem_um = req.getParameter("mem_um").trim();


			MemberVO memVO = new MemberVO();

			memVO.setMem_pwd(mem_pwd);
			memVO.setMem_name(mem_name);
			memVO.setMem_idnum(mem_idnum);
			memVO.setMem_bday(mem_bday);
			memVO.setMem_phone(mem_phone);
			memVO.setMem_addr(mem_addr);
			memVO.setMem_mail(mem_mail);
			memVO.setMem_due(mem_due);
			memVO.setKey_id(key_id);
			memVO.setKey_date(key_date);
			memVO.setMem_um(mem_um);

			if (!errorMsgs.isEmpty()) {
				model.addAttribute("errorMsgs", errorMsgs);				
				return"/MEMBER/addMem";
			}
		
				
			/*************************** 2.永續層存取 ***************************************/
			
				memVO = MemSvc.addMem(mem_pwd, mem_name, mem_idnum, mem_bday, mem_phone,
						mem_addr, mem_mail,mem_due, key_id, key_date, mem_um);
				
				List<MemberVO> list = MemSvc.getAll();				
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			
				req.getSession().setAttribute("list", list);
				return "redirect:/MEMBER/listAllMem.jsp";
								
		}

//按下修改 轉交到修改頁面		
			@RequestMapping(method = RequestMethod.POST,value = "/MEMBER/allForUpdateMem.do")
			public String updateMem(ModelMap model,HttpServletRequest req){
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
				String mem_id = new String(req.getParameter("mem_id"));
			/*************************** 2.永續層存取 ***************************************/
				
				MemberVO memVO = MemSvc.getOneMem(mem_id);
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

				model.addAttribute("memVO",memVO);
				return "/MEMBER/updateMem";
								
			}

//送出修改

			@RequestMapping(method = RequestMethod.POST,value = "/MEMBER/updateMem.do")
			public String update(ModelMap model,HttpServletRequest req) throws Exception, Exception {
				/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String mem_id = req.getParameter("mem_id").trim();
			if (mem_id == null || mem_id.length() == 0) {
				errorMsgs.add("ID不可為空值");
			}
			
			String mem_pwd = req.getParameter("mem_pwd").trim();
			if (mem_pwd == null || mem_pwd.length() == 0) {
				errorMsgs.add("密碼不可為空值");
			}
			
			String mem_pwdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9!@#$%^)]{1,20}$";
			if (!mem_pwd.matches(mem_pwdReg)) {
				errorMsgs.add("密碼:只能是中、英文字母、數字和!@#$%^ , 且長度必須在1到20之間");
			}
			
			String mem_name = req.getParameter("mem_name").trim();
			if (mem_name == null || mem_name.length() == 0) {
				errorMsgs.add("姓名不可為空值");
			}
			
			String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\s)]{1,20}$";
			if (!mem_name.matches(mem_nameReg)) {
				errorMsgs.add("姓名:只能是中、英文字母、數字和_ , 且長度必須在1到20之間");
			}
			
			String mem_idnum = req.getParameter("mem_idnum").trim();
			if (mem_idnum == null || mem_idnum.length() == 0) {
				errorMsgs.add("身分證ID不可為空值");
			}
			
			String mem_idnumReg = "^[(a-zA-Z0-9)]{1,12}$";		//簡易的判斷,之後再做修改
			if (!mem_idnum.matches(mem_idnumReg)) {
				errorMsgs.add("身分證ID:只能是英文字母、數字 , 且長度必須在1到12之間");
			}
						
			Date mem_bday = null;
			if (req.getParameter("mem_bday").length() == 0) {

			} else {
				mem_bday = Date.valueOf(req.getParameter("mem_bday"));
			}
			
			String mem_phone = req.getParameter("mem_phone").trim();
			if (mem_phone == null || mem_phone.length() == 0) {
				errorMsgs.add("聯絡方式不可為空值");
			
			String mem_phoneReg = "^[(0-9)]{1,10}$";		//簡易的判斷,之後再做修改
			if (!mem_phone.matches(mem_phoneReg)) {
				errorMsgs.add("聯絡方式:只能是數字 , 且長度必須在1到10之間");
			}	
				
			}
			String mem_addr = req.getParameter("mem_addr").trim();
			String mem_mail = req.getParameter("mem_mail").trim();
			
			Date mem_due = null;
			if (req.getParameter("mem_due").length() == 0) {

			} else {
				mem_due = Date.valueOf(req.getParameter("mem_due"));
			}
			
			String key_id = req.getParameter("key_id");
			if (key_id == null || key_id.length() == 0) {
				errorMsgs.add("建檔ID不可為空值");
			}
			Long now = new java.util.Date().getTime();
			Date key_date = new Date(now);
			String mem_um = req.getParameter("mem_um").trim();

			// if discVO include error message send back
			MemberVO memVO = new MemberVO();
			memVO.setMem_id(mem_id);
			memVO.setMem_pwd(mem_pwd);
			memVO.setMem_name(mem_name);
			memVO.setMem_idnum(mem_idnum);
			memVO.setMem_bday(mem_bday);
			memVO.setMem_phone(mem_phone);
			memVO.setMem_addr(mem_addr);
			memVO.setMem_mail(mem_mail);
			memVO.setMem_due(mem_due);
			memVO.setKey_id(key_id);
			memVO.setKey_date(key_date);
			memVO.setMem_um(mem_um);

			if (!errorMsgs.isEmpty()) {
				model.addAttribute("errorMsgs", errorMsgs);
				return "/MEMBER/updateMem";
				
			}
			/*************************** 2.永續層存取 ***************************************/
			
			memVO = MemSvc.updMem(mem_id, mem_pwd, mem_name, mem_idnum, mem_bday, mem_phone, mem_addr, mem_mail,
						mem_due, key_id, key_date, mem_um);
				List<MemberVO> list = new ArrayList<MemberVO>();
				list.add(memVO);
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
					
				req.getSession().setAttribute("list", list);
				return "redirect:/MEMBER/listAllMem.jsp";
	
		}
//查詢全部		
			@RequestMapping(method = RequestMethod.POST, value = "/MEMBER/allMemb.do")
			public String getAllMem(ModelMap model) {	
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				List<MemberVO> list = MemSvc.getAll();
					
			/*************************** 2.永續層存取 ***************************************/	
						model.addAttribute("list", list);	
						
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
						return "/MEMBER/listAllMem";			
				}


		

			@RequestMapping(method = RequestMethod.POST,value = "/MEMBER/idsMemb.do")
			public String insertCom(ModelMap model,HttpServletRequest req) throws Exception, Exception {
				
				/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				

					String mem_id_1 = req.getParameter("mem_id_1");
					String mem_id_2 = req.getParameter("mem_id_2");

				
					List<MemberVO> list = MemSvc.getIdRge(mem_id_1,mem_id_2);
					req.setAttribute("list", list);
					return "/MEMBER/listAllMem";

		}		
		

			@RequestMapping(method = RequestMethod.POST,value = "/MEMBER/datesMem.do")
			public String getDates(ModelMap model,HttpServletRequest req) throws Exception, Exception {
				
				/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

					Date key_date_1 = null;
					if (req.getParameter("key_date_1").length() == 0) {
						errorMsgs.add("日期範圍: 請勿空白");
					} else {
						key_date_1 = Date.valueOf(req.getParameter("key_date_1"));
					}
					
					Date key_date_2 = null;
					if (req.getParameter("key_date_2").length() == 0) {
						errorMsgs.add("日期範圍: 請勿空白");
					} else {
						key_date_2 = Date.valueOf(req.getParameter("key_date_2"));
					}
					/*************************** 2.永續層存取 ***************************************/
					
					List<MemberVO> list = MemSvc.getKdateRge(key_date_1,key_date_2);
					/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
					
					req.setAttribute("list", list);

					if (list == null) {
						errorMsgs.add("資料不存在");
					}
					if (!errorMsgs.isEmpty()) {
						model.addAttribute("errorMsgs", errorMsgs);				
						return"/MEMBER/member";
					}
					return "/MEMBER/listAllMem";

		}		
		
//刪除	
			@RequestMapping(method = RequestMethod.POST, value = "/MEMBER/deleteMem.do")
			public String delete(@RequestParam("mem_id") String mem_id, ModelMap model,HttpServletRequest req) throws Exception, Exception {
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				
			/*************************** 2.永續層存取 ***************************************/
				try{
					MemSvc.delMem(mem_id);					
				}catch (Exception e) {
					e.printStackTrace();
				}
				List<MemberVO> list = MemSvc.getAll();
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				
				req.getSession().setAttribute("list", list);
				return "redirect:/MEMBER/listAllMem.jsp";

			
		}
}
