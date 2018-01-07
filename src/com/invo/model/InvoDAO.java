package com.invo.model;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;



public class InvoDAO implements InvoDAO_interface {
	
	private static final String GET_ALL_STMT = "from InvoVO";

	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }
	
	@Override
	public void insert(InvoVO invoVO) {
		hibernateTemplate.saveOrUpdate(invoVO);
	}
	@Override
	public void update(InvoVO invoVO) {
		hibernateTemplate.update(invoVO);
		}
	


	@Override
	public void delete(String invoice_id) {
		InvoVO invoVO = new InvoVO(); 
		invoVO.setInvoice_id(invoice_id);
		hibernateTemplate.delete(invoVO);
	}

			
	@Override
	public InvoVO findByPrimaryKey(String invoice_id) {
		InvoVO invoVO = (InvoVO) hibernateTemplate.get(InvoVO.class, invoice_id);
		return invoVO;
	}
		
		

	@Override
	public List<InvoVO> getAll() {
		List<InvoVO> list = null;
		list = hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}
}
