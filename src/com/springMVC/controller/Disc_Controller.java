package com.springMVC.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.discount.model.DiscountService;
import com.discount.model.DiscountVO;
import gvjava.org.json.JSONArray;

@Controller
public class Disc_Controller  {
	private static final DiscountService DiscSvc = new DiscountService();

//查詢全部	
	@RequestMapping(method = RequestMethod.POST, value = "/DISCOUNT/allDisc.do")
	public String getAllDisc(ModelMap model) {	
	/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		List<DiscountVO> list = DiscSvc.getAll();
			
	/*************************** 2.永續層存取 ***************************************/	
				model.addAttribute("list", list);	
				
	/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				return "/DISCOUNT/listAllDic";
	
		}
//選擇折扣%數	
	@RequestMapping(method = RequestMethod.POST, value = "/DISCOUNT/disc.do")
	public String getOneCpon(@RequestParam("dis_id") String dis_id, ModelMap model) {
		/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
	
			List<String> errorMsgs = new LinkedList<String>();
			model.addAttribute("errorMsgs", errorMsgs);
			
			/*************************** 2.永續層存取 ***************************************/
			
			DiscountVO discVO = DiscSvc.getOneDisc(dis_id);
			List<DiscountVO> list = new ArrayList<DiscountVO>();
			list.add(discVO);

				if (discVO == null) {
					errorMsgs.add("資料不存在");
				}
				if (!errorMsgs.isEmpty()) {
					model.addAttribute("message", errorMsgs);
					return "redirect:/DISCOUNT/discount.jsp";
					
				}
		/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				
				model.addAttribute("list", list);
				return "/DISCOUNT/listAllDic";

		}
//新增資料		
			@RequestMapping(method = RequestMethod.POST,value = "/DISCOUNT/insertDisc.do")
			public String insertCpon(ModelMap model,HttpServletRequest req) throws Exception, Exception {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String dis_id = req.getParameter("dis_id").trim();
			if (dis_id == null || dis_id.length() == 0) {
				errorMsgs.add("折扣身分: 請勿空白");
			}
			
			String dis_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
			if(!dis_id.matches(dis_idReg) ) { 
				errorMsgs.add("折扣身分:只能是中、英文字母、數字和_ , 且長度必須在1到10之間");
            }
			
			String dis_price = req.getParameter("dis_price").trim();
			if (dis_price == null || dis_price.length() == 0) {
				errorMsgs.add("折扣%數: 請勿空白");
				dis_price = "1";
			}
			
			Float price = null;
			
			String dis_priceReg = "^[0-9.]{1,4}$";
			if (!dis_price.matches(dis_priceReg)) {
				errorMsgs.add("折扣%數:只能是數字 ,且數值必須在1到0.01之間");
				dis_price = "1";
			} else if (!(Float.valueOf(dis_price)>=0.01f && Float.valueOf(dis_price) <= 1.0f)) {
				errorMsgs.add("數值必須在1到0.01之間");
				dis_price = "1";
			} else {
				price = Float.valueOf(dis_price);
			}			
			
			DiscountVO discVO = new DiscountVO();
			discVO.setDis_id(dis_id);
			discVO.setDis_price(Float.valueOf(dis_price));

			if (!errorMsgs.isEmpty()) {				
				model.addAttribute("discVO", discVO);
				return "redirect:/DISCOUNT/discount.jsp";
				
			}
			/*************************** 2.永續層存取 ***************************************/
				
				discVO = DiscSvc.addDisc(dis_id, price);
				List<DiscountVO> list = new LinkedList<DiscountVO>();
				list.add(discVO);
								
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/				

				req.getSession().setAttribute("list", list);
				return "redirect:/DISCOUNT/listAllDic.jsp";
			}
////按下修改 轉交到修改頁面	
//			
//			@RequestMapping(method = RequestMethod.POST,value = "/DISCOUNT/allForUpdateDisc.do")
//			public String DiscAll(ModelMap model,HttpServletRequest req){
//			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
//				String dis_id = new String(req.getParameter("dis_id"));
//			/*************************** 2.永續層存取 ***************************************/
//				
//				DiscountVO discVO = DiscSvc.getOneDisc(dis_id);
//			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
//
//				model.addAttribute("discVO",discVO);
//				return "/DISCOUNT/updateDic";
//
//			}
			
