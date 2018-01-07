package com.shipments_detail.model;


import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class ShipdetaiSpringlDAO implements ShipdetailDAO_interface {

	private static final String GET_SHIPDETAIL_ByShip_STMT = "from ShipdetailVO where ship_id=?"; 

	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }
	
	 @Override
	public void insert(ShipdetailVO shipdetailVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ShipdetailVO shipdetailVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String ship_id, String prod_id) {
		hibernateTemplate.bulkUpdate("DELETE ShipdetailVO WHERE ship_id=? AND prod_id=?",new Object[]{ship_id,prod_id});	
		
	}

	@Override
	public List<ShipdetailVO> findByShipId(String ship_id) {
		List<ShipdetailVO> list = null;
		list=hibernateTemplate.find(GET_SHIPDETAIL_ByShip_STMT,ship_id);
			
		return list;
	}

	@Override
	public List<ShipdetailVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
