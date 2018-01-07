package login;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;

import com.employee.model.EmpVO;

//@WebFilter(urlPatterns = { "/*" })
public class LoginFilter implements Filter {
	Collection<String> url = new ArrayList<String>();
	String servletPath;
	
	String contextPath;
	String requestURI;

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
System.out.println("Login_req.getServletPath()===="+req.getServletPath());
			servletPath=req.getServletPath();
			boolean flag=false;
			if (servletPath.length() > 10) {
				if (servletPath.substring(0, 10).equals("/resources")) {
					flag = true;
					
				}
			}

			if (req.getServletPath().equals("/index.jsp")||req.getServletPath().equals("/login.do")||
					req.getServletPath().equals("/sign_in.jsp")||flag||req.getServletPath().equals("/ANDROID/getProdFromAndroid")||
					req.getServletPath().equals("/ANDROID/Login.do")||req.getServletPath().equals("/ANDROID/getBOP.do")||
					req.getServletPath().equals("/ANDROID/getBopOfY2.do")||req.getServletPath().equals("/ANDROID/goInsertProd.do")
					) {
				chain.doFilter(request, response);

			} else {
				
				if (checkLogin(req)) { // 檢查帳密是否有登入
					chain.doFilter(request, response);
				} else { 
					resp.sendRedirect(req.getContextPath() + "/index.jsp");
					return;
				}
			}

		} else {
			throw new ServletException("Request / Response 型態錯誤");
		}
	}

	private boolean checkLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		EmpVO loginToken = (EmpVO) session.getAttribute("LoginOK");
		if (loginToken == null) {
			requestURI  = req.getRequestURI();
			session.setAttribute("requestURI", requestURI);
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void destroy() {
	}
}