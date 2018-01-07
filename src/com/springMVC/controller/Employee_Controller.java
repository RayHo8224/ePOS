package com.springMVC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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

import com.discount.model.DiscountVO;
import com.employee.model.EmpService;
import com.employee.model.EmpVO;

import gvjava.org.json.JSONArray;
import gvjava.org.json.JSONObject;


/**
 * Servlet implementation class Employee_Servlet
 */

@Controller
public class Employee_Controller {
	
	private final static EmpService empSrv =new EmpService();
//	private  EmpService empSrv;
//	
//	public EmpService getEmpSrv() {
//		return empSrv;
//	}
//
//	public void setEmpSrv(EmpService empSrv) {
//		this.empSrv = empSrv;
//	}

//	@RequestMapping(method = RequestMethod.GET, value = "/i")
//	public String addEmp(ModelMap model) {
//		System.out.println("OK");
//		EmpVO empVO = new EmpVO();
//		model.addAttribute("empVO", empVO);
//		return "/Index1";
//	}

	@RequestMapping(method = RequestMethod.POST, value = "/EMPLOYEE/getOneEmp.do")
	public String getOneEmp(@RequestParam("emp_id") String emp_id, ModelMap model) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		 List<String> errorMsgs = new LinkedList<String>();

		if (emp_id == null || emp_id == "") {
			errorMsgs.add("請輸入員工編號");
		}
		if (!errorMsgs.isEmpty()) {
		model.addAttribute("message", errorMsgs);
		return "redirect:/EMPLOYEE/employee.jsp";
		}
		/*************************** 2.永續層存取 ***************************************/
//		EmpService empSrv = new EmpService();
		EmpVO empVO;
		List list = new LinkedList<EmpVO>();

		try {
			empVO = empSrv.getOne(emp_id);

			if (empVO == null) {
				model.addAttribute("message", "查無資料");
				return "redirect:/EMPLOYEE/employee.jsp";
			}
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			list.add(empVO);

		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("list", list);

		return "/EMPLOYEE/AllEmp";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/EMPLOYEE/getAllEmp.do")
	public String getAllEmp(ModelMap model) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

		/*************************** 2.永續層存取 ***************************************/
		EmpService empSrv = new EmpService();
		List<EmpVO> list = null;
		try {
			list = empSrv.getAll();
		} catch (IOException e) {

			e.printStackTrace();
		}
		model.addAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "/EMPLOYEE/AllEmp";
	}
	

	@RequestMapping(method = RequestMethod.POST,value = "/EMPLOYEE/insertEmp.do")
	public String insertEmp(ModelMap model,HttpServletRequest request) throws Exception, Exception {
		
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);

		String emp_pwd = request.getParameter("emp_pwd").trim();
		if (emp_pwd == null || emp_pwd.length() == 0) {
			errorMsgs.add("密碼不可為空值");
		}
		String emp_pwdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9!@#$%^)]{1,20}$";
		if (!emp_pwd.matches(emp_pwdReg)) {
			errorMsgs.add("密碼:只能是中、英文字母、數字和!@#$%^ , 且長度必須在1到20之間");
		}
		String emp_name = request.getParameter("emp_name").trim();
		if (emp_name == null || emp_name.length() == 0) {
			errorMsgs.add("姓名不可為空值");
		}
		String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\s)]{1,20}$";
		if (!emp_name.matches(emp_nameReg)) {
			errorMsgs.add("姓名:只能是中、英文字母、數字和_ , 且長度必須在1到20之間");
		}
		String emp_sex = request.getParameter("emp_sex");
		String emp_idnum = request.getParameter("emp_idnum").trim();
		if (emp_idnum == null || emp_idnum.length() == 0) {
			errorMsgs.add("身分證ID不可為空值");
		}
		String emp_idnumReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";		//簡易的判斷,之後再做修改
		if (!emp_idnum.matches(emp_idnumReg)) {
			errorMsgs.add("請輸入有效的身份證字號");
		}
		String emp_addr = request.getParameter("emp_addr").trim();
		if (emp_addr == null || emp_addr.length() == 0) {
			errorMsgs.add("地址不可為空值");
		}
		String emp_mail = request.getParameter("emp_mail").trim();
		if (emp_mail == null || emp_mail.length() == 0) {
			errorMsgs.add("email不可為空值");
		}
		String emp_mailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
		if (!emp_mail.matches(emp_mailReg)) {
			errorMsgs.add("email輸入格式不正確");
		}
		String emp_phone = request.getParameter("emp_phone").trim();
		if (emp_phone == null || emp_phone.length() == 0) {
			errorMsgs.add("電話不可為空值");
		}		
		Date emp_bday;
		if (request.getParameter("emp_bday").length() == 0) {
			emp_bday = null;
		} else {
			emp_bday = Date.valueOf(request.getParameter("emp_bday"));
		}
		Date emp_reg = Date.valueOf(request.getParameter("emp_reg"));
		Date emp_due;
		if (request.getParameter("emp_due").length() == 0) {
			emp_due = null;
		} else {
			emp_due = Date.valueOf(request.getParameter("emp_due"));
		}

		Part filePart1 = request.getPart("picture");
		InputStream in = filePart1.getInputStream();
		byte[] picture = new byte[in.available()];
		in.read(picture);
		
		String key_id = request.getParameter("key_id");
		long nowD = new java.util.Date().getTime();
		Date key_date = new Date(nowD);

		EmpVO empVO = new EmpVO();

		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_sex(emp_sex);
		empVO.setEmp_idnum(emp_idnum);
		empVO.setEmp_addr(emp_addr);
		empVO.setEmp_mail(emp_mail);
		empVO.setEmp_phone(emp_phone);
		empVO.setEmp_bday(emp_bday);
		empVO.setEmp_reg(emp_reg);
		empVO.setEmp_due(emp_due);
		empVO.setPicture(Base64.getEncoder().encodeToString(picture));
		empVO.setKey_id(key_id);
		empVO.setKey_date(key_date);
		
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("errorMsgs", errorMsgs);				
			return"/EMPLOYEE/addEmp";
		}
		/*************************** 2.永續層存取 ***************************************/
