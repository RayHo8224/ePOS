package com.shipments.model;

import java.sql.Date;
import java.util.List;

import com.shipments_detail.model.ShipdetailVO;

public interface ShipDAO_interface {

	public void insert(ShipVO shipVO,List<ShipdetailVO> list);

	public void update(ShipVO shipVO);

	public void delete(String ship_id);

	public ShipVO findByPrimaryKey(String ship_id);

	public List<ShipVO> findByOrderId(String ord_id);
	
	public List<ShipVO> getAll() ;

	public List<ShipVO> findByDate(Date dateBegin,Date dateEnd );
}
