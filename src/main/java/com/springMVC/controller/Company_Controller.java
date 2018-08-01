package com.springMVC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.company.model.ComService;
import com.company.model.ComVO;
import com.employee.model.EmpService;
import com.employee.model.EmpVO;


/**
 * Servlet implementation class Company_Servlet
 */

@Controller
public class Company_Controller {

	
	private final static ComService comSrv =new ComService();
	


	@RequestMapping(method = RequestMethod.POST, value = "/COMPANY/getOneCom.do")
	public String getOneCom(@RequestParam("com_id") String com_id, ModelMap model) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		 List<String> errorMsgs = new LinkedList<String>();

		if (com_id == null || (com_id.trim()).length() == 0) {
			errorMsgs.add("請輸入廠商編號");
		}
		if (!errorMsgs.isEmpty()) {
		model.addAttribute("message", errorMsgs);
		return "redirect:/COMPANY/company.jsp";
		}
		/*************************** 2.永續層存取 ***************************************/
		
		ComVO comVO = comSrv.getOne(com_id);

			if (comVO == null) {
				model.addAttribute("message", "查無此廠商");
				return "redirect:/COMPANY/company.jsp";
			}
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			List list = new LinkedList<ComVO>();
			list.add(comVO);

		model.addAttribute("list", list);

		return "/COMPANY/AllCom";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/COMPANY/getAllCom.do")
	public String getAllCom(ModelMap model) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		List list = comSrv.getAll();
		/*************************** 2.永續層存取 ***************************************/
		
		model.addAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "/COMPANY/AllCom";
	}
	

	@RequestMapping(method = RequestMethod.POST,value = "/COMPANY/insertCom.do")
	public String insertCom(ModelMap model,HttpServletRequest request) throws Exception, Exception {
		
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		String com_name = request.getParameter("com_name");
		String com_um = request.getParameter("com_um");
		String com_phone = request.getParameter("com_phone");
		String com_addr = request.getParameter("com_addr");
		String com_mail = request.getParameter("com_mail");

//		Part filePart1 = request.getPart("picture");
//		InputStream in = filePart1.getInputStream();
//		byte[] picture = new byte[in.available()];
//		in.read(picture);
		
		String key_id = request.getParameter("key_id");

		long nowD = new java.util.Date().getTime();
		Date key_date = new Date(nowD);
		
		ComVO comVO = new ComVO();
		
		comVO.setCom_name(com_name);
		comVO.setCom_um(com_um);
		comVO.setCom_phone(com_phone);
		comVO.setCom_addr(com_addr);
		comVO.setCom_mail(com_mail);
//		comVO.setPicture(Base64.getEncoder().encodeToString(picture));
		comVO.setKey_id(key_id);
		comVO.setKey_date(key_date);

		/*************************** 2.永續層存取 ***************************************/
		comSrv.insertOne(comVO);
		List list = comSrv.getAll();
		request.getSession().setAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "redirect:/COMPANY/AllCom.jsp";
	}



	@RequestMapping(method = RequestMethod.POST, value = "/COMPANY/getComByName.do")
	public String getComByName(ModelMap model,HttpServletRequest request,
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		
		@RequestParam("com_name")String com_name) {
		
		List<String> errorMsgs = new LinkedList<String>();
		
		if (com_name == null || (com_name.trim()).length() == 0) {
			errorMsgs.add("請輸入廠商名稱");
		}
		if (!errorMsgs.isEmpty()) {
		model.addAttribute("message", errorMsgs);
		return "redirect:/COMPANY/company.jsp";  //導回首頁顯示錯誤資訊
		}
		/*************************** 2.永續層存取 ***************************************/
		List list=comSrv.getByName(com_name);
		
		if (list.isEmpty()) {
			errorMsgs.add("查無此廠商");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("message", errorMsgs);
			return "redirect:/COMPANY/company.jsp";
			}
		
		request.getSession().setAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "redirect:/COMPANY/AllCom.jsp";
	}

	
	@RequestMapping(method = RequestMethod.POST,value ={"/updateDeleteCom.do","/COMPANY/updateDeleteCom.do"})
	public String updateDeleteCom(ModelMap model,HttpServletRequest request,
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			@RequestParam("action")String action,
			@RequestParam("com_id") String com_id
			) throws Exception, Exception {
		/*************************** 2.永續層存取 ***************************************/
			if("update".equals(action)){
				ComVO comVO = comSrv.getOne(com_id);

				model.addAttribute("comVO", comVO);
				return "/COMPANY/UpdateCom";
			}

			if("delete".equals(action)){
				try {
					comSrv.delete(com_id);					
				} catch (Exception e) {
					e.printStackTrace();
				}
				List list = comSrv.getAll();
				
				request.getSession().setAttribute("list", list);
				return "redirect:/COMPANY/AllCom.jsp";
			}
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			return null;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = {"/COMPANY/updateCom.do","/updateCom.do"})
	public String updateCom(ModelMap model,HttpServletRequest request) throws Exception, Exception {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		String com_id = request.getParameter("com_id");
		String com_name = request.getParameter("com_name");
		String com_um = request.getParameter("com_um");
		String com_phone = request.getParameter("com_phone");
		String com_addr = request.getParameter("com_addr");
		String com_mail = request.getParameter("com_mail");

		Part filePart1 = request.getPart("newPicture");
		
		String picture;
		if(filePart1.getSize()!=0){
		InputStream in = filePart1.getInputStream();
		byte[] BytePicture = new byte[in.available()];
		in.read(BytePicture);
		picture=Base64.getEncoder().encodeToString(BytePicture);
		}else{
		picture=request.getParameter("picture");
		}
		
		String key_id = request.getParameter("key_id");
		
		long nowD = new java.util.Date().getTime();
		Date key_date = new Date(nowD);

		ComVO comVO = new ComVO();
		
		comVO.setCom_id(com_id);
		comVO.setCom_name(com_name);
		comVO.setCom_um(com_um);
		comVO.setCom_phone(com_phone);
		comVO.setCom_addr(com_addr);
		comVO.setCom_mail(com_mail);
		comVO.setKey_id(key_id);
		comVO.setKey_date(key_date);
		comVO.setPicture(picture);
		comVO.setKey_id(key_id);
		comVO.setKey_date(key_date);
		
		
		/*************************** 2.永續層存取 ***************************************/

		comSrv.update(comVO);
		
		List list = comSrv.getAll();
		request.getSession().setAttribute("list", list);
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "redirect:/COMPANY/AllCom.jsp";
	
	}
	
}


