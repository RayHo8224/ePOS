package com.springMVC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.product.model.ProdService;
import com.product.model.ProdVO;

import gvjava.org.json.JSONObject;

/**
 * Servlet implementation class Company_Servlet
 */

@Controller
public class Product_Controller {
	
	private final static ProdService prodSrv = new ProdService();;	

	@RequestMapping(method = RequestMethod.POST, value = "/PRODUCT/getOneProd.do")
	public String getOneProd(@RequestParam("prod_id") String prod_id, ModelMap model) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		 List<String> errorMsgs = new LinkedList<String>();

		if (prod_id == null || (prod_id.trim()).length() == 0) {
			errorMsgs.add("請輸入商品編號");
		}
		if (!errorMsgs.isEmpty()) {
		model.addAttribute("message", errorMsgs);
		return "redirect:/PRODUCT/product.jsp";
		}
		/*************************** 2.永續層存取 ***************************************/
		
		ProdVO prodVO = prodSrv.getOne(prod_id);

			if (prodVO == null) {
				model.addAttribute("message", "查無此商品");
				return "redirect:/PRODUCT/product.jsp";
			}
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			List list = new LinkedList<ProdVO>();
			list.add(prodVO);

		model.addAttribute("list", list);

		return "PRODUCT/AllProd";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/PRODUCT/getAllProd.do")
	public String getAllProd(ModelMap model) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		List list = prodSrv.getAll();

		/*************************** 2.永續層存取 ***************************************/
		
