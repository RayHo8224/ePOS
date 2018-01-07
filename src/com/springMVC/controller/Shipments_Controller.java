package com.springMVC.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.product.model.ProdService;
import com.product.model.ProdVO;
import com.shiftreport.model.ShiftreService;
import com.shiftreport.model.ShiftreVO;
import com.shipments.model.ShipService;
import com.shipments.model.ShipVO;
import com.shipments_detail.model.ShipdetailService;
import com.shipments_detail.model.ShipdetailVO;


/**
 * Servlet implementation class Shipments_Servlet
 */

@Controller
public class Shipments_Controller {

	
	private final static ShipService shipSrv =new ShipService();
	private final static ShipdetailService shipdetailSrv=new ShipdetailService();
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/SHIPMENTS/getShipByDate.do")
	public String getShipByDate(ModelMap model,HttpServletRequest request,
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		
		@RequestParam("dateBegin")Date dateBegin,
		@RequestParam("dateEnd")Date dateEnd	) {
		
		List<String> errorMsgs = new LinkedList<String>();

		/*************************** 2.永續層存取 ***************************************/
		List<ShipVO> list=shipSrv.getByDate(dateBegin, dateEnd);
		
		if (list.isEmpty()) {
			errorMsgs.add("查無此出貨單");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("message", errorMsgs);
			return "redirect:Index1.jsp";
			}
		
		model.addAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "/SHIPMENTS/SelectShip";
	}
	