//		EmpService empSrv = new EmpService();
		List<EmpVO> list = null;
		try {
			empSrv.insertOne(empVO);
			list = empSrv.getAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "redirect:/EMPLOYEE/AllEmp.jsp";
	}



	@RequestMapping(method = RequestMethod.POST, value = "/EMPLOYEE/getEmpByName.do")
	public String getEmpByName(ModelMap model,HttpServletRequest request,
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		
		@RequestParam("emp_name")String emp_name) {
		List<String> errorMsgs = new LinkedList<String>();
		
		if (emp_name == null || emp_name == "") {
			errorMsgs.add("請輸入員工姓名");
		}
		if (!errorMsgs.isEmpty()) {
		model.addAttribute("message", errorMsgs);
		return "redirect:/EMPLOYEE/employee.jsp";
		}
		/*************************** 2.永續層存取 ***************************************/
//		EmpService empSrv = new EmpService();
		List list=empSrv.getByName(emp_name);

		if (list.isEmpty()) {
			errorMsgs.add("查無此員工");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("message", errorMsgs);
			return "redirect:/EMPLOYEE/employee.jsp";
			}
		
		request.getSession().setAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "redirect:/EMPLOYEE/AllEmp.jsp";
	}

	
	@RequestMapping(method = RequestMethod.POST,value ={"/updateDeleteEmp.do","/EMPLOYEE/updateDeleteEmp.do"})
	public String updateDeleteEmp(ModelMap model,HttpServletRequest request,
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			@RequestParam("action")String action,
			@RequestParam("emp_id") String emp_id
			) throws Exception, Exception {
		/*************************** 2.永續層存取 ***************************************/
			if("update".equals(action)){
//				EmpService empSrv = new EmpService();
				EmpVO empVO=null;
				try {
					empVO = empSrv.getOne(emp_id);
				} catch (IOException e) {
					e.printStackTrace();
				}					
				model.addAttribute("empVO", empVO);
				return "/EMPLOYEE/UpdateEmp";
			}

			if("delete".equals(action)){
//				EmpService empSrv = new EmpService();
				try{
				empSrv.delete(emp_id);
				}catch (Exception e) {
					e.printStackTrace();
				}
				List list = empSrv.getAll();
				
				request.getSession().setAttribute("list", list);
				request.setAttribute("list", list);
				return "redirect:/EMPLOYEE/AllEmp.jsp";
			}
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			return null;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = {"/EMPLOYEE/updateEmp.do","/updateEmp.do"})
	public String updateEmp(ModelMap model,HttpServletRequest request) throws Exception, Exception {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);

		String emp_id = request.getParameter("emp_id").trim();
		String emp_pwd = request.getParameter("emp_pwd").trim();
		if (emp_pwd == null || emp_pwd.length() == 0) {
			errorMsgs.add("密碼不可為空值");
		}
		String emp_pwdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9!@#$%^)]{1,20}$";
		if (!emp_pwd.matches(emp_pwdReg)) {
			errorMsgs.add("密碼:只能是中、英文字母、數字和!@#$%^ , 且長度必須在1到20之間");
		}
		String emp_name = request.getParameter("emp_name").trim();
		if (emp_name == null || emp_name.length() == 0) {
			errorMsgs.add("姓名不可為空值");
		}
		String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\s)]{1,20}$";
		if (!emp_name.matches(emp_nameReg)) {
			errorMsgs.add("姓名:只能是中、英文字母、數字和_ , 且長度必須在1到20之間");
		}
		String emp_sex = request.getParameter("emp_sex").trim();
		String emp_idnum = request.getParameter("emp_idnum").trim();
		if (emp_idnum == null || emp_idnum.length() == 0) {
			errorMsgs.add("身分證ID不可為空值");
		}
		String emp_idnumReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";		//簡易的判斷,之後再做修改
		if (!emp_idnum.matches(emp_idnumReg)) {
			errorMsgs.add("身分證ID:只能是英文字母、數字 , 且長度必須在1到12之間");
		}
		String emp_addr = request.getParameter("emp_addr").trim();
		if (emp_addr == null || emp_addr.length() == 0) {
			errorMsgs.add("地址不可為空值");
		}
		String emp_mail = request.getParameter("emp_mail").trim();
		if (emp_mail == null || emp_mail.length() == 0) {
			errorMsgs.add("email不可為空值");
		}
		String emp_mailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
		if (!emp_mail.matches(emp_mailReg)) {
			errorMsgs.add("email輸入格式不正確");
		}
		String emp_phone = request.getParameter("emp_phone").trim();
		if (emp_phone == null || emp_phone.length() == 0) {
			errorMsgs.add("電話不可為空值");
		}	
		Date emp_bday = Date.valueOf(request.getParameter("emp_bday"));

		Date emp_reg = Date.valueOf(request.getParameter("emp_reg"));
		
		Date emp_due;
		if (request.getParameter("emp_due").length() == 0) {
			emp_due = null;
		} else {
			emp_due = Date.valueOf(request.getParameter("emp_due"));
		}

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
		String pass_code=request.getParameter("pass_code");
		
		long nowD = new java.util.Date().getTime();
		Date key_date = new Date(nowD);

		EmpVO empVO = new EmpVO();
		
		empVO.setEmp_id(emp_id);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_sex(emp_sex);
		empVO.setEmp_idnum(emp_idnum);
		empVO.setEmp_addr(emp_addr);
		empVO.setEmp_mail(emp_mail);
		empVO.setEmp_phone(emp_phone);
		empVO.setEmp_bday(emp_bday);
		empVO.setEmp_reg(emp_reg);
		empVO.setEmp_due(emp_due);
		empVO.setPicture(picture);
		empVO.setKey_id(key_id);
		empVO.setKey_date(key_date);
		empVO.setPass_code(pass_code);
		
		/*************************** 2.永續層存取 ***************************************/

