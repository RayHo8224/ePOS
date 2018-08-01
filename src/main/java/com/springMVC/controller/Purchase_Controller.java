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

import com.company.model.ComVO;
import com.product.model.ProdVO;
import com.pur.model.PurService;
import com.pur.model.PurVO;
import com.pur_detail.model.Pur_detailVO;

@Controller
public class Purchase_Controller {

	private final static PurService purSvc = new PurService();

	@RequestMapping(method = RequestMethod.POST, value = { "/PURCHASE/setStatus2.do" })
	public String setStatus2(ModelMap model, HttpServletRequest request, String status, String pur_id) {
		purSvc.setStatus(status, pur_id);
		PurVO purVO = purSvc.getOnePur(pur_id);
		model.addAttribute("purVO", purVO);

		return "/PURCHASE/SelectPur1";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/PURCHASE/setStatus.do" })
	public String setStatus(ModelMap model, HttpServletRequest request, String status, String pur_id) {
		purSvc.setStatus(status, pur_id);
		List<PurVO> list = purSvc.selectOfN();
		model.addAttribute("list", list);
		return "PURCHASE/SelectOfN";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/PURCHASE/selectOfN.do" })
	public String selectOfN(ModelMap model, HttpServletRequest request) {
		List<PurVO> list = purSvc.selectOfN();
		model.addAttribute("list", list);
		return "PURCHASE/SelectOfN";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/PURCHASE/selectOfN2.do" })
	public String selectOfN2(ModelMap model, HttpServletRequest request, String pur_id) {
		PurVO purVO = purSvc.getOnePur(pur_id);
		List<Pur_detailVO> purDetail = purSvc.getPurDetail(pur_id);
		request.setAttribute("purVO", purVO);
		request.setAttribute("purDetail", purDetail);

//		model.addAttribute("purVO", purVO);
		return "PURCHASE/selectOfN2";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/PURCHASE/findByDate.do" })
	public String findByDate(ModelMap model, HttpServletRequest request, Date begin_date, Date end_date)
			throws Exception {
		List<PurVO> list = purSvc.findByDate(begin_date, end_date);
		model.addAttribute("list", list);
		return "PURCHASE/FindByDate";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/PURCHASE/insertPur0.do" })
	public String insertPur0(ModelMap model, String com_name) throws Exception {
//		List<ComVO> list = purSvc.getCom();
		List<ComVO> list = purSvc.getOneCom(com_name);
		ComVO comVO = list.get(0);
		String com_id = comVO.getCom_id();
		List<ProdVO> list2 = purSvc.getProd(com_id);
		model.addAttribute("ComVO", comVO);
		model.addAttribute("list2", list2);
		return "PURCHASE/addPur";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = { "/PURCHASE/insertPur00.do" })
	public String insertPur00(ModelMap model) throws Exception{
		List<ComVO> list = purSvc.getCom();
		model.addAttribute("list",list);
		return "PURCHASE/addPur0";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/PURCHASE/getOneProd1.do" })
	public void getOneProd1(ModelMap mode, HttpServletResponse response, String prod_name) throws Exception {
		ProdVO prodVO = purSvc.getOneProd(prod_name);
		String prod_id = prodVO.getProd_id();
		String prod_cost = Integer.toString(prodVO.getProd_cost());
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = response.getWriter();
		out.println(prod_id);

	}

	@RequestMapping(method = RequestMethod.POST, value = { "/PURCHASE/getOneProd2.do" })
	public void getOneProd2(ModelMap mode, HttpServletResponse response, String prod_name) throws Exception {
		ProdVO prodVO = purSvc.getOneProd(prod_name);
//		String prod_name = prodVO.getProd_name();
		String prod_cost = Integer.toString(prodVO.getProd_cost());
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = response.getWriter();
		out.println(prod_cost);

	}

	@RequestMapping(method = RequestMethod.POST, value = { "/insertPur.do", "/PURCHASE/insertPur.do" })
	public String insertPur(ModelMap model, HttpServletRequest request) throws Exception {
		/*************************** 1.��隢�� - 頛詨�撘�隤方��� ******************/
		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		// String quo_id = request.getParameter("quo_id");
		// if(quo_id == null || quo_id.trim().length()==0){
		// errorMsgs.add("閰Ｗ�蝺刻��蝛箇");
		// }

		Date pur_date = null;
		try {
			pur_date = Date.valueOf(request.getParameter("pur_date"));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			pur_date = new Date(System.currentTimeMillis());
			errorMsgs.add("隢撓��鞈潭���");
		}

		Date delivery_date = null;
		try {
			delivery_date = Date.valueOf(request.getParameter("delivery_date"));
		} catch (IllegalArgumentException e) {
			delivery_date = new Date(System.currentTimeMillis());
			errorMsgs.add("隢撓��疏����");
		}

		String com_id = request.getParameter("com_id");
		if (com_id == null || com_id.trim().length() == 0) {
			errorMsgs.add("隢撓�撱�楊���");
		}

		String key_id = request.getParameter("key_id");
		if (key_id == null || key_id.trim().length() == 0) {
			errorMsgs.add("隢撓�撱箸�犖�蝺刻��");
		}

		Date key_date = null;
		try {
			key_date = Date.valueOf(request.getParameter("key_date"));
		} catch (IllegalArgumentException e) {
			key_date = new Date(System.currentTimeMillis());
			errorMsgs.add("隢撓�撱箸����");
		}

		String remark = request.getParameter("remark");

		String status = request.getParameter("status");

		PurVO purVO = new PurVO();

		Set<Pur_detailVO> set = new LinkedHashSet<Pur_detailVO>();

		Integer i = 1;

		while (true) {
			String x = i.toString();
			try {
				String prod_id = request.getParameter("prod_id" + x);
				String prod_name = request.getParameter("prod_name" + x);
				int prod_quantity = Integer.valueOf(request.getParameter("prod_quantity" + x));
				int prod_price = Integer.valueOf(request.getParameter("prod_price" + x));
				int prod_lsum = Integer.valueOf(request.getParameter("prod_lsum" + x));

				Pur_detailVO pur_detailVO = new Pur_detailVO();
				// pur_detailVO.setProd_id(prod_id);
				pur_detailVO.setProd_name(prod_name);
				pur_detailVO.setProd_quantity(prod_quantity);
				pur_detailVO.setProd_price(prod_price);
				pur_detailVO.setProd_lsum(prod_lsum);
				pur_detailVO.setPurVO(purVO);
				ProdVO prodVO = new ProdVO();
				prodVO.setProd_id(prod_id);
				// prodVO.setProd_name(prod_name); //!!!????
				pur_detailVO.setProdVO(prodVO);

				set.add(pur_detailVO);
				i++;

			} catch (Exception e) {
				if (i < 100) {
					i++;
					continue;
				} else
					break;
			}
		}

		// purVO.setQuo_id(quo_id);
		purVO.setPur_date(pur_date);
		purVO.setDelivery_date(delivery_date);
		purVO.setCom_id(com_id);
		purVO.setKey_id(key_id);
		purVO.setKey_date(key_date);
		purVO.setRemark(remark);
		purVO.setStatus(status);
		purVO.setPurs(set);

		/*************************** 2.���憓��� ***************************************/
		purSvc.insert(purVO);
		List<PurVO> listAll = purSvc.getAll();
		request.getSession().setAttribute("list", listAll);
		/***************************
		 * 3.�憓���,皞��漱(Send the Success view)
		 ***********/
		return "PURCHASE/AllPur"; // �憓����漱output_page.jsp
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/updatePur.do", "/PURCHASE/updatePur.do" })
	public String updatePur(ModelMap model, HttpServletRequest request) throws Exception {
		/*************************** 1.��隢�� - 頛詨�撘�隤方��� ******************/
		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		// String quo_id = request.getParameter("quo_id");
		// if(quo_id == null || quo_id.trim().length()==0){
		// errorMsgs.add("閰Ｗ�蝺刻��蝛箇");
		// }
		String pur_id = request.getParameter("pur_id");

		Date pur_date = null;
		try {
			pur_date = Date.valueOf(request.getParameter("pur_date"));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			pur_date = new Date(System.currentTimeMillis());
			errorMsgs.add("隢撓��鞈潭���");
		}

		Date delivery_date = null;
		try {
			delivery_date = Date.valueOf(request.getParameter("delivery_date"));
		} catch (IllegalArgumentException e) {
			delivery_date = new Date(System.currentTimeMillis());
			errorMsgs.add("隢撓��疏����");
		}

		String com_id = request.getParameter("com_id");
		if (com_id == null || com_id.trim().length() == 0) {
			errorMsgs.add("隢撓�撱�楊���");
		}

		String key_id = request.getParameter("key_id");
		if (key_id == null || key_id.trim().length() == 0) {
			errorMsgs.add("隢撓�撱箸�犖�蝺刻��");
		}

		Date key_date = null;
		try {
			key_date = Date.valueOf(request.getParameter("key_date"));
		} catch (IllegalArgumentException e) {
			key_date = new Date(System.currentTimeMillis());
			errorMsgs.add("隢撓�撱箸����");
		}

		String remark = request.getParameter("remark");

		String status = request.getParameter("status");

		PurVO purVO = new PurVO();

		Set<Pur_detailVO> set = new LinkedHashSet<Pur_detailVO>();

		Integer i = 1;

		while (true) {

			String x = i.toString();
			try {
				String prod_id = request.getParameter("prod_id" + x);
				String prod_name = request.getParameter("prod_name" + x);
				int prod_quantity = Integer.valueOf(request.getParameter("prod_quantity" + x));
				int prod_price = Integer.valueOf(request.getParameter("prod_cost" + x));
				int prod_lsum = Integer.valueOf(request.getParameter("prod_lsum" + x));

				Pur_detailVO pur_detailVO = new Pur_detailVO();

				// pur_detailVO.setProd_id(prod_id);
				pur_detailVO.setProd_name(prod_name);
				pur_detailVO.setProd_quantity(prod_quantity);
				pur_detailVO.setProd_price(prod_price);
				pur_detailVO.setProd_lsum(prod_lsum);

				pur_detailVO.setPurVO(purVO);

				ProdVO prodVO = new ProdVO();
				// prodVO.getProds().clear();
				// purVO.getPurs().clear();
				prodVO.setProd_id(prod_id);
				// prodVO.setProd_name(prod_name); //!!!????
				pur_detailVO.setProdVO(prodVO);

				set.add(pur_detailVO);
				i++;

			} catch (Exception e) {
				if (i < 100) {
					i++;
					continue;
				} else
					break;
			}
		}

		purVO.setPur_id(pur_id);
		// purVO.setQuo_id(quo_id);
		purVO.setPur_date(pur_date);
		purVO.setDelivery_date(delivery_date);
		purVO.setCom_id(com_id);
		purVO.setKey_id(key_id);
		purVO.setKey_date(key_date);
		purVO.setRemark(remark);
		purVO.setStatus(status);
		purVO.setPurs(set);

		/*************************** 2.���憓��� ***************************************/
		purSvc.update(purVO);

		PurVO purVO2 = purSvc.getOnePur(pur_id);
		// List list = new LinkedList<PurVO>();
		// list.add(purVO);
		// model.addAttribute("list",list);
		List<Pur_detailVO> detailList = purSvc.getPurDetail(pur_id);
		request.setAttribute("purDetail", detailList);
		request.setAttribute("purVO", purVO2);

		return "/PURCHASE/SelectPur1";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/getOnePurforDisplay.do", "/PURCHASE/getByPur_id.do" })
	public String getOnePurforDisplay(@RequestParam("pur_id") String pur_id, ModelMap model,HttpServletRequest request) {
System.out.println("pur_id"+pur_id);
		PurVO purVO = purSvc.getOnePur(pur_id);
		List<Pur_detailVO> purDetail = purSvc.getPurDetail(pur_id);
		// List list = new LinkedList<PurVO>();
		// list.add(purVO);
		// model.addAttribute("list",list);
		

		request.setAttribute("purVO", purVO);
		request.setAttribute("purDetail", purDetail);

		return "/PURCHASE/SelectPur1";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/PURCHASE/getAllPur.do")
	public String getAllPur(ModelMap model) {
		List<PurVO> list = purSvc.getAll();
		model.addAttribute("list", list);
		return "/PURCHASE/AllPur";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/PURCHASE/deleteDetailPur.do", "/deleteDetailPur.do" })
	public String deleteDetail(ModelMap model, HttpServletRequest request,
			/***************************
			 * 1.��隢�� - 頛詨�撘�隤方���
			 *******************/
			@RequestParam("prod_id") String prod_id, @RequestParam("pur_id") String pur_id) {
		/*************************** 2.���閰Ｚ��� ***************************************/
		purSvc.deleteDetail(pur_id, prod_id);
		List<Pur_detailVO> detailList = purSvc.getPurDetail(pur_id);
		PurVO purVO = purSvc.getOnePur(pur_id);
		List<PurVO> list = new LinkedList<PurVO>();
		list.add(purVO);
		/***************************
		 * 3.�閰Ｗ���,皞��漱(Send the Success view)
		 ***********/
		request.getSession().setAttribute("detailList", detailList);
		request.getSession().setAttribute("list", list);
		return "PURCHASE/AllPur"; // �閰Ｗ����漱AllQuo.jsp
	}

	@RequestMapping(method = RequestMethod.POST, value = "/PURCHASE/DetailUpdateDeletePur.do")
	public String DetailUpdateDeletePur(ModelMap model, HttpServletRequest request,
			@RequestParam("pur_id") String pur_id, @RequestParam("action") String action) {

		if ("Detail".equals(action)) {
			List<Pur_detailVO> detailList = purSvc.getPurDetail(pur_id);
			PurVO purVO = purSvc.getOnePur(pur_id);
			LinkedList<PurVO> list = new LinkedList<PurVO>();
			list.add(purVO);
			request.getSession().setAttribute("detailList", detailList);
			request.getSession().setAttribute("list", list);
			return "redirect:/PURCHASE/AllPurdetail.jsp";
		}

		if ("delete".equals(action)) {
			purSvc.deletePur(pur_id);
			List<PurVO> list = purSvc.getAll();
			request.getSession().setAttribute("list", list);
			return "redirect:/PURCHASE/AllPur.jsp";
		}

		if ("update".equals(action)) {
			PurVO purVO = purSvc.getOnePur(pur_id);
			List<Pur_detailVO> detailList = purSvc.getPurDetail(pur_id);
			request.setAttribute("detailList", detailList);

			request.setAttribute("purVO", purVO);
			return "/PURCHASE/updatePur";
		}
		return null;
	}
}
