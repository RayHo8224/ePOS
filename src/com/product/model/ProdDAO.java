package com.product.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;


public class ProdDAO implements ProdDAO_interface {

	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }
	
	private static final String GET_ALL_STMT = "from ProdVO order by prod_id";
	

	@Override
	public void insert(ProdVO prodVO) {
			hibernateTemplate.saveOrUpdate(prodVO);
	}

	@Override
	public void update(ProdVO prodVO) {
		
		hibernateTemplate.saveOrUpdate(prodVO);
		}

	@Override
	public void delete(String prod_id) {
		ProdVO prodVO = (ProdVO) hibernateTemplate.get(ProdVO.class, prod_id);
		hibernateTemplate.delete(prodVO);
	}
	@Override
	public ProdVO findByPrimaryKey(String prod_id) {
		ProdVO prodVO = null;
		prodVO = (ProdVO) hibernateTemplate.get(ProdVO.class, prod_id);
		return prodVO;	
		}
	@Override
	public List<ProdVO> getAll() {
		List<ProdVO> list = null;
			list= (List<ProdVO>) hibernateTemplate.find(GET_ALL_STMT);
			
		return list;	
		}


	@Override
	public List<ProdVO> findByName(String prod_name) {
		
		List<ProdVO> list = null;
		list= (List<ProdVO>) hibernateTemplate.find("from ProdVO where prod_name like '%"+prod_name+"%'");
		return list;
		}
	
	@Override
	public List<ProdVO> findByGroup(String prod_group) {
		
		List<ProdVO> list = null;
			list= (List<ProdVO>) hibernateTemplate.find("from ProdVO where prod_group like '%"+prod_group+"%'");
			
		return list;
		}
	@Override
	public List<ProdVO> selectByGroup() {
		
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO =null;
		List<String> temp = null;
		temp = (List<String>) hibernateTemplate.find("select prod_group from ProdVO group by prod_group");
		Iterator<String> iterator =	temp.iterator();
			while(iterator.hasNext()) {
				prodVO = new ProdVO();
				prodVO.setProd_group(iterator.next());
				list.add(prodVO);
			}
		return list;
		}
	
	@Override
	public void update2(Integer prod_stock, String prod_id) {
		// TODO Auto-generated method stub
		hibernateTemplate.bulkUpdate("update ProdVO set prod_stock=? where prod_id=?",new Object[]{prod_stock,prod_id});
		return;
	}
}

