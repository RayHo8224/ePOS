package com.returns.model;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

public class RtnDetailDAO implements RtnDetail_Interface{
	
	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }
    


	@Override
	public void insert(RtnDetailVO rtnDetailVO) {
		hibernateTemplate.save(rtnDetailVO);
		
	}

	private static final String UPDATE_RtnDetail ="UPDATE RtnDetailVO where ret_id = ? and prod_name=?";
	private static final String FindByPrimaryKey ="from RtnDetailVO where ret_id = ? and prod_name=?";

	@Override
	public void update(RtnDetailVO rtnDetailVO) {
		hibernateTemplate.saveOrUpdate(rtnDetailVO);
		System.out.println(rtnDetailVO);
	}

	@Override
	public void delete(String ret_id, String prod_name) {
		hibernateTemplate.bulkUpdate("DELETE RtnDetailVO WHERE ret_id=? AND prod_name=?",new Object[]{ret_id,prod_name});
	}

	@Override
	public RtnDetailVO findByPrimaryKey(String ret_id, String prod_name) {
		RtnDetailVO rtnDetailVO=null;
	
		List<RtnDetailVO> list=(List<RtnDetailVO>) hibernateTemplate.find(FindByPrimaryKey, new Object[]{ret_id,prod_name});
		System.out.println("list-------->"+list);
		for(RtnDetailVO detailUpdate:list)
		{
			rtnDetailVO=detailUpdate;
			System.out.println("detailUpdate--------------------------->"+detailUpdate);
		}
		System.out.println("rtnDetailVO-------->"+rtnDetailVO);
		return rtnDetailVO;
	}
	
	private static final String GET_ID ="FROM RtnDetailVO where ret_id = ?";
	@Override
	public List<RtnDetailVO> findById(String ret_id) {
		List<RtnDetailVO> list = null;
		list = (List<RtnDetailVO>) hibernateTemplate.find(GET_ID,ret_id);
		return list;
	}
	
	private static final String GET_NAME ="FROM RtnDetailVO where prod_name = ?";
	@Override
	public List<RtnDetailVO> findByName(String prod_name) {
		List<RtnDetailVO> list = null;
		list = (List<RtnDetailVO>) hibernateTemplate.find(GET_NAME,prod_name);
		return list;
	}

	private static final String GET_ALL_STMT = "FROM RtnDetailVO order by ret_id";
	@Override
	public List<RtnDetailVO> getAll() {
		List<RtnDetailVO> list = null;
		list = (List<RtnDetailVO>) hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}

}
