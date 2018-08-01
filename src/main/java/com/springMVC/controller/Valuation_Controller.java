package com.springMVC.controller;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.model.ProdService;
import com.product.model.ProdVO;
import com.valuation.model.ValuationService;
import com.valuation.model.ValuationVO;
import com.valuation_detail.model.Valuation_DetailVO;

@Controller
public class Valuation_Controller extends HttpServlet {

	private final static ValuationService vltSvc = new ValuationService();
	private final static ProdService prodSvc = new ProdService();

	@RequestMapping(method = RequestMethod.POST, value = { "/addVltList.do", "/VALUATION/addVltList.do" })
	public String addVltList(ModelMap model, HttpServletRequest request) throws Exception {

		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			/************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 *************************/
			// getVlt主檔參數
			String vlt_date = request.getParameter("vlt_date");
			if (vlt_date == null || vlt_date.trim().length() == 0) {
				errorMsgs.add("報價日期請勿空白");
			}

			String delivery_date = request.getParameter("delivery_date");
			if (delivery_date == null || delivery_date.trim().length() == 0) {
				errorMsgs.add("交貨日期請勿空白");
			}

			String total_price = request.getParameter("total_price");

			String status = request.getParameter("status");
			if (status == null || status.trim().length() == 0) {
				errorMsgs.add("狀態請勿空白");
			}
			String statusCK = "^[N,Y]{1}$";
			if (!status.trim().matches(statusCK)) {
				errorMsgs.add("狀態格式:N or Y");
			}

			String key_id = request.getParameter("key_id");
			if (key_id == null || key_id.trim().length() == 0) {
				errorMsgs.add("修改人員請勿空白");
			}
			String keyidCK = "^[E][0-9]{5}$";
			if (!key_id.trim().matches(keyidCK)) {
				errorMsgs.add("修改人員格式EX:E00001");
			}

			String remark = request.getParameter("remark");
			String key_date = request.getParameter("key_date");
			if (key_date == null || key_date.trim().length() == 0) {
				errorMsgs.add("修改日期請勿空白");
			}

			String exp_date = request.getParameter("exp_date");
			if (exp_date == null || exp_date.trim().length() == 0) {
				errorMsgs.add("有效日期請勿空白");
			}

			// 待檢核

			// setVlt主檔參數
			ValuationVO vltVO = new ValuationVO();

			vltVO.setVlt_date(Date.valueOf(vlt_date));
			vltVO.setDelivery_date(Date.valueOf(delivery_date));
			vltVO.setTotal_price(Double.parseDouble(total_price));
			vltVO.setStatus(status);
			vltVO.setKey_id(key_id);
			vltVO.setRemark(remark);
			vltVO.setKey_date(Date.valueOf(key_date));
			vltVO.setExp_date(Date.valueOf(exp_date));

			// getVlt明細檔參數
			// Valuation_DetailVO vltDetailVO = new Valuation_DetailVO();

			// String vlt_id2 = request.getParameter("vlt_id1");
			// String prod_id2 = request.getParameter("prod_id1");
			// String prod_name2 = request.getParameter("prod_name1");
			// String prod_quantity2 =
			// request.getParameter("prod_quantity1");
			// String prod_price2 = request.getParameter("prod_price1");
			//
			// setVlt明細檔參數

			List<Valuation_DetailVO> list = new LinkedList<Valuation_DetailVO>();

			Valuation_DetailVO vltDetailVO = null;
			ProdVO prodVO = null;

			Set<Valuation_DetailVO> set = new LinkedHashSet<Valuation_DetailVO>();

			int i = 1;
			while (true) {
				try {

					vltDetailVO = new Valuation_DetailVO();

					vltDetailVO.setValuationVO(vltVO);

					prodVO = new ProdVO();
					prodVO.setProd_id(request.getParameter("prod_id" + String.valueOf(i)));
					vltDetailVO.setProdVO(prodVO);

					// vltDetailVO.setProd_id(request.getParameter("prod_id" +
					// String.valueOf(i)));
					vltDetailVO.setProd_name(request.getParameter("prod_name" + String.valueOf(i)));
					vltDetailVO.setProd_quantity(
							Integer.parseInt(request.getParameter("prod_quantity" + String.valueOf(i))));
					vltDetailVO
							.setProd_price(Double.parseDouble(request.getParameter("prod_price" + String.valueOf(i))));
					// list.add(vltDetailVO);
					set.add(vltDetailVO);
					i++;
				} catch (Exception e) {
					if (i < 100) {
						i++;
						continue;
					} else
						break;
				}

			}
			/*************************** 2.開始新增資料 ***************************************/
			vltVO.setValuationdetails(set);
			// ValuationService vltSvc = new ValuationService();
			vltVO = vltSvc.addVltList(vltVO, list);
			List<ValuationVO> listAll = vltSvc.getAll();

			request.getSession().setAttribute("list", listAll);

		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add("商品不可重複");
		}