		model.addAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "PRODUCT/AllProd";
	}
	

	@RequestMapping(method = RequestMethod.POST,value = "/PRODUCT/insertProd.do")
	public String insertCom(ModelMap model,HttpServletRequest request) throws Exception, Exception {
		
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		StringBuffer u = request.getRequestURL();
		String prod_name = request.getParameter("prod_name");
		String com_id = request.getParameter("com_id");
		String prod_group = request.getParameter("prod_group");
		String prod_mkprice = request.getParameter("prod_mkprice");
		String prod_cost = request.getParameter("prod_cost");
		String prod_stock = request.getParameter("prod_stock");
		String prod_q_safty = request.getParameter("prod_q_safty");
		String prod_spec = request.getParameter("prod_spec");
System.out.println(u);
		Part filePart1 = request.getPart("picture");
		InputStream in = filePart1.getInputStream();
		byte[] picture = new byte[in.available()];
		in.read(picture);

		String remark = request.getParameter("remark");
		String status = request.getParameter("status");

		ProdVO prodVO = new ProdVO();

		prodVO.setProd_name(prod_name);
		prodVO.setCom_id(com_id);
		prodVO.setProd_group(prod_group);
		prodVO.setProd_mkprice(Integer.parseInt(prod_mkprice));
		prodVO.setProd_cost(Integer.parseInt(prod_cost));
		prodVO.setProd_stock(Integer.parseInt(prod_stock));
		prodVO.setProd_q_safty(Integer.parseInt(prod_q_safty));
		prodVO.setProd_spec(prod_spec);
		prodVO.setPicture(Base64.getEncoder().encodeToString(picture));
		prodVO.setRemark(remark);
		prodVO.setStatus(status);

		/*************************** 2.永續層存取 ***************************************/
		prodSrv.insertOne(prodVO);
		List list = prodSrv.getAll();
		request.getSession().setAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "redirect:/PRODUCT/AllProd.jsp";
	}



	@RequestMapping(method = RequestMethod.POST, value = "/PRODUCT/getProdByName.do")
	public String getProdByName(ModelMap model,HttpServletRequest request,
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		
		@RequestParam("prod_name")String prod_name) {
		
		List<String> errorMsgs = new LinkedList<String>();
		
		if (prod_name == null || (prod_name.trim()).length() == 0) {
			errorMsgs.add("請輸入商品名稱");
		}
		if (!errorMsgs.isEmpty()) {
		model.addAttribute("message", errorMsgs);
		return "redirect:/PRODUCT/AllProd.jsp";  //導回首頁顯示錯誤資訊
		}
		/*************************** 2.永續層存取 ***************************************/
		List list = prodSrv.getByName(prod_name);
		
		if (list.isEmpty()) {
			errorMsgs.add("查無此商品");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("message", errorMsgs);
			return "redirect:/PRODUCT/AllProd.jsp";
			}
		
		request.getSession().setAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "redirect:/PRODUCT/AllProd.jsp";
	}

	
	@RequestMapping(method = RequestMethod.POST,value = "/PRODUCT/updateDeleteProd.do")
	public String updateDeleteProd(ModelMap model,HttpServletRequest request,
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			@RequestParam("action")String action,
			@RequestParam("prod_id") String prod_id
			) throws Exception, Exception {
		/*************************** 2.永續層存取 ***************************************/
			if("update".equals(action)){
				ProdVO prodVO = prodSrv.getOne(prod_id);

				model.addAttribute("prodVO", prodVO);
				return "/PRODUCT/UpdateProd";
			}
System.out.println(1);
			if("delete".equals(action)){
				
				prodSrv.delete(prod_id);
				List list = prodSrv.getAll();
				
				request.getSession().setAttribute("list", list);
				request.setAttribute("list", list);
				return "redirect:/PRODUCT/AllProd.jsp";
			}
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			return null;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = {"PRODUCT/updateProd.do","/PRODUCT/updateProd.do"})
	public String updateProd(ModelMap model,HttpServletRequest request) throws Exception, Exception {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);

		String prod_id = request.getParameter("prod_id");
		String prod_name = request.getParameter("prod_name");
		String com_id = request.getParameter("com_id");
		String prod_group = request.getParameter("prod_group");
		String prod_mkprice = request.getParameter("prod_mkprice");
		String prod_cost = request.getParameter("prod_cost");
		String prod_stock = request.getParameter("prod_stock");
		String prod_q_safty = request.getParameter("prod_q_safty");
		String prod_spec = request.getParameter("prod_spec");

		Part filePart1 = request.getPart("newPicture");
		String picture;
		if (filePart1.getSize() != 0) {
			InputStream in = filePart1.getInputStream();
			byte[] BytePicture = new byte[in.available()];
			in.read(BytePicture);
			picture = Base64.getEncoder().encodeToString(BytePicture);
		} else {
			picture = request.getParameter("picture");
		}

		String remark = request.getParameter("remark");
		String status = request.getParameter("status");

		ProdVO prodVO = new ProdVO();

		prodVO.setProd_id(prod_id);
		;
		prodVO.setProd_name(prod_name);
		prodVO.setCom_id(com_id);
		prodVO.setProd_group(prod_group);
		prodVO.setProd_mkprice(Integer.parseInt(prod_mkprice));
		prodVO.setProd_cost(Integer.parseInt(prod_cost));
		prodVO.setProd_stock(Integer.parseInt(prod_stock));
		prodVO.setProd_q_safty(Integer.parseInt(prod_q_safty));
		prodVO.setProd_spec(prod_spec);
		prodVO.setPicture(picture);
		prodVO.setRemark(remark);
		prodVO.setStatus(status);
		
		
		/*************************** 2.永續層存取 ***************************************/

		prodSrv.update(prodVO);
		List list = prodSrv.getAll();
		request.getSession().setAttribute("list", list);
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "redirect:/PRODUCT/AllProd.jsp";
	
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/PRODUCT/getProdByGroup.do")
	public String getProdByGroup(ModelMap model,HttpServletRequest request,
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		
		@RequestParam("prod_group")String prod_group) {
		
		List<String> errorMsgs = new LinkedList<String>();
		
		if (prod_group == null || (prod_group.trim()).length() == 0) {
			errorMsgs.add("請輸入商品分類");
		}
		if (!errorMsgs.isEmpty()) {
		model.addAttribute("message", errorMsgs);
		return "redirect:/PRODUCT/product.jsp";  //導回首頁顯示錯誤資訊
		}
		/*************************** 2.永續層存取 ***************************************/
		List list = prodSrv.getByGroup(prod_group);
		
		if (list.isEmpty()) {
			errorMsgs.add("查無此分類");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("message", errorMsgs);
			return "redirect:PRODUCT/product.jsp";
			}
		
		request.getSession().setAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "redirect:/PRODUCT/AllProd.jsp";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/ANALYSIS/getLowStockProd")
	public String GetLowStockProd(ModelMap model,HttpServletRequest request){
		List<ProdVO> lessProdlist = new LinkedList<ProdVO>();
		List<ProdVO> list = prodSrv.getAll();
		
		for(ProdVO prodVO : list){
			if(prodVO.getProd_stock()<=prodVO.getProd_q_safty()){
				lessProdlist.add(prodVO);
			}
		} 
//		request.getSession().setAttribute("lessProdlist", lessProdlist);
		model.addAttribute("lessProdlist", lessProdlist);
		return "/ANALYSIS/getLessProdDemo";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/ANDROID/getProdFromAndroid")
	public void getProdFromAndroid(@RequestParam("prod_id") String prod_id,HttpServletResponse response,ModelMap model) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		
		/*************************** 2.永續層存取 ***************************************/
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		ProdVO prodVO = prodSrv.getOne(prod_id);

			if (prodVO == null) {
				 try {
					PrintWriter out = response.getWriter();
					out.print("無資料");
				 } catch (IOException e) {
					e.printStackTrace();
				}

			}else if(prodVO != null){
			String prodJson=null;
			JSONObject json = new JSONObject(prodVO);
			prodJson = json.toString(); 
			System.out.println(prodJson);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			
				try {
					PrintWriter out = response.getWriter();
//					out.print("<html>");
//					out.print("<head>");
//					out.print("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
//					out.print("</head>");
//					out.print("<body>");
					out.print(prodJson);
//					out.write(prodJson);
//					out.print("</body>");
//					out.print("</html>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}

	}

}


