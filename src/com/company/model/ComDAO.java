package com.company.model;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;


public class ComDAO implements ComDAO_interface {
	
	private static final String GET_ALL_STMT = "from ComVO order by com_id";

	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
    	this.hibernateTemplate = hibernateTemplate;
    }
	
	@Override
	public void insert(ComVO comVO) {
		hibernateTemplate.saveOrUpdate(comVO);
	}


	@Override
	public void update(ComVO comVO) {	
		
		hibernateTemplate.saveOrUpdate(comVO);
	
		}

	@Override
	public void delete(String com_id) {

			ComVO comVO = (ComVO) hibernateTemplate.get(ComVO.class, com_id);
			hibernateTemplate.delete(comVO);
	}

	@Override
	public ComVO findByPrimaryKey(String com_id) {
		ComVO comVO = null;
		comVO = (ComVO) hibernateTemplate.get(ComVO.class, com_id);
		
		return comVO;	
		}

	@Override
	public List<ComVO> getAll() {
		
		List<ComVO> list = null;
	
		list = hibernateTemplate.find(GET_ALL_STMT);
			
		return list;	
		}

	@Override
	public List<ComVO> findByName(String com_name) {

		List<ComVO> list = null;
	
			list = hibernateTemplate.find("from ComVO where com_name like '%"+com_name+"%'");
		
		return list;
		}
	
}