		/*****************************
		 * 3.新增完成,準備轉交(Send the Success view)
		 ***********/
		String url="";
		if(errorMsgs.size() >0){
			url = "/VALUATION/ValuationList.jsp";
		}else{
			url = "redirect:/VALUATION/SelectVlt.jsp";
		}
		
		return url;
		/*************************** 其他可能的錯誤處理 **********************************/
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/getVltDate.do", "/VALUATION/getVltDate.do" })
	public String getVltDate(ModelMap model, HttpServletRequest request) throws Exception {

		Date dateBegin = Date.valueOf(request.getParameter("dateBegin"));
		Date dateEnd = Date.valueOf(request.getParameter("dateEnd"));

		ValuationService vltsvc = new ValuationService();
		try {
			List<ValuationVO> list = vltsvc.Select_vlt_date(dateBegin, dateEnd);
			model.addAttribute("list", list);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "/VALUATION/SelectVlt";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/Querydetail_DeleteVlt.do",
			"/VALUATION/Querydetail_DeleteVlt.do" })
	public String Querydetail_DeleteVlt(ModelMap model, HttpServletRequest request,
			/***************************
			 * * 1.接收請求參數 - 輸入格式的錯誤處理
			 *************************/
			@RequestParam("action") String action, @RequestParam("vlt_id") String vlt_id) throws Exception {
		/*************************** 2.永續層存取 ***************************************/
		if ("Detail".equals(action)) {

			// OrderService ordSvc = new OrderService();
			try {
				List<Valuation_DetailVO> detailList = vltSvc.Select_valuation_detailALL(vlt_id);
				request.setAttribute("detailList", detailList);

				ValuationVO vltVO = vltSvc.Select_vlt_id(vlt_id);
				LinkedList<ValuationVO> list = new LinkedList<ValuationVO>();
				list.add(vltVO);
				request.setAttribute("list", list);

			} catch (Exception e) {

				e.printStackTrace();
			}
			return "/VALUATION/AllVltDetail";
		}

//		if ("Delete".equals(action)) {
//			// OrderService ordSvc = new OrderService();
//			try {
//				vltSvc.delete(vlt_id);
//
//				List<ValuationVO> list = vltSvc.getAll();
//
//				model.addAttribute("list", list);
//
//			} catch (Exception e) {
//
//				e.printStackTrace();
//			}
//			return "/VALUATION/SelectVlt";
//		}
		/***************************
		 * * 3.完成,準備轉交(Send the Success view)
		 ***********/
		return null;

	}

