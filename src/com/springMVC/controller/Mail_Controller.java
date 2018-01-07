package com.springMVC.controller;

import java.security.Security;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.order.model.OrderService;
import com.order.model.OrderVO;

@Controller
public class Mail_Controller {

	private static final MemberService MemSvc = new MemberService();

	@RequestMapping(method = RequestMethod.POST, value = "/MAIL/email.do")
	public String mail(ModelMap model, HttpServletRequest request) throws Exception, Exception {

		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		String addres = request.getParameter("addres");

		if (request.getParameter("howMany").equals("寄送單一會員")) {

			if (addres == null || (addres.trim()).length() == 0) {
				errorMsgs.add("寄送單一聯絡人信箱請勿空白");
			}
		}
		
		String from = request.getParameter("from");

		String subject = request.getParameter("subject");
		if (!(request.getParameter("howMany").equals("查詢未下訂單會員"))) {
			if (subject == null || (subject.trim()).length() == 0) {
				errorMsgs.add("主旨請勿空白");
			}
		}
		if (!errorMsgs.isEmpty()) {
			model.addAttribute("message", errorMsgs);
			return "/MAIL/Mail";
		}

		String text = request.getParameter("text");
//---------------------連結到gmail司服器---------------------------
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		props.put("mail.smtp.auth", "true");
		final String username = "eeit8902";
		final String password = "P@ssw0rd8902";
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
//--------------------------到此結束----------------------------------
		Message msg = new MimeMessage(session); //建立gmail物件

		msg.setFrom(new InternetAddress(username + "@gmail.com", from));
		// ----------------------------記送單一會員----------------------
		if (request.getParameter("howMany").equals("寄送單一會員")) { //假如取得 howMany參數=寄送單一會員則
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addres, false));//addres=收件者
			msg.setSubject(subject);  					//subject=主旨
			msg.setText(text); 							//text=內容
			msg.setSentDate(new Date()); 				//日期
			Transport.send(msg); 						//發送
		} else if (request.getParameter("howMany").equals("寄送全部會員")) { //或，假如取得 howMany參數=寄送全部會員則
			// -----------------------------寄送全部會員----------------------
			List<MemberVO> list = MemSvc.getAll(); 			//取得MemberVO物件
			for (MemberVO all : list) {  					//列出MemberVO
				String mail = all.getMem_mail();			//取得VO裡的mail，設成字串型別的變數
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail, false)); //寄送收件者(mail變數只能是字串型別)
				msg.setSubject(subject);
				msg.setText(text);
				msg.setSentDate(new Date());
				Transport.send(msg);
				System.out.println("信件已寄出");
			}
			// ------------------------------------未下訂單會員------------------------------
		} else {
			OrderService ordSvc = new OrderService();// 建立ord物件
			java.sql.Date s_ord_date = java.sql.Date.valueOf(request.getParameter("s_ord_date"));// 取得日期(起)的參數
			java.sql.Date e_ord_date = java.sql.Date.valueOf(request.getParameter("e_ord_date"));// 取得日期(迄)的參數
			List<OrderVO> list = ordSvc.getOneOrderDate(s_ord_date, e_ord_date); // 取得會員下訂單日期
			List<String> memInOrd = new LinkedList<String>();					 // 建立新的List物件

			for (OrderVO orderVO : list) { 										 // 列出會員下訂單日期
				String mem_id = orderVO.getMem_id(); 							 // 取得下訂單會員人數
				memInOrd.add(mem_id); 											 // 加到 memInOrd 這個物件
			}
			List<MemberVO> memberList = new MemberService().getAll();			 // 建立member物件.並取得全部MemberVO
			Map<String, MemberVO> memNotInOrd = new LinkedHashMap<String, MemberVO>();// 建立MAP物件放入(字串.MemberVO)
			for (MemberVO memberVO : memberList) {								 // 列出memberVO
				String mem_id = memberVO.getMem_id();							 // 取得會員人數
				if (mem_id != null) { 										     // 假如不=null則
					memNotInOrd.put(mem_id, memberVO); 							 // 加到memberList(字串.MemberVO)
				}
			}
			for (String mem_id2 : memInOrd) { 									 // 列出下訂單會員人數
				memNotInOrd.remove(mem_id2); 									 // 移除下訂單會員人數
			}

			if (request.getParameter("howMany").equals("查詢未下訂單會員")) {         //假如取得 howMany參數=查詢未下訂單會員則
				model.addAttribute("list1", memNotInOrd);						 // memNotInOrd=全部會員-下訂單會員=未下訂單會員
				return "/MAIL/Mail";
			} else {
				Collection<MemberVO> noOrdMem = memNotInOrd.values(); 			 //取得noOrdMem(Map裡的key值)
				for (MemberVO list1 : noOrdMem) { 					  			 //列出全部未下訂單會員(key值)
					String noOrdMem1 = list1.getMem_mail();           			 //取得全部未下訂單會員信箱，設成字串型別的變數

					System.out.println(noOrdMem1);
					msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(noOrdMem1, false));//寄送收件者(noOrdMem1變數只能是字串型別)
					msg.setSubject(subject);
					msg.setText(text);
					msg.setSentDate(new Date());
					Transport.send(msg);
					System.out.println("信件已寄出");
				}
			}
		}
		return "/MAIL/NewFile1";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/MAIL/allMail.do")
	public String getAll(ModelMap model, HttpServletRequest request) throws Exception {

		List<MemberVO> list = new MemberService().getAll();
		model.addAttribute("listMail", list);
		return "/MAIL/Mail";
	}
}
