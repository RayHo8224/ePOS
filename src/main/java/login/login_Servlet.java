package login;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmpService;
import com.employee.model.EmpVO;

@WebServlet("/login.do")

public class login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public login_Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		System.out.println("login.do");

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(86400);
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("ErrorMsgKey", errorMsgMap);

		// 1. 讀取使用者輸入資料
		// EmpVO empVO;
		String emp_id = request.getParameter("emp_id");
		String emp_pwd = request.getParameter("emp_pwd");

		// 2. 進行必要的資料轉換
		// 無
		// 3. 檢查使用者輸入資料
		if (emp_id == null || emp_id.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
		}
		if (emp_pwd == null || emp_pwd.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}

		if (!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			return;
		}
		EmpService empSrv = new EmpService();
		EmpVO empVO = empSrv.getOne(emp_id);

		if (empVO != null) {
			if (checkPassword(empVO, emp_pwd)) {
				session.setAttribute("LoginOK", empVO);
				
				System.out.println("emp_psC="+empVO.getPass_code());
				
				long nowD = new java.util.Date().getTime();
				Date nowdate = new Date(nowD);
				String x = (nowdate.toString().split(" "))[3].split(":")[0];
				
				if (Integer.parseInt(x) < 14) {

					session.setAttribute("SHIFT", "A");
				} else {
					session.setAttribute("SHIFT", "B");
				}

				response.sendRedirect("/ePOS/ORDER/order.jsp");

			} else {
				errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}
		} else {
			errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}

	}

	private boolean checkPassword(EmpVO empVO, String emp_pwd) {
		if (empVO.getEmp_pwd().equals(emp_pwd)) {
			return true;
		}
		return false;
	}
}