//		EmpService empSrv = new EmpService();
		empSrv.update(empVO);
		List list = empSrv.getAll();
		request.getSession().setAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "redirect:/EMPLOYEE/AllEmp.jsp";
	
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/EMPLOYEE/setPassCode.do","/setPassCode.do"})
	public String setPassCode(ModelMap model,HttpServletRequest request) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
	
		
		String emp_id=request.getParameter("emp_id") ;
		String[] pass_codes = request.getParameterValues("pass_code");
		
		String pass_code="";
		for(int i=0;i<pass_codes.length;i++){
			if(i==pass_codes.length-1)
				pass_code+=pass_codes[i];
			else
			pass_code=pass_code+pass_codes[i]+",";
			
		}
		
		System.out.println(pass_code);
		/*************************** 2.永續層存取 ***************************************/
//		EmpService empSrv = new EmpService();
		empSrv.setPassCode(pass_code, emp_id);
		
		List list=null;
		try {
			list = empSrv.getAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			
		return "redirect:/EMPLOYEE/SetPassCode.jsp";
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = {"/EMPLOYEE/getPassCode.do","/getPassCode.do"})
	public void getJsonPassCode(ModelMap model,HttpServletRequest request,HttpServletResponse resp) throws IOException {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		String emp_id=request.getParameter("emp_id");
		
		List<EmpVO> list=null;
		try {
			EmpVO empVO = empSrv.getOne(emp_id);
			List l1 = new LinkedList();
			Map m1 = new HashMap();
			m1.put("pass_code[i]", empVO.getPass_code());
			l1.add(m1);
			
			resp.setHeader("content-type","text/html;charset=utf-8");
			JSONArray jsonArray = new JSONArray(l1);
			PrintWriter out = resp.getWriter();
			out.print(jsonArray);
			System.out.println("-----------3------------------");
			System.out.println(jsonArray);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/ANDROID/Login.do")
	public void androidLogin(@RequestParam("emp_id") String emp_id,@RequestParam("emp_pwd") String emp_pwd, ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception, Exception {
	/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		
	/*************************** 2.永續層存取 ***************************************/
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		EmpVO empVO = empSrv.getOne(emp_id);
		empVO.setPicture("");
		if(emp_pwd.equals(empVO.getEmp_pwd())){
			String empJson=null;
			JSONObject json = new JSONObject(empVO);
			empJson = json.toString(); 
			System.out.println(empJson);
			out.print(empJson);
		}else{
			out.print("帳號不存在或密碼錯誤");

		}

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
		

	
}

}


