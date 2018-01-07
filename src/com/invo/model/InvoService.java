package com.invo.model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class InvoService {

	private InvoDAO_interface dao;

	public InvoService() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(InvoDAO_interface) context.getBean("invoDAO");	
		}

	public InvoVO addInvo(String invoice_id, String ord_id, String new_invoice_number,
			String new_ord_id) {

		InvoVO invoVO = new InvoVO();
		invoVO.setInvoice_id(invoice_id);
		invoVO.setOrd_id(ord_id);
		invoVO.setNew_invoice_number(new_invoice_number);
		invoVO.setNew_ord_id(new_ord_id);

		dao.insert(invoVO);

		return invoVO;
	}

	
	public InvoVO updateInvo(String invoice_id,String ord_id, String new_invoice_number, 
			String new_ord_id) {

		InvoVO invoVO = new InvoVO();

		invoVO.setInvoice_id(invoice_id);
		invoVO.setOrd_id(ord_id);
		invoVO.setNew_invoice_number(new_invoice_number);
		invoVO.setNew_ord_id(new_ord_id);
		dao.update(invoVO);

		return dao.findByPrimaryKey(invoice_id);
	}

	public void deleteInvo(String invoice_id) {
		dao.delete(invoice_id);
	}

	public InvoVO getOneInvo(String invoice_id) {
		return dao.findByPrimaryKey(invoice_id);
	}

	public List<InvoVO> getAll() {
		return dao.getAll();
	}
}
