package checkPassCode;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmpVO;


@WebFilter(urlPatterns = {
		"/DISCOUNT/updateDisc.do",
		"/MEMBER/deleteMem.do",
		"/MEMBER/addMem.jsp",
		"/MEMBER/searchMem.jsp",
		"/MEMBER/allForUpdateMem.do",
		"/EMPLOYEE/searchEmp.jsp",
		"/EMPLOYEE/addEmp.jsp",
		"/EMPLOYEE/SetPassCode.jsp",
		"/EMPLOYEE/updateDeleteEmp.do",
		"/COMPANY/searchCom.jsp",
		"/COMPANY/addCom.jsp",
		"/COMPANY/updateDeleteCom.do",
		"/REQUISITION/addReq.jsp",
		"/REQUISITION/SelectReq.jsp",
		"/REQUISITION/getAllReq.do",
		"/REQUISITION/SelectbyDate.jsp",
		"/REQUISITION/selectOfN.do",
		"/QUOTATION/addQuo0.do",
		"/QUOTATION/SelectQuo.jsp",
		"/QUOTATION/getAllQuo.do",
		"/QUOTATION/SelectbyDate.jsp",
		"/QUOTATION/selectOfN.do",
		"/QUOTATION/selectOfY.do",
		"/PURCHASE/insertPur00.do",
		"/PURCHASE/SelectPur.jsp",
		"/PURCHASE/getAllPur.do",
		"/PURCHASE/SelectbyDate.jsp",
		"/BILL_OF_PURCHASE/selectOfY.do",
		"/BILL_OF_PURCHASE/SelectBOP0.jsp",
		"/BILL_OF_PURCHASE/getAllBop.do",
		"/BILL_OF_PURCHASE/SelectbyDate.jsp",
		"/BILL_OF_PURCHASE/selectOfN.do",
		"/BILL_OF_PURCHASE/selectOfY2.do",
		"/VALUATION/addVltList.do",
		"/VALUATION/searchList.jsp",
		"/VALUATION/SelectVltAll.jsp",
		"/VALUATION/SelectVltAllForCHK.jsp",
		"/ORDER/OrdsearchList.jsp",
		"/ORDER/SelectOrdAll.jsp",
		"/ORDER/Querydetail_DeleteOrd.do",
		"/ORDER/OrdToShip.do",
		"/SHIPMENTS//searchList.jsp",
		"/SHIPMENTS/AllShip.jsp",
		"/PRODUCT/InsertProd.jsp",
		"/PRODUCT/getAllProd.do",
		"/PRODUCT/getOneProd.do",
		"/PRODUCT/getProdByName.do",
		"/PRODUCT/getProdByGroup.do",
		"/PRODUCT/updateDeleteProd.do",
		"/PROMOTING/searchProm.jsp",
		"/PROMOTING/addProm.jsp",
		"/PROMOTING/deleteProm.do",
		"/PROMOTING/updateProm.do",
		"/RETURNS/insert_Item.do",
		"/RETURNS/searchItem.jsp",
		"/RETURNS/delete_Item.do",
		"/RETURNS/update_Item.do",
		"/RETURNS/insert.do",
		"/RETURNS/searchList.jsp",
		"/RETURNS/AllList.jsp",
		"/RETURNS/Return_Detail.jsp",
		"/RETURNS/delete.do",
		"/RETURNS/update.do",
		"/INVO/insertInvo.do",
		"/INVO/searchinvo.jsp",
		"/INVO/listAllInvo.jsp",
		"/INVO/deleteInvo.do",
		"/INVO/updateInvo.do",
		"/SHIFTREPORT/getOneShiftre.do",
		"/SHIFTREPORT/getAllShiftre.do",
		"/SHIFTREPORT/getShiftreByDate.do",
		"/SHIFTREPORT/insertShiftre.do",
		"/SHIFTREPORT/updateShiftre.do",
		"/SHIFTREPORT/updateDeleteShiftre.do",
		"/COUPON/coupon.do",
		"/COUPON/namesCpon.do",
		"/COUPON/dollarCpon.do",
		"/COUPON/datesCpon.do",
		"/COUPON/allCpon.do",
		"/COUPON/insertCpon.do",
		"/COUPON/updateCpon.do",
		"/COUPON/deleteCpon.do",
		"/DISCOUNT/search_disc.jsp",
		"/DISCOUNT/insertDic.jsp",
		"/DISCOUNT/deleteDisc.do",
		"/DISCOUNT/updateDisc.do",
		"/ANALYSIS/analysis.jsp",
		"/ANALYSIS/Mail.jsp"
})
//@WebFilter(urlPatterns = {"/*"})
public class CheckPassCodeFilter implements Filter {

	String requestURI;
	
    public CheckPassCodeFilter() {
    }


	public void destroy() {
	}

	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
						
			System.out.println("checkPW"+req.getServletPath());
			if(checkPCode(req)){
				chain.doFilter(request, response);
			}else{
				requestURI = (String) req.getSession().getAttribute("requestURI");
				System.out.println(requestURI);
				
				resp.sendRedirect("javascript:'onclick='history.back(); ");
				System.out.println("沒有權限");
			}						
		}		
	}

	
	private boolean checkPCode(HttpServletRequest req){
		HttpSession session = req.getSession();
		EmpVO empVO = (EmpVO) session.getAttribute("LoginOK");
		if(empVO.getPass_code()==null){
			return false;
		}
		if(empVO.getPass_code().equals("ALL")){
			return true;
		}
		
		String[] passCode = (empVO.getPass_code()).split(",");

		if(checkPCode2(passCode,req)){
			return true;
		}
		return false;
	}
	
	private boolean checkPCode2(String[] passCode,HttpServletRequest req){
		HttpSession session = req.getSession();

		String servletPath = req.getServletPath();	
		System.out.println("servletPath="+servletPath);
		servletPath = servletPath.substring(servletPath.lastIndexOf("/"));		
		for(int i=0;i<=(passCode.length)-1;i++){
			System.out.println(passCode[i]);
			if(passCode[i].equals(servletPath) || passCode[i].equals("ALL") ){
				return true;
			}				
		}
		requestURI  = req.getRequestURI();
		session.setAttribute("requestURI", requestURI);
		return false;
	}
	
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
