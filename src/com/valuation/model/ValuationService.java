package com.valuation.model;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.order.model.Order_Interface;
import com.order_detail.model.Order_Detail_Interface;
import com.valuation_detail.model.Valuation_DetailHibernateDAO;
import com.valuation_detail.model.Valuation_DetailVO;
import com.valuation_detail.model.Valuation_Detail_Interface;

public class ValuationService {

	private Valuation_Interface vlt_dao;
	private Valuation_Detail_Interface vltDtail_dao;

	public ValuationService() {
//		vlt_dao = new ValuationHibernateDAO();
//		vltDtail_dao = new Valuation_DetailHibernateDAO();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		vlt_dao =(Valuation_Interface) context.getBean("ValuationSpringDAO");	
		vltDtail_dao =(Valuation_Detail_Interface) context.getBean("Valuation_DetailSpringDAO");
	}

	public List Select_valuation_detailALL(String vlt_id) throws Exception {

		return vltDtail_dao.Select_valuation_detailALL(vlt_id);

	}

	public List Select_vlt_date(Date s_ord_date, Date e_ord_date) throws Exception {

		return vlt_dao.Select_vlt_date(s_ord_date, e_ord_date);

	}

	public ValuationVO addVltList(ValuationVO valuationVO, List<Valuation_DetailVO> valuation_detailVO_list)
			throws Exception {

		return vlt_dao.addVltList(valuationVO, valuation_detailVO_list);
	}

	public ValuationVO updateVltlist(List<Valuation_DetailVO> valuation_detailVO_list, ValuationVO valuationVO)
			throws Exception {

		return vltDtail_dao.updateVltlist(valuation_detailVO_list, valuationVO);

	}

	public List<ValuationVO> getAll() throws Exception {

		return vlt_dao.getAll();
	}

	public ValuationVO Select_vlt_id(String vlt_id) throws Exception {

		return vlt_dao.Select_vlt_id(vlt_id);

	}

	public void delete(String vlt_id) throws Exception {
		vlt_dao.delete(vlt_id);
	}

	public void delete(String vlt_id, String prod_id) throws Exception {
		vltDtail_dao.delete(vlt_id, prod_id);
	}

	public void setStatus(String status, String vlt_id) throws Exception {

		vlt_dao.setStatus(status, vlt_id);
	}
	
	public List<ValuationVO> getAllByN() throws Exception {
		
		return vlt_dao.getAllByN();
	}
	
}