	@RequestMapping(method = RequestMethod.POST,value ="/SHIPMENTS/detailDeleteShip.do")
	public String detailDeleteShip(ModelMap model,HttpServletRequest request,
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			@RequestParam("action")String action,
			@RequestParam("ship_id") String ship_id
			) throws Exception, Exception {
		/*************************** 2.永續層存取 ***************************************/
			if("Detail".equals(action)){
				List<ShipdetailVO> detailList = shipdetailSrv.getByShipId(ship_id);
				ShipVO shipVO = shipSrv.getByShipId(ship_id);
				LinkedList<ShipVO> list = new LinkedList<ShipVO>();
				list.add(shipVO);
				request.getSession().setAttribute("list", list);
				request.getSession().setAttribute("detailList", detailList);
			
				
				return "redirect:/SHIPMENTS/AllShipdetail.jsp";
			}

			if("Delete".equals(action)){
				
				shipSrv.delete(ship_id);
				List<ShipVO> list = shipSrv.getAll();

				request.getSession().setAttribute("list", list);
				return "redirect:/SHIPMENTS/SelectShip.jsp";
			}
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			return null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/SHIPMENTS/deleteShipDetail.do")
	public String deleteShipDetail(ModelMap model,HttpServletRequest request,
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			@RequestParam("prod_id")String prod_id,
			@RequestParam("ship_id") String ship_id
			) {
		
		/*************************** 2.永續層存取 ***************************************/
		shipdetailSrv.delete(ship_id, prod_id);
		List<ShipdetailVO> detailList = shipdetailSrv.getByShipId(ship_id);
		
		ShipVO shipVO = shipSrv.getByShipId(ship_id);
		List<ShipVO> list = new LinkedList<ShipVO>();
		list.add(shipVO);
		
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
		request.getSession().setAttribute("list", list);
		request.getSession().setAttribute("detailList", detailList);
		return "redirect:/SHIPMENTS/AllShipdetail.jsp";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/SHIPMENTS/deleteShip.do")
	public String deleteShip(ModelMap model,HttpServletRequest request,
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			@RequestParam("ship_id") String ship_id
			) {
		
		/*************************** 2.永續層存取 ***************************************/
		shipSrv.delete(ship_id);
		List<ShipVO> list = shipSrv.getAll();		
		
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
		request.getSession().setAttribute("list", list);

		return "redirect:/SHIPMENTS/SelectShip.jsp";
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/SHIPMENTS/getShipByShipId.do")
	public String getShipByShipId(ModelMap model,
			@RequestParam("ship_id") String ship_id
			) {
							
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		 List<String> errorMsgs = new LinkedList<String>();

		if (ship_id == null || (ship_id.trim()).length() == 0) {
			errorMsgs.add("請輸入出貨單號");
		}
		
		if (!errorMsgs.isEmpty()) {
		model.addAttribute("message", errorMsgs);
		return "redirect:searchList.jsp";
		}
		/*************************** 2.永續層存取 ***************************************/
		
		ShipVO shipVO = shipSrv.getByShipId(ship_id);
			if (shipVO == null) {
				model.addAttribute("message", "查無此出貨單");
				return "/SHIPMENTS/searchList";
			}
			List<ShipVO> list = new LinkedList<ShipVO>();
			list.add(shipVO);
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
			
		model.addAttribute("list", list);

		return "/SHIPMENTS/SelectShip";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/SHIPMENTS/getShipByOrdId.do")
	public String getShipByOrdId(ModelMap model,
			@RequestParam("ord_id") String ord_id
			) {
							
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		 List<String> errorMsgs = new LinkedList<String>();

		if (ord_id == null || (ord_id.trim()).length() == 0) {
			errorMsgs.add("請輸入訂單編號");
		}
		
		
		/*************************** 2.永續層存取 ***************************************/
		
		List<ShipVO> list= shipSrv.getByOrderId(ord_id);
		if(list.size()==0){
			errorMsgs.add("查無此訂單");
		}
		
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("message", errorMsgs);
			return "/SHIPMENTS/searchList";
			}
		model.addAttribute("list", list);
			
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "/SHIPMENTS/SelectShip";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/SHIPMENTS/getAllShip.do")
	public String getAllShip(ModelMap model) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		List<ShipVO> list = shipSrv.getAll();

		/*************************** 2.永續層存取 ***************************************/
		
		model.addAttribute("list", list);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "/SHIPMENTS/SelectShip";
	}
	

	@RequestMapping(method = RequestMethod.POST,value = {"insertShip.do","/SHIPMENTS/insertShip.do"})
	public String insertShiftre(ModelMap model,HttpServletRequest request) throws Exception, Exception {
		
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

		String ord_id=request.getParameter("ord_id");
		Date ship_date=Date.valueOf(request.getParameter("ship_date"));
		String rec_addr=request.getParameter("rec_addr");
		String rec_name=request.getParameter("rec_name");
		String key_id=request.getParameter("key_id");
		String remark=request.getParameter("remark");
		
		
		long nowD = new java.util.Date().getTime();
		Date key_date = new Date(nowD);
		
		
		ShipVO shipVO=new ShipVO();
		shipVO.setOrd_id(ord_id);
		shipVO.setShip_date(ship_date);
		shipVO.setRec_addr(rec_addr);
		shipVO.setRec_name(rec_name);
		shipVO.setKey_id(key_id);
		shipVO.setKey_date(key_date);
		shipVO.setRemark(remark);
		
		List<ShipdetailVO> list = new LinkedList<ShipdetailVO>();
		Set<ShipdetailVO> set = new LinkedHashSet<ShipdetailVO>();

		Integer i = 1;
		while (true) {

			String x = i.toString();
			try {
				
				ProdVO prodVO = new ProdVO();
				ShipdetailVO shipdetailVO = new ShipdetailVO();

				prodVO.setProd_id(request.getParameter("prod_id" + x));
				shipdetailVO.setProdVO(prodVO);
				shipdetailVO.setProd_name(request.getParameter("prod_name" + x));
				shipdetailVO.setProd_quantity(Integer.parseInt(request.getParameter("prod_quantity" + x)));
				shipdetailVO.setShipVO(shipVO);
				set.add(shipdetailVO);
				i++;
			} catch (Exception e) {
				if(i<100){
				i++;
				continue;
				}else
				break;
			}
		}
		
		shipVO.setShipdetails(set);
		/*************************** 2.永續層存取 ***************************************/
		shipSrv.insertOne(shipVO, list);
		List<ShipVO> listAll = shipSrv.getAll();
		request.getSession().setAttribute("list", listAll);

		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/

		return "redirect:/SHIPMENTS/SelectShip.jsp";
	}	

	
}


