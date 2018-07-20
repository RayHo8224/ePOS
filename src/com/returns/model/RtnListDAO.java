package com.returns.model;

import java.sql.Date;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

public class RtnListDAO implements RtnList_Interface{
	
	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }

	@Override
	public void insert(RtnListVO rtnListVO, List<RtnDetailVO> listDetail) {
		
		hibernateTemplate.save(rtnListVO);
	}

	@Override
	public void update(RtnListVO rtnListVO) {
		
		hibernateTemplate.update(rtnListVO);
	}

	@Override
	public void delete(String ret_id) {
		
		RtnListVO rtnListVO = (RtnListVO)hibernateTemplate.get(RtnListVO.class, ret_id);
		hibernateTemplate.delete(rtnListVO);
	}

	@Override
	public RtnListVO findByPrimaryKey(String ret_id) {
		RtnListVO rtnListVO = (RtnListVO)hibernateTemplate.get(RtnListVO.class, ret_id);
		return rtnListVO;
	}
	
	
	private static final String FIND_DATE ="FROM RtnListVO where ret_date = ?";
	@Override
	public List<RtnListVO> findByDate(Date ret_date) {
		List<RtnListVO> list = null;
		list = (List<RtnListVO>) hibernateTemplate.find(FIND_DATE, ret_date);
		return list;
	}

	private static final String FIND_ID = "FROM RtnListVO where ret_id = ?";
	@Override
	public List<RtnListVO> getfindById(String ret_id) {
		List<RtnListVO> list = null;
		list = (List<RtnListVO>) hibernateTemplate.find(FIND_ID, ret_id);
		return list;
	}

	private static final String FIND_COM = "FROM RtnListVO where com_name = ?";	
	@Override
	public List<RtnListVO> findByCom(String com_name) {
		List<RtnListVO> list = null;
		list = (List<RtnListVO>) hibernateTemplate.find(FIND_COM, com_name);
		return list;
	}

	private static final String GET_ALL_LIST = "FROM RtnListVO order by ret_id";
	@Override
	public List<RtnListVO> getAll() {
		List<RtnListVO> list = null;
		list = (List<RtnListVO>) hibernateTemplate.find(GET_ALL_LIST);
		return list;
	}

	private static final String GET_DETAIL = "FROM RtnDetailVO where ret_id = ?";
	@Override
	public List<RtnDetailVO> findByDetail(String ret_id) {
		List<RtnDetailVO> list = null;
		list = (List<RtnDetailVO>) hibernateTemplate.find(FIND_COM,ret_id);
		return list;
	}

}
