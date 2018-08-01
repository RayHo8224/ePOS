package com.springMVC.controller;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.model.ComVO;
import com.product.model.ProdVO;
import com.quotation.model.QuoService;
import com.quotation.model.QuoVO;
import com.quotation_detail.model.QuoDetailService;
import com.quotation_detail.model.QuoDetailVO;
import com.requisition.model.ReqService;
import com.requisition.model.ReqVO;
import com.requisition_detail.model.ReqDetailService;
import com.requisition_detail.model.ReqDetailVO;

//@WebServlet(
//		urlPatterns= {"/Quotation.do","/QUOTATION/Quotation.do"})

@Controller
public class Quotation_Controller {

	private final static QuoService quoSrv = new QuoService();

	private final static QuoDetailService quoDetailSrv = new QuoDetailService();

	// public QuoService getQuoSrv() {
	// return quoSrv;
	// }
	//
	// public void setQuoSrv(QuoService quoSrv){
	// this.quoSrv=quoSrv;
	// }
	@RequestMapping(method = RequestMethod.POST, value = "QUOTATION/selectByDate.do")
	public String selectByDate(ModelMap model, Date begin_date, Date end_date) {
		List<QuoVO> list = null;
		list = quoSrv.getByDate(begin_date, end_date);
		model.addAttribute("list", list);
		return "QUOTATION/SelectbyDate2";
	}

	@RequestMapping(method = RequestMethod.POST, value = "QUOTATION/selectOfN.do")
	public String selectOfN(ModelMap model) {
		List<QuoVO> list = new LinkedList<QuoVO>();
		list = quoSrv.selectOfN();
		model.addAttribute("list", list);
		return "QUOTATION/SelectOfN";
	}

	@RequestMapping(method = RequestMethod.POST, value = "QUOTATION/selectOfY.do")
	public String selectOfY(ModelMap model) {
		List<QuoVO> list = new LinkedList<QuoVO>();
		list = quoSrv.selectOfY();
		model.addAttribute("list", list);
		return "QUOTATION/SelectOfY";
	}

	@RequestMapping(method = RequestMethod.POST, value = "QUOTATION/addQuo0.do")
	public String addQuo0(ModelMap model) {
		List<ReqVO> list = new LinkedList<ReqVO>();
		list = quoSrv.findYReq();
		model.addAttribute("list", list);
		return "QUOTATION/addQuo0";
	}

	@RequestMapping(method = RequestMethod.GET, value = "QUOTATION/addQuo")
	public String addQuo(ModelMap model) {
		System.out.println("OK");
		QuoVO quoVO = new QuoVO();
		// List<QuoDetailVO> list = new LinkedList<QuoDetailVO>();
		model.addAttribute("quoVO", quoVO);
		// model.addAttribute("list",list);
		return "QUOTATION/addQuo";
	}

	@RequestMapping(method = RequestMethod.POST, value = "QUOTATION/getTheReq.do")
	public String getTheReq(@RequestParam("req_id") String req_id, ModelMap model, HttpServletRequest request) {
		ReqVO reqVO = quoSrv.findByReqKey(req_id);
		List<ComVO> list = quoSrv.getAllCom();
		List<ReqDetailVO> reqDetailVO = new ReqDetailService().getByReqId(req_id);
		request.setAttribute("reqDetailVO", reqDetailVO);
		request.setAttribute("reqVO", reqVO);
		request.setAttribute("list", list);
		return "QUOTATION/addQuo1";
	}

