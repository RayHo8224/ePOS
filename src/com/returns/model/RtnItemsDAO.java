package com.returns.model;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;



public class RtnItemsDAO implements RtnItems_Interface{
	
	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }
	
	@Override
	public void insert(RtnItemsVO rtnItemsVO) {
		
		hibernateTemplate.saveOrUpdate(rtnItemsVO);
	}

	@Override
	public void update(RtnItemsVO rtnItemsVO) {
		
		hibernateTemplate.saveOrUpdate(rtnItemsVO);	
	}

	@Override
	public void delete(String prod_name) {
		
		RtnItemsVO rtnItemsVO = (RtnItemsVO)hibernateTemplate.get(RtnItemsVO.class, prod_name);
		hibernateTemplate.delete(rtnItemsVO);
		
	}

	@Override
	public RtnItemsVO findByPrimaryKey(String prod_name) {
		RtnItemsVO rtnItems = hibernateTemplate.get(RtnItemsVO.class, prod_name);
		return rtnItems;
	}
	
	
	private static final String GET_NAME ="FROM RtnItemsVO where prod_name = ?";
	private static final String GET_ID ="FROM RtnItemsVO where com_id = ?";
	private static final String GET_ALL_STMT = "FROM RtnItemsVO order by prod_name";

	@Override
	public List<RtnItemsVO> findByName(String prod_name) {
		List<RtnItemsVO> list = null;
		list = (List<RtnItemsVO>) hibernateTemplate.find(GET_NAME,prod_name);

		return list;
	}

	@Override
	public List<RtnItemsVO> findById(String com_id) {
		List<RtnItemsVO> list = null;
		list = (List<RtnItemsVO>) hibernateTemplate.find(GET_ID, com_id);
		return list;
	}
	
	

	@Override
	public List<RtnItemsVO> getAll() {
		List<RtnItemsVO> list = null;
		list = (List<RtnItemsVO>) hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}

}
