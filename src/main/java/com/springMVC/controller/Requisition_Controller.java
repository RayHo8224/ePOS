package com.springMVC.controller;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.model.ProdVO;
import com.requisition.model.ReqService;
import com.requisition.model.ReqVO;
import com.requisition_detail.model.ReqDetailService;
import com.requisition_detail.model.ReqDetailVO;

@Controller
public class Requisition_Controller {

	private final static ReqService reqSrv = new ReqService();

	private final static ReqDetailService reqDetailSrv = new ReqDetailService();

	@RequestMapping(method = RequestMethod.GET, value = "/REQUISITION/addReq")
	public String addReq(ModelMap model) {
		ReqVO reqVO = new ReqVO();
		model.addAttribute("reqVO", reqVO);
		return "REQUISITION/addReq";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/REQUISITION/checkProdId.do" })
	public void checkProdId(ModelMap model, HttpServletResponse response, String prod_name) throws Exception {
		List<ProdVO> list = reqSrv.getAllProd();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = response.getWriter();
		for (ProdVO s : list) {
			String name = s.getProd_name();
			if (prod_name.equals(name)) {
				out.println("exist");
				System.out.println("exist");
			}
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/REQUISITION/getByReq_id.do")
	public String getByReq_id(@RequestParam("req_id") String req_id, ModelMap model,HttpServletRequest request) {
		/***************************
		 * * 1.接收請求參數 - 輸入格式的錯誤處理
		 *************************/
		List<String> errorMsgs = new LinkedList<String>();
		if (req_id == null || req_id == "" || (req_id.trim()).length() == 0) {
			errorMsgs.add("請輸入請購單編號");
		}
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("message", errorMsgs);
			return "redirect:/SelectReq.jsp";
		}
		/*************************** 2.永續層存取 ***************************************/
		ReqVO reqVO = reqSrv.getByReqId(req_id);
		if (reqVO == null) {
			model.addAttribute("errorMsgs", "查無資料");
			return "redirect:/REQUISITION/SelectReq.jsp";
		}
		List<ReqVO> list = new LinkedList<ReqVO>();
		list.add(reqVO);
		
		List<ReqDetailVO> reqDetaolList = new ReqDetailService().getByReqId(req_id);
		/***************************
		 * * 3.完成,準備轉交(Send the Success view)
		 ***********/
		request.setAttribute("list", list);
		request.setAttribute("reqDetaolList", reqDetaolList);
		
//		model.addAttribute("list", list);

		return "/REQUISITION/AllReq2";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/REQUISITION/getByReq_id2.do")
	public String getByReq_id2(@RequestParam("req_id") String req_id, ModelMap model,HttpServletRequest request) {

		ReqVO reqVO = reqSrv.getByReqId(req_id);
		List<ReqDetailVO> reqDetailVO = reqDetailSrv.getByReqId(req_id);
		request.setAttribute("reqVO", reqVO);
		request.setAttribute("reqDetailVO", reqDetailVO);

		return "/REQUISITION/updateReq2";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/REQUISITION/selectOfN.do")
	public String selectOfN(ModelMap model) {
		List<ReqVO> list = null;
		try {
			list = reqSrv.selectOfN();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		return "/REQUISITION/AllReq4";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/REQUISITION/findByDate.do")
	public String findByDate(@RequestParam("begin_date") Date begin_date, @RequestParam("end_date") Date end_date,
			ModelMap model) {

		List<ReqVO> list = null;
		try {
			list = reqSrv.getByDate(begin_date, end_date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("list", list);

		return "/REQUISITION/AllReq3";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/REQUISITION/getAllReq.do")
	public String getAllReq(ModelMap model) {
		/***************************
		 * * 1.接收請求參數 - 輸入格式的錯誤處理
		 *************************/

		/*************************** 2.永續層存取 ***************************************/
		ReqService reqSrv = new ReqService();
		List<ReqVO> list = null;
		try {
			list = reqSrv.getAll();
		} catch (Exception e) {

			e.printStackTrace();
		}
		model.addAttribute("list", list);

		/***************************
		 * * 3.完成,準備轉交(Send the Success view)
		 ***********/

		return "/REQUISITION/AllReq";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/REQUISITION/updateReq5.do")
	public String updateReq2(ModelMap model, HttpServletRequest req) throws Exception {
		String req_id = req.getParameter("req_id");
		String key_id = req.getParameter("key_id");
		Date key_date = Date.valueOf(req.getParameter("key_date"));
		String status = req.getParameter("status");

		ReqVO reqVO = new ReqVO();
		reqVO.setReq_id(req_id);
		reqVO.setKey_id(key_id);
		;
		reqVO.setKey_date(key_date);
		reqVO.setStatus(status);

		List<ReqDetailVO> list = new LinkedList<ReqDetailVO>();
		Set<ReqDetailVO> setDetail = new LinkedHashSet<ReqDetailVO>();
		Integer i = 1;

		while (true) {

			String x = i.toString();
			try {

				ReqDetailVO reqDetailVO = new ReqDetailVO();

				reqDetailVO.setReqVO(reqVO);
				reqDetailVO.setProd_name(req.getParameter("prod_name" + x));
				reqDetailVO.setProd_quantity(Integer.parseInt(req.getParameter("prod_quantity" + x)));

				setDetail.add(reqDetailVO);
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
		reqVO.setReqDetails(setDetail);
		reqSrv.updateReq(reqVO);
		reqVO = reqSrv.getByReqId(req_id);
		List<ReqVO> list2 = new LinkedList<ReqVO>();
		list2.add(reqVO);
		/***************************
		 * * 3.完成,準備轉交(Send the Success view)
		 ***********/
		List<ReqDetailVO> reqDetailVO = reqDetailSrv.getByReqId(req_id);

		req.setAttribute("reqDetaolList", reqDetailVO);
		System.out.println(reqDetailVO.get(0).getProd_name());
		req.setAttribute("list", list2);
//		model.addAttribute("list", list2);

		return "/REQUISITION/AllReq2";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/REQUISITION/insertReq.do")
	public String insertReq(ModelMap model, HttpServletRequest req) throws Exception, Exception {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ******************/
		List<String> errorMsgs = new LinkedList<String>();
		String key_id = req.getParameter("key_id");
		if (key_id == null || key_id.trim().length() == 0) {
			errorMsgs.add("修改人員請勿空白");
		}
		String keyidCK = "^[E][0-9]{5}$";
		if (!key_id.trim().matches(keyidCK)) {
			errorMsgs.add("修改人員格式:E00001");
		}

		String status = req.getParameter("status");
		if (status == null || status.trim().length() == 0) {
			errorMsgs.add("狀態請勿空白");
		}
		// String statusCK = "^[N,Y]{1}$";
		// if(!status.trim().matches(statusCK) ) {
		// errorMsgs.add("狀態格式:N or Y");
		// }

		// Long now = new java.util.Date().getTime();
		// Date key_date = new Date(now);

		Date key_date = null;
		try {
			key_date = Date.valueOf(req.getParameter("key_date"));
		} catch (IllegalArgumentException e) {
			key_date = new Date(System.currentTimeMillis());
			errorMsgs.add("請輸入建檔日期");
		}

		ReqVO reqVO = new ReqVO();
		reqVO.setKey_id(key_id);
		;
		reqVO.setKey_date(key_date);
		reqVO.setStatus(status);

		List<ReqDetailVO> list = new LinkedList<ReqDetailVO>();
		Set<ReqDetailVO> setDetail = new LinkedHashSet<ReqDetailVO>();
		Integer i = 1;

		while (true) {

			String x = i.toString();
			try {

				ReqDetailVO reqDetailVO = new ReqDetailVO();

				reqDetailVO.setReqVO(reqVO);
				reqDetailVO.setProd_name(req.getParameter("prod_name" + x));
				reqDetailVO.setProd_quantity(Integer.parseInt(req.getParameter("prod_quantity" + x)));

				setDetail.add(reqDetailVO);
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
		reqVO.setReqDetails(setDetail);
		reqSrv.addReq(reqVO, list);
		List<ReqVO> listAll = reqSrv.getAll();
		req.getSession().setAttribute("list", listAll);

		/***************************
		 * 3.新增完成,準備轉交(Send the Success view)
		 ***********/
		return "redirect:/REQUISITION/AllReq.jsp"; // 新增成功後轉交AllReq.jsp
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/REQUISITION/deleteReqDetail.do", "/deleteReqDetail.do" })
	public String deleteReqDetail(ModelMap model, HttpServletRequest request,
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 *******************/
			@RequestParam("prod_name") String prod_name, @RequestParam("req_id") String req_id) {
		/*************************** 2.開始查詢資料 ***************************************/
		reqDetailSrv.delete(req_id, prod_name);
		List<ReqDetailVO> detailList = reqDetailSrv.getByReqId(req_id);
		ReqVO reqVO = reqSrv.getByReqId(req_id);
		List<ReqVO> list = new LinkedList<ReqVO>();
		list.add(reqVO);
		/***************************
		 * 3.查詢完成,準備轉交(Send the Success view)
		 ***********/
		request.getSession().setAttribute("detailList", detailList);
		request.getSession().setAttribute("list", list);
		return "REQUISITION/AllReq"; // 查詢完成後轉交AllReq.jsp
	}

	@RequestMapping(method = RequestMethod.POST, value = "/REQUISITION/DetailDeleteReq.do")
	public String DetailDeleteReq(ModelMap model, HttpServletRequest request,
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 *******************/
			@RequestParam("req_id") String req_id, @RequestParam("action") String action) throws Exception, Exception {
		/*************************** 2.開始查詢資料 ***************************************/

		if ("Detail".equals(action)) {
			System.out.println("=====");
			List<ReqDetailVO> detailList = reqDetailSrv.getByReqId(req_id);
			ReqVO reqVO = reqSrv.getByReqId(req_id);
			LinkedList<ReqVO> list = new LinkedList<ReqVO>();
			list.add(reqVO);
			request.getSession().setAttribute("detailList", detailList);
			request.getSession().setAttribute("list", list);
			return "redirect:/REQUISITION/AllReqdetail.jsp";
		}
		if ("delete".equals(action)) {
			reqSrv.delete(req_id);
			List<ReqVO> list = reqSrv.getAll();
			request.getSession().setAttribute("list", list);
			return "/REQUISITION//AllReq"; // 查詢完成後轉交listAllEmp.jsp
		}
		if ("update".equals(action)) {
			ReqVO reqVO = reqSrv.getByReqId(req_id);
			List<ReqDetailVO> reqDetailVO = reqDetailSrv.getByReqId(req_id);

			request.getSession().setAttribute("reqVO", reqVO);
			request.getSession().setAttribute("reqDetailVO", reqDetailVO);
			// model.addAttribute("hello","Hello");
			return "/REQUISITION/updateReq";
		}
		/***************************
		 * 3.查詢完成,準備轉交(Send the Success view)
		 ***********/
		return null;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/REQUISITION/updateReq2.do")
	public String updateReq2(ModelMap model, String req_id, String status) {
		try {
			reqSrv.setStatus(status, req_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List list = reqSrv.selectOfN();
		model.addAttribute("list", list);
		return "/REQUISITION/AllReq4";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/REQUISITION/updateReq.do", "/updateReq.do" })
	public String updateReq(ModelMap model, HttpServletRequest req) throws Exception {
		List<String> errorMsgs = new LinkedList<String>();
		String req_id = req.getParameter("req_id");

		// String key_id = req.getParameter("key_id");
		// if (key_id == null || key_id.trim().length() == 0) {
		// errorMsgs.add("修改人員請勿空白");
		// }
		// String keyidCK = "^[E][0-9]{5}$";
		// if(!key_id.trim().matches(keyidCK) ) {
		// errorMsgs.add("修改人員格式:E00001");
		// }

		String status = req.getParameter("status");
		if (status == null || status.trim().length() == 0) {
			errorMsgs.add("狀態請勿空白");
		}
		// String statusCK = "^[N,Y]{1}$";
		// if(!status.trim().matches(statusCK) ) {
		// errorMsgs.add("狀態格式:N or Y");
		// }
		try {
			reqSrv.setStatus(status, req_id);

			List list = reqSrv.getAll();

			req.getSession().setAttribute("list", list);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return "/REQUISITION/AllReq";
	}
}