	@RequestMapping(method = RequestMethod.POST, value = "QUOTATION/test.do")
	public void test(ModelMap model, QuoVO quoVO) {

		// List<QuoDetailVO> list = new LinkedList<QuoDetailVO>();

		// model.addAttribute("list",list);
		System.out.println("====================");
		System.out.println(quoVO.getRemark());
		System.out.println("====================");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/QUOTATION/getByQuo_id.do")
	public String getByQuo_id(@RequestParam("quo_id") String quo_id, ModelMap model,HttpServletRequest request) {
		/***************************
		 * * 1.接收請求參數 - 輸入格式的錯誤處理
		 *************************/
		List<String> errorMsgs = new LinkedList<String>();
		if (quo_id == null || quo_id == "" || (quo_id.trim()).length() == 0) {
			errorMsgs.add("請輸入詢價單編號");
		}
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("message", errorMsgs);
			return "redirect:/SelectQuo.jsp";
		}
		/*************************** 2.永續層存取 ***************************************/
		QuoVO quoVO = null;
		List list = new LinkedList<QuoVO>();

		try {
			quoVO = quoSrv.getByQuoId(quo_id);
			List<QuoDetailVO> quoDetailVO = quoDetailSrv.getByQuoId(quo_id);

			if (quoVO == null) {
				model.addAttribute("errorMsgs", "查無資料");
				return "redirect:/SelectQuo.jsp";
			}
			
			/***************************
			 * * 3.完成,準備轉交(Send the Success view)
			 ***********/
			
			list.add(quoVO);

			request.setAttribute("list", list);
			request.setAttribute("quoVO", quoVO);
			request.setAttribute("quoDetailVO", quoDetailVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return "/QUOTATION/selectQuo1";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/QUOTATION/getByQuo_id2.do")
	public String getByQuo_id2(@RequestParam("quo_id") String quo_id, ModelMap model,HttpServletRequest request) {
		/***************************
		 * * 1.接收請求參數 - 輸入格式的錯誤處理
		 *************************/
		List<String> errorMsgs = new LinkedList<String>();
		if (quo_id == null || quo_id == "" || (quo_id.trim()).length() == 0) {
			errorMsgs.add("請輸入詢價單編號");
		}
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("message", errorMsgs);
			return "redirect:/SelectQuo.jsp";
		}
		/*************************** 2.永續層存取 ***************************************/
		QuoVO quoVO = null;
		List list = new LinkedList<QuoVO>();

		try {
			quoVO = quoSrv.getByQuoId(quo_id);
			List<QuoDetailVO> quoDetailVO = quoDetailSrv.getByQuoId(quo_id);
			if (quoVO == null) {
				model.addAttribute("errorMsgs", "查無資料");
				return "redirect:/SelectQuo.jsp";
			}
			/***************************
			 * * 3.完成,準備轉交(Send the Success view)
			 ***********/
			list.add(quoVO);
			request.setAttribute("list", list);
			request.setAttribute("quoVO", quoVO);
			request.setAttribute("quoDetailVO", quoDetailVO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return "/QUOTATION/selectQuo2";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/QUOTATION/getByQuo_id3.do")
	public String getByQuo_id3(@RequestParam("quo_id") String quo_id, ModelMap model,HttpServletRequest request) {
		/***************************
		 * * 1.接收請求參數 - 輸入格式的錯誤處理
		 *************************/
		List<String> errorMsgs = new LinkedList<String>();
		if (quo_id == null || quo_id == "" || (quo_id.trim()).length() == 0) {
			errorMsgs.add("請輸入詢價單編號");
		}
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("message", errorMsgs);
			return "redirect:/SelectQuo.jsp";
		}
		/*************************** 2.永續層存取 ***************************************/
		QuoVO quoVO = null;
		List list = new LinkedList<QuoVO>();
		String com_id = null;
		String com_name = null;

		try {
			quoVO = quoSrv.getByQuoId(quo_id);
//			Set<QuoDetailVO> set = quoVO.getQuoDetails();
			List<QuoDetailVO> quoDetailVO = quoDetailSrv.getByQuoId(quo_id);

			for (QuoDetailVO s : quoDetailVO) {
				com_name = s.getCom_name();
			}
			com_id = quoSrv.getComId(com_name);
			if (quoVO == null) {
				model.addAttribute("errorMsgs", "查無資料");
				return "redirect:/SelectQuo.jsp";
			}
			/***************************
			 * * 3.完成,準備轉交(Send the Success view)
			 ***********/
			list.add(quoVO);
			request.setAttribute("list", list);
			request.setAttribute("quoVO", quoVO);
			request.setAttribute("com_id", com_id);
			request.setAttribute("com_name", com_name);
			request.setAttribute("quoDetailVO", quoDetailVO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	

		return "/QUOTATION/selectQuo3";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/QUOTATION/getByReq_id.do")
	public String getByReq_id(@RequestParam("req_id") String req_id, ModelMap model) {
		/***************************
		 * * 1.接收請求參數 - 輸入格式的錯誤處理
		 *************************/
		List<String> errorMsgs = new LinkedList<String>();
		if (req_id == null || req_id == "" || (req_id.trim()).length() == 0) {
			errorMsgs.add("請輸入請購單編號");
		}
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("message", errorMsgs);
			return "redirect:/SelectQuo.jsp";
		}
		/*************************** 2.永續層存取 ***************************************/
		QuoVO quoVO;
		List list = new LinkedList<QuoVO>();

		try {
			list = quoSrv.getByReqId(req_id);

			if (list == null) {
				model.addAttribute("errorMsgs", "查無資料");
				return "redirect:/QUOTATION/SelectQuo.jsp";
			}
			/***************************
			 * * 3.完成,準備轉交(Send the Success view)
			 ***********/

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("list", list);

		return "/QUOTATION/AllQuo";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/QUOTATION/getAllQuo.do")
	public String getAllQuo(ModelMap model) {
		/***************************
		 * * 1.接收請求參數 - 輸入格式的錯誤處理
		 *************************/

		/*************************** 2.永續層存取 ***************************************/
		QuoService quoSrv = new QuoService();
		List<QuoVO> list = null;
		try {
			list = quoSrv.getAll();
		} catch (Exception e) {

			e.printStackTrace();
		}
		model.addAttribute("list", list);

		/***************************
		 * * 3.完成,準備轉交(Send the Success view)
		 ***********/

		return "/QUOTATION/AllQuo";

	}

	@RequestMapping(method = RequestMethod.POST, value = { "/QUOTATION/insertProd.do" })
	public String insertProd(ModelMap model, HttpServletRequest req) throws Exception {

		Integer i = 1;

		while (true) {
			String x = i.toString();
			try {
				ProdVO prodVO = new ProdVO();
				prodVO.setProd_name(req.getParameter("prod_name" + x));
				prodVO.setCom_id(req.getParameter("com_id" + x));
				prodVO.setProd_group(req.getParameter("prod_group" + x));
				prodVO.setProd_cost(Integer.valueOf(req.getParameter("prod_cost" + x)));
				prodVO.setProd_mkprice(Integer.valueOf(req.getParameter("prod_mkprice" + x)));
				prodVO.setProd_stock(Integer.valueOf(req.getParameter("prod_stock" + x)));
				prodVO.setProd_q_safty(Integer.valueOf(req.getParameter("prod_q_safty" + x)));
				prodVO.setProd_spec(req.getParameter("prod_spec" + x));
				prodVO.setRemark(req.getParameter("remark" + x));
				prodVO.setPicture(null);
				prodVO.setStatus("Y");

				quoSrv.addProd(prodVO);

				i++;

			} catch (Exception e) {
				if (i < 100) {
					i++;
					continue;
				} else
					break;
			}
		}
		String status = "S";
		String quo_id = req.getParameter("quo_id");
		quoSrv.setStatus(status, quo_id);
		List<ProdVO> list = new LinkedList<ProdVO>();
		list = quoSrv.getAllProd();
		model.addAttribute("list", list);
		return "QUOTATION/allProd";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/insertQuo.do", "/QUOTATION/insertQuo.do" })
	public String insertQuo(ModelMap model, HttpServletRequest req, String status2) throws Exception, Exception {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ******************/
		List<String> errorMsgs = new LinkedList<String>();
		String req_id = req.getParameter("req_id");
		if (req_id == null || req_id.trim().length() == 0) {
			errorMsgs.add("請購單編號請勿空白");
		}

		String key_id = req.getParameter("key_id");

		Date key_date = Date.valueOf(req.getParameter("key_date"));

		String remark = req.getParameter("remark");

		String status = req.getParameter("status");
		if (status == null || status.trim().length() == 0) {
			errorMsgs.add("狀態請勿空白");
		}
		// String statusCK = "^[N,Y]{1}$";
		// if(!status.trim().matches(statusCK) ) {
		// errorMsgs.add("狀態格式:N or Y");
		// }

		// System.out.println(req_id);

		QuoVO quoVO = new QuoVO();
		// quoVO.setQuo_id(quo_id);
		quoVO.setReq_id(req_id);
		quoVO.setKey_id(key_id);
		quoVO.setKey_date(key_date);
		quoVO.setRemark(remark);
		quoVO.setStatus(status);

		List<QuoDetailVO> list = new LinkedList<QuoDetailVO>();
		Set<QuoDetailVO> set = new LinkedHashSet<QuoDetailVO>();

		Integer i = 1;

		while (true) {
			String x = i.toString();
			try {
				QuoDetailVO quoDetailVO = new QuoDetailVO();
				// quoDetailVO.setQuo_id(req.getParameter("quo_id"+x));
				// quoDetailVO.setQuoVO(quoVO);
				quoDetailVO.setProd_name(req.getParameter("prod_name" + x));
				quoDetailVO.setProd_quantity(Integer.parseInt(req.getParameter("prod_quantity" + x)));
				quoDetailVO.setProd_cost(Integer.parseInt(req.getParameter("prod_cost" + x)));
				quoDetailVO.setCom_name(req.getParameter("com_name"));
				// quoDetailVO.setDelivery_date(Date.valueOf(req.getParameter("delivery_date")));
				quoDetailVO.setRemark(req.getParameter("remark" + x));
				// quoDetailVO.setKey_id(req.getParameter("key_id"));
				// quoDetailVO.setKey_date(Date.valueOf(req.getParameter("key_date")));
				quoDetailVO.setQuoVO(quoVO);

				set.add(quoDetailVO);
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
		quoVO.setQuoDetails(set);
		quoSrv.addQuo(quoVO, list);
		status2 = "S";
		quoSrv.setReqStatus(status2, req_id);
		List<QuoVO> listAll = quoSrv.getAll();
		req.getSession().setAttribute("list", listAll);

		/***************************
		 * 3.新增完成,準備轉交(Send the Success view)
		 ***********/
		return "/QUOTATION/AllQuo"; // 新增成功後轉交AllQuo.jsp
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/QUOTATION/deleteDetailQuo.do", "/deleteDetailQuo.do" })
	public String deleteDetail(ModelMap model, HttpServletRequest request,
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 *******************/
			@RequestParam("prod_name") String prod_name, @RequestParam("quo_id") String quo_id) {
		/*************************** 2.開始查詢資料 ***************************************/
		quoDetailSrv.delete(quo_id, prod_name);
		List<QuoDetailVO> detailList = quoDetailSrv.getByQuoId(quo_id);
		QuoVO quoVO = quoSrv.getByQuoId(quo_id);
		List<QuoVO> list = new LinkedList<QuoVO>();
		list.add(quoVO);
		/***************************
		 * 3.查詢完成,準備轉交(Send the Success view)
		 ***********/
		request.getSession().setAttribute("detailList", detailList);
		request.getSession().setAttribute("list", list);
		return "QUOTATION/AllQuo"; // 查詢完成後轉交AllQuo.jsp
	}

	@RequestMapping(method = RequestMethod.POST, value = "/QUOTATION/DetailDeleteQuo.do")
	public String DetailDeleteQuo(ModelMap model, HttpServletRequest request,
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 *******************/
			@RequestParam("quo_id") String quo_id, @RequestParam("action") String action) throws Exception, Exception {
		/*************************** 2.開始查詢資料 ***************************************/

		if ("Detail".equals(action)) {
			List<QuoDetailVO> detailList = quoDetailSrv.getByQuoId(quo_id);
			QuoVO quoVO = quoSrv.getByQuoId(quo_id);
			LinkedList<QuoVO> list = new LinkedList<QuoVO>();
			list.add(quoVO);
			request.getSession().setAttribute("detailList", detailList);
			request.getSession().setAttribute("list", list);
			return "redirect:/QUOTATION/AllQuodetail.jsp";
		}
		if ("delete".equals(action)) {
			quoSrv.delete(quo_id);
			List<QuoVO> list = quoSrv.getAll();
			request.getSession().setAttribute("list", list);
			return "QUOTATION//AllQuo"; // 查詢完成後轉交listAllEmp.jsp
		}
		if ("update".equals(action)) {
			QuoVO quoVO = quoSrv.getByQuoId(quo_id);
			List<QuoDetailVO> detailList = quoDetailSrv.getByQuoId(quo_id);
			request.setAttribute("detailList", detailList);

			request.setAttribute("quoVO", quoVO);
			return "/QUOTATION/updateQuo";
		}

		/***************************
		 * 3.查詢完成,準備轉交(Send the Success view)
		 ***********/
		return null;

	}

	@RequestMapping(method = RequestMethod.POST, value = { "/QUOTATION/updateQuo2.do" })
	public String updateQuo2(ModelMap model, HttpServletRequest req, String quo_id, String status) {
		try {
			quoSrv.setStatus(status, quo_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List list = quoSrv.getAll();
		model.addAttribute("list", list);
		return "/QUOTATION/AllQuo";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/QUOTATION/updateQuo3.do" })
	public String updateQuo3(ModelMap model, HttpServletRequest req, String quo_id, String status) {
		try {
			quoSrv.setStatus(status, quo_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List list = quoSrv.selectOfN();
		model.addAttribute("list", list);
		return "/QUOTATION/SelectOfN";
	}

	// ======================================================================================
	@RequestMapping(method = RequestMethod.POST, value = { "/QUOTATION/updateQuo.do", "/updateQuo.do" })
	public String updateQuo(ModelMap model, HttpServletRequest req) throws Exception {
		/***************************
		 * * 1.接收請求參數 - 輸入格式的錯誤處理
		 *************************/
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		String quo_id = req.getParameter("quo_id");

		String req_id = req.getParameter("req_id");
		if (req_id == null || req_id.trim().length() == 0) {
			errorMsgs.add("請購單編號請勿空白");
		}

		String key_id = req.getParameter("key_id");

		Date key_date = Date.valueOf(req.getParameter("key_date"));

		String remark = req.getParameter("remark");

		String status = req.getParameter("status");
		if (status == null || status.trim().length() == 0) {
			errorMsgs.add("狀態請勿空白");
		}
		// String statusCK = "^[N,Y]{1}$";
		// if(!status.trim().matches(statusCK) ) {
		// errorMsgs.add("狀態格式:N or Y");
		// }
		QuoVO quoVO = new QuoVO();
		quoVO.setQuo_id(quo_id);
		quoVO.setReq_id(req_id);
		quoVO.setKey_id(key_id);
		quoVO.setKey_date(key_date);
		quoVO.setRemark(remark);
		quoVO.setStatus(status);

		List<QuoDetailVO> list = new LinkedList<QuoDetailVO>();
		Set<QuoDetailVO> set = new LinkedHashSet<QuoDetailVO>();

		Integer i = 1;

		while (true) {
			String x = i.toString();
			try {
				QuoDetailVO quoDetailVO = new QuoDetailVO();
				// quoDetailVO.setQuo_id(req.getParameter("quo_id"+x));
				// quoDetailVO.setQuoVO(quoVO);
				quoDetailVO.setProd_name(req.getParameter("prod_name" + x));
				quoDetailVO.setProd_quantity(Integer.parseInt(req.getParameter("prod_quantity" + x)));
				quoDetailVO.setProd_cost(Integer.parseInt(req.getParameter("prod_cost" + x)));
				quoDetailVO.setCom_name(req.getParameter("com_name" + x));
				// quoDetailVO.setDelivery_date(Date.valueOf(req.getParameter("delivery_date"
				// + x)));
				quoDetailVO.setRemark(req.getParameter("remark" + x));
				// quoDetailVO.setKey_id(req.getParameter("key_id" + x));
				// quoDetailVO.setKey_date(Date.valueOf(req.getParameter("key_date"
				// + x)));
				quoDetailVO.setQuoVO(quoVO);

				set.add(quoDetailVO);
				i++;

			} catch (Exception e) {
				if (i < 100) {
					i++;
					continue;
				} else
					break;
			}
		}
		try {
			quoVO.setQuoDetails(set);
			quoSrv.updateQuo(quoVO);
			QuoVO quoVO2 = quoSrv.getByQuoId(quo_id);
			List<QuoDetailVO> detailList = quoDetailSrv.getByQuoId(quo_id);

			req.setAttribute("quoDetailVO", detailList);
			req.setAttribute("quoVO", quoVO2);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return "/QUOTATION/selectQuo1";

	}
}