			//查詢全部(json)
//			@RequestMapping(method = RequestMethod.POST, value = "/DISCOUNT/alljson.do")
//			public void getAllDiscJson(ModelMap model,HttpServletResponse resp) throws Exception {	
//			/*************************** * 接收請求參數 - 輸入格式的錯誤處理 *************************/
//				List<DiscountVO> list = DiscSvc.getAll();
//				List l1 = new LinkedList();
//				for(DiscountVO vo:list){
//					Map m1 = new HashMap();
//					m1.put("dis_id", vo.getDis_id());
//					m1.put("dis_price", vo.getDis_price());
//					l1.add(m1);
//				}
//				resp.setHeader("content-type","text/html;charset=utf-8");
//				JSONArray jsonall = new JSONArray(l1);
//				PrintWriter out = resp.getWriter();
//				out.print(jsonall);
//			}	
//			/*************************** * 完成,準備轉交(Send the Success view) ***********/
//						return "/DISCOUNT/listAllDic";
			
//				}
			
			// 輸入折價券編號
			
			
			@RequestMapping(method = RequestMethod.POST, value = "/DISCOUNT/GroupByDisc.do")
			public String getOneDic(@RequestParam("dis_price") float dis_price, ModelMap model) {
				/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				List<String> errorMsgs = new LinkedList<String>();
				model.addAttribute("errorMsgs", errorMsgs);

					
			/*************************** 2.永續層存取 ***************************************/
						
						List<DiscountVO> list = new ArrayList<DiscountVO>();
						list = DiscSvc.findByPrice(dis_price);

						if (!errorMsgs.isEmpty()) {
							model.addAttribute("message", errorMsgs);
							return "redirect:search_disc.jsp";
						}

			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
						
						model.addAttribute("list", list);				
						return "/DISCOUNT/listAllDic";				
				}
			
//送出修改
			@RequestMapping(method = RequestMethod.POST,value = "/DISCOUNT/updateDisc.do")
			public String update(ModelMap model,HttpServletRequest req) throws Exception, Exception {
				/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			
				String dis_id = req.getParameter("dis_id");
				String dis_price = req.getParameter("dis_price").trim();
				if (dis_price == null || dis_price.length() == 0) {
					errorMsgs.add("折扣%數: 請勿空白");
					dis_price = "1";
				}
				Float price = null;
				String dis_priceReg = "^[0-9.]{1,4}$";
				if (!dis_price.matches(dis_priceReg)) {
					errorMsgs.add("折扣%數:只能是數字, 且數值必須在1到0.01之間");
					dis_price = "1";
				} else if (!(Float.valueOf(dis_price)>=0.01f && Float.valueOf(dis_price) <= 1.0f)) {
					errorMsgs.add("數值必須在1到0.01之間");
					dis_price = "1";
				} else {
					price = Float.valueOf(dis_price);
				}
				
				DiscountVO discVO = new DiscountVO();
				discVO.setDis_id(dis_id);
				discVO.setDis_price(Float.valueOf(dis_price));

				if (!errorMsgs.isEmpty()) {
					model.addAttribute("discVO", discVO);
					return "redirect:/DISCOUNT/listAllDic.jsp";
					
				}

			/*************************** 2.永續層存取 ***************************************/
				
				discVO = DiscSvc.updDisc(dis_id, price);
				List<DiscountVO> list = new ArrayList<DiscountVO>();
				list.add(discVO);
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				
				req.getSession().setAttribute("list", list);
				return "redirect:/DISCOUNT/listAllDic.jsp";
		}

//刪除	
			@RequestMapping(method = RequestMethod.POST, value = "/DISCOUNT/deleteDisc.do")
			public String delete(@RequestParam("dis_id") String dis_id, ModelMap model,HttpServletRequest req) throws Exception, Exception {
			/*************************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				
			/*************************** 2.永續層存取 ***************************************/
				
				DiscSvc.delDisc(dis_id);
				List<DiscountVO> list = DiscSvc.getAll();
			/*************************** * 3.完成,準備轉交(Send the Success view) ***********/
				
				req.getSession().setAttribute("list", list);
				return "redirect:/DISCOUNT/listAllDic.jsp";
			}

	
}