	@RequestMapping(method = RequestMethod.POST, value = { "/getAllVlt.do", "/VALUATION/getAllVlt.do" })
	public String getAllVlt(ModelMap model, HttpServletRequest request) throws Exception {

		// ValuationService vltSvc = new ValuationService();
		List<ValuationVO> list;

		System.out.println("======================================");
		try {
			list = vltSvc.getAll();
			model.addAttribute("list", list);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return "/VALUATION/SelectVlt";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/DeleteVltDetail.do", "/VALUATION/DeleteVltDetail.do" })
	public String DeleteVltDetail(ModelMap model, HttpServletRequest request) throws Exception {

		String vlt_id = request.getParameter("vlt_id");
		String prod_id = request.getParameter("prod_id");

		ValuationService vltSvc = new ValuationService();
		try {
			vltSvc.delete(vlt_id, prod_id);

			List<ValuationVO> detailList = vltSvc.Select_valuation_detailALL(vlt_id);
			model.addAttribute("detailList", detailList);

			// ValuationService shipSrv =new ValuationService();
			ValuationVO vltVO = vltSvc.Select_vlt_id(vlt_id);
			List<ValuationVO> list = new LinkedList<ValuationVO>();
			list.add(vltVO);
			model.addAttribute("list", list);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "/VALUATION/AllVltDetail";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/getByVlt_id.do", "/VALUATION/getByVlt_id.do" })
	public String getByVlt_id(ModelMap model, HttpServletRequest request) throws Exception {

		List<String> errorMsgs = new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
		try {
			String vlt_id = request.getParameter("vlt_id");
			if (vlt_id == null || (vlt_id.trim()).length() == 0) {
				errorMsgs.add("請輸入報價單編號");
			}

			// ValuationService vltSvc =new ValuationService();
			ValuationVO vltVO;

			vltVO = vltSvc.Select_vlt_id(vlt_id);
			if (vltVO == null) {
				errorMsgs.add("查無此報價單號");
			}
			if (!errorMsgs.isEmpty()) {
				return "/VALUATION/searchList";
			}
			List<ValuationVO> list = new LinkedList<ValuationVO>();
			list.add(vltVO);
			model.addAttribute("list", list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/VALUATION/SelectVlt";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/setVltStatus.do", "/VALUATION/setVltStatus.do" })
	public String setVltStatus(ModelMap model, HttpServletRequest request) throws Exception {

		List<String> errorMsgs = new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);

		String vlt_id = request.getParameter("vlt_id");

		// ValuationService vltSvc =new ValuationService();
		try {
			vltSvc.setStatus("D", vlt_id);

//			List list = vltSvc.getAllByN();

//			request.getSession().setAttribute("list", list);
			// request.setAttribute("list", list);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "redirect:/VALUATION/SelectVltAllForCHK.jsp";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/forVltCHK.do", "/VALUATION/forVltCHK.do" })
	public String forVltCHK(ModelMap model, HttpServletRequest request,
			/***************************
			 * * 1.接收請求參數 - 輸入格式的錯誤處理
			 *************************/
			@RequestParam("action") String action, @RequestParam("vlt_id") String vlt_id) throws Exception {
		/*************************** 2.永續層存取 ***************************************/
		if ("CHK".equals(action)) {

			// OrderService ordSvc = new OrderService();
			try {
				List<Valuation_DetailVO> detailList = vltSvc.Select_valuation_detailALL(vlt_id);
				request.setAttribute("detailList", detailList);

				ValuationVO vltVO = vltSvc.Select_vlt_id(vlt_id);
				LinkedList<ValuationVO> list = new LinkedList<ValuationVO>();
				list.add(vltVO);
				request.setAttribute("list", list);
				request.setAttribute("vltVO", vltVO);

			} catch (Exception e) {

				e.printStackTrace();
			}
			/***************************
			 * * 3.完成,準備轉交(Send the Success view)
			 ***********/
			return "/VALUATION/AllVltDetailForCHK";
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/VltToOrd.do", "/VALUATION/VltToOrd.do" })
	public String VltToOrd(ModelMap model, HttpServletRequest request,
			/***************************
			 * * 1.接收請求參數 - 輸入格式的錯誤處理
			 *************************/
			@RequestParam("action") String action, @RequestParam("vlt_id") String vlt_id) throws Exception {
		/*************************** 2.永續層存取 ***************************************/
		if ("toOrd".equals(action)) {

			// OrderService ordSvc = new OrderService();
			try {
				List<Valuation_DetailVO> detailList = vltSvc.Select_valuation_detailALL(vlt_id);
				request.setAttribute("detailList", detailList);

				ValuationVO vltVO = vltSvc.Select_vlt_id(vlt_id);
				LinkedList<ValuationVO> list = new LinkedList<ValuationVO>();
				list.add(vltVO);
				request.setAttribute("list", list);
				request.setAttribute("vltVO", vltVO);

			} catch (Exception e) {

				e.printStackTrace();
			}
			/***************************
			 * * 3.完成,準備轉交(Send the Success view)
			 ***********/
			return "/ORDER/VltToOrd";
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/getProd_DDL.do","/VALUATION/getProd_DDL.do" })
	public void getProd_DDL(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			
			list = prodSvc.getAll();
			
			for(ProdVO prodVO : list){
				Map map = new HashMap();
				map.put("SelectValue",prodVO.getProd_id()+"^"+prodVO.getProd_name()+"^" + prodVO.getProd_mkprice());
				map.put("SelectText", prodVO.getProd_name());
				prodlist.add(map);
			}
			
			String jsonString = JSONValue.toJSONString(prodlist);
			out.println(jsonString);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
