package com.shipments.model;

import java.sql.Date;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.shipments_detail.model.ShipdetailVO;


public class ShipDAO implements ShipDAO_interface {
	
	
	private static final String GET_ALL_STMT = "from ShipVO order by ship_id";

	private static final String GET_ONE_STMT_ByDate = "from ShipVO where ship_date between ? and ?";

	private static final String GET_ONE_STMT_ByOrderId = "from ShipVO where ord_id = ?";

	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }

	@Override
	public void insert(ShipVO shipVO, List<ShipdetailVO> list) {

		hibernateTemplate.save(shipVO);
			
	}			

	@Override
	public void update(ShipVO shipVO) {
	
	}

	@Override
	public void delete(String ship_id) {
		ShipVO shipVO = (ShipVO) hibernateTemplate.get(ShipVO.class, ship_id);
		hibernateTemplate.delete(shipVO);
	}


	@Override
	public ShipVO findByPrimaryKey(String ship_id) {
		ShipVO shipVO = null;

			shipVO = (ShipVO) hibernateTemplate.get(ShipVO.class, ship_id);
		return shipVO;
	}
		

	@Override
	public List<ShipVO> getAll() {
	
		List<ShipVO> list = null;
		list= (List<ShipVO>) hibernateTemplate.find(GET_ALL_STMT);
			
		return list;
	}
		

	@Override
	public List<ShipVO> findByDate(Date dateBegin, Date dateEnd) {
		List<ShipVO> list = null;
		list= (List<ShipVO>) hibernateTemplate.find(GET_ONE_STMT_ByDate,new Object[]{dateBegin,dateEnd});
			
		return list;
	}
	
	@Override
	public List<ShipVO> findByOrderId(String ord_id) {
		List<ShipVO> list = null;
		list = (List<ShipVO>) hibernateTemplate.find(GET_ONE_STMT_ByOrderId,ord_id);
			
		return list;
	}
	
}
